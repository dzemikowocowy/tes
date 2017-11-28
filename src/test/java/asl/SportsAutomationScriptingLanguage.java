package asl;

import com.williamhill.whgtf.asl.AutomationScriptingLanguage;
import com.williamhill.whgtf.webdriver.BrowserTypes;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import model.openbet.SportId;
import office.BackOffice;
import office.BackOfficeOxifeed;
import office.openbet.model.Event;
import office.openbet.model.FootballServiceHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import stepdefs.shared.ScenarioContext;
import util.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mkoza on 21/04/2015.
 */
public class SportsAutomationScriptingLanguage extends AutomationScriptingLanguage {

    protected Logger logger = LogManager.getRootLogger();


    protected final String sbUrl = getProperty("sportsbookUrl");

    protected final String pdsUrl = getProperty("pdsRestEndpoint");
    protected final String footballServiceUrl = getProperty("footballServiceUrl");
    protected String openbetUsername = getProperty("openbetUsername");
    protected String openbetPassword = getProperty("openbetPassword");
    protected String oxifeedUrl = getProperty("oxifeedUrl");
    protected String openBetServer = getProperty("openBet.server");
    protected String pdsRestEndpoint = getProperty("pdsRestEndpoint");
    protected boolean baseline = Boolean.valueOf(System.getProperty("baseline", "false"));
    protected int diffSizeTrigger = Integer.valueOf(System.getProperty("diffSizeTrigger", "20"));
    protected final String sportsbookUsername = getProperty("sportsbookUsername");
    protected final String sportsbookPassword = getProperty("sportsbookPassword");

    protected String browser = System.getProperty("browser", "");
    protected String device = System.getProperty("device", "");
    protected String env = System.getProperty("env");
    protected String environment = System.getProperty("envConfig", "dev");
    protected String sport = System.getProperty("sport", "horse-racing");
    protected String footballQueueName = getProperty("footballQueueName");
    protected String genericQueueName = getProperty("genericQueueName");
    protected String tennisQueueName = getProperty("tennisQueueName");
    protected String jmsConnectionString = getProperty("jmsConnectionString");


    //initializations
    protected PDSHelper getPDSHelper() {
        PDSHelper.getInstance().initialize(pdsUrl);
        return PDSHelper.getInstance();
    }

    protected SquizHelper getSquizHelper() {
        SquizHelper.getInstance().initialize(environment, getSport());
        return SquizHelper.getInstance();
    }

    protected SquizVirtualHelper getVirtualSquizHelper() {
        SquizVirtualHelper.getInstance().initialize(env);
        return SquizVirtualHelper.getInstance();
    }

    protected ScenarioContext getScenarioContext() {
        return ScenarioContext.getInstance();
    }

    protected String getSport() {
        if (!sport.isEmpty())
            return sport.toLowerCase();
        else
            throw new IllegalArgumentException(
                    "No parameter 'sport' defined to run the tests. Add a maven parameter '-Dsport=sportName' "
                            + "or a meta parameter '@sport sportName' in the story file.");
    }

    protected void setSport(String sport) {
        this.sport = SportId.getSport(sport);
    }

    protected BackOfficeOxifeed getOxifeedHelper() {
        BackOfficeOxifeed.getInstance().initialize(oxifeedUrl, openbetUsername, openbetPassword);
        return BackOfficeOxifeed.getInstance();
    }

    protected FootballServiceHelper getOxifootballHelper() {
        FootballServiceHelper.getInstance().initialize(footballServiceUrl);
        return FootballServiceHelper.getInstance();
    }

    protected BackOffice getBackOfficeHelper() {
        BackOffice.getInstance().initialize(openBetServer, openbetUsername, openbetPassword);
        return BackOffice.getInstance();
    }

    protected void checkEventsReadyForTesting(ArrayList<Event> eventList) {
        PDSCatalogChecker.getInstance().initialize(pdsRestEndpoint);
        // wait for the events to be present in the PDS catalog
        if (!PDSCatalogChecker.getInstance().areEventsInCatalog(eventList, 10))
            throw new RuntimeException("No assertions possible. Some of the events aren't present in the PDS catalog.");

        // wait for the events to be present in RIAK
        //if(environment.equals("pp1") && !getRIAKHelper().areEventsIndexed(eventList, 60*10))
        //	throw new RuntimeException("No assertions possible. Some of the events aren't present in RIAK.");

        // give some additional seconds for the frontend.
        Timer.sleep(2, TimeUnit.SECONDS);
    }

