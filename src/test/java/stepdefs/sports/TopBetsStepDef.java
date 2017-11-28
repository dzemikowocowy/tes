package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.openbet.model.*;
import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.WebDriverException;
import stepdefs.shared.ScenarioHardcodedData;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import util.SportsNavigation;
import util.SportsNavigationHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mkoza 06/04/2016
 */
public class TopBetsStepDef extends SportsAutomationScriptingLanguage {

    // Classes initializations:
    private DataStepDef dataStepDef = new DataStepDef();
    private Selectors selectors = new Selectors();
    private ScenarioHardcodedData hardcodedData = new ScenarioHardcodedData();
    private SportsStepDef sportsStepDef = new SportsStepDef();
    protected SportsNavigation navigation = SportsNavigationHelper.getNavigation();
    private NavigationStepDef navigationStepDef = new NavigationStepDef();
    private BetslipStepdefs betslipStepdefs = new BetslipStepdefs();


    /**
     * This method will assert if our hardcoded Event is being displayed, and we will add it to the Scenario Context.
     * Note: Currenty we have commented the Selection and Market since we are not currently using them.
     *
     * @throws Throwable
     */
    @And("^specific Top Bet is displayed$")
    public void specificBetIsDisplayed() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we verify that our Top Bet is there on the page
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId)));

        // Here we create the Event and Market elements
        Event event = new Event();

        // Here we set the values from the Button Element
        event.setPdsId(getAttribute("data-event"));

        // And the event to the Scenario Context
        getScenarioContext().addEvent(event);

        // Here we specify that the Data must not be deleted
        SharedData.clearData = false;


    }


    /**
     * On this method we set our Top Bet into the Scenario Context.
     *
     * @throws Throwable
     */
    @And("^specific Top Bet is set on Scenario Context")
    public void specificBetIsSetInScenarioContext() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        Event event = new Event();

        event.setPdsId(String.valueOf(hardcodedData.eventId));

        // And the event to the Scenario Context
        getScenarioContext().addEvent(event);

        // Here we specify that the Data must not be deleted
        SharedData.clearData = false;

    }

    /**
     * This method will set the Manual Weight of our "Hardcoded" event to how it was before the Manual Weight on the Backoffice.
     *
     * @throws Throwable
     */
    @And("^set back the Top Bet Manual Weight")
    public void setBackTopBetManualWeight() throws Throwable {
        getBackOfficeHelper().updateAlternateSelectionTopBet(hardcodedData.selectionTypeOfSport,
                hardcodedData.selectionSportCategory,
                hardcodedData.selectionSportSubCategory,
                hardcodedData.selectionMatch,
                hardcodedData.selectionMarket,
                hardcodedData.selection,
                hardcodedData.manualWeight);
    }


    /**
     * On this method we get the current list of Top Bets displayed on the Widget.
     * Also we save the List for later assertions.
     * We store also the name of the Last Top bet for later usages.
     *
     * @throws Throwable
     */
    @Then("^the user get the current order of the Top Bets$")
    public void userGetTheCurrentOrderOfTheTopBets() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByCSS(".topbets__list-item--link"));
        SharedData.topBetsNames = getElementList();
        navigateToListElement(9);
        SharedData.nameOfLastTopBet = getText().replace("v", "vs");
    }

    /**
     * This method will set the last Top Bet displayed on the Widget to be the First one displayed on
     * the Top Bets Widget.
     * <p>
     * Note: Not currently in use due to the forbidden usages of top bets / selections that are not ours.
     *
     * @throws Throwable
     */
    @And("^the user modify the order of the last Top Bet to be the first one displayed$")
    public void userSetTopBet() throws Throwable {
        getBackOfficeHelper().setLastTopBetToBeTheFirstOne(SharedData.nameOfLastTopBet);
        sleep(300000);
    }

    /**
     * This method will set all the Manual Weight setted on the Top Bets to 0 on the BackOffice.
     *
     * @throws Throwable
     */
    @And("^the user erase from Top Bets Back Office all the Man. Inc from the Bets$")
    public void userRemovesAllManInc() throws Throwable {
        getBackOfficeHelper().removeManIncFromAllBets();
    }

    /**
     * On this method we will verify that the order of the Top Bets Widget is not the same one as
     * when we started the Test.
     *
     * @throws Throwable
     */
    @Then("^the user verify that the order of the Top Bets was successfully modified$")
    public void userVerifyThatLastTopBetIsNowTheFirst() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByCSS(".topbets__list-item--link"));
        navigateToListElement(0);
        assertTrue(SharedData.nameOfLastTopBet.equals(getText()));
    }

    /**
     * This is just a simple method to save lines of code by clicking on the Top Bets when the Test is being run in Mobile.
     */
    public void clickOnTopBetsMobile() {
        navigateToRootElement();
        try {
            assertTrue(navigateToElementByCSS("a[data-name*='Bets']"));
            click();

            // This is mainly because on mobile we need to scroll to the very bottom if its a Daily scenario.
            scrollToTheBottom();

        } catch (WebDriverException | AssertionError | InterruptedException e) {
        }
    }


    /**
     * This method just click on the Show More link that redirects to the Top Bets page.
     * Independent on the device, if its Desktop or Mobile.
     *
     * @throws Throwable
     */
    @Then("^the user clicks on Top Bets Show more link$")
    public void clickShowMoreTopBets() throws Throwable {

        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(selectors.SHOW_MORE_TOP_BETS_LINK));
            click();
        } else {
            clickOnTopBetsMobile();
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(selectors.SHOW_MORE_TOP_BETS_LINK));
            clickJS();
        }
    }

    @Then("^the user clicks on Top Bets from the Carousel or from the Left Hand menu")
    public void clickTopBetsFromCarousel() throws Throwable {
        if (isSportsbookOnDesktop()) {
            navigationStepDef.clickOnElementFromLeftHandMenu("Top Bets");
        } else {
            navigationStepDef.clickOnElementFromCarousel("Top Bets");
        }
    }

    /**
     * On this method we verify that when user is on a Page that contains the Top Bets Widget that this one contains
     * the correct title.
     *
     * @throws Throwable
     */
    @Then("^the user verifies that Top Bets title is being displayed only for desktop$")
    public void verifyTopBetsTitle() throws Throwable {
        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(".topbets__header h2"));
            assertTrue(getText().equals("Top Bets"));
        } else {
            navigateToRootElement();
            navigateToElementByCSS(".topbets__header h2");
            assertFalse(isDisabled());
        }
    }

    /**
     * On this method we verify the Top Bets Page, that has the Title, We verify that all the selections
     * have all the components as well.
     *
     * @throws Throwable
     */
    @Then("^all bets components on Top Bets page are being displayed$")
    public void verifyTopBetsClickToSeeMore() throws Throwable {

        // Here we assert the Page Title if its Top Bets
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".header-panel__title"));
        assertEquals(getText(), "Top Bets");

        // Here we get the list of Bets, we assert that its not 0 the number of elements and we get how many they are.
        navigateToRootElement();
        assertTrue(buildListByCSS("li[id^='OB_EV']"));
        assertTrue(getListSize() != 0);
        int amountOfBets = getListSize();

        // Here we verify if all the bets contain all the elements
        for (int i = 1; i <= amountOfBets; i++) {

            // Here we verify that the Bet has the Sport Name
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//span[@class='topbets__icon-name'])[" + i + "]"));
            assertTrue(isDisplayed());
            String sportName = getText().toLowerCase().replace(" |","").replace(" ","-");

            // Here we verify that the Bet has the Sport Icon
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//li)[" + i + "]//i[contains(@class,'topbets__icon icon-" + sportName + "')]"));
            assertTrue(isDisplayed());

            // Here we verify the date of the Bet
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//time)[" + i + "]"));

            // Here we verify the Title
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//h3)[" + i + "]"));

            // Here we verify the Link
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//a)[" + i + "]"));

            // Here we verify the Bet Button / Selection
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//button)[" + i + "]"));

        }
    }

    /**
     * On this method we verify that all the Top Bets from the Widget have all the components correctly.
     *
     * @throws Throwable
     */
    @Then("^all bets components on Top Bets widget are being displayed$")
    public void verifyTopBetsComponents() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        navigateToRootElement();
        assertTrue(buildListByCSS("li[id^=OB_EV]"));

        for (int i = 1; i <= getListSize(); i++) {

            // Here we verify that the Bet has the Sport Name
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//span[@class='topbets__icon-name'])[" + i + "]"));
            assertTrue(isDisplayed());
            String sportName = getText().toLowerCase().replace(" |","").replace(" ","-");

            // Here we verify that the Bet has the Sport Icon
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//li)[" + i + "]//i[contains(@class,'topbets__icon icon-" + sportName + "')]"));
            assertTrue(isDisplayed());

            // Here we verify that the Bet has the time
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//time)[" + i + "]"));
            assertTrue(!getText().equals(null));
            assertTrue(isDisplayed());

            // Here we verify that has a Title
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//h3[@class='topbets__list-item--event'])[" + i + "]"));
            assertTrue(!getText().equals(null));
            assertTrue(isDisplayed());

            // Here we verify that the Bet have a link
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//a[@class='topbets__list-item--link'])[" + i + "]"));
            assertTrue(!getText().equals(null));
            assertTrue(isDisplayed());

            // Here we verify that the Bet have a Odd button
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//button[contains(@id,'OB_OU')])[" + i + "]/span[1]"));
            assertTrue(!getText().equals(null));
            assertTrue(isDisplayed());

        }
    }

    /**
     * On this method we will click on a Selection and we verify that the Betslip is incremented by one.
     *
     * @throws Throwable
     */
    @Then("^the user is able to add a Top Bet Selection to the Betslip$")
    public void verifyUserCanBetOnTheTopBets() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//div[@class='topbets clickable-selections']//button[contains(@id,'OB_OU')])[1]"));
        clickJS();

        sleep(3000);

        assertTrue(betslipStepdefs.theBetslipIconHasXbets("1"));
    }

    /**
     * On this method we will click on the Hardcoded event and verify that we are taken to that Event page.
     * Also we verify that what is displayed on the Top Bet Widget for that Event is also the same data.
     *
     * @throws Throwable
     */
    @Then("^the user navigates to the event page by clicking on the Top Bet event$")
    public void verifyUserIsTakenToEventPageFromTopBet() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        int eventId = hardcodedData.eventId;

        // Here we get the Bet Time.
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(selectors.TOP_BET_TIME_BY_EVENT_ID, eventId)));
        String betTime = getText();

        // Here we get the Bet price
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, eventId)));
        String betAmount = getAttribute("data-odds");

        // Then we click on the link
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(selectors.TOP_BET_LINK_BY_EVENT_ID, eventId)));
        String nameOfEvent = getText();
        clickJS();

        // Here we verify if the title matches with the one from the Widget - Due to translations discrepancies this
        // section is temporary commented.
