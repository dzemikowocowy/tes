package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.openbet.model.BackOfficeConstants;
import office.openbet.model.Event;

import java.util.*;


/**
 *  Created by Michal Koza on 09/07/15.
 */

public class FootballMobileStepDef extends SportsAutomationScriptingLanguage {
    private static String[] Carousel = new String[]{"football", "matches",
            "in-play", "coupons", "competitions"};

    private static String[] defaultCarousel = new String[]{"in-play", "football",
            "horse", "virtual"};

    private static String[] dailylistdays = new String[]{"filterSelectionSunday", "filterSelectionMonday", "filterSelectionTuesday", "filterSelectionWednesday",
            "filterSelectionThursday", "filterSelectionFriday", "filterSelectionSaturday", "filterSelectionSunday", "filterSelectionMonday", "filterSelectionTuesday", "filterSelectionWednesday",
            "filterSelectionThursday", "filterSelectionFriday", "filterSelectionSaturday"};


    @Then("^the mobile '(football|default)' Carousel displayed with correct order$")
    public void theSportCarouselDisplayedWithCorrectOrder(final String option) throws Throwable {
        if("football".equalsIgnoreCase(option)) {
            List<String> CarouselNames = Arrays.asList(Carousel);
            navigateToRootElement();
            assertTrue(navigateToElementById("carousel"));
            assertTrue(navigateToElementByClassName("sidebar__group"));
            assertTrue(navigateToElementByClassName("contextual-nav__inner"));
            buildListByClassName("btn");
            for (int i = 0; i < getListSize() - 1; i++) {
                navigateToListElement(i);
                assertTrue(getAttribute("href").contains(CarouselNames.get(i)));
                System.out.println(getAttribute("href"));
                if (i == 0) {
                    assertTrue(hasPartialURL(getAttribute("href")));

                }
            }
        } else {
            List<String> CarouselNames = Arrays.asList(defaultCarousel);
            navigateToRootElement();
            assertTrue(navigateToElementById("carousel"));
            assertTrue(navigateToElementByClassName("sidebar__group"));
            assertTrue(navigateToElementByClassName("contextual-nav__inner"));
            buildListByClassName("btn");
            int counter = 0;
            for (int i = 0; i < getListSize() - 1 && counter < CarouselNames.size(); i++) {
                navigateToListElement(i);
                assertTrue(getAttribute("href").contains(CarouselNames.get(i)));
                System.out.println(getAttribute("href"));
                counter++;
            }
        }

    }


    @And("^Default option in Football Carousel is Featured$")
    public void defaultOptionInFootballCarouselIsFeatured() throws Throwable {
        //check theat Featured is acive by default
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        assertTrue(navigateToElementByClassName("sidebar__group"));
        assertTrue(navigateToElementByClassName("contextual-nav__inner"));
        assertTrue(navigateToElementByClassName("navHighlights"));
        assertTrue(isActive());

        swipeCollectionsSliderToRight();
    }

    @And("^the user select '(.*)' from the football Carousel$")
    public void theUserSelectCarouseloptionFromTheFootbalCarousel(String carouseloption) throws Throwable {
        String url = "" ;
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        assertTrue(navigateToElementByClassName("sidebar__group"));
        assertTrue(navigateToElementByClassName("contextual-nav__inner"));
        switch (carouseloption.toLowerCase().replaceAll(" ", "")) {
            case "dailylist":
                assertTrue(navigateToElementByClassName("navMatches"));
                navigateToChild();
                url = getAttribute("href");
                click();
                break;
            case "in-play":
                assertTrue(navigateToElementByClassName("navInplay"));
                navigateToChild();
                url = getAttribute("href");
                click();
                break;
            case "coupons":
                assertTrue(navigateToElementByClassName("navCoupons"));
                navigateToChild();
                url = getAttribute("href");
                click();
                break;
            case "competitions":
                assertTrue(navigateToElementByClassName("navCompetitions"));
                navigateToChild();
                url = getAttribute("href");
                click();
                break;
        }
        waitSportsbook();
        assertTrue(hasPartialURL(url));
    }


    @Then("^Football Daily List components displayed$")
    public void footballDailyListComponentsDisplayed() throws Throwable {

        navigateToRootElement();
        navigateToElementById("contentarea");
        assertTrue(navigateToElementByClassName("header-panel__content-inner"));
        assertTrue(isDisplayed());

        navigateToRootElement();
        navigateToElementById("dailyMatchesFilters");
        assertTrue(isDisplayed());

        navigateToRootElement();
        assertTrue(navigateToElementById("dailyMatchesFilters"));
        assertTrue(isDisplayed());

        navigateToRootElement();
        assertTrue(navigateToElementByClassName("matches-events-container"));
        assertTrue(isDisplayed());

        navigateToRootElement();
        assertTrue(navigateToElementById("topbets"));
        assertTrue(isDisplayed());
    }