    protected void checkEventReadyForStreaming(ArrayList<Event> eventList) {
        if (System.getProperty("env", "dev").equals("pp1")) {
            // trigger PP1 dsm to check for new mapped streams
            HttpHelper.getInstance().postRequest("http://10.212.147.67:9191/vsm/tick");
            Timer.sleep(200, TimeUnit.MILLISECONDS);
            HttpHelper.getInstance().postRequest("http://10.212.147.68:9191/vsm/tick");
        }

        PDSCatalogChecker.getInstance().initialize(pdsRestEndpoint);
        // wait for the events to be present in the PDS catalog
        for (Event event : eventList)
            if (!PDSCatalogChecker.getInstance().isElementInCatalog(event.getId(), "\"streamingAvailable\":true", 6, 10))
                throw new RuntimeException(String.format(
                        "The stream was set for the event OB_EV%s but not fetched up by PDS after 60sec", event.getId()));


        // give some additional seconds for the frontend.
        Timer.sleep(2, TimeUnit.SECONDS);
    }

    protected void checkEventAlreadySettled(Event event) {
        PDSCatalogChecker.getInstance().initialize(pdsRestEndpoint);
        // wait for the events to be present in the PDS catalog

        if (!PDSCatalogChecker.getInstance().isElementInCatalog(event.getId(), "\"settled\":true", 10, 10))
            throw new RuntimeException(String.format(
                    "The event OB_EV%s was not settled by PDS after 60sec", event.getId()));

        // give some additional seconds for the frontend.
        Timer.sleep(2, TimeUnit.SECONDS);
    }


    public boolean navigateToPage(String url) {
        boolean navigate;
        navigate = super.navigateToPage(url);
        if (device.isEmpty()) {
            manageBrowser().setBrowserDimensions(1600, 900);
        }
        return navigate;

    }

