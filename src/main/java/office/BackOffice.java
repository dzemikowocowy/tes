package office;

import office.openbet.model.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import 

import util.Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.*;

/**
 * {@link office.BackOffice} is able to automate different OpenBet back office tasks
 * like events, markets, selections creation using {@link PhantomJSDriver} and
 * without navigating through the different menus.
 *
 * @author israel.lozano@williamhill.com
 */
public class BackOffice {
    private ArrayList<Event> events;
    private static BackOffice instance = null;

    private static final Logger logger =
            Logger.getLogger(BackOffice.class.getName());

    private String user;
    private WebElement currentElement = null;
    private String password;
    public String cuponName;
    public String cuponID;
    private List<WebElement> elementList;
    private WebDriver officeDriver;

    private String url;

    public static BackOffice getInstance() {
        if (instance == null) {
            instance = new BackOffice();
        }
        return instance;
    }


    public void initialize(String officeIP, String user, String password) {
        this.url = officeIP + "/admin";
        this.user = user;
        this.password = password;
        this.officeDriver = getOfficeDriver();

    }

    private WebDriver getOfficeDriver() {
        if (officeDriver == null) {
            System.setProperty("phantomjs.binary.path", "src/test/resources/selenium/windows/phantomjs.exe");
            open(System.getProperty("phantomjs.binary.path"));
            logIn(user, password);
        }
        return officeDriver;
    }

    /**
     * //	 * Initialise the {@link office.BackOffice} instance creating a new {@link PhantomJSDriver}
     *
     * @param PHANTOMJS_EXECUTABLE_PATH
     */
    public void open(String PHANTOMJS_EXECUTABLE_PATH) {
        if (officeDriver == null) {
            DesiredCapabilities dCaps = new DesiredCapabilities();
            ArrayList<String> cliArgsCap = new ArrayList<String>();
            cliArgsCap.add("--web-security=false");
            cliArgsCap.add("--ssl-protocol=any");
            cliArgsCap.add("--ignore-ssl-errors=true");
            cliArgsCap.add("--proxy-type=none");
            dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
            dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOMJS_EXECUTABLE_PATH);
            dCaps.setJavascriptEnabled(true);
            dCaps.setCapability("takesScreenshot", false);
            officeDriver = new PhantomJSDriver(dCaps);
            officeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            officeDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
    }

    /**
     * openCupons
     */
    public String addNewCupon(String competition, String couponName, String order) {
        competition = competition.toUpperCase();
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCatCoupon&Category=" + competition);
        clickButton("Add Coupon");
        officeDriver.findElement(name("CouponDesc")).sendKeys(couponName);
        officeDriver.findElement(name("CouponDisporder")).sendKeys(order);
        clickButton("Add Coupon");
        logger.info("logged in the back office");
        this.cuponName = couponName;
        this.cuponID = officeDriver.findElement(name("CouponId")).getAttribute("value");
        System.out.println(officeDriver.findElement(name("CouponId")).getAttribute("value"));

        return couponName;
    }


    public String getCupponId() {
        return cuponID;
    }

    public String getCuponName() {
        return cuponName;
    }

    public void deleteCupon() {
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCatCoupon&Category=FOOTBALL");
        officeDriver.findElement(linkText(cuponName)).click();
        clickButton("Delete Coupon");
        logger.info("delet cupon " + cuponName);
        cuponName = null;
    }


    public ArrayList<Event> cleanFuryData() {
        events = new ArrayList<Event>();
        getOfficeDriver().get(url + "?action=ADMIN::EV_SEL::GoEvSel");
        officeDriver.findElement(By.id("ClassId"));
        getOfficeDriver().findElement(id("ClassId")).findElement(By.cssSelector("[value=\'46']")).click();
        getOfficeDriver().findElement(name("TypeId")).findElement(By.cssSelector("[value=\'338']")).click();
        getOfficeDriver().findElement(id("date_range")).findElement(xpath(String.format("option[. = \"%s\"]", "<--Please Specify-->"))).click();
        getOfficeDriver().findElement(id("date_lo")).sendKeys("2016-07-07");
        getOfficeDriver().findElement(id("date_hi")).sendKeys("2016-07-08");
        clickButton("Show Events");
        this.elementList = getOfficeDriver().findElements(By.className("active"));
        for(int i=0;i<elementList.size();i++){
            if (elementList.get(i).findElement(By.tagName("a")).getText().contains("Fury")){
                // String eventID= elementList.get(i).findElement(By.tagName("a")).getAttribute("href").replace("javascript:go_ev('","").replace("');","");
                String eventID=StringUtils.substringBetween(elementList.get(i).findElement(By.tagName("a")).getAttribute("href"),"('","')");
                Event event = new Event();
                event.setPdsId(String.valueOf(eventID));
                events.add(event);
            }
        }
        return  events;

    }




