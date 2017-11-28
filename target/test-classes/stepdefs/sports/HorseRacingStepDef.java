package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import office.openbet.model.BackOfficeConstants;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import stepdefs.shared.ScenarioContext;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import util.StepNotMetException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mkoza on 14/04/16.
 */
public class HorseRacingStepDef extends SportsAutomationScriptingLanguage {

    @Then("^scroller menu (.*) on (horse|greyhounds) racing racecard$")
    public void scrollerMenuDoesNotAppearOnHorseRacingRacecard(String arg1,final String section) throws Throwable {
    boolean condition=true;
        refresh();
        String EventId="OB_EV"+ScenarioContext.getInstance().getFirstEvent().getId();
        navigateToRootElement();
        condition=navigateToElementById("marketCollectionsMenu_"+EventId);
        if (arg1.toLowerCase().contains("does not")){
            assertFalse(condition);
        }
        else {
            assertTrue(condition);
        }
    }

    @Then("^scroller menu (.*) on horse racing meeting market event$")
    public void scrollerMenuAppearsOnHorseRacingMeetingMarketEvent(String arg1) throws Throwable {
        boolean condition=true;
        String eventId="";
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            if(event.getName().contains("Meeting Markets")){
                eventId = "OB_EV" + event.getId();
                break;
            }
        }
        navigateToRootElement();
        waitForPresenceOfElementById(eventId);