    public boolean navigateToPage1(String url) {

        // the cache timeout has been set to 50ms
        // workaround to run tests on a custom profile


        if (MultiThreadedDriverFactory.getDriversMap().size() == 0) {
            FirefoxProfile profile = new FirefoxProfile();
            File modifyHeaders = new File("src/main/resources/selenium/modify_headers.xpi");
            try {
                profile.addExtension(modifyHeaders);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profile.setPreference("modifyheaders.headers.count", 1);
            profile.setPreference("modifyheaders.headers.action0", "Add");
            profile.setPreference("modifyheaders.headers.name0", "X-WH-SRV-FROM");
            profile.setPreference("modifyheaders.headers.value0", "SBAPI");
            profile.setPreference("modifyheaders.headers.enabled0", true);
            profile.setPreference("modifyheaders.config.active", true);
            profile.setPreference("modifyheaders.config.alwaysOn", true);

            if (Boolean.valueOf(System.getProperty("grid", "false")))
                createBrowser().setBrowserType(BrowserTypes.FIREFOX)
                        .setFirefoxProfile(profile).setGridExecution();
            else
                createBrowser().setBrowserType(BrowserTypes.FIREFOX)
                        .setFirefoxProfile(profile);

            super.navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
            // fixed browser size for image validation
            manageBrowser().setBrowserDimensions(1600, 900);
        }

        // retry navigation up to 3 times if we get a 'Service Unavailable' message
        int retry = 6;
        do {
            super.navigateToPage(url);
            retry--;
        } while (hasPartialPageTitle("Service Unavailable") && retry >= 0);

        if (retry < 0)
            return false;
        else {
            boolean pageLoaded = true;
            if (!hasPartialURL("sr-admin-set-white-list-cookie"))
                pageLoaded = waitForPresenceOfElementByCss("body.pace-done");
            return pageLoaded;

        }
//        checkRIAKContentUpdated(url, 560);
//
//        if (MultiThreadedDriverFactory.getDriversMap().isEmpty()) {
//            // no browser already instantiated
//            super.navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
//            // cookie url will redirect to the home page
//            //waitSportsbook();
//            // fixed browser size for image validation
//            if(!browser.isEmpty())
//                manageBrowser().setBrowserDimensions(1400, 768);
//        }
//
//        boolean pageLoaded = false;
//        pageLoaded = super.navigateToPage(url);
//        pageLoaded = waitSportsbook();
//        // streaming cookie needed - MOBSTR-251
//        setCookie("trk_uid", "69582PN");
//        return pageLoaded;

    }


    public boolean navigateToMeetingsPage(String sportName) {
        if (sportName.equals("horse-racing") || sportName.equals("greyhounds"))
            return navigateToPage(sbUrl + String.format("/betting/en-gb/%s/meetings", sportName));
        else
            throw new IllegalArgumentException("navigateToMeetingsPageBySportName: valid values are 'horse-racing', 'greyhounds'");
    }


    public boolean navigateToCupponsPage(String sportName) {
        if (sportName.equals("football"))
            return navigateToPage1(sbUrl + String.format("/betting/en-gb/%s/coupons", sportName));
        else
            throw new IllegalArgumentException("navigateToMeetingsPageBySportName: valid values are 'horse-racing', 'greyhounds'");
    }

    public boolean openSportPage(String sportName) {
        if (sportName.equals("football") || sportName.equals("baseball"))
            return navigateToPage(sbUrl + String.format("/betting/en-gb/%s", sportName));
        else
            throw new IllegalArgumentException("navigateTo sportName: valid values are 'football' or 'baseball'");
    }


    public boolean navigateToAntepostPage(String sportName) {
        if (sportName.equals("horse-racing") || sportName.equals("greyhounds"))
            return navigateToPage(sbUrl + String.format("/betting/en-gb/%s/ante-post", sportName));
        else
            throw new IllegalArgumentException("navigateToAntepostPageBySportName: valid values are 'horse-racing', 'greyhounds'");
    }

    public boolean navigateToSpecialsPage(String sportName) {
        if (sportName.equals("horse-racing") || sportName.equals("greyhounds"))
            return navigateToPage(sbUrl + String.format("/betting/en-gb/%s/specials", sportName));
        else
            throw new IllegalArgumentException("navigateToAntepostPageBySportName: valid values are 'horse-racing', 'greyhounds'");
    }

    public boolean navigateToSportPage(String name) {

        if (isSportsbookOnDesktop()) {
            // test running on full desktop size
            navigateToRootElement();
            navigateToElementByClassName("localnavigation__button-showmore");
            click();
        } else {
            // test running on small display
            navigateToRootElement();
            navigateToElementByCSS(".localnavigation__left button:nth-child(0)");
            click();
        }

        navigateToRootElement();
        return navigateToElementByPartialLinkText(name);
    }

    public boolean navigateToTabByClassName(String tabClassName) {

        if (isSportsbookOnDesktop()) {
            // test running on full desktop size
            navigateToRootElement();
            navigateToElementByClassName("sidebar");
            return navigateToElementByClassName(tabClassName);
        } else {
            // test running on small display
            navigateToRootElement();
            navigateToElementByCSS(".localnavigation__left button:nth-child(1)");
            if (!getAttribute("class").contains("expanded"))
                // top menu not expanded
                click();
            navigateToRootElement();
            return navigateToElementByCSS(".localnavigation__button-dropdown.button." + tabClassName);
        }
    }

    public void clickJS() {
        executeScriptOnCurrentElement("arguments[0].click()");
    }

    public boolean isSportsbookOnDesktop() {
        return device.isEmpty();
    }

    public boolean expandMeetingNavigation() {
        boolean allElementsExpanded = false;

        navigateToRootElement();
        navigateToElementByClassName("race-menu");
        navigateToElementByClassName("tabs__navcontainer-today");
        if (!isDisplayed()) {
            navigateToRootElement();
            navigateToElementByClassName("race-menu");
            navigateToElementByClassName("tabs__navcontainer-tomorrow");
        }

        // expand all collapsed elements from the menu
        buildListByCSS(".button-clear:not(.-expanded)");
        for (int i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            allElementsExpanded = click();
            sleep(200);
        }

        return getListSize() <= 0 || allElementsExpanded;
    }

    public boolean collapseMeetingNavigation() {
        boolean allElementsCollapsed = false;

        navigateToRootElement();
        navigateToElementByClassName("race-menu");
        // expand all collapsed elements from the menu
        buildListByCSS(".button-clear.-expanded");
        for (int i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            allElementsCollapsed = click();
            sleep(200);
        }

        return getListSize() > 0 ? allElementsCollapsed : true;
    }

    public boolean clickAndWaitSportsbook() {
        super.click();
        sleep(500);
        boolean siteReady;
        siteReady = waitForPresenceOfElementByCss("body.pace-done");
        siteReady = waitForInvisibilityOfElementByClassName("loadmask");
        return siteReady;
    }

    public boolean waitSportsbook() {
        sleep(500);
        boolean siteReady;
//        siteReady = waitForPresenceOfElementByCss("body.pace-done");
        siteReady = waitForInvisibilityOfElementByClassName("loadmask");
        return siteReady;
    }

    public void waitAfterPDSUpdate() {
        sleep(2000);
    }

    public boolean validateCurrentElementByImage(String imagePath) {

        try {

            // wait for the page to be fully rendered
            sleep(1000);
            String firstPartImagPath = "src/test/resources";
            // validate image or generate baseline
            if (baseline)
                return takeScreenshotOfCurrentElement(firstPartImagPath + imagePath, true);
            else
                return isCurrentElementEqualToImage(firstPartImagPath + imagePath, diffSizeTrigger);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateListElementsByImage(String imagePath) {
        try {

            // wait for the page to be fully rendered
            sleep(1000);

            // validate image or generate baseline
            if (baseline)
                return takeScreenshotOfListElements(imagePath, true);
            else
                return isListOfElementsEqualToImage(imagePath, diffSizeTrigger);
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getNextOffRacesIds() throws Throwable {
        List<String> eventsFromMeetingPage = new ArrayList<String>();

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("racing-highlights"));
        if (navigateToElementById("tabs-1")) {
            assertTrue(buildListByClassName("race-event--highlights"));
            for (int i = 0; i < getListSize(); i++) {
                assertTrue(navigateToListElement(i));
                assertTrue(navigateToElementByPartialId("OB_EV"));
                String eventId = (getAttribute("id"));
                eventsFromMeetingPage.add(eventId);

            }
        }

        return eventsFromMeetingPage;

    }

    public void swipeCollectionsSliderToRight() {
        navigateToRootElement();
        navigateToElementByClassName("swiper-nav-container");
        navigateToElementByClassName("arrow-right");
        do {
            click();
            sleep(150);
        } while (!getAttribute("class").contains("swiper-button-disabled"));
    }


    public void colapse_competiotions_contatiner_above() throws Throwable {
        if (!getScenarioContext().getEvents().isEmpty()) {
            String competitionId = "OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
            navigateToRootElement();
            if (buildListByClassName("comp-container ")) {
                navigateToRootElement();
                buildListByClassName("comp-container ");
                for (int i = 0; i < getListSize(); i++) {
                    navigateToListElement(i);
                    if (getAttribute("data-entityid").contains(competitionId)) {
                        break;
                    } else {
                        navigateToRootElement();
                        navigateToListElement(i);
                        navigateToElementByClassName("button-clear ");
                        click();
                    }

                }
            }
        }
    }

    public void scrollToTheBottom() throws InterruptedException {
        By selBy = By.tagName("body");
        navigateToRootElement();
        navigateToElementByTag("body");
        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        int initialHeight = driver.findElement(selBy).getSize().getHeight();
        int currentHeight = 0;
        while (initialHeight != currentHeight) {
            initialHeight = driver.findElement(selBy).getSize().getHeight();

            //Scroll to bottom
            ((JavascriptExecutor) driver).executeScript("scroll(0," + initialHeight + ");");
            System.out.println("Sleeping... wleepy");
            Thread.sleep(2000);
            currentHeight = driver.findElement(selBy).getSize().getHeight();
        }
    }

    public void scrollDown() throws InterruptedException {
        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
    }
}