    public void setMarketForCupon(String eventName, String marketID) {
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCatCoupon&Category=FOOTBALL");
        officeDriver.findElement(linkText(cuponName)).click();
        officeDriver.findElement(tagName("tbody"));
        officeDriver.findElement(xpath(String.format("//option[@value = \"%s\"]", marketID))).click();
        clickButton("Modify Coupon Markets");
        clickButton("Modify Coupon");
    }


    /**
     * Logs the user with the current user/password in the OpenBet back office
     *
     * @param user
     * @param password
     */
    public void logIn(String user, String password) {
        if (officeDriver.manage().getCookieNamed("OB_REQ") == null) {
            officeDriver.get(url);
            officeDriver.findElement(name("username")).sendKeys(user);
            officeDriver.findElement(name("password")).sendKeys(password);
            officeDriver.findElement(xpath("//input[@value = 'Login']")).click();
            logger.info("logged in the back office");
        }
    }

    /**
     * Adds an event to the office.
     *
     * @param event The event to add.
     * @return a {@link Event} with all attributes set.
     */
    public Event addEvent(Event event) {
        getOfficeDriver().get(url + "?action=ADMIN::EV_SEL::GoEvSel");

        // select sport and competition
        getOfficeDriver()
                .findElement(id("ClassId"))
                .findElement(xpath(String.format("option[. = \"%s\"]",
                        StringUtils.capitalize(event.sport)))).click();
        if (!event.competition.isEmpty())
            getOfficeDriver().findElement(xpath(String.format("//select/option[. = \"%s\"]", event.competition))).click();
        //else
        //getOfficeDriver().findElement(name("TypeId")).findElements(tagName("option")).get(event.competitionIndex).click();

        clickButton("Add Event");

        // Add event
        getOfficeDriver().findElement(name("EvDesc")).sendKeys(event.name);
        getOfficeDriver().findElement(name("EvStartTime")).sendKeys(event.startTime);
        getOfficeDriver().findElement(name("EvSort")).findElement(xpath(String.format("option[. = \"%s\"]", event.sort))).click();
        getOfficeDriver().findElement(name("EvStatus")).findElement(xpath(String.format("option[. = \"%s\"]", event.status))).click();
        getOfficeDriver().findElement(name("EvDisplayed")).findElement(xpath(String.format("option[. = \"%s\"]", event.displayed))).click();
        getOfficeDriver().findElement(name("EvIsOff")).findElement(xpath(String.format("option[. = \"%s\"]", event.offFlag))).click();
        clickButton("Add Event");
        event.id = Integer.valueOf(getOfficeDriver().findElement(xpath("/html/body/table/tbody/tr[2]/td[2]")).getText());

        logger.info(String.format("event with id:%s added", event.id));
        return event;
    }

    /**
     * Get an event from the back office by id.
     *
     * @param eventId
     * @return an {@link Event} with all its attributes.
     */
    public Event getEventById(Integer eventId) {
        checkEventExists(eventId);

        Event event = new Event(eventId);
        event.name = getOfficeDriver().findElement(name("EvDesc")).getAttribute("value"); // getText() returns ""
        event.status = new Select(getOfficeDriver().findElement(xpath("//select[@name = 'EvStatus']"))).getFirstSelectedOption().getText();
        event.displayed = new Select(getOfficeDriver().findElement(xpath("//select[@name = 'EvDisplayed']"))).getFirstSelectedOption().getText();
        event.sort = new Select(getOfficeDriver().findElement(xpath("//select[@name = 'EvSort']"))).getFirstSelectedOption().getText();
        event.offFlag = new Select(getOfficeDriver().findElement(name("EvIsOff"))).getFirstSelectedOption().getText();
        event.startTime = getOfficeDriver().findElement(name("EvStartTime")).getAttribute("value");
        String[] sportAndCompetition = getOfficeDriver().findElement(xpath("/html/body/table/tbody/tr[1]/th")).getText().split(",");
        event.sport = sportAndCompetition[0].replace("|", "").trim();
        event.competition = sportAndCompetition[1].replace("|", "").trim();
        return event;
    }