    @When("^the user select '(.*)' tab from Football Daily List bar$")
    public void theUserSelectTomorrowTabFromFootballDailyListBar(String footballTab) throws Throwable {
        footballTab = "filterSelection" + footballTab;
        navigateToRootElement();
        navigateToElementById("dailyMatchesFilters");
        assertTrue(navigateToElementById("comp-nav"));
        assertTrue(navigateToElementById(footballTab));
        assertTrue(clickAndWaitSportsbook());

    }


    @Then("^the event is available on the '(.*)' tab but It is not available for the other tabs$")
    public void theEventIsAvailableOnTheTomorrowTabButItIsNotAvailableForTheOtherTabs(String dailylistTab) throws Throwable {
        String dailylistAtribute;
        Boolean eventCorrectlyfounded=false;

        if (dailylistTab.toLowerCase().contains("third")){
            Calendar calendar = Calendar.getInstance();
            dailylistTab = Arrays.asList(dailylistdays).get(calendar.get(Calendar.DAY_OF_WEEK) +1);
        } else{
            dailylistTab = "filterSelection" + dailylistTab;
        }

        scrollToTheBottom();
        waitSportsbook();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        navigateToRootElement();
        navigateToElementById("dailyMatchesFilters");
        assertTrue(navigateToElementById("comp-nav"));
        assertTrue(buildListByClassName("swiper-slide "));
        System.out.println(getListSize());

        for (int i = 0; i < getListSize() - 1; i++) {
            navigateToListElement(i);
            navigateToChild();
            dailylistAtribute=getAttribute("id");

            click();
            waitSportsbook();

            navigateToRootElement();
            assertTrue(navigateToElementById("prefooter"));
            assertTrue(navigateToElementByClassName("prefooter__back-to-top-wrapper"));
            click();
            waitSportsbook();



            if ((dailylistAtribute.contains(dailylistTab))) {
                navigateToRootElement();
                assertTrue(navigateToElementById(competitionId));
                assertTrue(navigateToElementByClassName("events-wrapper"));
                assertTrue(navigateToElementById(eventId));
                assertFalse(isHidden());
                assertTrue(isDisplayed());
                System.out.println("tutaj  znaleziono "+dailylistAtribute);
                eventCorrectlyfounded=true;
            } else {
                System.out.println("tutaj nie znaleziono "+dailylistAtribute);
                navigateToRootElement();
                assertFalse(navigateToElementById(eventId));
            }

        }
        assertTrue(eventCorrectlyfounded);
    }

    @When("^user scroll down to the botton of the page$")
    public void userScrollDown() throws Throwable {
        navigateToRootElement();

        assertTrue(navigateToElementById("prefooter"));
        click();
        waitSportsbook();
    }

    @Then("^the event does not appear in all tabs of Football Daily List$")
    public void theEventDoesNotAppearInAllTabsOfFootballDailyList() {

        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        navigateToElementById("dailyMatchesFilters");
        assertTrue(navigateToElementById("comp-nav"));
        assertTrue(buildListByClassName("swiper-slide "));
        System.out.println(getListSize());
        //wait for event 1 min
        sleep(80000);
        for (int i = 0; i < getListSize() - 1; i++) {
            navigateToListElement(i);
            navigateToChild();
            click();
            waitSportsbook();

            navigateToRootElement();
            assertTrue(navigateToElementById("prefooter"));
            click();
            waitSportsbook();
            navigateToRootElement();
            assertFalse(navigateToElementById(eventId));
        }
    }