//        navigateToRootElement();
//        navigateToElementByCSS(".header-panel__title");
//        assertEquals(nameOfEvent, getText());

        //Here we verify the time
        navigateToRootElement();
        if (navigateToElementByCSS(".event-start-date")) {
            assertEquals(getText(), betTime);
        } else {
            assertEquals(betTime, getBackOfficeHelper().eventStartingTime(String.valueOf(eventId)));
        }

        //Here we verify if the displayed Bet amount displayed on the widget is displayed on the Event page.
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//button[contains(@id,'OB_OU')])[1]"));
        assertEquals(getAttribute("data-odds"), betAmount);

    }

    /**
     * Note: The method first intention to be Dynamic and not data dependent / hardcoded values.
     * I just replace the navigation to the Element for our hardcoded one, so the day of tomorrow if we want to have
     * it with any Bet we can.
     *
     * @throws Throwable
     */
    @And("^increase the price of a Top Bet and the Increase animation appears$")
    public void increasePriceOfFirstTopBet() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we verify that the element is present on the page.
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId)));

        // Here we modify the price of the bet to be EVS / 0.0
        modifySelectionPrice(hardcodedData.selectionId, "0.0");
        SharedData.previousBetPrice = "0.0";

        sleep(2000);

        // Once its EVS, we increase the Bet
        modifySelectionPrice(hardcodedData.selectionId, "2.1");

        int counter = 0;
        while(!hasBackgroundColour("#a0d69f") && counter <= 100){
            sleep(100);
            counter++;
        }
        assertTrue(hasBackgroundColour("#a0d69f"));
    }

    /**
     * On this method we will decrease the price of the Hardcoded Selection.
     *
     * @throws Throwable
     */
    @When("^decrease the price of a Top Bet and the Decrease animation appears$")
    public void decreasePriceOfFirstTopBet() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we verify that the element is present on the page.
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId)));

        // Here we modify back the price of the bet.
        modifySelectionPrice(hardcodedData.selectionId, "0.0");

        // Here we verify the colour change.
        int counter = 0;
        while(!hasBackgroundColour("#ff9999") && counter <= 300){
            sleep(100);
            counter++;
        }

        assertTrue(hasBackgroundColour("#ff9999"));
    }

    /**
     * On this method we will verify if the previous price modification were successfully done by checking if the
     * price was increased or decreased depending on the input of the value.
     *
     * @param value
     * @throws Throwable
     */
    @Then("^the price has '(increased|decreased)' for the modified selection$")
    public void verifyPriceModification(String value) throws Throwable {

        // We navigate to the On-Focus element
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(Selectors.TOP_BET_BUTTON_BY_EVENT_ID, Integer.toString(hardcodedData.eventId))));

        // We get the actual price of the Selection
        double actualPrice;
        if (getAttribute("data-odds").equals("EVS")) {
            actualPrice = 0.0;
        } else {
            actualPrice = sportsStepDef.fromFractionToDecimal(getAttribute("data-odds"));
        }

        // Here we verify according to the VALUE given the price how this should be modified
        if (value.contains("increased")) {
            double previousPrice = sportsStepDef.fromFractionToDecimal(SharedData.previousBetPrice);
            assertTrue(actualPrice > previousPrice);
        } else {
            double previousPrice = sportsStepDef.fromFractionToDecimal(SharedData.previousBetPrice);
            assertTrue(actualPrice <= previousPrice);
        }
    }

    /**
     * This method will verify that our Hardcoded Top Bet is being displayed, then we increase the value of the Handicap on
     * the BackOffice.
     * <p>
     * Note: The commented section is due to this method first intention to be Dynamic and not data dependent / hardcoded values.
     * I just replace the navigation to the Element for our hardcoded one, so the day of tomorrow if we want to have
     * it with any Bet we can.
     *
     * @throws Throwable
     */
    @When("^increase the value of the handicap on a selection on Top Bets$")
    public void increaseHandicapValue() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // We verify that our hardcoded Bet is there
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId)));

        //Here we get the Market ID
        SharedData.marketId = Integer.parseInt(getAttribute("data-market").replace("OB_MA", ""));

        // Here we get the ID of the Event
        SharedData.eventId = Integer.parseInt(getAttribute("data-event").replace("OB_EV", ""));

        //Here we get and store the original time
        SharedData.originalTime = sportsStepDef.getEventCurrentTime(getAttribute("data-event"));

        // Here we modify the time of the event for a few hours from now
        dataStepDef.modifyEventTime(SharedData.eventId, "");

        //Here we get and store the original Handicap value
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(selectors.TOP_BET_TITLE_BY_EVENT_ID, hardcodedData.eventId)));
        SharedData.handicapValue = getText().split("\\(")[1].split("\\)")[0];

        // Here we modify it - in this case - to +4.0
        dataStepDef.modifyHandicapValue(SharedData.marketId, "+4.0");

        // We wait for the changes to be process in order to be displayed on the Frontend
        sleep(120000);

    }

    /**
     * On this method we verify that the Handicap from the Hardcoded Event was successfully increased after we modify it.
     */
    @Then("^the value of the Top Bet handicap selection is increased$")
    public void verifyEventHandicapValueIsIncreased() {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we navigate to our Hardcoded Bet
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(selectors.TOP_BET_TITLE_BY_EVENT_ID, hardcodedData.eventId)));

        // Then we verify that the Handicap Value was modified.
        assertTrue(SharedData.handicapValue.equals(getText().split("\\(")[1].split("\\)")[0]));

    }

    /**
     * On this method we revert the Handicap changes that we previously done.
     *
     * @throws ParseException
     */
    @Then("^revert the Top Bet handicap selection value$")
    public void revertHandicapValue() throws ParseException {

        // Here we modify back the Handicap Value
        dataStepDef.modifyHandicapValue(SharedData.marketId, SharedData.handicapValue);

        // Here we modify back to the original time of the event
        dataStepDef.modifyEventTime(SharedData.eventId, SharedData.originalTime);

        // This sleep is in order to dont break the next scenarios when the test goes to the page and still see the modified value.
        sleep(120000);
    }


    /**
     * TODO: Change the Event id to be Dynamic
     * On this method we set the Hardcoded Event to display flag "Yes" or "No".
     *
     * @param value
     * @throws Throwable
     */
    @Then("^user set a Top Bet Event to display flag '(yes|no)'$")
    public void setSelectionBetDisplayFlag(String value) throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        SharedData.eventId = hardcodedData.eventId;

        if (value.equals("yes")) {
            dataStepDef.modifySelectionEventFlagValue(SharedData.eventId, true);
        } else {
            dataStepDef.modifySelectionEventFlagValue(SharedData.eventId, false);
        }

        // We wait for changes to be done
        sleep(360000);
    }


    /**
     * Here we verify if the Top Bet "is" or "is not" displayed after the modifications that we previously made.
     *
     * @param value
     * @throws Throwable
     */
    @Then("^the Top Bet Event '(is|is not)' displayed$")
    public void betEventShouldBeOrNotDisplayed(String value) throws Throwable {

        refresh();

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        boolean result = false;

        if (value.equals("is")) {
            for (int i = 0; i < 40; i++) {
                navigateToRootElement();
                if(navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId))) {
                    result = true;
                    break;
                }else {
                    refresh();

                    if (!isSportsbookOnDesktop()) {
                        clickOnTopBetsMobile();
                    }
                    sleep(10000);
                }
            }
        }else {
            for (int i = 0; i < 40; i++) {
                navigateToRootElement();
                if(!navigateToElementByCSS(String.format(selectors.TOP_BET_BUTTON_BY_EVENT_ID, hardcodedData.eventId))) {
                    result = true;
                    break;
                }else {
                    refresh();
                    sleep(10000);
                }
            }
        }
        assertTrue(result);
    }

    /**
     * On this method we set the Display Flag of the Bet Market from the Hardcoded Event to "Yes" or "No".
     *
     * @param value
     * @throws Throwable
     */
    @Then("^user set a Bet Market to display flag '(.*)'$")
    public void setMarketBetDisplayFlag(String value) throws Throwable {

        refresh();

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        SharedData.marketId = hardcodedData.marketId;

        // Here we modify the Display Flag given the value on the step.
        if (value.equals("yes")) {
            dataStepDef.modifyMarketDisplayFlagValue(SharedData.marketId, true);
        } else {
            dataStepDef.modifyMarketDisplayFlagValue(SharedData.marketId, false);
        }

        // We wait for changes to be done
        sleep(240000);
    }

    /**
     *  On this method we verify if the market ID that we previously used is In Play or not.
     *
     * @param value
     * @throws Throwable
     */
    @Then("^the Top Bet should '(be|not be)' In-Play$")
    public void verifyTopBetInPlay(String value) throws Throwable {

        refresh();

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        if (value.equals("be")) {
            assertTrue(dataStepDef.verifyIfEventIsInPlay(SharedData.marketId));
        } else {
            assertFalse(dataStepDef.verifyIfEventIsInPlay(SharedData.marketId));
        }
    }

    /**
     * On this Method we set our Hardcoded Top Bet time to a prestent / past one in order to turn it into a In-Play event.
     *
     * @throws Throwable
     */
    @Then("^set a Top Bet time for the In-Play$")
    public void setTopBetStartingTimeToNow() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // This is the needed time in order to set it to In Play.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -4);
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());

        // This is the hardcoded event.
        SharedData.eventId = hardcodedData.eventId;

        //Here we get and store the original time
        SharedData.originalTime = sportsStepDef.getEventCurrentTime(String.valueOf(SharedData.eventId));

        // Here we modify the time of the event for a few hours from now
        dataStepDef.modifyEventTime(SharedData.eventId, raceStartTime);

        // We wait for changes to be done
        sleep(60000);

    }

    /**
     * On this method we will set the original time of the Top Bet previously modified.
     *
     * @throws Throwable
     */
    @Then("^set a Top Bet to its original time$")
    public void setTopBetStartingTimeToOriginal() throws Throwable {

        // Here we modify the time of the event for a few hours from now
        dataStepDef.modifyEventTime(hardcodedData.eventId, SharedData.originalTime);

        // We wait for changes to be done
        sleep(60000);

    }

    /**
     * On this method we will get the current list being displayed on the Frontend and store it on the Shared Data
     * for later usages.
     *
     * @throws Throwable
     */
    @Then("^get the current List of Top Bets elements$")
    public void getCurrentTopBetsList() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we initialize our Map which will contain the Event Names, plus the position
        HashMap<Integer, String> currentTopBets = new HashMap<Integer, String>();

        // Here we get the List of Top Bets
        navigateToRootElement();
        assertTrue(buildListByCSS(selectors.ALL_TOP_BETS_DISPLAYED));

        // Here we pass the List with their respectives positions to the Map, that we will later assert to the one from
        // The BackOffice.
        for (int i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            currentTopBets.put(i, getText());
        }
        SharedData.topBetsFrontendMap = currentTopBets;

    }

    /**
     * On this method we will verify if the first Top Bets displayed on the Widget are from the current sport page
     * that the user is on.
     *
     * @throws Throwable
     */
    @Then("^verify that the first Top Bets displayed are from that sport$")
    public void verifyIfFirstTopBetsAreFromTheCurrentSportPage() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we get the current sport no matter which one was previously selected from the url
        String sport = MultiThreadedDriverFactory.getCurrentDriver().getCurrentUrl();
        String currentTopBetSport;

        // We get the sport from the url, and it will be the same format as how we are going to assert on the Top Bets
        // list, since we will be asserting the "href" values of the Top Bets.
        sport = sport.split("/")[5];

        // Here we get the List of Top Bets
        navigateToRootElement();
        assertTrue(buildListByCSS(selectors.ALL_TOP_BETS_DISPLAYED));

        boolean currentSportAppearsFirst = true;

        for (int i = 0; i < getListSize(); i++) {

            navigateToListElement(i);

            // Here we get the type of sport from the current Top Bet element
            currentTopBetSport = getAttribute("href").split("/")[5];

            // Here we verify if the current sport type is the same as the one on the element
            // It will always have hopes that will be the first the same type as the one from the sports page that we are
            if (currentSportAppearsFirst) {
                currentSportAppearsFirst = currentTopBetSport.equals(sport);
            } else {
                // If not, then should always be different than the one of the sports page where the user is
                assertFalse(currentTopBetSport.equals(sport));
            }
        }
    }


    /**
     * On this method we will get the current Top Bets list being display on the Frontend and assert the Order with the
     * one we have on the Backoffice.
     *
     * @throws Throwable
     */
    @Then("^verify the order of Top Bets elements on BackOffice$")
    public void verifyTopBetsOrderInBackOffice() throws Throwable {

        HashMap<Integer, String> backOfficeTopBetsList = getBackOfficeHelper().getTopBetsMapByEventName();

        boolean result = false;
        String currentFrontEndTopBet;

        for (int i = 0; i < SharedData.topBetsFrontendMap.size(); i++) {

            //Here we modify/format the Names from the Frontend to the ones on the Backend
            if (SharedData.topBetsFrontendMap.get(i).contains(" v ")) {
                currentFrontEndTopBet = "|" + SharedData.topBetsFrontendMap.get(i).replace(" v ", "| |vs| |") + "|";
            } else {
                if (SharedData.topBetsFrontendMap.get(i).contains(" vs ")) {
                    currentFrontEndTopBet = "|" + SharedData.topBetsFrontendMap.get(i).replace(" vs ", "| vs |") + "|";
                } else {
                    currentFrontEndTopBet = SharedData.topBetsFrontendMap.get(i);
                }
            }

            for (int y = i + 1; y <= backOfficeTopBetsList.size(); y++) {

                // Here we verify if the Frontend Top Bet was in correct order or not.
                if (currentFrontEndTopBet.equals(backOfficeTopBetsList.get(y))) {
                    result = true;
                    break;
                }

            }
            assertTrue(result);
        }
    }


    /**
     * This method will modify the Manual Weight of a Top Bet, if user doesnt provide the Event Name for this one,
     * the method will modify our hardcoded one.
     * <p>
     * Note: the Selection must be displayed on the Top Bets, ensure that before using this method.
     *
     * @throws Throwable
     */
    @Then("^modify the Manual Weight of one Top Bet to '(.*)'$")
    public void modifyTopBetManualWeight(String amount) throws Throwable {

        String eventOnFocus;

        eventOnFocus = hardcodedData.selectionMatch;

        // NOTE: This method was intended to have as input parameters String amount AND String eventName

        // If eventName is empty, we will use our hardcoded one.
//        if(eventName.isEmpty()){
//            eventOnFocus = "|Mallorca FC| vs |Real Balompie Alicante|";
//        }else{
//            // Here we modify the given one for the format used on the Back Office
//            eventOnFocus = "|" + eventName.replace(" vs ", "| v |") + "|";
//        }

        // Here we moddify on the BackOffice the Weight of the Bet
        getBackOfficeHelper().setTopBetByEventName(eventOnFocus, amount);

        // Here we store on the Shared data the eventOnFocus so we can later revert the changes - On Hold for now.
//        SharedData.eventName = eventOnFocus;

        // Here we wait for changes to be done in the Frontend side
        // otherwise will break the next Scenario / Step.
        sleep(350000);

    }

    /**
     * On this method we will modify that the Display Order is not the same on the Top Bets Widget as when we previously
     * modify it.
     *
     * @throws Throwable
     */
    @Then("^verify that display order was modified$")
    public void verifyTopBetsOrderIsModified() throws Throwable {

        // This variable will be modified to "true" once we verify that the both Maps are not the same.
        boolean result = false;

        sleep(400000);
        refresh();

        if (!isSportsbookOnDesktop()) {
            clickOnTopBetsMobile();
        }

        // Here we get the current List of Top Bets
        navigateToRootElement();
        assertTrue(buildListByCSS(selectors.ALL_TOP_BETS_DISPLAYED));

        // Here we get the old list of the Top Bets on the Frontend before modifying it
        for (int i = 0; i < SharedData.topBetsFrontendMap.size(); i++) {
            navigateToListElement(i);
            if (!SharedData.topBetsFrontendMap.get(i).equals(getText())) {
                result = true;
            }
        }
        // Here we do the final verification if the Top Bets list displayed was modified or not.
        assertTrue(result);
    }

    /**
     * What this method will do is to set our Hardcoded Bet to its original state in case some test fail in the middle
     * of the execution.
     *
     * @throws Throwable
     */
    @Given("^we set our Hardcoded Top Bet to Default values$")
    public void setToDefaultValueOurHardcodedEvent() throws Throwable {

        // FLAGS SETUP
        // Here we set the Flag value of the MARKET
        dataStepDef.modifyMarketDisplayFlagValue(hardcodedData.marketId, true);
        // Here we set the Flag value of the EVENT
        dataStepDef.modifySelectionEventFlagValue(hardcodedData.eventId, true);

        // TIME SETUP
        // set the Event start time, we add from today + Month
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, +6);
        // format the start time
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        // set the Time
        dataStepDef.modifyEventTime(hardcodedData.eventId, startTime);

        // MANUAL WEIGHT SETUP
        // Here we set the manual weight on the Selection
        getBackOfficeHelper().updateAlternateSelectionTopBet(hardcodedData.selectionTypeOfSport,
                hardcodedData.selectionSportCategory,
                hardcodedData.selectionSportSubCategory,
                hardcodedData.selectionMatch,
                hardcodedData.selectionMarket,
                hardcodedData.selection,
                hardcodedData.manualWeight);

        // WE SET THE HANDICAP
        dataStepDef.modifyHandicapValue(hardcodedData.marketId, "+1.0");

        // WE SET THE PRICE TO EVS OF THE SELECTION
        modifySelectionPrice(hardcodedData.selectionId, "0.0");

        // Then after all these changes are done, we wait for all them to be done
        sleep(300000);
    }

    public void modifySelectionPrice(String selectionId, String price) {

        if (selectionId.startsWith("OB_OU")) {

            Selection selection = new Selection();
            selection.setId(Integer.valueOf(selectionId.replaceAll("[\\D]", "")));

            // calculate new price
            Fraction fraction = Fraction.getFraction(price);
            String newPrice = fraction.getNumerator() + 1 + "/" + fraction.getDenominator();
            selection.setPrice(newPrice);
            selection.setName(hardcodedData.selectionName);

            getOxifeedHelper().updateSelection(selection);

        } else {
            throw new IllegalArgumentException(
                    String.format("The passed argument %s is not a selection.", selectionId));
        }
    }
}