    /**
     * Updates the attributes for the given {@link Event}.
     *
     * @param event
     * @return an {@link Event} with the updated attributes.
     */
    public Event updateEvent(Event event) {
        checkEventExists(event.id);

        getOfficeDriver().findElement(name("EvDesc")).clear();
        getOfficeDriver().findElement(name("EvDesc")).sendKeys(event.name);
        getOfficeDriver().findElement(name("EvStatus")).findElement(xpath(String.format("option[. = \"%s\"]", StringUtils.capitalize(event.status)))).click();
        getOfficeDriver().findElement(name("EvDisplayed")).findElement(xpath(String.format("option[. = \"%s\"]", StringUtils.capitalize(event.displayed)))).click();
        getOfficeDriver().findElement(name("EvSort")).findElement(xpath(String.format("option[. = \"%s\"]", event.sort))).click();
        getOfficeDriver().findElement(name("EvIsOff")).findElement(xpath(String.format("option[. = \"%s\"]", StringUtils.capitalize(event.offFlag)))).click();
        getOfficeDriver().findElement(name("EvStartTime")).clear();
        getOfficeDriver().findElement(name("EvStartTime")).sendKeys(event.startTime);
        String[] sportAndCompetition = getOfficeDriver().findElement(xpath("/html/body/table/tbody/tr[1]/th")).getText().split(",");
        event.sport = sportAndCompetition[0].replace("|", "").trim();
        event.competition = sportAndCompetition[1].replace("|", "").trim();
        clickButton("Update Event");

        logger.info(String.format("event with id:%s updated", event.id));
        return event;
    }

    /**
     * Updates the attributes for the given {@link office.openbet.model.Market}.
     *
     * @param market
     * @return a {@link office.openbet.model.Market} with the updated attributes.
     */
    public Market updateMarket(Market market) {
        checkMarketExists(market.getId());

        getOfficeDriver().findElement(name("MktStatus")).findElement(xpath(String.format("option[normalize-space(.) = \"%s\"]", market.status))).click();
        getOfficeDriver().findElement(name("MktDisplayed")).findElement(xpath(String.format("option[. = \"%s\"]", market.displayed))).click();
        getOfficeDriver().findElement(name("MktBetInRun")).findElement(xpath(String.format("option[. = \"%s\"]", market.bir))).click();
        getOfficeDriver().findElement(name("MktBlurb")).clear();
        getOfficeDriver().findElement(name("MktBlurb")).sendKeys(market.blurb);

        // Control alert message before click
        JavascriptExecutor executor = (JavascriptExecutor) getOfficeDriver();
        executor.executeScript("window.confirm = function(){return true;}");
        clickButton("Modify Market");

        logger.info(String.format("market with id:%s updated", market.id));
        return market;
    }

    /**
     * Sets a market blurb to the market with the current marketId
     *
     * @param marketId
     * @param marketBlurb
     */
    public void setMarketBlurb(Integer marketId, String marketBlurb) {
        checkMarketExists(marketId);
        getOfficeDriver().findElement(name("MktBlurb")).clear();
        getOfficeDriver().findElement(name("MktBlurb")).sendKeys(marketBlurb);

        // Control alert message before click
        JavascriptExecutor executor = (JavascriptExecutor) getOfficeDriver();
        executor.executeScript("window.confirm = function(){return true;}");
        clickButton("Modify Market");

        logger.info(String.format("market blurb set to market with id:%s", marketId));
    }

    /**
     * Sets a market each/way to the market with the current marketId
     *
     * @param marketId
     * @param eachWayTipe
     */
    public void setMarketEachWay(Integer marketId, String eachWayTipe) {
        checkMarketExists(marketId);

        getOfficeDriver().findElement(name("MktEWAvail")).findElement(xpath("option[. = 'Yes']")).click();
        if (eachWayTipe.equals("Win Only")) {
            getOfficeDriver().findElement(name("MktEWPlaces")).sendKeys("1");
            getOfficeDriver().findElement(name("MktEWFacNum")).sendKeys("1");
            getOfficeDriver().findElement(name("MktEWFacDen")).sendKeys("1");
        } else if (eachWayTipe.equals("With Terms")) {
            getOfficeDriver().findElement(name("MktEWPlaces")).sendKeys("2");
            getOfficeDriver().findElement(name("MktEWFacNum")).sendKeys("1");
            getOfficeDriver().findElement(name("MktEWFacDen")).sendKeys("2");
        }

        // Control alert message before click
        JavascriptExecutor executor = (JavascriptExecutor) getOfficeDriver();
        executor.executeScript("window.confirm = function(){return true;}");
        clickButton("Modify Market");

        logger.info(String.format("market each/way set to market with id:%s", marketId));
    }

