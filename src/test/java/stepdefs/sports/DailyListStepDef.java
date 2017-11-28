package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import stepdefs.sports.FootballMobileStepDef;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Mkoza on 02/06/16.
 */
public class DailyListStepDef extends SportsAutomationScriptingLanguage {
    private static String[] dailylistdays = new String[]{"filterSelectionSunday", "filterSelectionMonday", "filterSelectionTuesday", "filterSelectionWednesday",
            "filterSelectionThursday", "filterSelectionFriday", "filterSelectionSaturday", "filterSelectionSunday", "filterSelectionMonday", "filterSelectionTuesday", "filterSelectionWednesday",
            "filterSelectionThursday", "filterSelectionFriday", "filterSelectionSaturday"};
    private static String[] shortDayNames = new String[]{"Sun", "Mon", "Tue", "Wed",
            "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed",
            "Thu", "Fri", "Saty"};

    FootballMobileStepDef footballMobileStepDef = new FootballMobileStepDef();

    @Then("^Correct options are available in (.*) Daily List bar$")
    public void correctOptionsAreAvailableInFootballDailyListBar(String sport) throws Throwable {
        if (sport.toLowerCase().contains("football")) {
            Calendar calendar = Calendar.getInstance();

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            List<String> Dailylistdays = Arrays.asList(dailylistdays);
            List<String> shortdays = Arrays.asList(shortDayNames);
            navigateToRootElement();
            navigateToElementById("swiperNavigationWrapper");
            assertTrue(navigateToElementById("swiperNavigationList"));
            assertTrue(buildListByClassName("filter-list__item"));
            System.out.println(getListSize());
            for (int i = 0; i < getListSize() - 1; i++) {
                navigateToListElement(i);
                navigateToChild();

                if (i == 0) {
                    assertTrue(getAttribute("id").contains("filterSelectionToday"));
                } else {

                    assertTrue(getAttribute("id").contains(Dailylistdays.get(dayOfWeek)));
                    assertTrue(getAttribute("data-short-name").contains(shortdays.get(dayOfWeek)));
                    dayOfWeek = dayOfWeek + 1;
                }

            }
        } else {
            navigateToRootElement();
            navigateToElementById("swiperNavigationWrapper");
            assertTrue(navigateToElementById("swiperNavigationList"));
            assertTrue(buildListByClassName("filter-list__item"));
            for (int i = 0; i < getListSize() - 1; i++) {
                navigateToListElement(i);
                navigateToChild();
                if (i == 0) {
                    assertTrue(getAttribute("id").contains("filterSelectionToday"));
                } else {
                    assertTrue(getAttribute("id").contains("filterSelectionFuture"));

                }
            }

        }

    }

//    @And("^Today option is highlighted by default$")
//    public void todayOptionIsHighlightedByDefault() throws Throwable {
//
//        navigateToRootElement();
//        navigateToElementById("swiperNavigationWrapper");
//        assertTrue(navigateToElementById("swiperNavigationList"));
//        assertTrue(buildListByClassName("filter-list__item"));
//        System.out.println(getListSize());
//        navigateToListElement(0);
//        navigateToChild();
//        assertTrue(getAttribute("id").contains("filterSelectionToday"));
//        navigateToRootElement();
//        assertTrue(navigateToElementById("filterSelectionToday"));
//        assertTrue(isActive());
//    }

