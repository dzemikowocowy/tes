package stepdefs.sports;

import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import util.SportsNavigationHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.googlecode.totallylazy.regex.Regex.regex;

/**
 * Created by mkoza 29/02/15.
 */
public class BetslipStepdefs extends SportsAutomationScriptingLanguage {

    private Logger logger = LogManager.getRootLogger();

    public String currentUrl;
    public ArrayList<String> currentSelections;
    public int numOpenBets;
    public Map<String,String> state = new HashMap<>();

    @And("^the user adds £([0-9.]+) to the \"([^\"]*)\" stake input$")
    public void the_user_adds_£_to_the_stake_input(String stake, String selection) throws Throwable {
        state.put("betType", selection.toLowerCase().replaceAll(" ", "_"));
        String selectSelector;
        String stakeSelector;
        if (regex("Accumulator [0-9]+").matches(selection)) {
            String[] selectionElements = selection.split(" ");
            selectSelector = Selectors.bsSelectors.get("select.accumulator");
            selectSelector += selectionElements[1];
            selectSelector += " > header";
            stakeSelector = Selectors.bsSelectors.get("stake.accumulator");
            stakeSelector += selectionElements[1];
        } else {
            selectSelector = Selectors.bsSelectors.get("select." + selection.toLowerCase().replaceAll(" ", ""));
            selectSelector += " > header";
            stakeSelector = Selectors.bsSelectors.get("stake." + selection.toLowerCase().replaceAll(" ", ""));
        }
        navigateToRootElement();
        logger.info("Cant see stake input, looking for the dropdown element");
        navigateToRootElement();
        assertTrue(scrollToElementByCSS(selectSelector));

        assertTrue(click());
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(selectSelector));
        if(!navigateToElementByClassName("opened")) {
            logger.info("*Click*");
            assertTrue(click());
            sleep(1000);
        }
        if(device.isEmpty()) {
            navigateToRootElement();
            selectSelector = Selectors.bsSelectors.get("select." + selection.toLowerCase().replaceAll(" ", ""));
            assertTrue(navigateToElementByCSS(selectSelector));
            assertTrue(navigateToElementByClassName("stake-input "));
            logger.info("Typing in " + stake);
            assertTrue(typeIn(stake));
            navigateToRootElement();
            selectSelector = Selectors.bsSelectors.get("select." + selection.toLowerCase().replaceAll(" ", ""));
            assertTrue(navigateToElementByCSS(selectSelector));
            assertTrue(navigateToElementByClassName("multiple-selection"));
            assertTrue(navigateToElementByClassName("multiple-amount "));
            SharedData.betsnumber = getText().replace("Bet", "").replace("s", "").replaceAll(" ", "");
            navigateToRootElement();
            navigateToElementByCSS(Selectors.bsSelectors.get("title"));
            click();
        }
        else{
            selectSelector = Selectors.bsSelectors.get("select." + selection.toLowerCase().replaceAll(" ", ""));
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(selectSelector));
            assertTrue(navigateToElementByClassName("multiple-selection"));
            assertTrue(navigateToElementByClassName("multiple-amount "));
            SharedData.betsnumber = getText().replace("Bet", "").replace("s", "").replaceAll(" ", "");
            selectSelector = Selectors.bsSelectors.get("select." + selection.toLowerCase().replaceAll(" ", ""));
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(selectSelector));
            assertTrue(navigateToElementByClassName("input-container "));
            assertTrue(navigateToElementByTag("input"));
            click();
            sleep(2000);
            char[] stakeValues = stake.toCharArray();
            for (char stkenumber : stakeValues) {
                navigateToRootElement();
                assertTrue(navigateToElementById("numberpad"));
                assertTrue(navigateToElementByAttributeValue("data-value",String.valueOf(stkenumber)));
                click();
                sleep(1000);
            }

        }
        SharedData.addedStake = stake;
    }

    /**
     * Note that this method is meant to be used for when there is only one bet on the betslip.
     *
     * @param amountForBet
     * @throws Throwable
     */
    @When("^set the stake to '(.*)' for the just added bet$")
    public void setAmountForBet(String amountForBet) throws Throwable {

        sleep(3000);

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#bets-container-singles input[class*='stake-input']"));
        click();

        sleep(3000);

        if(!isSportsbookOnDesktop()){

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='0']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='.']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='5']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-action='close']");
            click();

        }else{
            typeIn(amountForBet);
        }
    }

    /**
     * On this method we will set all the stakes of the Bets that there are currently on the Betslip.
     * @throws Throwable
     */
    @When("^user set all the stakes on the Betslip$")
    public void setAllStakesTo05() throws Throwable {

        sleep(5000);
        navigateToRootElement();
        assertTrue(buildListByCSS("span[data-ng-model='bet.stake'] input"));

        for(int i = 0; i<getListSize(); i++){

            navigateToRootElement();
            navigateToListElement(i);
            click();

            sleep(2000);

            if(!isSportsbookOnDesktop()){

                navigateToRootElement();
                navigateToElementByCSS("button[data-value='0']");
                click();

                navigateToRootElement();
                navigateToElementByCSS("button[data-value='.']");
                click();

                navigateToRootElement();
                navigateToElementByCSS("button[data-value='5']");
                click();

                navigateToRootElement();
                navigateToElementByCSS("button[data-action='close']");
                click();

            }else{
                typeIn("0.5");
            }
        }
    }

    /**
     * On this method we set the Stake for the first Double Bet displayed on the Betslip.
     * @throws Throwable
     */
    @When("^user set the stake for the Double bet added$")
    public void setAmountForBet() throws Throwable {

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//label[@for='stake-input_DBL']/..//div[not(contains(@class,'hide'))]//input)[1]"));
        click();

        sleep(3000);

        if(!isSportsbookOnDesktop()){

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='0']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='.']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-value='5']");
            click();

            navigateToRootElement();
            navigateToElementByCSS("button[data-action='close']");
            click();

        }else{
            typeIn("0.5");
        }
    }

    /**
     * This method just clicks on the "Place Bet" button from the Betslip.
     * @throws Throwable
     */
    @And("^user click on Place Bet$")
    public void clickOnPlaceBet() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#place-bet-button"));
        click();
    }

    /**
     * This method will just verify that the "Bets Placed" is successfully displayed.
     * @throws Throwable
     */
    @Then("^the message Bets Placed is displayed$")
    public void betsPlacedMessageDisplay() throws Throwable {
        sleep(30000);
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//i[@class='icon-tick']"));
        assertTrue(isDisplayed());
    }

    @And("^the user clicks on Place Bet$")
    public void the_user_clicks_on_Place_Bet() throws Throwable {
        String buttonSelector = Selectors.bsSelectors.get("button.place-bet");
        String receiptSelector = Selectors.bsSelectors.get("bet-receipt.title");
        String errorSelector = Selectors.bsSelectors.get("error.price.change");
        int timeOut = 0;

        assertTrue(navigateToRootElement());
        waitForVisibilityOfElementByCss(buttonSelector);
        assertTrue(navigateToElementByCSS(buttonSelector));
        startTimeout(30000);
        click();
        navigateToRootElement();
        if (waitForVisibilityOfElementByCss(receiptSelector)) {
            stopTimeout("traf_placebet_" + environment + "_" + state.get("betType"));
        }
        while (navigateToRootElement() && navigateToElementByCSS(errorSelector) && isDisplayed() && timeOut < 60) {
            navigateToRootElement();
            navigateToElementByCSS(buttonSelector);
            click();
            sleep(500);
            timeOut++;
        }
        state.put("betPlaced", "true");
        // sleep(5000);
    }

    @Then("^the user balance is updated on refresh$")
    public void the_user_balance_is_updated_on_refresh() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.bsSelectors.get("tabs.betslip-tab")));
        logger.info("*Click*");
        click();
        sleep(1000);
        if (!device.isEmpty()){
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("betslip-toolbar"));
            assertTrue(navigateToChild());
            assertTrue(navigateToElementByClassName("icon-x"));
            click();
        }

        navigateToRootElement();
        scrollToElementByClassName("header__balance-refresh");
        assertTrue(click());
        sleep(4000);

        Double newBalance = Double.valueOf(SportsNavigationHelper.getBetSlip().getCurrentBalance().replaceAll("£",""));
        Double currentBalance= Double.valueOf(SharedData.currentBalance.replaceAll("£",""));
        // check balance update
        Double betsnumber= Double.valueOf(SharedData.betsnumber);
        if(SharedData.eachway){
            betsnumber= Math.abs(Double.valueOf(SharedData.betsnumber)*2);

        }

        Double addedStake= Double.valueOf(SharedData.addedStake);
        DecimalFormat df = new DecimalFormat("#.##");
        Double p=Math.abs(currentBalance-betsnumber * addedStake);
        Double calculation = Double.parseDouble(df.format(p));
        if(!calculation.equals(newBalance)) {
            logger.error("new balance: "+newBalance+ "should be: "+calculation+" is wrong , balance one the begining was "+currentBalance+" bets number was: " +betsnumber+ "and stake added was:"+ addedStake);
            refresh();
            waitSportsbook();
            navigateToRootElement();
            scrollToElementByClassName("header__balance-refresh");
            assertTrue(click());
            sleep(3000);
            newBalance = Double.valueOf(SportsNavigationHelper.getBetSlip().getCurrentBalance().replaceAll("£",""));


        }
        assertEquals(calculation,newBalance);
    }

    @When("^the user views the sports book$")
    public void the_user_views_the_sportsbook() throws Throwable {
        navigateToRootElement();
        logger.info("Waiting for the top bar to be visible");
        assertTrue(waitForPresenceOfElementByClassName("main"));
    }

    @Then("^a bet slip icon is visible$")
    public void a_bet_slip_icon_is_visible() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#open-betslip"));
        assertTrue(isDisplayed());
    }

    @And("^clicking the bet slip icon reveals the bet slip$")
    public void clicking_the_bet_slip_icon_reveals_the_bet_slip() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for bet slip icon");
        navigateToElementById("open-betslip");
        if (isHidden()) {
            navigateToRootElement();
            navigateToElementByClassName("toggle-betslip");
        }
        logger.info("*Click*");
        click();
        navigateToRootElement();
        assertTrue(waitForPresenceOfElementById("betslipwrapper"));
        logger.info("Looking for betslip");
        assertTrue(navigateToElementById("betslipwrapper"));
        assertFalse(isHidden());
    }

    @And("^(\\d+) tabs are visible$")
    public void tabs_are_visible(int numTabs) throws Throwable {
        navigateToRootElement();
        logger.info("Looking for betslip tabs");
        buildListByClassName("betslip-tab");
        int numElements = getListSize();
        logger.info("Found " + numElements + " tabs, expecting " + numTabs);
        assertTrue(numElements == numTabs);
    }

    @And("^there is a tab labelled (.*)$")
    public void there_is_a_tab_labelled_labelName(String labelName) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("betslip-navigation"));

        logger.info("Looking for a " + labelName + " tab");
        switch (labelName.toLowerCase()) {
            case "bet slip":
                assertTrue(navigateToElementById("betslip-tab"));
                break;
            case "open bets":
                assertTrue(navigateToElementById("openbets-tab"));
                break;
            case "tip advisor":
                assertTrue(navigateToElementById("openbets-tab"));
                break;
            default:
                assertTrue(false);
        }
    }

    @And("^the user clicks on the bet slip icon$")
    public void the_user_clicks_on_the_bet_slip_icon() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("open-betslip"));
        click();
        sleep(500);
    }

    @Then("^the (.*) section contains (.+) entry$")
    public void the_sectionName_section_contains_numEntries_entry(String sectionName, String stringNumMarkets) throws Throwable {
        navigateToRootElement();
        logger.info("looking for the section with this id: " + "bets-container-" + sectionName.toLowerCase());
        assertTrue(navigateToElementById("bets-container-" + sectionName.toLowerCase()));
        logger.info("Looking within it for selections");
        buildListByCSS("header.bet-header");
        logger.info("Found " + getListSize() + ", expecting " + stringNumMarkets);
        assertEquals(getListSize(), Integer.parseInt(stringNumMarkets));
    }

    @When("^the user navigates to the daily list$")
    public void the_user_navigates_to_the_daily_list() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(buildListByClassName("navMatches"));
        assertTrue(navigateToListElement(getListSize() - 1));
        click();
        sleep(500);
    }

    @Then("^the selection is added to the bet slip$")
    public void the_selection_is_added_to_the_bet_slip() throws Throwable {
        String market = currentSelections.get(0);
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("open-betslip"));
        executeScript("$('#open-betslip')[0].click();");
        assertTrue(navigateToRootElement());
        assertTrue(waitForVisibilityOfElementByClassName("single-bet"));
        assertTrue(buildListByClassName("single-bet"));
        assertTrue(navigateToListElement(0));
        assertTrue(getText().contains(market));
    }

    @And("^the user selects the tomorrow tab$")
    public void the_user_selects_the_tomorrow_tab() throws Throwable {
        assertTrue(waitForVisibilityOfElementByClassName("inner"));
        executeScript("$('.inner')[2].click();");
        sleep(500);
    }

    @And("^the user selects the top event$")
    public void the_user_selects_the_top_event() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(buildListByClassName("event__title"));
        assertTrue(navigateToListElement(0));
        click();
        sleep(500);
    }

    @And("^the user clicks the same selection again$")
    public void the_user_clicks_the_same_selection_again() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(waitForVisibilityOfElementByClassName("selectiondetails"));
        executeScript("$('.selectiondetails')[0].click();");
        sleep(500);
    }

    @Then("^the selection is removed$")
    public void the_selection_is_removed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertFalse(navigateToElementByClassName("slip-name"));

    }

    @And("^in play markets are available$")
    public void in_play_markets_are_available() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for 'Live' badges");
        buildListByCSS("div.area-livescore > span.event__badge");
        logger.info("Found " + getListSize());
        assertTrue(getListSize() > 0);
    }


    @Then("^the ajax loader spinning wheel is displayed$")
    public void the_ajax_loader_spinning_wheel_is_displayed() throws Throwable {
        // sleep(500);
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("loading-box"));
        assertTrue(navigateToElementByClassName("loader-image"));
        assertTrue(isDisplayed());
    }

    @And("^the following message is displayed: Please wait while your bet is being placed$")
    public void the_following_message_is_displayed_Please_wait_while_your_bet_is_being_placed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("loading-message"));
        assertTrue(buildListByTag("span"));
        assertTrue(navigateToListElement(0));
        if (isDisplayed()) {
            assertTrue(hasPreciseText("Please wait while your bet is being placed"));
        } else {
            navigateToNextListElement();
            assertTrue(hasPreciseText("Please wait while your bet is being placed"));
        }
    }

    @And("^following a delay the Bet Receipt is displayed$")
    public void following_a_delay_the_Bet_Receipt_is_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
    }

    @Then("^there is no count on the bet slip button$")
    public void there_is_no_count_on_the_bet_slip_button() throws Throwable {
        // sleep(2000);
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("betslip-count"));
        logger.info("Found betslip count text: " + getText());
        // waitForTextToBePresentInElement("");
        if (regex(".*[0-9]+.*").matches(getText())) {
            logger.info("Bet count text is still '" + getText() + "', waiting for it to reduce");
            sleep(1500);
        }
        logger.info("Bet count is now: " + getText());
        assertFalse(regex("[0-9]+").matches(getText()));
    }

    @Then("^the number (\\d+) is displayed in the selection counter$")
    public void the_number_is_displayed_in_the_selection_counter(String num) throws Throwable {
//     navigateToRootElement();
//        scrollToElementById("header");
        assertTrue(navigateToRootElement());
        if(device.isEmpty()){
            assertTrue(navigateToElementById("betslip-count"));
        }
        else {
            assertTrue(navigateToElementById("mobile-betslip-count"));
        }
        logger.info("Looking for '" + num + "' in the selection counter (currently " + getText() + ")");
        assertTrue(waitForTextToBePresentInElement(num));
    }

    @And("^the user removes (\\d+) selection using the \"x\" on the bet slip$")
    public void the_user_removes_selection_using_the_on_the_bet_slip(int num) throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("betslip-content"));
        if (isHidden()) {
            executeScript("$('#open-betslip')[0].click()");
        }
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("betslip-content"));
        assertTrue(buildListByClassName("remove-bet"));

        for (int i = 0; i < num; i++) {
           assertTrue( navigateToListElement(i));
            click();
            sleep(50);
        }
    }

    @And("^the user clicks the Clear Slip link$")
    public void the_user_clicks_the_Clear_Slip_link() throws Throwable {
        navigateToRootElement();
        logger.info("Waiting for the 'Clear Slip' link to be visible");
        waitForVisibilityOfElementByCss("#clear-slip-link");
        logger.info("Navigating to the link");
        navigateToElementByCSS("#clear-slip-link");
        logger.info("*Click*");
        click();
    }

    @Then("^(.*) should be displayed in the receipt Bet Details header$")
    public void Single_should_be_displayed_in_the_receipt_Bet_Details_header(String displayText) throws Throwable {
        sleep(1000);
        if(displayText.contains("Single")) {
            assertTrue(navigateToRootElement());
            waitForVisibilityOfElementByCss(".single-bet");
            assertTrue(navigateToElementByClassName("single-bet"));
            assertTrue(navigateToElementByClassName("selection"));
            assertTrue(hasPreciseText(displayText));
        }else{
            String selector = Selectors.bsSelectors.get("receipt.header");
            assertTrue(navigateToRootElement());
            waitForVisibilityOfElementByCss(selector);
            assertTrue(navigateToElementByCSS(selector));
            System.out.println(getText());
            assertTrue(hasPreciseText(displayText));
        }
    }

    @And("^the continue button is displayed$")
    public void the_continue_button_is_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("continue-button"));
        assertTrue(isDisplayed());
    }

    @And("^the user clicks on continue$")
    public void the_user_clicks_on_continue() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("continue-button"));
        click();
    }

    @Then("^the bet slip refreshes and displays \"([^\"]*)\"$")
    public void the_bet_slip_refreshes_and_displays(String message) throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("view-mode-message"));
        assertTrue(hasPreciseText(message));
    }

    @Then("^no each way terms are displayed$")
    public void no_each_way_terms_are_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("slip-ew"));
        assertTrue(isHidden());
    }

    @Then("^the \"Each Way\" check box is displayed$")
    public void the_check_box_is_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("slip-ew"));
        assertTrue(navigateToElementByTag("input"));
        assertTrue(isDisplayed());
    }

    @And("^the \"Each Way\" place terms are displayed$")
    public void the_place_terms_are_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("slip-ew"));
        assertTrue(navigateToElementByTag("label"));
        assertTrue(hasPartialText(false, "Each Way @"));
    }

    @Then("^each way terms are displayed$")
    public void each_way_terms_are_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(waitForVisibilityOfElementByClassName("slip-name"));
        assertTrue(navigateToElementByClassName("slip-name"));
        logger.info("Expected text: 'EW @', actual text: '" + getText() + "'");
        assertTrue(hasPartialText("EW @"));
    }

    @And("^Selection Name is '(.*)'$")
    public void selectionNameIs(String selectionName) throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("slip-name"));
        assertTrue(navigateToElementByTag("strong"));
        assertTrue(hasPartialText(false, selectionName));
    }

    @When("^the user clicks the Price Format select box$")
    public void the_user_clicks_the_Price_Format_select_box() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByCSS(Selectors.sbSelectors.get("link.priceformat")));
        click();
    }

    @And("^the user selects the (.*) price format$")
    public void the_user_selects_the_formatName_price_format(String formatName) throws Throwable {
        String formatKey;
        // String cookieText;
        switch (formatName.toLowerCase()) {
            case "fractional":
                formatKey = "ODDS";
                break;
            case "decimal":
                formatKey = "DECIMAL";
                break;
            case "american":
                formatKey = "AMERICAN";
                break;
            default:
                formatKey = "ODDS";
        }
        sleep(1000);
        logger.info("Setting odds to " + formatName + " using js");
        executeScript("window.WH.betslip.update('" + formatKey.toLowerCase() + "')");
        /*logger.info("Clearing cookies");
        deleteCookies();
        logger.info("Setting the Trafalgar cookie");
        setCookie("wh_traf_bet", "Yes");
        if (state.get("browserMode").equalsIgnoreCase("mobile")) {
            logger.info("Setting the device cookie");
            executeScript("var cookieName = 'wh_device';\n" +
                    "var cookieValue = '{\"is_native\":false,\"device_os\":\"iOS\",\"os_version\":\"9.0\",\"is_tablet\":false}';\n" +
            "var myDate = new Date();\n" +
                    "myDate.setMonth(myDate.getMonth() + 12);\n" +
                    "document.cookie = cookieName +\"=\" + cookieValue + \";expires=\" + myDate + \";domain=" + pr.getProperty("cookiedomain") + ";path=/\";");
            logger.info("Setting browser dimensions to 375, 667");
            manageBrowser().setBrowserDimensions(375, 667);
        }
        logger.info("Setting cookie with format key: " + formatKey);
        cookieText = "en|" + formatKey + "|form|TYPE|PRICE|||1445381170-0|SB|0|0||0|en|1|TIME|TYPE|0|1|A|0|850|0|1|0||TYPE|";
        logger.info("Using the following cookie string: " + cookieText);
        executeScript("var cookieName = 'cust_prefs';\n" +
                "var cookieValue = '" + cookieText + "';\n" +
                "var myDate = new Date();\n" +
                "myDate.setMonth(myDate.getMonth() + 12);\n" +
                "document.cookie = cookieName \"=\" + cookieValue + \";expires=\" + myDate + \";domain=" + pr.getProperty("cookiedomain") + ";path=/\";");
        // setCookie("cust_prefs", cookieText);
        logger.info("Refreshing to update view");
        refresh();

        // log back in
        navigateToRootElement();
        logger.info("Looking for the coathanger man");
        assertTrue(navigateToElementByCSS(Selectors.sbSelectors.get("coathanger-man.logged-out")));
        logger.info("*Click*");
        assertTrue(click());

        navigateToRootElement();

        assertTrue(navigateToElementByName("username"));
        assertTrue(clear());
        logger.info("Logging in as " + qduser);
        assertTrue(typeIn(qduser));

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByName("password"));
        assertTrue(clear());
        assertTrue(typeIn("pass123"));

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("inline-inputs"));
        assertTrue(navigateToElementByTag("button"));
        click();*/
    }

    @Then("^the format of the prices on the page changes to (.*)$")
    public void the_format_of_the_prices_on_the_page_changes_to_formatName(String formatName) throws Throwable {
        String regexString;
        String lcFormatName = formatName.toLowerCase();
        String price;
        switch (lcFormatName) {
            case "fractional":
                regexString = "([0-9]+/[0-9]+|EVS)";
                break;
            case "decimal":
                regexString = "[0-9]+\\.[0-9]+";
                break;
            case "american":
                regexString = "[+-][0-9]+";
                break;
            default:
                regexString = "([0-9]+/[0-9]+|EVS)";
        }
        assertTrue(navigateToRootElement());
        assertTrue(buildListByClassName("oddsbutton"));
        assertTrue(navigateToListElement(0));
        assertTrue(navigateToElementByClassName("selectiondetails"));
        //System.out.println(getAttribute("data-odds"));
        price = getAttribute("data-odds");
        logger.info("Checking price format, regex: '" + regexString + "', price text: '" + price + "'");
        assertTrue(regex(regexString).matches(price));
    }

    @Then("^the format of the price on the bet slip is (.*)$")
    public void the_format_of_the_price_on_the_bet_slip_is_formatName(String formatName) throws Throwable {
        String regexString;
        String lcFormatName = formatName.toLowerCase();
        switch (lcFormatName) {
            case "fractional":
                regexString = "([0-9]+/[0-9]+|EVS)";
                break;
            case "decimal":
                regexString = "[0-9]+\\.[0-9]+";
                break;
            case "american":
                regexString = "[+-][0-9]+";
                break;
            default:
                regexString = "([0-9]+/[0-9]+|EVS)";
        }
        assertTrue(navigateToRootElement());
        assertTrue(buildListByClassName("slip-name"));
        assertTrue(navigateToListElement(0));
        assertTrue(buildListByClassName("price"));
        assertTrue(navigateToListElement(0));
        while (isHidden()) {
            navigateToNextListElement();
        }
        logger.info("Checking '" + getText() + "' against the regex '" + regexString + "'");
        assertTrue(regex(regexString).matches(getText()));
        /*assertTrue(waitForPresenceOfElementByClassName("price"));
        assertTrue(buildListByClassName("price"));
        for (int i = 0; i < getListSize(); i++){
            assertTrue(navigateToRootElement());
            assertTrue(navigateToListElement(i));
            if (isDisplayed()) {
                assertTrue(regex(regexString).matches(getText()));
                break;
            }
        }*/
    }

    @Then("^the format of the price on the bet receipt is (.*)$")
    public void the_format_of_the_price_on_the_bet_receipt_is_formatName(String formatName) throws Throwable {
        String regexString;
        String lcFormatName = formatName.toLowerCase();
        switch (lcFormatName) {
            case "fractional":
                regexString = "([0-9]+/[0-9]+|EVS)";
                break;
            case "decimal":
                regexString = "([0-9]+.[0-9]+|EVS)";
                break;
            case "american":
                regexString = "([+-][0-9]+|EVS)";
                break;
            default:
                regexString = "([0-9]+/[0-9]+|EVS)";
        }
        waitForVisibilityOfElementByClassName("single-bet");
        String buttonSelector = Selectors.bsSelectors.get("button.place-bet");
        String errorSelector = Selectors.bsSelectors.get("error.price-change");
        while (navigateToRootElement() && navigateToElementByCSS(errorSelector) && isDisplayed()) {
            navigateToRootElement();
            navigateToElementByCSS(buttonSelector);
            click();
        }
        assertTrue(navigateToRootElement());
        assertTrue(buildListByClassName("single-bet"));
        assertTrue(navigateToListElement(0));

        assertTrue(navigateToElementByClassName("selection-price"));
        logger.info("Price displayed is: " + getText());
        assertTrue(regex(regexString).matches(getText()));
    }

    @Then("^the Bet Receipt header is displayed$")
    public void the_Bet_Receipt_header_is_displayed() throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementById("betslip-title-receipt"));
        assertTrue(hasPreciseText("Bet Receipt"));
        assertTrue(isDisplayed());
        // assertTrue(hasPreciseText("Bet Receipt"));
    }

    @And("^the Transaction Details header is Displayed$")
    public void the_Transaction_Details_header_is_Displayed() throws Throwable {
        String selector = Selectors.bsSelectors.get("receipt.header");
        assertTrue(navigateToRootElement());
        waitForVisibilityOfElementByCss(selector);
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @Then("^the total stake is displayed below the transaction reference$")
    public void the_total_stake_is_displayed_below_the_transaction_reference() throws Throwable {
        assertTrue(navigateToRootElement());
        waitForVisibilityOfElementById("total-stake-price");
        assertTrue(navigateToElementById("total-stake-price"));
        assertTrue(isDisplayed());
    }

    @And("^the total stake is £(\\d+.\\d+)$")
    public void the_total_stake_is_£_(String totalStake) throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("total-stake-price"));
        assertTrue(hasPartialText(false, totalStake));
    }

    @Then("^the transaction reference is displayed$")
    public void the_transaction_reference_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByClassName("receipt-transaction"));
        assertTrue(hasPartialText(false, "Transaction Reference"));
    }

    @Then("^the Clear Slip link is not displayed$")
    public void the_Clear_Slip_link_is_not_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("clear-slip-link"));
        assertTrue(isHidden());
    }

    @Then("^the Clear Slip link is displayed$")
    public void the_Clear_Slip_link_is_displayed() throws Throwable {
        navigateToRootElement();
        waitForVisibilityOfElementById("clear-slip-link");
        assertTrue(navigateToElementById("clear-slip-link"));
        assertTrue(isDisplayed());
    }

    @Then("^all selections are removed from the bet slip$")
    public void all_selections_are_removed_from_the_bet_slip() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for selections on the betslip");
        assertEquals(new Long(0), executeScript("return $('input.stake-input').length"));
    }

    @And("^a message is displayed on the empty bet slip which reads: \"([^\"]*)\"$")
    public void a_message_is_displayed_on_the_bet_slip_which_reads(String message) throws Throwable {
        navigateToRootElement();
        logger.info("Looking for the betslip message");
        navigateToElementByCSS("#betslip-content");
        logger.info("Found message text '" + getText() + "', expecting '" + message + "'");
        assertTrue(hasPartialText(false, message));
    }

    @Then("^the Contact Customer Services hyperlink is displayed in the bet slip footer$")
    public void the_Contact_Customer_Services_hyperlink_is_displayed_in_the_bet_slip_footer() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByPartialLinkText("Contact Customer Services"));
        assertTrue(isDisplayed());
    }

    @And("^the user clicks on Contact Customer Services$")
    public void the_user_clicks_on_Contact_Customer_Services() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByPartialLinkText("Contact Customer Services"));
        click();
    }

    @Then("^the online help pop-up overlay opens on the on the (.*) page$")
    public void the_online_help_pop_up_overlay_opens_on_the_on_the_title_page(String title) throws Throwable {
        navigateToRootElement();
        waitForVisibilityOfElementById("helpWindow");
        assertTrue(navigateToFrameById("iframe_mainFrame"));
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("rn_Summary"));
        assertTrue(hasPartialText(false, title));
    }



    @And("^the user clicks on Estimated Returns$")
    public void the_user_clicks_on_Estimated_Returns() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByPartialLinkText("Estimated Returns"));
        click();
    }

    @Then("^the Print Receipt text displays as a hyperlink$")
    public void the_Print_Receipt_text_displays_as_a_hyperlink() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByPartialLinkText("Print Receipt"));
    }

    @Then("^clicking Print Receipt launches the print dialogue$")
    public void clicking_Print_Receipt_launches_the_print_dialogue() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByPartialLinkText("Print Receipt"));
        assertTrue(getAttribute("href").equals("javascript:window.print();"));
    }

    @Then("^the Reuse Selections text displays as a hyperlink$")
    public void the_Reuse_Selections_text_displays_as_a_hyperlink() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByPartialLinkText("Reuse Selections"));
    }

    @And("^the user clicks on Reuse Selections$")
    public void the_user_clicks_on_Reuse_Selections() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for bet receipt");
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        logger.info("Looging for reuse selections button");
        assertTrue(navigateToElementByCSS("a[data-ng-click=\"reuseSelections($event)\"]"));
        logger.info("*Click*");
        click();
    }

    @Then("^the single selection is added back to the Bet Slip$")
    public void the_single_selection_is_added_back_to_the_Bet_Slip() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("slip-name"));
        assertTrue(navigateToElementByTag("label"));
        System.out.println("Looking on bet slip for " + currentSelections.get(0));
        assertTrue(hasPartialText(false, currentSelections.get(0)));
    }

    @Then("^each selection is added back to the Bet Slip$")
    public void each_selection_is_added_back_to_the_Bet_Slip() throws Throwable {
        navigateToRootElement();
        buildListByCSS("#bets-container-singles > div > div > div:nth-child(3) > div > strong > label");
        for (int i = 0; i < currentSelections.size(); i++) {
            logger.info("Looking for " + currentSelections.get(i));
            assertTrue(navigateToListElementByVisibleText(currentSelections.get(i)));
        }
    }

    @Then("^the William Hill Betting Rules text displays as a hyperlink$")
    public void the_William_Hill_Betting_Rules_text_displays_as_a_hyperlink() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByPartialLinkText("William Hill Betting Rules"));
    }

    @And("^the user clicks on WilliamHill Betting Rules$")
    public void the_user_clicks_on_WilliamHill_Betting_Rules() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("betslip-title-receipt"));
        assertTrue(navigateToElementByPartialLinkText("William Hill Betting Rules"));
        click();
    }

    @And("^the user checks the number of open bets$")
    public void the_user_checks_the_number_of_open_bets() throws Throwable {
        navigateToRootElement();
        waitForVisibilityOfElementById("openbet-count");
        assertTrue(navigateToElementById("openbet-count"));
        if (getText().length() > 0) {
            numOpenBets = Integer.parseInt(getText());
        } else {
            numOpenBets = 0;
        }
        logger.info("Current open bets count is " + numOpenBets);
    }

    @And("^the number of open bets has ([a-z]+) by ([0-9]+)$")
    public void the_number_of_open_bets_has_changed(String direction, int size) throws Throwable {
        navigateToRootElement();
        int change;
        int newNumOpenBets;
        switch (direction) {
            case "increased":
                change = size;
                break;
            case "decreased":
                change = -1 * size;
                break;
            default:
                change = size;
        }
        String target = Integer.toString(numOpenBets + change);

        waitForVisibilityOfElementById("openbet-count");
        assertTrue(navigateToElementById("openbet-count"));
        waitForTextToBePresentInElement(target);
        newNumOpenBets = Integer.parseInt(getText());
        assertEquals(numOpenBets + change, newNumOpenBets);
        numOpenBets = newNumOpenBets;
    }

    @Then("^a number is displayed indicating there are open bets$")
    public void a_number_is_displayed_indicating_there_are_open_bets() throws Throwable {
        assertTrue(numOpenBets > 0);
    }

    @Then("^the bet slip tab contains the words \"([^\"]*)\"$")
    public void the_bet_slip_tab_contains_the_words(String label) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("open-betslip"));
        assertTrue(hasPartialText(false, label));
    }

    @And("^the total stake is not displayed$")
    public void the_total_stake_is_not_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("total-stake-price"));
        assertTrue(isHidden());

    }

    @Then("^the total stake field is displayed$")
    public void the_total_stake_field_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(waitForVisibilityOfElementById("total-stake-price"));
    }

    @And("^the user clears the stake from the price field$")
    public void the_user_clears_the_stake_from_the_price_field() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByClassName("stake-input"));
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            assertTrue(navigateToListElement(i));
            clear();
            typeIn("");
        }
    }

    @Then("^the \"Accumulators/Multiples\" section is displayed$")
    public void the_section_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("bets-container-multiples"));
        assertTrue(isDisplayed());
    }

    @And("^the \"Accumulators/Multiples\" help icon is displayed$")
    public void the_help_icon_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByClassName("help-bet"));
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            navigateToListElement(i);
            assertTrue(isDisplayed());
        }
    }

    @And("^the user clicks the \"Accumulators/Multiples\" help icon$")
    public void the_user_clicks_the_help_icon() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByClassName("help-bet"));
        assertTrue(navigateToListElement(0));
        click();
    }

    @And("^the user selects the ([A-Za-z]+) Locale$")
    public void the_user_selects_the_French_Locale(String region) throws Throwable {
        String newUrl;
        switch (region.toLowerCase()) {
            case "french":
                newUrl = currentUrl.replace("en-gb", "fr-fr");
                break;
            case "german":
                newUrl = currentUrl.replace("en-gb", "de-de");
                break;
            case "spanish":
                newUrl = currentUrl.replace("en-gb", "es-es");
                break;
            default:
                newUrl = currentUrl;
        }
        navigateToPage(newUrl);
    }

    @Then("^the displayed Estimated Returns value for the accumulator is ([0-9.]+)$")
    public void the_Estimated_Returns_value_is_(Double returns) throws Throwable {
        ArrayList<String> ixStakes = new ArrayList<>();
        navigateToRootElement();
        assertTrue(navigateToElementById("bets-container-multiples"));
        assertTrue(buildListByClassName("stake-input"));
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            navigateToListElement(i);
            if (getAttribute("value").length() > 0) {
                ixStakes.add(String.valueOf(i));
            }
        }
        navigateToRootElement();
        assertTrue(navigateToElementById("bets-container-multiples"));
        assertTrue(buildListByClassName("estimated-returns"));
        for (int i = 0; i < ixStakes.size(); i++) {
            navigateToRootElement();
            navigateToListElement(Integer.parseInt(ixStakes.get(i)));
            assertTrue(isDisplayed());
            assertTrue(hasPartialText(false, returns.toString()));
        }
    }

    @Then("^the Estimated Returns field is not displayed by the accumulator$")
    public void the_Estimated_Returns_field_is_not_displayed_by_the_accumulator() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("bets-container-multiples"));
        assertTrue(buildListByClassName("estimated-returns"));
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            navigateToListElement(i);
            assertTrue(isHidden());
        }
    }

    @And("^the user adds £([0-9.]+) to the \"[^\"]*\" price field$")
    public void the_user_adds_£_to_the_price_field(String stake) throws Throwable {
        // sleeping for half a second because wd doesn't seem to want to type in the numbers
        sleep(2000);
        navigateToRootElement();
        logger.info("Looking for the expanded field");
        waitForVisibilityOfElementByCss("#bets-container-multiples > div.multiple-bet.selected-bet.ng-scope.bet-expanded > div > div:nth-child(1) > div:nth-child(3) > input");
        navigateToElementByCSS("#bets-container-multiples > div.multiple-bet.selected-bet.ng-scope.bet-expanded > div > div:nth-child(1) > div:nth-child(3) > input");
        logger.info("Typing in " + stake);
        typeIn(stake);
        navigateToRootElement();
        navigateToElementByCSS(Selectors.bsSelectors.get("title"));
        click();
    }

    @Then("^the Estimated Returns field is displayed by the staked accumulator$")
    public void the_Estimated_Returns_field_is_displayed_by_the_staked_accumulator() throws Throwable {
        navigateToRootElement();
        logger.info("Waiting for expanded section");
        waitForVisibilityOfElementByCss("div.multiple-bet.selected-bet.bet-expanded");
        navigateToElementByCSS("div.multiple-bet.selected-bet.bet-expanded");
        logger.info("Navigating to estimated returns");
        assertTrue(navigateToElementByCSS("span.estimated-returns > span"));
        logger.info("Looking for text, found: " + getText());
        assertTrue(hasAnyText());
    }

    @Then("^the browser width is less than (\\d+) px$")
    public void the_browser_width_is_less_than_px(int wIx) throws Throwable {
        manageBrowser().setBrowserDimensions(wIx, wIx);
        refresh();
    }

    @And("^the user has chosen 'All Races' for a meeting$")
    public void the_user_has_chosen_All_Races_for_a_meeting() throws Throwable {
        Random rand = new Random();
        String allracesSelector = Selectors.sbSelectors.get("button.all-races");
        navigateToRootElement();
        logger.info("Looking for an All Races Button");
        assertTrue(buildListByCSS(allracesSelector));
        int index = rand.nextInt(getListSize());
        logger.info("Found " + getListSize() + " 'All Races' buttons, clicking the " + index + "th one");
        assertTrue(navigateToListElement(index));
        while (!isDisplayed()) {
            index = rand.nextInt(getListSize());
            logger.info("This one is not visible, trying the " + index + "th");
            assertTrue(navigateToListElement(index));
        }
        logger.info("*Click*");
        assertTrue(click());
    }

    @Then("^the Estimated Returns field is(.*)displayed by the selections*")
    public void the_Estimated_Returns_field_is_not_displayed_by_the_selection(String displayStatus) throws Throwable {
        boolean expDisplayed = !(displayStatus.equalsIgnoreCase(" not "));
        String text;
        int i;
        logger.info((expDisplayed ? "Expecting " : "Not expecting ") + "estimated returns field to be displayed");
        navigateToRootElement();
        buildListByCSS("#bets-container-singles > div.single-bet");
        logger.info("Found " + getListSize() + " selections");
        for (i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            navigateToElementByCSS("div.selection-footer");
            text = getText();
            if (expDisplayed) {
                logger.info("Found text '" + text + "', expecting 'Estimated Returns:.*");
                assertTrue(hasPartialText(false, "Estimated Returns:"));
                navigateToElementByCSS("span.estimated-returns");
                text = getText();
                logger.info("Found text '" + text + "', checking against regex '[0-9]+\\.[0-9]{2}'");
                assertTrue(regex("[0-9]+\\.[0-9]{2}").matches(text));
            } else {
                logger.info("Found text '" + text + "', expecting ''");
                assertTrue(getText().length() == 0);
            }
        }
    }

    @Then("^a 'Please Log In' message is displayed which reads: \"([^\"]*)\"$")
    public void a_Please_Log_In_message_is_displayed_which_reads(String errorMessage) throws Throwable {
        navigateToRootElement();
        logger.info("Looking for error message");
        assertTrue(navigateToElementByCSS("#place-bet-error > strong"));
        logger.info("Found error message with text '" + getText() + "', expected text: '" + errorMessage + "'");
        assertTrue(hasPartialText(false, errorMessage));
    }

    @Then("^a quick deposit canvas is displayed$")
    public void a_quick_deposit_canvas_is_displayed() throws Throwable {
        String selector = Selectors.bsSelectors.get("div.quickdeposit");
        navigateToRootElement();
        logger.info("Looking for the quick deposit canvas");
        assertTrue(waitForVisibilityOfElementByCss(selector));
    }

    @Then("^the \"([^\"]*)\" is shown at the top of the canvas$")
    public void the_is_shown_at_the_top_of_the_canvas(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get("" + elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " header");
        assertTrue(waitForVisibilityOfElementByCss(selector));
    }

    @And("^the \"([^\"]*)\" text is \"([^\"]*)\"$")
    public void the_text_is(String elementDesc, String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " header");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Expected text: " + expectedText + ", actual text: " + getText());
        assertTrue(hasPartialText(false, expectedText));
    }

    @And("^the close button is shown on the \"([^\"]*)\" bar$")
    public void the_close_button_is_shown_on_the_bar(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " header");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Looking for the close button");
        assertTrue(navigateToElementByCSS("i.icon-x"));
    }

    @When("^the user clicks the close button on the \"([^\"]*)\" bar$")
    public void the_user_clicks_the_close_button_on_the_bar(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", ".") + ".close");
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " close button");
        assertTrue(waitForElementToBeClickableByCss(selector));
        assertTrue(navigateToElementByCSS(selector));
        logger.info("*Click*");
        assertTrue(click());
    }

    @Then("^the quick deposit overlay should be removed$")
    public void the_quick_deposit_overlay_should_be_removed() throws Throwable {
        Long qdCount = executeScript("return $('#qd-title').length");
        assertEquals(new Long(0), qdCount);
    }

    @Then("^the quick deposit balance required text is displayed$")
    public void the_quick_deposit_balance_required_text_is_displayed() throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.message");
        navigateToRootElement();
        logger.info("Looking for the qd balance message");
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(hasAnyText());
    }

    @Then("^the customers current balance is shown in the \"([^\"]*)\" element$")
    public void the_customers_current_balance_is_shown_in_the_element(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the current balance element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking '" + getText() + "' against the regex '[0-9]*\\.[0-9]{2}'");
        assertTrue(regex(".*[0-9]*\\.[0-9]{2}.*").matches(getText()));
    }

    @And("^the currency symbol prefixes the amount shown in the \"([^\"]*)\" element$")
    public void the_currency_symbol_prefixes_the_amount_shown_in_the_element(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the current balance element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking '" + getText() + "' against the regex '£[0-9]*'");
        assertTrue(regex(".*£[0-9]*.*").matches(getText()));
    }

    @And("^the text \"([^\"]*)\" is shown in the \"([^\"]*)\" element$")
    public void the_text_is_shown_in_the_element(String expectedText, String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking actual text '" + getText() + "' contains expected text '" + expectedText + "'");
        assertTrue(hasPartialText(false, expectedText));
    }

    @Then("^a \"([^\"]*)\" field is displayed$")
    public void a_field_is_displayed(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking that it is visible");
        assertTrue(isDisplayed());
    }

    @And("^the contents of the \"([^\"]*)\" field match the pattern \"([^\"]*)\"$")
    public void the_contents_of_the_field_match_the_pattern(String elementDesc, String regexPattern) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        String fieldContents = getAttribute("value");
        logger.info("Checking field contents '" + fieldContents + "' against the regex '" + regexPattern + "'");
        assertTrue(regex(".*" + regexPattern + ".*").matches(fieldContents));
    }

    @And("^the \"([^\"]*)\" field \"([^\"]*)\" be editable$")
    public void the_field_be_editable(String elementDesc, String status) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        String jsQuery = "$('" + selector + "').is('[readonly]')";
        Boolean readOnly = executeScript("return " + jsQuery);
        Boolean expectReadOnly = (status.equalsIgnoreCase("must not"));
        logger.info("Expect read only " + elementDesc + " field = " + expectReadOnly);
        logger.info(elementDesc + " field " + (readOnly ? "is" : "is not") + " read only");
        assertEquals(expectReadOnly, readOnly);
    }

    @And("^the \"([^\"]*)\" field must have a title of \"([^\"]*)\"$")
    public void the_field_must_have_a_title_of(String elementDesc, String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", ".") + ".title");
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element's title");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking actual text '" + getText() + "' contains expected text '" + expectedText + "'");
        assertTrue(hasPartialText(false, expectedText));
    }

    @And("^the \"([^\"]*)\" field has the following placeholder: \"([^\"]*)\"$")
    public void the_field_has_the_following_placeholder(String elementDesc, String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking actual placeholder '" + executeScript("return $('" + selector + "').attr('placeholder')") + "' contains expected text '" + expectedText + "'");
        assertTrue(hasPrecisePlaceholder(expectedText));
    }

    @And("^a 'credit card' icon is shown within the \"([^\"]*)\" field$")
    public void a_credit_card_icon_is_shown_within_the_field(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", ".") + ".title");
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Looking for the credit card icon");
        assertTrue(navigateToElementByCSS(Selectors.bsSelectors.get("cvv-icon")));
    }

    @Then("^three 'increase amount' buttons are displayed$")
    public void three_increase_amount_buttons_are_displayed() throws Throwable {
        navigateToRootElement();
        navigateToElementByCSS(Selectors.bsSelectors.get("div.quickdeposit"));
        buildListByCSS(Selectors.bsSelectors.get("quick-deposit.add-button"));
        logger.info("Found " + getListSize() + " increase amount buttons");
        assertEquals(3, getListSize());
    }

    @And("^the 'increase amount' buttons have the following text on them: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_increase_amount_buttons_have_the_following_text_on_them_(String expTextA, String expTextB, String expTextC) throws Throwable {
        navigateToRootElement();
        navigateToElementByCSS(Selectors.bsSelectors.get("div.quickdeposit"));
        buildListByCSS(Selectors.bsSelectors.get("quick-deposit.add-button"));
        logger.info("looking for increase amount button with the following text: " + expTextA);
        assertTrue(listContainsTextElement(expTextA));
        logger.info("looking for increase amount button with the following text: " + expTextB);
        assertTrue(listContainsTextElement(expTextB));
        logger.info("looking for increase amount button with the following text: " + expTextC);
        assertTrue(listContainsTextElement(expTextC));
    }

    @Then("^the 'quick deposit' button is displayed$")
    public void the_quick_deposit_button_is_displayed() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for the deposit button");
        assertTrue(navigateToElementByCSS(Selectors.bsSelectors.get("quick-deposit.deposit-button")));
        assertTrue(isDisplayed());
    }

    @And("^the 'quick deposit' button should read: \"([^\"]*)\"$")
    public void the_quick_deposit_button_should_read(String expectedText) throws Throwable {
        navigateToRootElement();
        logger.info("Looking for the deposit button");
        assertTrue(navigateToElementByCSS(Selectors.bsSelectors.get("quick-deposit.deposit-button")));
        logger.info("Expected text: '" + expectedText + "', actual text: '" + getText() + "'");
        assertTrue(hasPartialText(false, expectedText));
    }

    @And("^the user types \"([0-9]*)\" \"([^\"]*)\" in the quick deposit \"([^\"]*)\" field$")
    public void the_user_types_in_the_quick_deposit_field(Integer count, String charType, String elementDesc) throws Throwable {
        String textEntryString = (charType.equalsIgnoreCase("digits")) ? RandomStringUtils.randomNumeric(count) : RandomStringUtils.randomAlphabetic(count);
        String selector = Selectors.bsSelectors.get("quick-deposit." + elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " field using css selector: " + selector);
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(clear());
        logger.info("Typing in " + textEntryString);
        assertTrue(typeIn(textEntryString));
    }

    @When("^the 'quick deposit' button is clicked$")
    public void the_quick_deposit_button_is_clicked() throws Throwable {
        navigateToRootElement();
        logger.info("Looking for the deposit button");
        assertTrue(navigateToElementByCSS(Selectors.bsSelectors.get("quick-deposit.deposit-button")));
        logger.info("*Click*");
        assertTrue(click());
        // wait for deposit successful
        navigateToRootElement();
        waitForVisibilityOfElementByCss(Selectors.bsSelectors.get("quick-deposit.receipt.balance-message"));
    }

    @Then("^the quick deposit should be successful$")
    public void the_quick_deposit_should_be_successful() throws Throwable {
        String qdSelector = Selectors.bsSelectors.get("quick-deposit.receipt");
        String betSelector = Selectors.bsSelectors.get("bet-receipt.title");
        navigateToRootElement();
        logger.info("Looking for qd receipt");
        logger.info("Looking for bet receipt title");
        assertTrue(waitForVisibilityOfElementByCss(betSelector));
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(qdSelector));
        assertTrue(isDisplayed());
    }

    @And("^the bet should be placed$")
    public void the_bet_should_be_placed() throws Throwable {
        String selector = Selectors.bsSelectors.get("bet-receipt.title");
        navigateToRootElement();
        logger.info("Looking for bet receipt title");
        assertTrue(waitForVisibilityOfElementByCss(selector));
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @And("^the customer should see a bet receipt$")
    public void the_customer_should_see_a_bet_receipt() throws Throwable {
        String selector = Selectors.bsSelectors.get("bet-receipt.transaction-ref");
        navigateToRootElement();
        logger.info("Looking for the bet transaction ref");
        assertTrue(waitForVisibilityOfElementByCss(selector));
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @And("^the Quick Deposit component should not be displayed$")
    public void the_Quick_Deposit_component_should_not_be_displayed() throws Throwable {
        String jsQuery = "$('" + Selectors.bsSelectors.get("quick-deposit.title") + "').length";
        Long qdElements = executeScript("return " + jsQuery);
        int timeout = 0;
        while (timeout < 30 && qdElements > 0) {
            logger.info("QD Element is still visible, waiting for 1 second");
            timeout++;
            sleep(1000);
            qdElements = executeScript("return " + jsQuery);
        }
        logger.info("Found " + qdElements + " quick deposit div");
        assertEquals(qdElements, new Long(0));
    }

    @Then("^the 'quick deposit' receipt is displayed$")
    public void the_quick_deposit_receipt_is_displayed() throws Throwable {
        the_quick_deposit_should_be_successful();
    }

    @And("^the 'quick deposit' receipt title text must be \"([^\"]*)\"$")
    public void the_quick_deposit_receipt_title_text_must_be(String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.receipt.title");
        navigateToRootElement();
        logger.info("Looking for qd receipt title");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Expected text: '" + expectedText + "', actual text: '" + getText() + "'");
        assertTrue(hasPartialText(false, expectedText));
    }

    @And("^the \"([^\"]*)\" is displayed$")
    public void the_is_displayed(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc);
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @And("^the \"([^\"]*)\" text matches the pattern \"([^\"]*)\"$")
    public void the_text_matches_the_pattern(String elementDesc, String regexPattern) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for " + elementDesc);
        assertTrue(navigateToElementByCSS(selector));
        String thisText = getText();
        logger.info("Checking text: '" + thisText + "', against the regex: '" + regexPattern + "'");
        assertTrue(regex(regexPattern).matches(thisText));
    }

    @Then("^'quick deposit' transaction details are shown$")
    public void _quick_deposit_transaction_details_are_shown() throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.receipt.transaction-details");
        navigateToRootElement();
        logger.info("Looking for transaction details");
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @And("^the 'quick deposit' transaction details title is \"([^\"]*)\"$")
    public void the_quick_deposit_transaction_details_title_is(String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.receipt.transaction-details.title");
        navigateToRootElement();
        logger.info("Looking for transaction details title");
        assertTrue(navigateToElementByCSS(selector));
        String thisText = getText();
        logger.info("Expected text: '" + expectedText + "', actual text: '" + thisText + "'");
        assertTrue(hasPartialText(false, expectedText));
    }

    @And("^the text \"([^\"]*)\" is shown in the 'quick deposit' transaction details$")
    public void the_text_is_shown_in_the_quick_deposit_transaction_details(String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.receipt");
        navigateToRootElement();
        logger.info("Looking for transaction details");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Looking for the text: " + expectedText + ", found: " + getText());
        assertTrue(hasPartialText(false, expectedText));
    }


    @And("^the transaction reference is shown in the 'quick deposit' transaction details$")
    public void the_transaction_reference_is_shown_in_the_quick_deposit_transaction_details() throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit.receipt.transaction-details.ref");
        navigateToRootElement();
        logger.info("Looking for transaction ref");
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(hasAnyText());
    }

    @Then("^an error message \"([^\"]*)\" display under the 'quick deposit' \"([^\"]*)\" field$")
    public void an_error_message_display_under_the_quick_deposit_field(String status, String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit." + elementDesc.toLowerCase().replaceAll(" ", ".") + ".error");
        Boolean expectError = status.equalsIgnoreCase("should");
        String errorText = executeScript("return $('" + selector + "').text()");
        Boolean hasError = (errorText.length() > 0);
        logger.info("An error message " + (hasError ? "is" : "isn't") + " displayed, we " + (expectError ? "did" : "didn't") + " expect to see one");
        assertEquals(hasError, expectError);
    }

    @And("^the 'quick deposit' \"([^\"]*)\" field error text should be \"([^\"]*)\"$")
    public void the_quick_deposit_field_error_text_should_be(String elementDesc, String expectedText) throws Throwable {
        String selector = Selectors.bsSelectors.get("quick-deposit." + elementDesc.toLowerCase().replaceAll(" ", ".") + ".error");
        String errorText = executeScript("return $('" + selector + "').text()");
        logger.info("Looking for error field text");
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Found error text: '" + errorText + "', expected error text: '" + expectedText);
        assertTrue(hasPartialText(false, expectedText));
    }

    @Then("^a \"([^\"]*)\" message is displayed with the following text: \"([^\"]*)\"$")
    public void a_message_is_displayed_with_the_following_text(String messageKey, String messageText) throws Throwable {
        String selector = Selectors.bsSelectors.get("error." + messageKey.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + messageKey + " error using the css selector: " + selector);
        assertTrue(waitForVisibilityOfElementByCss(selector));
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking the text: '" + getText() + "' matches the expected text: " + messageText);
        assertTrue(hasPartialText(false, messageText));
    }

    @Then("^a \"([^\"]*)\" message is no longer displayed$")
    public void a_message_is_no_longer_displayed(String messageKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("error." + messageKey.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        buildListByCSS(selector);
        logger.info("Error message " + ((getListSize() > 0) ? "is" : "isn't") + " displayed");
        assertTrue(!(getListSize() > 0));
    }

    @Then("^the \"([^\"]*)\" element displayed should have an estimated returns field$")
    public void the_element_displayed_should_have_an_estimated_returns_field(String elementKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("open-bets.bet");
        navigateToRootElement();
        logger.info("Looking for the " + elementKey + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking for the text 'Potential Returns'");
        assertTrue(hasPartialText(false, "Potential Returns"));
    }

    @And("^the user selects the \"([^\"]*)\" Each Way checkbox$")
    public void the_user_selects_the_Each_Way_checkbox(String divKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("div.stake." + divKey.toLowerCase().replaceAll(" ", "."));
        String checkboxSelector = Selectors.bsSelectors.get("checkbox.each-way");
        navigateToRootElement();
        logger.info("Looking for the " + divKey + " stake div");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Looking for the 'Each Way' check box");
        assertTrue(navigateToElementByCSS(checkboxSelector));
        logger.info("*Click*");
        waitForElementToBeClickableByCss(checkboxSelector);
        assertTrue(click());
    }

    @And("^the user has chosen an 'All Races' view$")
    public void the_user_has_chosen_an_All_Races_view() throws Throwable {
        the_user_has_chosen_All_Races_for_a_meeting();
    }

    @And("^the user navigates to the bottom of the \"([^\"]*)\" betslip tab$")
    public void the_user_navigates_to_the_bottom_of_the_betslip_tab(String tabKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("button." + tabKey.toLowerCase().replaceAll(" ", ".") + ".more");
        while (navigateToRootElement() && navigateToElementByCSS(selector) && isDisplayed()) {
            logger.info("Found a 'More' button");
            logger.info("*Click*");
            assertTrue(click());
        }
    }

    @And("^the user waits for the bet placement to be complete$")
    public void the_user_waits_for_the_bet_placement_to_be_complete() throws Throwable {
        String selector = Selectors.bsSelectors.get("bet-receipt.title");
        navigateToRootElement();
        logger.info("Waiting for the bet receipt");
        assertTrue(waitForVisibilityOfElementByCss(selector));
    }

    @Then("^the top bet contains the text: \"([^\"]*)\"$")
    public void the_top_bet_contains_the_text(String text) throws Throwable {
     sleep(3000);
        String selector = Selectors.bsSelectors.get("open-bets.bet");
        navigateToRootElement();
        logger.info("Looking for the top element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking for the text '" + text + "', found text: '" + getText() + "'");
        assertTrue(hasPartialText(false, text));
    }

    @And("^the \"([^\"]*)\" stake entry is available$")
    public void the_stake_entry_is_available(String fieldKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("stake." + fieldKey.toLowerCase().replaceAll(" ", ""));
        navigateToRootElement();
        logger.info("Looking for " + fieldKey + " stake input field");
        waitForVisibilityOfElementByCss(selector);
        assertTrue(navigateToElementByCSS(selector));
    }

    @When("^the user clicks an 'Include in Multiples' checkbox$")
    public void the_user_clicks_an_Include_in_Multiples_checkbox() throws Throwable {
        String selector = Selectors.bsSelectors.get("input.include-in-multiples");
        navigateToRootElement();
        logger.info("Looking for an 'Include in Multiples' checkbox");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("*Click*");
        assertTrue(click());
    }

    @Then("^the \"([^\"]*)\" stake entry is no longer available$")
    public void the_stake_entry_is_no_longer_available(String fieldKey) throws Throwable {
        String selector = Selectors.bsSelectors.get("stake." + fieldKey.toLowerCase().replaceAll(" ", ""));
        navigateToRootElement();
        logger.info("Checking the " + fieldKey + " stake input field is not visible");
        assertTrue(waitForInvisibilityOfElementByCss(selector));
    }

    @Then("^an 'Each Way' checkbox is not displayed in the \"([^\"]*)\" div$")
    public void an_Each_Way_checkbox_is_not_displayed_in_the_div(String fieldKey) throws Throwable {
        String divSelector = Selectors.bsSelectors.get("select." + fieldKey.toLowerCase().replaceAll(" ", ""));
        String ewSelector = Selectors.bsSelectors.get("checkbox.each-way");
        navigateToRootElement();
        logger.info("Looking for the " + fieldKey + " div");
        waitForVisibilityOfElementByCss(divSelector);
        assertTrue(navigateToElementByCSS(divSelector));
        logger.info("Expecting not to find a visible each way selector");
        assertTrue(waitForInvisibilityOfElementByCss(ewSelector));
    }

    @And("^the user selects the \"([^\"]*)\" each way checkbox$")
    public void the_user_selects_the_each_way_checkbox(String divKey) throws Throwable {
        String divSelector = Selectors.bsSelectors.get("select." + divKey.toLowerCase().replaceAll(" ", ""));
        String ewSelector = Selectors.bsSelectors.get("checkbox.each-way");
        navigateToRootElement();
        logger.info("Looking for the " + divKey + " div");
        waitForVisibilityOfElementByCss(divSelector);
        assertTrue(navigateToElementByCSS(divSelector));
        logger.info("Looking for the Each Way checkbox");
        assertTrue(navigateToElementByCSS(ewSelector));
        logger.info("*Click*");
        assertTrue(click());
    }

    @And("^the user makes (\\d+) selections* from (\\d+) races*$")
    public void the_user_makes_selections_from_race(int numSelections, int numRaces) throws Throwable {
        String raceSelector = Selectors.sbSelectors.get("section.race-event");
        String oddsbuttonSelector = Selectors.sbSelectors.get("button.oddsbutton");
        navigateToRootElement();
        logger.info("Looking for races");
        assertTrue(buildListByCSS(raceSelector));
        logger.info("Found " + getListSize());
        for (int i = 0; i < numRaces; i++) {
            navigateToRootElement();
            assertTrue(buildListByCSS(raceSelector));
            assertTrue(navigateToListElement(i));
            logger.info("Looking for odds buttons");
            assertTrue(buildListByCSS(oddsbuttonSelector));
            logger.info("Found " + getListSize());
            for (int j = 0; j < numSelections; j++) {
                logger.info("Clicking the " + j + "th selection in the " + i + "th race");
                assertTrue(navigateToListElement(j));
                assertTrue(click());
            }
        }
    }

    @Then("^the bet receipt contains the text: \"([^\"]*)\"$")
    public void the_bet_receipt_contains_the_text(String searchText) throws Throwable {
        String selector = Selectors.bsSelectors.get("bet-receipt.body");
        String words[] = searchText.split(" ");
        navigateToRootElement();
        logger.info("Looking for the bet receipt");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Looking for the text '" + searchText + "' in the receipt body text '" + getText() + "'");
        for (int i = 0; i < words.length; i++) {
            assertTrue(hasPartialText(false, words[i]));
        }
    }

    @When("^the user clicks the low funds error message deposit link$")
    public void the_user_clicks_the_low_funds_error_message_deposit_link() throws Throwable {
        String linkSelector = Selectors.bsSelectors.get("error.low-funds.deposit-link");
        navigateToRootElement();
        logger.info("Looking for the deposit link");
        waitForVisibilityOfElementByCss(linkSelector);
        assertTrue(navigateToElementByCSS(linkSelector));
        logger.info("*Click*");
        assertTrue(click());
    }

    @Then("^the \"([^\"]*)\" page opens in an overlay$")
    public void the_page_opens_in_an_overlay(String overlayKey) throws Throwable {
        String overlaySelector = Selectors.sbSelectors.get("overlay." + overlayKey.replaceAll(" ", "").toLowerCase());
        String descriptionSelector = Selectors.sbSelectors.get("overlay.deposit.description");
        navigateToRootElement();
        logger.info("Looking for deposit overlay");
        waitForVisibilityOfElementByCss(overlaySelector);
        assertTrue(navigateToFrameByCss(overlaySelector));
        navigateToRootElement();
        logger.info("Looking for the deposit overlay description");
        assertTrue(navigateToElementByCSS(descriptionSelector));
        logger.info("Checking the text 'Select one of the Payment Methods below and Make a Deposit to Start Playing' is present, found: '" + getText() + "'");
        assertTrue(hasPartialText(false, "Select one of the Payment Methods below and Make a Deposit to Start Playing"));
    }

    @Then("^the user is transferred to the payment providers 3DS service$")
    public void the_user_is_transferred_to_the_payment_providers_DS_service() throws Throwable {
        String iframeSelector = Selectors.bsSelectors.get("quick-deposit.iframe.3ds");
        String iframeBodySelector = Selectors.bsSelectors.get("quick-deposit.iframe.3ds.body");
        navigateToRootElement();
        logger.info("Looking for the 3ds iframe");
        waitForVisibilityOfElementByCss(iframeSelector);
        assertTrue(navigateToFrameByCss(iframeSelector));
        navigateToRootElement();
        logger.info("Checking the body says 'Click Submit to send this message', found text: '" + getText() + "'");
        assertTrue(navigateToElementByCSS(iframeBodySelector));
        assertTrue(hasPartialText(false, "Click Submit to send this message"));
        returnToDefaultWindow();
    }

    @And("^the \"([^\"]*)\" field is not displayed$")
    public void the_field_is_not_displayed(String elementDesc) throws Throwable {
        String selector = Selectors.bsSelectors.get(elementDesc.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + elementDesc + " element");
        assertTrue(navigateToElementByCSS(selector));
        logger.info("Checking that it is not visible");
        assertTrue(isHidden());
    }

    @And("^the \"([^\"]*)\" button is displayed$")
    public void the_button_is_displayed(String buttonKey) throws Throwable {
        String selector = Selectors.bsSelectors.get(buttonKey.toLowerCase().replaceAll(" ", "."));
        navigateToRootElement();
        logger.info("Looking for the " + buttonKey + " button");
        waitForVisibilityOfElementByCss(selector);
        assertTrue(navigateToElementByCSS(selector));
        assertTrue(isDisplayed());
    }

    @When("^the quick deposit journey is completed$")
    public void the_quick_deposit_journey_is_completed() throws Throwable {
        String iframeSelector = Selectors.bsSelectors.get("quick-deposit.iframe.3ds");
        String iframeSubmitSelector = Selectors.bsSelectors.get("quick-deposit.iframe.3ds.submit");
        navigateToRootElement();
        logger.info("Looking for the 3ds iframe");
        waitForVisibilityOfElementByCss(iframeSelector);
        assertTrue(navigateToFrameByCss(iframeSelector));
        navigateToRootElement();
        logger.info("Looking for the submit button");
        assertTrue(navigateToElementByCSS(iframeSubmitSelector));
        logger.info("*Click*");
        assertTrue(click());
    }

    @Then("^each event name displays as a link$")
    public void each_event_name_displays_as_a_link() throws Throwable {
        String multSelector = Selectors.bsSelectors.get("open-bets.top-bet");
        String eventSelector = Selectors.bsSelectors.get("open-bets.event-name");
        navigateToRootElement();
        logger.info("Looking for the latest open bet");
        assertTrue(navigateToElementByCSS(multSelector));
        assertTrue(buildListByCSS(eventSelector));
        logger.info("Found " + getListSize() + " event names, checking each is a link");
        for (int i = 0; i < getListSize(); i++) {
            assertTrue(navigateToListElement(i));
            assertTrue(hasAttribute("href"));
            logger.info("Event " + i + " is ok");
        }
    }

    @And("^each event name points at an event$")
    public void each_event_name_points_at_an_event() throws Throwable {
        String multSelector = Selectors.bsSelectors.get("open-bets.top-bet");
        String eventSelector = Selectors.bsSelectors.get("open-bets.event-name");
        String eventUrlRegex = ".*betting/en-gb/football/OB_EV.*";
        String eventUrl;
        navigateToRootElement();
        logger.info("Looking for the latest open bet");
        assertTrue(navigateToElementByCSS(multSelector));
        assertTrue(buildListByCSS(eventSelector));
        logger.info("Found " + getListSize() + " event names, checking each url is an event link");
        for (int i = 0; i < getListSize(); i++) {
            assertTrue(navigateToListElement(i));
            eventUrl = getAttribute("href");
            logger.info("Checking the link url (" + eventUrl + ") points to a trafalgar event using the regex: " + eventUrlRegex);
            assertTrue(regex(eventUrlRegex).matches(eventUrl));
        }
    }

    @Then("^(\\d+) event names are displayed$")
    public void event_names_are_displayed(int expectedCount) throws Throwable {
        int count = 0;
        String multSelector = Selectors.bsSelectors.get("open-bets.top-bet");
        String eventSelector = Selectors.bsSelectors.get("open-bets.event-name");
        navigateToRootElement();
        logger.info("Looking for the latest open bet");
        assertTrue(navigateToElementByCSS(multSelector));
        assertTrue(buildListByCSS(eventSelector));
        logger.info("Found " + getListSize() + " event names, checking each url is an event link");
        for (int i = 0; i < getListSize(); i++) {
            assertTrue(navigateToListElement(i));
            if (isDisplayed()) {
                logger.info("Event " + i + " is displayed");
                count++;
            } else {
                logger.info("Event " + i + " is hidden");
            }
        }
        assertEquals(expectedCount, count);
    }

    @And("^the total stake value is calculated correctly$")
    public void theTotalStakeValueIsCalculatedCorrectly() throws Throwable {
        Double betsnumber= Double.valueOf(SharedData.betsnumber);
        Double addedStake= Double.valueOf(SharedData.addedStake);
        DecimalFormat df = new DecimalFormat("#.##");
        Double p= Math.abs(betsnumber * addedStake);
        sleep(2000);
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("total-stake-price"));
        sleep(1000);
        Double TotalStake= Double.valueOf(getText());
        if(SharedData.eachway){
            p = Math.abs((betsnumber+betsnumber) * addedStake);
        }
        Double calculation = Double.parseDouble(df.format(p));
        assertEquals(calculation,TotalStake);
        SharedData.totalStake= TotalStake;
    }

    @And("^Check Each Way flag$")
    public void checkEachWayFlag() throws Throwable {
      navigateToRootElement();
     assertTrue(navigateToElementByClassName("slip-ew"));
        assertTrue(click());
        SharedData.eachway= true;
    }

    @Then("^User is notified about market suspended$")
    public void userIsNotifiedAboutMarketSuspended() throws Throwable {
        sleep(5000);
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("bet-suspended "));
        assertTrue(getText().contains("BET SUSPENDED"));
    }

    @When("^change the selection price to (.*)$")
    public void changeTheSelectionPrice(String price) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        Selection selection = market.getFirstSelection();
        selection.setPrice(price);
        getOxifeedHelper().updateSelection(selection);
        waitAfterPDSUpdate();
    }

    @And("^New odd value is displayed$")
    public void greenSmallArrowAppears() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        String selectionid = market.getFirstSelection().getId()+"L";
        String selectionPrice=   market.getFirstSelection().getPrice();
        navigateToRootElement();
        assertTrue(navigateToElementById("bet-price_"+selectionid));
        assertTrue(getText().contains(selectionPrice));
        navigateToRootElement();
        assertTrue(navigateToElementById("bet-price_"+selectionid));
        String imgpath = getCssAttribute("background-image");
        System.out.println(imgpath);
    }

    @When("^change the selection handicap$")
    public void changeTheSelectionHandicap() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        market.setHandicap("4.4");
        getOxifeedHelper().updateMarket(market);
        waitAfterPDSUpdate();;
    }

    @When("^the Each way place terms changed$")
    public void theEachWayPlaceTermsChanged() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        market.setEachWayPlacesAt("1/2");
        getOxifeedHelper().updateMarket(market);
        waitAfterPDSUpdate();;
    }

    @Then("^New E/W place terms is displayed$")
    public void newEWPlaceTermsIsDisplayed() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        System.out.println(event.getFirstMarket().getEachWayPlacesAt()+"places at");
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("slip-ew"));
        assertTrue(getText().replaceAll(" ","").replace("EachWay@/","").contains(market.getEachWayPlacesAt()));
    }

    @When("^add selection to the betslip$")
    public void addSelectionToTheBetslip() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
       // assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        click();

    }

    @And("^the betslip counter is increased$")
    public void the_betslip_counter_is_increased() throws Throwable {
        String betslipCounter;
        navigateToRootElement();
        navigateToElementById("betslip-content");
        buildListByClassName("selection");
        int slipSelections = getListSize();
        navigateToRootElement();
        navigateToElementById("betslip-count");
        if (isDisplayed()) {
            betslipCounter = getText().toLowerCase().replace(" ", "").replace("(", "").replace(")", "").replace("betslip", "");
            System.out.println(slipSelections + "   " + betslipCounter);
            int i = Integer.parseInt(betslipCounter.trim());
            assertTrue(i == slipSelections);
        } else {
            navigateToRootElement();
            assertTrue(navigateToElementById("mobile-betslip-count"));
            betslipCounter = getText();
            int i = Integer.parseInt(betslipCounter.trim());
            assertTrue(i == slipSelections);
        }
    }

    @And("^the user opens their bet slip$")
    public void toggle_betslip() throws Throwable {
        SportsNavigationHelper.getBetSlip().open();
    }

    @When("^the Betslip icon has '(.*)' bets$")
    public boolean theBetslipIconHasXbets(String bets) throws Throwable {
        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS("#betslip-count"));
        } else {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS("#mobile-betslip-count"));
        }
        return getText().equals(bets);
    }



}