    /**
     * Adds a market to the event with the current eventId.
     *
     * @param eventId The parent event reference.
     * @param market  The market to add.
     * @return a {@link office.openbet.model.Market} with all attributes set.
     */
    public Market addMarket(Integer eventId, Market market) {
        checkEventExists(eventId);

        // Add market
        getOfficeDriver().findElement(name("MktGrpId")).findElement(xpath(String.format("option[. = \"%s\"]", market.name))).click();
        clickButton("Add Event Market");
        getOfficeDriver().findElement(name("MktStatus")).findElement(xpath(String.format("option[normalize-space(.) = \"%s\"]", market.status))).click();
        getOfficeDriver().findElement(name("MktDisplayed")).findElement(xpath(String.format("option[. = \"%s\"]", market.displayed))).click();
        getOfficeDriver().findElement(name("MktBetInRun")).findElement(xpath(String.format("option[. = \"%s\"]", market.bir))).click();
        getOfficeDriver().findElement(name("MktDblRes")).findElement(xpath("option[. = 'No']")).click();
        getOfficeDriver().findElement(name("MktBlurb")).sendKeys(market.blurb);
        clickButton("Add Market");
        market.id = Integer.valueOf(getOfficeDriver().findElement(xpath("/html/body/form/table[1]/tbody/tr[3]/td[2]")).getText());
        market.name = getOfficeDriver().findElement(name("mkt_name")).getAttribute("value").replace("|", "");

        logger.info(String.format("market with id:%s and name:%s added", market.id, market.name));
        return market;
    }

    /**
     * Adds the pre-match primary market to the event with the current eventId.
     *
     * @param eventId The parent event reference.
     * @return a {@link office.openbet.model.Market} with all attributes set.
     */
    public Market addPreMatchMarket(Integer eventId) {
        return addPreMatchMarket(eventId, 0);
    }

    /**
     * Adds the pre-match market with the current marketIndex to the event with the current eventId.
     *
     * @param eventId     The parent event reference.
     * @param marketIndex The market index to select from all pre-match available markets.
     * @return a {@link office.openbet.model.Market} with all attributes set.
     */
    public Market addPreMatchMarket(Integer eventId, Integer marketIndex) {
        checkEventExists(eventId);
        Market market = new Market(getPreMatchMarketNames(eventId).get(marketIndex));
        market.setBir(BackOfficeConstants.MARKET_BIR_NO);

        logger.info("adding pre-match market");
        return addMarket(eventId, market);
    }

    /**
     * Adds the in-play primary market to the event with the current eventId.
     *
     * @param eventId The parent event reference.
     * @return a {@link office.openbet.model.Market} with all attributes set.
     */
    public Market addInPlayMarket(Integer eventId) {
        return addInPlayMarket(eventId, 0);
    }

    /**
     * Adds the in-play market with the current marketIndex to the event with the current eventId.
     *
     * @param eventId     The parent event reference.
     * @param marketIndex The market index to select from all in-play available markets.
     * @return a {@link office.openbet.model.Market} with all attributes set.
     */
    public Market addInPlayMarket(Integer eventId, Integer marketIndex) {
        checkEventExists(eventId);
        Market market = new Market(getInPlayMarketNames(eventId).get(marketIndex));
        market.setBir(BackOfficeConstants.MARKET_BIR_YES);

        logger.info("adding in-play market");
        return addMarket(eventId, market);
    }

    /**
     * Adds a selection to the market with the current marketId and with a default price of 1/5.
     *
     * @param marketId The parent market reference.
     * @return a {@link office.openbet.model.Selection} with all attributes set.
     */
    public Selection addSelection(Integer marketId) {
        return addSelection(marketId, new Selection());
    }

    /**
     * Adds a selection to the market with the current marketId.
     *
     * @param marketId  The parent market reference.
     * @param selection The selection to add.
     * @return a {@link office.openbet.model.Selection} with all attributes set.
     */
    public Selection addSelection(Integer marketId, Selection selection) {
        checkMarketExists(marketId);

        // Add selection
        clickButton("Add Market Selection");
        getOfficeDriver().findElement(name("OcDesc")).sendKeys(selection.name);
        if (!selection.price.isEmpty())
            getOfficeDriver().findElement(name("OcLP")).sendKeys(selection.price);
        getOfficeDriver().findElement(name("OcStatus")).findElement(xpath(String.format("option[. = \"%s\"]", StringUtils.capitalize(selection.status)))).click();
        getOfficeDriver().findElement(name("OcDisplayed")).findElement(xpath(String.format("option[. = \"%s\"]", StringUtils.capitalize(selection.displayed)))).click();
        if (!selection.runner_order.isEmpty())
            getOfficeDriver().findElement(name("OcRunnerNum")).sendKeys(selection.runner_order);
        if (selection.getName().contains("1st Favourite"))
            getOfficeDriver().findElement(xpath("//select[@name = 'OcFlag']/option[. = 'Unnamed favourite']")).click();
        if (selection.getName().contains("2nd Favourite"))
            getOfficeDriver().findElement(xpath("//select[@name = 'OcFlag']/option[. = 'Unnamed 2nd favourite']")).click();
        clickButton("Add Selection");
        getOfficeDriver().findElement(linkText(selection.name)).click();
        selection.id = Integer.valueOf(getOfficeDriver().findElement(xpath("/html/body/form/table[1]/tbody/tr[2]/td[2]")).getText());

        logger.info(String.format("selection with id:%s added", selection.id));
        return selection;
    }