    @Then("^user can navigate trough all daily list options$")
    public void userCanNavigateTroughAllDailyListOptions() throws Throwable {

        Calendar calendar = Calendar.getInstance();
        //  Date date = calendar.getTime();
//        String curentDay = new SimpleDateFormat("EEEEE", Locale.ENGLISH).format(date.getTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        List<String> Dailylistdays = Arrays.asList(dailylistdays);
        List<String> shortdays = Arrays.asList(shortDayNames);
        navigateToRootElement();
        navigateToElementById("swiperNavigationWrapper");
        assertTrue(navigateToElementById("swiperNavigationList"));
        assertTrue(buildListByClassName("filter-list__item"));
        System.out.println(getListSize());
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            navigateToElementById("swiperNavigationWrapper");
            assertTrue(navigateToElementById("swiperNavigationList"));
            assertTrue(buildListByClassName("filter-list__item"));
            navigateToListElement(i);
            navigateToChild();
            saveCurrentElementPosition();

            if (i == 0) {
                assertTrue(getAttribute("id").contains("filterSelectionToday"));
            } else {

                String dayilistID = getAttribute("id");
                assertTrue(dayilistID.contains(Dailylistdays.get(dayOfWeek)));
                assertTrue(getAttribute("data-short-name").contains(shortdays.get(dayOfWeek)));
                restoreElementPosition();
                //click on element  fom daily list filter
                click();
                sleep(1000);
                //verify that selected element  is active on the daily list scroller
                navigateToRootElement();
                assertTrue(navigateToElementById(dayilistID));
                assertTrue(isActive());
                dayOfWeek = dayOfWeek + 1;

            }

        }

    }

    @Then("^View by filter working correctly on each daily list option$")
    public void viewByFilterWorkingCorrectlyOnEachDailyListOption() throws Throwable {

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        List<String> Dailylistdays = Arrays.asList(dailylistdays);
        List<String> shortdays = Arrays.asList(shortDayNames);
        navigateToRootElement();
        navigateToElementById("swiperNavigationWrapper");
        assertTrue(navigateToElementById("swiperNavigationList"));
        assertTrue(buildListByClassName("filter-list__item"));
        System.out.println(getListSize());
        for (int i = 0; i < getListSize(); i++) {
            navigateToRootElement();
            navigateToElementById("swiperNavigationWrapper");
            assertTrue(navigateToElementById("swiperNavigationList"));
            assertTrue(buildListByClassName("filter-list__item"));
            navigateToListElement(i);
            navigateToChild();
            saveCurrentElementPosition();

            if (i == 0) {
                assertTrue(getAttribute("id").contains("filterSelectionToday"));
            } else {

                String dayilistID = getAttribute("id");
                assertTrue(dayilistID.contains(Dailylistdays.get(dayOfWeek)));
                assertTrue(getAttribute("data-short-name").contains(shortdays.get(dayOfWeek)));
                restoreElementPosition();
                //click on element  fom daily list filter
                click();
                sleep(1000);
                //verify that selected element  is active on the daily list scroller
                navigateToRootElement();
                assertTrue(navigateToElementById(dayilistID));
                assertTrue(isActive());
                //verify  filter on football daily list working  correctly
                footballMobileStepDef.theUserClickOnTheViewByButton();
                footballMobileStepDef.viewByMenuContainsTwoOptionsTimeAndCompetition();
                footballMobileStepDef.defaultOptionIsCompetition();
                // change filter to time

                navigateToRootElement();
                assertTrue(navigateToElementByClassName("filterlist"));
                assertTrue(navigateToElementByClassName("-date "));
                click();
                sleep(1000);
                navigateToRootElement();
                assertTrue(navigateToElementByClassName("filterlist"));
                assertTrue(navigateToElementByClassName("-active "));
                assertTrue(getText().contains("Time") || getAttribute("data-sort-order").contains("date"));

                //verify that after changer  highlighted element from daily list is still active
                navigateToRootElement();
                assertTrue(navigateToElementById(dayilistID));
                assertTrue(isActive());

                //select competition from filter  and verify that is activ
                navigateToRootElement();
                assertTrue(navigateToElementByClassName("filterlist "));
                assertTrue(navigateToElementByClassName("-competition "));
                click();
                sleep(1000);
                navigateToRootElement();
                assertTrue(navigateToElementByClassName("filterlist "));
                assertTrue(navigateToElementByClassName("-competition "));
                assertTrue(isActive());

                //verify that after change  highlighted element from daily list is still active
                navigateToRootElement();
                assertTrue(navigateToElementById(dayilistID));
                assertTrue(isActive());

                dayOfWeek = dayOfWeek + 1;
            }
        }
    }

    @When("^the user selects future from day filter$")
    public void userSelectFutureFromDailyListBar() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("filterSelectionFuture"));
        click();
        navigateToRootElement();
        assertTrue(navigateToElementById("filterSelectionFuture"));
        assertTrue(isActive());
    }

    @When("^the user changes view filter to '(time|competition)'$")
    public void userChangeViewFilterFromCompetitionToTime(String option) throws Throwable {
        String viewByOption = "competition";

        if (option.equals("time")) {
            viewByOption = "date";
        }

        expandViewByFilter();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".filterlist__options__button.-" + viewByOption));
        click();
        sleep(2000);
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".filterlist__options__button.-" + viewByOption + ".-active"));

        collapseViewByFilter();
    }

    public void expandViewByFilter() {
        if (!isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(".filterlist"));
            click();
            sleep(1000);
        }
    }

    public void collapseViewByFilter() {
        if (!isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(".filterlist.-opened"));
            click();
            sleep(1000);
        }
    }

    @And("^the user changes view filter to competition$")
    public void userChangeViewFilterFromTimeToCompetition() throws Throwable {
        expandViewByFilter();

        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist"));
        assertTrue(navigateToElementByClassName("-competition "));
        click();
        sleep(2000);
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".filterlist__options__button.-competition"));
        assertTrue(isActive());

        collapseViewByFilter();
    }

//    @And("^Today option is highlighted$")
//    public void todayOptionIsHighlighted() throws Throwable {
//        todayOptionIsHighlightedByDefault();
//    }

    @And("^the option '(Today|Future)' in day filter is highlighted$")
    public void DayFilterOptionIsHighlighted(String option) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("filterSelection" + option));
        assertTrue(isActive());
    }

    @And("^the user navigates to any '(\\d+)' option in the day filter$")
    public void theUserNavigatesToAnyOtherDayInTheDayFilter(int dailylistOption) throws Throwable {
        Calendar calendar = Calendar.getInstance();
        dailylistOption=dailylistOption-1;
        navigateToRootElement();
        navigateToElementById("swiperNavigationWrapper");
        assertTrue(navigateToElementById("swiperNavigationList"));
        assertTrue(buildListByClassName("filter-list__item"));
        assertTrue(getListSize()>0);
        if(getListSize()<dailylistOption){
            dailylistOption=0;
        }
            navigateToRootElement();
            navigateToElementById("swiperNavigationWrapper");
            assertTrue(navigateToElementById("swiperNavigationList"));
            assertTrue(buildListByClassName("filter-list__item"));
            navigateToListElement(dailylistOption);
            navigateToChild();
            saveCurrentElementPosition();
                String dayilistID = getAttribute("id");
                restoreElementPosition();
                //click on element  fom daily list filter
                click();
                sleep(1000);
                //verify that selected element  is active on the daily list scroller
                navigateToRootElement();
                assertTrue(navigateToElementById(dayilistID));
                assertTrue(isActive());
            }

    @When("^the user clicks on back button$")
    public void theUserClicksOnBackButton() throws Throwable {
        sleep(1000);
       navigateToRootElement();
        assertTrue(buildListByPartialId("back-button"));
       assertTrue(navigateToListFirstElement());
        if(isHidden()){
            navigateToListElement(1);
        }
                click();
        waitSportsbook();
    }

}