        navigateToRootElement();
        condition= navigateToElementById("marketCollectionItemsList");
        if (arg1.toLowerCase().contains("does not")){
            assertFalse(condition);
        }
        else {
            assertTrue(condition);
        }
    }


    @And("^after click correct content display for following scroller options$")
    public void afterClickCorrectContentDisplayForFollowingScrollerOptions(DataTable scrollerOptions) throws Throwable {
        String EventId="marketCollectionsMenu_OB_EV"+ScenarioContext.getInstance().getFirstEvent().getId();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        List<Map<String, String>> scrollerOptionsRows = scrollerOptions.asMaps(String.class, String.class);
        for (Map<String, String> scrollerOptionsRow : scrollerOptionsRows) {
            String scroller_option = scrollerOptionsRow.get("scroller_options");
            navigateToRootElement();
           assertTrue(navigateToElementByXpath(String.format(("(//div[@id=\"%s\"]//a/span[contains(., \"%s\")])[1]"), EventId, scroller_option)));
            click();
            sleep(1000);
            assertTrue(waitForVisibilityOfElementById(eventId));

            navigateToRootElement();
            assertTrue(navigateToElementByXpath(String.format(("//a[contains(@class,'filter-list__link--active filter-list__link')]"))));
          assertTrue(getText().contains(scroller_option));
        }
    }



    @And("^after click correct content display for following meeting market scroller options$")
    public void afterClickCorrectContentDisplayForFollowingMeetingMarketScrollerOptions(DataTable scrollerOptions) throws Throwable {
     ;
        String eventId="";
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            if(event.getName().contains("Meeting Markets")){
                eventId = "OB_EV" + event.getId();
                break;
            }
        }
        List<Map<String, String>> scrollerOptionsRows = scrollerOptions.asMaps(String.class, String.class);
        for (Map<String, String> scrollerOptionsRow : scrollerOptionsRows) {
            String scroller_option = scrollerOptionsRow.get("scroller_options");
            navigateToRootElement();
           // assertTrue(navigateToElementByXpath(String.format(("(//div[@id='marketCollectionsMenu']//ul[@id='marketCollectionItemsList']//span[contains(., \"%s\")])[1]"), scroller_option)));
            assertTrue(navigateToElementByXpath("//ul[@id='marketCollectionItemsList']//span[contains(.,'" + scroller_option+ "')]"));


            click();
            sleep(1000);
            assertTrue(waitForVisibilityOfElementById(eventId));
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("//ul[@id='marketCollectionItemsList']//a[contains(@class,'filter-list__link--active')]"));
            assertTrue(getText().contains(scroller_option));
        }
    }



    @And("^the (.*) (?:race|races) (?:are|is) resulted$")
    public void the_all_races_are_resulted(String race) throws Throwable {

        ArrayList<Event> allEvents = getScenarioContext().getEvents();
        for (Event event : allEvents) {
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
            getOxifeedHelper().updateEvent(event);
            // set the selection results for the Win market
            for (Market market : event.getMarkets()) {
                ArrayList<Selection> selections = market.getSelections();
                for (int i = 0; i < selections.size(); i++) {
                    Selection selection = selections.get(i);
                    if (i == 0) {
                        selection.setResult("win");
                        selection.setPlace(String.valueOf(i + 1));
                        getOxifeedHelper().resultSelection(selection);
                    } else if (i > 0 && i < 3) {
                        selection.setResult("place");
                        selection.setPlace(String.valueOf(i + 1));
                    } else {
                        selection.setResult("lose");
                        selection.setPlace(String.valueOf(i + 1));
                    }
                    getOxifeedHelper().resultSelection(selection);
                }
                if (race.equals("first"))
                    break;
            }
        }



    }

    @And("^the races are settled with forecast and tricast dividends$")
    public void the_races_are_settled_with_forecast_and_tricast_dividends() throws Throwable {
        for (Event event : getScenarioContext().getEvents()) {
            for (Market market : event.getMarkets()) {
                getOxifeedHelper().settleMarket(market, "59.69", "306.15");
                checkEventAlreadySettled(event);
            }
        }
    }
    @And("^the (?:race|first race) is settled with forecast and tricast dividends$")
    public void the_race_is_settled_with_forecast_and_tricast_dividends() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        // get the Win market
        Market winMarket = event.getMarket("|Win|");

        // check if the market contains favourite selections
        boolean marketWithFavourites = false;
        for(Selection selection : winMarket.getSelections()) {
            if(selection.getName().contains("1st Favourite")) {
                marketWithFavourites = true;
                break;
            }
        }

        // oxifeed is not settling market dividends with favourite selections properly
        // so we need BackOfficeHelper.
        if(marketWithFavourites)
            getBackOfficeHelper().settleMarket(winMarket, "59.69", "306.15");
        else
            getOxifeedHelper().settleMarket(winMarket, "59.69", "306.15");

        checkEventAlreadySettled(event);

    }

    @Then("^the 'results' icon is displayed next to the race link$")
    public void the_Results_icon_is_displayed_next_to_the_race_link() throws Throwable {
        boolean resultIconFound=false;
        refresh();
        waitSportsbook();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        for (int i=0;i<10; i++){
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(
                    String.format("[data-meetingmarketsid='%s'], [data-entityid='%s']", eventId, eventId)));
            if(navigateToElementByClassName("icon__results")){
                resultIconFound=true;
                break;
            } else {
                sleep(60000);
                refresh();
                waitSportsbook();
            }
        }
        if(!resultIconFound){
            throw new StepNotMetException("The result icon is not displayed  after waiting 10 minutes");
        }


    }

    @And("^the  user click on Meetings$")
    public void theUserClickOnMeetings() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("nav-meetings"));
         click();

    }

    @And("^the user selects first available race$")
    public void theUserSelectsFirstAvailableRace() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByPartialId("OB_TY"));
        assertTrue(getListSize()>0);
        navigateToListFirstElement();
        assertTrue(buildListByClassName("race-meeting__item "));
        assertTrue(getListSize()>0);
        navigateToListFirstElement();
        navigateToChild();
        if(getAttribute("data-name").contains("All Races")){
            navigateToListElement(1);
        }
        click();
        waitSportsbook();
    }

    @And("^the user navigates through the races in the meeting$")
    public void theUserNavigatesThroughTheRacesInTheMeeting() throws Throwable {
        sleep(500);
       navigateToRootElement();
        assertTrue(navigateToElementByClassName("race-event__header"));
        assertTrue(navigateToElementByClassName("arrow-right"));
        click();

    }

    @Then("^the user is the Horse Racing Meetings page$")
    public void theUserIsTheHorseRacingPage() throws Throwable {
        sleep(500);
        assertTrue(hasPartialURL("horse-racing"));
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("header-panel__title"));
        assertTrue(getText().equalsIgnoreCase("Meetings"));
    }
}