    /**
     * Updates the attributes for the given {@link office.openbet.model.Selection}.
     *
     * @param selection
     * @return an {@link office.openbet.model.Selection} with the updated attributes.
     */
    public Selection updateSelection(Selection selection) {
        checkSelectionExists(selection.id);

        getOfficeDriver().findElement(name("OcDesc")).clear();
        getOfficeDriver().findElement(name("OcDesc")).sendKeys(selection.name);
        getOfficeDriver().findElement(xpath(String.format("//select[@name = 'OcStatus']/option[. = \"%s\"]", selection.status))).click();
        getOfficeDriver().findElement(xpath(String.format("//select[@name = 'OcDisplayed']/option[. = \"%s\"]", selection.displayed))).click();
        getOfficeDriver().findElement(name("OcLP")).clear();
        getOfficeDriver().findElement(name("OcLP")).sendKeys(selection.price);


        // Control alert message before click
        JavascriptExecutor executor = (JavascriptExecutor) getOfficeDriver();
        executor.executeScript("window.confirm = function(){return true;}");
        clickButton("Modify Selection");

        logger.info(String.format("selection with id:%s updated", selection.id));
        return selection;
    }


    private void clickButton(String label) {
        getOfficeDriver().findElement(xpath(String.format("//input[@type = 'button' and @value = \"%s\"]", label))).click();

    }

    private void checkEventExists(Integer eventId) {
        getOfficeDriver().get(url + "?action=ADMIN::EVENT::GoEv&EvId=" + eventId);
        assertTrue("No event found with id: " + eventId, !getOfficeDriver()
                .findElements(xpath("//tbody/tr/td[. = 'Event Identifier']"))
                .isEmpty());
    }

    private void checkMarketExists(Integer marketId) {
        getOfficeDriver().get(url + "?action=ADMIN::MARKET::GoMkt&MktId=" + marketId);
        assertTrue("No market found with id: " + marketId, !getOfficeDriver()
                .findElements(xpath("//tbody/tr/td[. = 'Market Identifier']"))
                .isEmpty());
    }

    private void checkSelectionExists(Integer selectionId) {
        getOfficeDriver().get(url + "?action=ADMIN::SELN::GoOc&OcId=" + selectionId);
        assertTrue("No selection found with id: " + selectionId, !getOfficeDriver()
                .findElements(xpath("//tbody/tr/td[. = 'Selection Identifier']"))
                .isEmpty());
    }

    public void quit() {
        if (officeDriver != null) {
            officeDriver.quit();
            officeDriver = null;
        }
    }

    public void cleanCupons() {
        if (cuponName != null && officeDriver != null) {
            deleteCupon();
        }
    }

    public void addEventCommentary(Integer eventId, Commentary commentary) {
        checkEventExists(eventId);
        clickButton("Add Commentary");

        getOfficeDriver()
                .findElement(name("CommentType"))
                .findElement(
                        xpath(String.format("option[. = \"%s\"]",
                                commentary.getCommentType()))).click();
        getOfficeDriver().findElement(name("CommentCol1")).sendKeys(commentary.getType1Score());
        getOfficeDriver().findElement(name("CommentCol2")).sendKeys(commentary.getType2Score());
        getOfficeDriver().findElement(name("CommentCol3")).sendKeys(commentary.getFreeText());

        clickButton("Add Commentary");
        logger.info(String.format("event commentary addded to event with id:%s", eventId));
    }

    public void setEventCountry(Integer eventId, String country) {
        checkEventExists(eventId);

        getOfficeDriver()
                .findElement(name("EvCountry")).sendKeys(country);
        clickButton("Update Event");

        logger.info(String.format("event  addded to event with id:%s", eventId));


    }

    //sprawdz
    public void setSelectionToMiddle(Integer selectionId) {

        getOfficeDriver().get(url + "?action=ADMIN::SELN::GoOc&OcId=" + selectionId);

        getOfficeDriver().findElement(By.xpath("//select[@name = 'OcFlag']/option[. = 'middle']")).click();
        clickButton("Modify Selection");
        logger.info(String.format("selection with id:%s has been  set as Middle", selectionId));
    }