    @When("^the user click on the View By button$")
    public void theUserClickOnTheViewByButton() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist__label"));
        click();
        sleep(1000);
    }

    @Then("^View By Menu is expanded$")
    public void viewByMenuIsExpanded() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist "));
        assertTrue(navigateToElementByClassName("filterlist__options__button "));
        assertTrue(isDisplayed());
        assertFalse(isHidden());

    }

    @Then("^Competition is selected in View By Menu$")
    public void competitionIsSelectedInViewByMenu() throws Throwable {
        theUserClickOnTheViewByButton();
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist "));
        assertTrue(navigateToElementByClassName("-competition "));
        if (!isActive()) {
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist "));
            assertTrue(navigateToElementByClassName("-competition "));
            click();
        }
    }

    @And("^View By Menu contains two options, Time and Competition$")
    public void viewByMenuContainsTwoOptionsTimeAndCompetition() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist "));
        buildListByClassName("filterlist__options__button ");
        assertTrue(getListSize() == 2);
        navigateToListElement(0);
        if (null == getAttribute ("data-sort-type")) {
            assertTrue(getAttribute("data-sort-order").contains("date"));
            navigateToListElement(1);
            assertTrue(getAttribute("data-sort-order").contains("competition"));
        } else {
            assertTrue(getAttribute("data-sort-type").contains("date"));
            navigateToListElement(1);
            assertTrue(getAttribute("data-sort-type").contains("competition"));
        }


    }

    @And("^Default option is Competition$")
    public void defaultOptionIsCompetition() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist "));
        assertTrue(navigateToElementByClassName("-competition "));
        assertTrue(isActive());
    }

    @And("^the events are grouped by Competition$")
    public void theEventsAreGroupedByCompetition() throws Throwable {

        sleep(80000);
        refresh();
        waitSportsbook();
        navigateToRootElement();
        assertTrue(navigateToElementById("prefooter"));
        assertTrue(navigateToElementByClassName("prefooter__back-to-top-wrapper"));
        click();
        waitSportsbook();
        List<Event> events = getScenarioContext().getEvents();
        System.out.println( events.size());
        if (!events.isEmpty()) {
            // add the event to OB
            for (Event event : events) {
                String competitionId = "comp-OB_TY" + event.getCompetitionId();
                String eventId = "OB_EV" + event.getId();
                navigateToRootElement();
                assertTrue(navigateToElementById(competitionId));
                assertTrue(navigateToElementByClassName("events-wrapper"));
                assertTrue(navigateToElementById(eventId));
                System.out.println(event.getId());
                System.out.println(event.getName());
            }
        }
    }

    @Then("^Time is selected in View By Menu$")
    public void timeIsSelectedInViewByMenu() throws Throwable {
        theUserClickOnTheViewByButton();
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist "));
        assertTrue(navigateToElementByClassName("-date "));
        if (!isActive()) {
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist "));
            assertTrue(navigateToElementByClassName("-date "));
            click();
        }
    }

    @And("^the events are grouped by time$")
    public void theEventsAreGroupedByTime() throws Throwable {
        int eventNumberFromSportsbookEventList = 0;
        int currentEventNumberFromCreatedEvents = 0;

        sleep(80000);
        refresh();
        waitSportsbook();
        navigateToRootElement();
        assertTrue(navigateToElementById("prefooter"));
        assertTrue(navigateToElementByClassName("prefooter__back-to-top-wrapper"));
        click();
        waitSportsbook();
        List<Event> events = getScenarioContext().getEvents();
        System.out.println(events.size());
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("matches-events-container"));
        buildListByPartialId("OB_EV");
        for (int i = 0; i < getListSize(); i++) {
            if (currentEventNumberFromCreatedEvents == events.size()) {
                break;
            }
            navigateToListElement(i);
            if (getAttribute("id").contains(events.get(currentEventNumberFromCreatedEvents).getId().toString())) {
                if (eventNumberFromSportsbookEventList < i) {
                    currentEventNumberFromCreatedEvents = currentEventNumberFromCreatedEvents + 1;
                    eventNumberFromSportsbookEventList = i;
                    System.out.println("event pokolei " + currentEventNumberFromCreatedEvents + " " + getAttribute("id"));
                }
            }
        }

        assertTrue(currentEventNumberFromCreatedEvents == events.size() );
    }

    @Then("^the event details are correct$")
    public void theEventDetailsAreCorrect() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        String competitionId = "comp-OB_TY" + event.getCompetitionId();
        String eventId = "OB_EV" + event.getId();
        String marketId="OB_MA"+event.getFirstMarket().getId();
        String eventName=event.getName();
        int amountOfSelections= event.getFirstMarket().getSelections().size();
        //        lazy loading
        sleep(90000);
        refresh();
        waitSportsbook();
        navigateToRootElement();
        assertTrue(navigateToElementById("prefooter"));
        assertTrue(navigateToElementByClassName("prefooter__back-to-top-wrapper"));
        click();
        waitSportsbook();

        //        verify event tittle

        navigateToRootElement();
        assertTrue(navigateToElementById(competitionId));
        assertTrue(navigateToElementByClassName("events-wrapper"));
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementByClassName("event__main"));
        assertTrue(navigateToElementByClassName("event__title"));
        String title=getText();
        assertTrue(eventName.replaceAll(event.getCompetition(),"").contains("0"+title));

        // verify selections name and type
        navigateToRootElement();
        assertTrue(navigateToElementById(competitionId));
        assertTrue(navigateToElementByClassName("events-wrapper"));
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementById(marketId));
        buildListByPartialId("OB_OU");
        assertTrue(getListSize()==amountOfSelections);
        for(int i=0; i<getListSize();i++){
            navigateToListElement(i);
            int pageSelectionID=Integer.parseInt(getAttribute("id").replaceAll("OB_OU",""));
            System.out.println("id"+getAttribute("id"));
            System.out.println("data Name "+getAttribute("data-name")+" selection name "+event.getFirstMarket().getSelections().get(i).getName());
            if (i==0){
                assertTrue(event.getFirstMarket().getSelections().get(i).getId().equals(pageSelectionID));
                assertTrue(getAttribute("data-name").contains(event.getFirstMarket().getSelections().get(i).getName()));
                assertTrue(event.getFirstMarket().getSelections().get(i).getType().contains("home"));
                assertTrue( event.getFirstMarket().getSelections().get(i).getPrice().contains(getAttribute("data-odds")));
            }
            else if (i==1){
                assertTrue(event.getFirstMarket().getSelections().get(i).getId().equals(pageSelectionID));
                assertTrue(getAttribute("data-name").contains(event.getFirstMarket().getSelections().get(i).getName()));
                assertTrue(event.getFirstMarket().getSelections().get(i).getType().contains("draw"));
                assertTrue( event.getFirstMarket().getSelections().get(i).getPrice().contains(getAttribute("data-odds")));
            }
            else {
                assertTrue(event.getFirstMarket().getSelections().get(i).getId().equals(pageSelectionID));
                assertTrue(getAttribute("data-name").contains(event.getFirstMarket().getSelections().get(i).getName()));
                assertTrue(event.getFirstMarket().getSelections().get(i).getType().contains("away"));
                assertTrue( event.getFirstMarket().getSelections().get(i).getPrice().contains(getAttribute("data-odds")));
            }


        }

    }

    @And("^the user is on the In Play '(.*)' page$")
    public void the_user_is_on_the_In_Play_sport_page(String sportName) throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/"+sportName + "/in-play");
        waitSportsbook();
        assertTrue(hasPartialURL(sportName + "/in-play"));
    }

    @And("^the event off flag set to n/a$")
    public void theEventOffFlagSetToNA() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();

        if (!events.isEmpty()) {
            // add the event to OB
            for (Event event : events) {
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NA);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
            }

        }
    }

    @And("^the event off flag set to no$")
    public void the_event_off_flag_set_to_no() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();

        if (!events.isEmpty()) {
            // add the event to OB
            for (Event event : events) {
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
            }

        }
    }

    @Then("^the event displayed on the page$")
    public void theEventDisplayedOnThePage() throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        String competitionId = "comp-OB_TY" + event.getCompetitionId();
        String eventId = "OB_EV" + event.getId();
        sleep(100000);
        refresh();
        waitSportsbook();
        waitForLazyLoading();
        navigateToRootElement();
        assertTrue(navigateToElementById(competitionId));
        assertTrue(navigateToElementByClassName("events-wrapper"));
        assertTrue(navigateToElementById(eventId));
        assertFalse(isHidden());
        assertTrue(isDisplayed());
    }

    @Then("^the event is not displayed on the page$")
    public void theEventIsNotDisplayedOnThePage() throws Throwable {

        Event event = getScenarioContext().getFirstEvent();
        String competitionId = "comp-OB_TY" + event.getCompetitionId();
        String eventId = "OB_EV" + event.getId();
        sleep(100000);
        refresh();
        waitSportsbook();
        waitForLazyLoading();
        navigateToRootElement();
        assertFalse(navigateToElementById(eventId));
    }
    private void waitForLazyLoading()throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("prefooter"));
        assertTrue(navigateToElementByClassName("prefooter__back-to-top-wrapper"));
        click();
        waitSportsbook();

    }

    @When("^user click in All In-Play button on carousel$")
    public void userClickInAllInPlayButtonOnCarousel() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        assertTrue(navigateToElementByClassName("sidebar__group"));
        assertTrue(navigateToElementByHref("/betting/en-gb/in-play/all"));
        click();

    }


    @Then("^Football In Play components displayed$")
    public void footballInPlayComponentsDisplayed() throws Throwable {
        navigateToRootElement();
    }

    @Then("^Football Couppons components displayed$")
    public void footballCoupponsComponentsDisplayed() throws Throwable {
        navigateToRootElement ();
    }



}