    public void voidSelectionResult(Integer selectionId) {
        getOfficeDriver().get(url + "?action=ADMIN::SELN::GoOc&OcId=" + selectionId);

        getOfficeDriver().findElement(By.xpath("//select[@name = 'OcResult']/option[. = 'Void']")).click();
        clickButton("Set Selection Result");
        logger.info(String.format("selection with id:%s has been voided", selectionId));
    }

    public void settleMarket(Integer marketId) {
        getOfficeDriver().get(url + "?action=ADMIN::MARKET::GoMkt&MktId=" + marketId);

        clickButton("Confirm Results");
        clickButton("Settle Market");
        logger.info(String.format("market with id:%s has been settled", marketId));
    }

    public void settleMarket(Market market, String forecastDividend, String tricastDividend) {
        checkMarketExists(market.getId());

        // set the forecast dividend
        clickButton("Edit Dividends");
        getOfficeDriver().findElement(xpath("//select[@name = 'NewDivType']/option[. = 'Forecast']")).click();
        getOfficeDriver().findElement(name("NewDivAmount")).sendKeys(forecastDividend);
        clickButton("Update Dividends");

        // set the tricast dividend
        clickButton("Edit Dividends");
        getOfficeDriver().findElement(xpath("//select[@name = 'NewDivType']/option[. = 'Tricast']")).click();
        new Select(getOfficeDriver().findElement(xpath("//select[@name = 'NewDivSeln3']"))).selectByIndex(1);
        getOfficeDriver().findElement(name("NewDivAmount")).sendKeys(tricastDividend);
        clickButton("Update Dividends");

        Timer.sleep(1, TimeUnit.SECONDS);
        clickButton("Confirm Results");
        Timer.sleep(1, TimeUnit.SECONDS);
        clickButton("Settle Market");
    }

    public boolean waitForEventSettlement(Integer eventId) {
        // PDS can takes up to 5min to update
        int count = 150;
        boolean isEventDeleted = false;
        while (!isEventDeleted && count > 0) {
            try {
                Thread.sleep(2000);
                getOfficeDriver().get(url + "?action=ADMIN::EVENT::GoEv&EvId=" + eventId);
                getOfficeDriver().findElement(xpath("//input[@type = 'button' and @value = 'Re-Settle Event']"));
                isEventDeleted = true;
            } catch (Throwable e) {
                count--;
            }
        }

        return isEventDeleted;
    }

    public ArrayList<String> getPreMatchMarketNames(Integer eventId) {
        checkEventExists(eventId);

        ArrayList<String> preMatchMarketNames = new ArrayList<String>();
        List<WebElement> marketNames = getOfficeDriver().findElement(name("MktGrpId")).findElements(tagName("option"));
        for (WebElement webElement : marketNames)
            if (!webElement.getText().endsWith("Live|"))
                preMatchMarketNames.add(webElement.getText());

        if (!preMatchMarketNames.isEmpty())
            return preMatchMarketNames;
        else {
            Event event = getEventById(eventId);
            throw new NegativeArraySizeException(
                    String.format(
                            "No markets not ending with 'Live|' found in sport '%s' and competition '%s'",
                            event.sport, event.competition));
        }
    }

    public ArrayList<String> getInPlayMarketNames(Integer eventId) {
        checkEventExists(eventId);

        ArrayList<String> inPlayMarketNames = new ArrayList<String>();
        List<WebElement> marketNames = getOfficeDriver().findElement(name("MktGrpId")).findElements(tagName("option"));
        for (WebElement webElement : marketNames)
            if (webElement.getText().endsWith("Live|"))
                inPlayMarketNames.add(webElement.getText());

        if (!inPlayMarketNames.isEmpty())
            return inPlayMarketNames;
        else {
            Event event = getEventById(eventId);
            throw new NegativeArraySizeException(
                    String.format(
                            "No markets ending with 'Live|' found in sport '%s' and competition '%s'",
                            event.sport, event.competition));
        }
    }

    public void resultSelection(Selection selection) {
        checkSelectionExists(selection.id);

        getOfficeDriver().findElement(xpath(String.format("//select[@name = 'OcResult']/option[. = \"%s\"]",
                StringUtils.capitalize(selection.getResult())))).click();
        getOfficeDriver().findElement(name("OcPlace")).sendKeys(selection.getPlace());
        getOfficeDriver().findElement(name("OcSP")).sendKeys(selection.getSP());

        clickButton("Set Selection Result");

        logger.info(String.format("selection with id:%s resulted", selection.id));
    }

    public boolean addStreamToRace(Event event) {
        FirefoxDriver driver = new FirefoxDriver();
        try {
            driver.get(url);
            driver.findElement(name("username")).sendKeys(user);
            driver.findElement(name("password")).sendKeys(password);
            driver.findElement(xpath("//input[@value = 'Login']")).click();
            Timer.sleep(2, TimeUnit.SECONDS);

            // Store the current window handle
            driver.get(url + "?action=ADMIN::ATR::GoMapATRStreams");
            String winHandleBefore = driver.getWindowHandle();

            new Select(driver.findElement(xpath("//select[@name = 'days']"))).selectByIndex(1);
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Show Events']")).click();

            // click first Map Event
            Timer.sleep(1, TimeUnit.SECONDS);
            driver.findElement(xpath("//*[.='Map Event']")).click();

            // Switch to new window opened
            for (String winHandle : driver.getWindowHandles())
                driver.switchTo().window(winHandle);

            driver.findElement(By.linkText("HORSE_RACING")).click();
            driver.findElement(By.linkText("|Horse Racing - Live|")).click();
            driver.findElement(By.linkText(event.getCompetition())).click();
            driver.findElement(xpath(String.format("//input[@value=%s]/../input[@type='radio']", event.getId()))).click();
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Select']")).click();

            driver.switchTo().window(winHandleBefore);
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Set Mappings']")).click();

            String mapResponse = driver.findElement(By.cssSelector(".infoyes")).getText();
            return mapResponse.contains("1 Events have been mapped.");

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        } finally {
            driver.quit();
        }

    }


    public void navigateToHowMuchDoYouWantToWin() throws InterruptedException {

        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::LOGIN::GoHome");

        officeDriver.switchTo().frame(officeDriver.findElement(By.name("TopBar")));

        officeDriver.findElement(By.xpath("//span[contains(.,'Betting Setup')]")).click();

        Thread.sleep(4000);

        officeDriver.findElement(By.xpath("//b[contains(.,'How Much Do You Want To Win')]")).click();
    }

    private void switchToMainAreaFrame() {
        officeDriver.switchTo().defaultContent();
        officeDriver.switchTo().frame(officeDriver.findElement(By.name("MainArea")));
    }

    /**
     * On this method user will be able to set a Top Bet by given number on the list of desired bets as he/she wants.
     *
     * @param listNumber
     * @param setBetNumber
     */
    public void setTopBetByNumberOnBackOffice(String listNumber, String setBetNumber) throws InterruptedException {

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        switchToMainAreaFrame();

        officeDriver.findElement(By.xpath("(//td[contains(.,'" + listNumber + ".')]/..//input)[1]")).clear();
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + listNumber + ".')]/..//input)[1]")).sendKeys(setBetNumber);
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + listNumber + ".')]/..//input)[2]")).click();

    }

    public void setTopBetByEventName(String eventName, String setBetNumber) throws InterruptedException {

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        switchToMainAreaFrame();

        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[1]")).clear();
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[1]")).sendKeys(setBetNumber);
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[2]")).click();

    }

    /**
     * This method will modify the Value of the field "Man. Inc." on the Backend to the desired one,
     * given the eventName.
     * <p>
     * Note: Please be aware that the eventName should be on this format: e.g. "|Mallorca FC| vs |Real Balompie Alicante|".
     *
     * @param eventName
     * @param setBetNumber
     */
    public void setTopBetManualWeightFromGivenEvent(String eventName, String setBetNumber) throws InterruptedException {

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        switchToMainAreaFrame();

        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[1]")).clear();
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[1]")).sendKeys(setBetNumber);
        officeDriver.findElement(By.xpath("(//td[contains(.,'" + eventName + "')]/..//input)[2]")).click();

    }


    public void updateAlternateSelectionTopBet(String typeOfSport, String sportCategory, String sportSubCategory,
                                               String match, String market, String selection,
                                               String manualWeight) throws InterruptedException {

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        // Here we switch to the Main Area Frame
        switchToMainAreaFrame();

        // Here we click on the Update Alternate Selection
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("(//input[@value='Update Alternate Selection'])[1]")).click();

        // Here we select the type of Sport
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("//a[contains(.,'" + typeOfSport + "')]")).click();

        // Here we select the Category
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("//a[contains(.,'" + sportCategory + "')]")).click();

        // Here we select the Sub-Category
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("//a[contains(.,'" + sportSubCategory + "')]")).click();

        // Here we select the Match
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("//a[contains(.,'" + match + "')]")).click();

        // Here we select the Market
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("//a[contains(.,'" + market + "')]")).click();

        // Here we set the values that we want on the selection that we want.

        // Click on the selection
        switchToMainAreaFrame();
        officeDriver.findElement(By.xpath("(//div[contains(.,'" + selection + "')]/input)[1]")).click();

        // Set the value of the Manual Weighting
        officeDriver.findElement(By.cssSelector("#weight")).clear();
        officeDriver.findElement(By.cssSelector("#weight")).sendKeys(manualWeight);

        // Then we click the Update button
        officeDriver.findElement(By.cssSelector("input[value='Update']")).click();

    }


    public void setLastTopBetToBeTheFirstOne(String nameOfEvent) throws InterruptedException {

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        officeDriver.switchTo().defaultContent();
        officeDriver.switchTo().frame(officeDriver.findElement(By.name("MainArea")));

        // Here we get the Total of the highest one
        int firstTopBetValue = Integer.parseInt(officeDriver.findElement(By.xpath("(//td[contains(.,'1.')]/..//td)[10]")).getText());
        int i = 0;
        do {
            i++;
            if (officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//td)[5]")).getText().replace("|", "").equals(nameOfEvent)) {

                // Here we get the current Act. Spots of the current event
                int currentActSpotsOfCurrentEvent = Integer.parseInt(officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//td)[8]")).getText());

                // We rest it against of the Total of the highest
                int difference = firstTopBetValue - currentActSpotsOfCurrentEvent;

                // Here we set it the difference plus 10 more in order to make it the highest Top Bet
                officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[1]")).clear();
                officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[1]")).sendKeys(String.valueOf(difference + 10));
                officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[2]")).click();
            }

        } while (i < 20);
    }

    public void removeManIncFromAllBets() {

        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::LOGIN::GoHome");

        officeDriver.switchTo().frame(officeDriver.findElement(By.name("TopBar")));

        officeDriver.findElement(By.xpath("//span[contains(.,'Betting Setup')]")).click();
        officeDriver.findElement(By.xpath("//b[contains(.,'How Much Do You Want To Win')]")).click();

        officeDriver.switchTo().defaultContent();
        officeDriver.switchTo().frame(officeDriver.findElement(By.name("MainArea")));

        for (int i = 1; i <= 20; i++) {

            officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[1]")).clear();
            officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[1]")).sendKeys("0");
            officeDriver.findElement(By.xpath("(//td[contains(.,'" + i + ".')]/..//input)[2]")).click();
        }
    }

    public HashMap<Integer, String> getTopBetsMapByEventName() throws InterruptedException {

        // This HashMap will contain at the First Integer value the position of the Top Bet and in the String value
        // The Event Name of it.
        HashMap<Integer, String> result = new HashMap<Integer, String>();

        // Here is the navigation just to get to the Ho Much Do You Want To Win
        navigateToHowMuchDoYouWantToWin();

        // Here we switch to the Main Area Frame
        switchToMainAreaFrame();

        // We get the Size of the list of how many Top Bets there are - In order to make it more robust if we have the case that there are less than 20 Top Bets.
        int listSize = officeDriver.findElements(By.xpath("//th[contains(.,'Top  Spots')]/../..//td/input[contains(@type,'button')]")).size();

        // Here we populate the Map previously created with the Event Names of the Top Bets.
        for(int i=1; i<=listSize; i++){
            result.put(i,officeDriver.findElement(By.xpath("//td[text()[contains(.,'" + i + ".')]]/..//td[5]")).getText());
        }

        // And finally here we return the Map.
        return result;
    }

    public String eventStartingTime(String eventId) throws ParseException {

        getOfficeDriver().get(url + "?action=ADMIN::COUPON::GoCouponSelect");
        getOfficeDriver().get(url + "?action=ADMIN::LOGIN::GoHome");

        //Here we go to the Event Page on the BackOffice
        getOfficeDriver().get(url + "/admin?action=ADMIN::EVENT::GoEv&EvId=" + eventId);

        // Here we get the date
        String originalString = officeDriver.findElement(By.cssSelector("input[name='EvStartTime']")).getAttribute("value").replace("-", " ");

        // Here we transform the String to Date type so we can work with it as a Date
        Date date = new SimpleDateFormat("yyyy MM dd HH:mm:ss").parse(originalString);

        // Here we modify the Hour, since in BackOffice and Frontend there is 1 hour of difference
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, +1);

        // From Callendar back to Date once we modified the Hour
        date = cal.getTime();

        // Here we format it to the date format that we have in the Frontend
        final String OLD_FORMAT = "yyyy MM dd HH:mm:ss";
        final String NEW_FORMAT = "dd MMM HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        sdf.applyPattern(NEW_FORMAT);

        return  sdf.format(date);
    }


}
