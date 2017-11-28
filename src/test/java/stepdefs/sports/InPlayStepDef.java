package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.openbet.model.Event;
import org.apache.commons.lang3.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Created by Michal Koza on 09/07/15.
 */
public class InPlayStepDef extends SportsAutomationScriptingLanguage {

    private static int marketsInPlayNumber=0;
    private static int eventsOnPage=0;
    private static String url="";

    @And("^the user selects '(.*)' button from the In-Play Betting menu$")
    public void the_user_selects_All_Sports_button_from_the_In_Play_Betting_menu(String sportName) throws Throwable {

        navigateToRootElement();
        if(device.isEmpty()) {
            assertTrue(navigateToElementById("market-collections"));
            if (sportName.equals("All Sports")) {
                sportName = "in-play/all";
            } else {
                sportName = "in-play/" + sportName.toLowerCase();
            }
            //expand  sports navigation bar
            navigateToRootElement();
            assertTrue(navigateToElementById("market-collections"));
            assertTrue(navigateToElementByHref("/betting/en-gb/" + sportName));

            click();
            waitSportsbook();
        }else{
            assertTrue(navigateToElementById("contextual-navigation-menu"));
            if (sportName.equals("All Sports")) {
                sportName = "in-play/all";
            } else {
                sportName = "in-play/" + sportName.toLowerCase();
            }

            //expand  sports navigation bar
            navigateToRootElement();
            assertTrue(navigateToElementById("contextual-navigation-menu"));
            assertTrue(navigateToElementByHref("/betting/en-gb/" + sportName));
            click();
            waitSportsbook();
        }
    }

    @And("^the number next to All Sports link is the sum of all events listed below$")
    public void the_number_next_to_All_Sports_link_is_the_sum_of_all_events_listed_below() throws Throwable {
        if(device.isEmpty()){
            manageBrowser().setBrowserDimensions(1600, 900);
            int AllSports =0;
            int sumOfSports=0;
            navigateToRootElement();
            navigateToElementById("market-collections");
            buildListByClassName("sportmenu__link ");
            for (int i = 0; getListSize() > i; i++) {
                navigateToListElement(i);
                String sportNumber=getText();
                sportNumber = StringUtils.substringAfter(sportNumber,"(").replace(")","");
                if(i==0){
                    AllSports=AllSports+Integer.parseInt(sportNumber.trim());

                }
                else {
                    sumOfSports = sumOfSports + Integer.parseInt(sportNumber.trim());
                }
            }
            assertTrue(AllSports==sumOfSports);
        }else{
            int AllSports =0;
            int sumOfSports=0;
            navigateToRootElement();
            navigateToElementById("contextual-navigation-menu");
            buildListByClassName("contextual-nav__item");
            for (int i = 0; getListSize() > i; i++) {
                navigateToListElement(i);
                assertTrue(navigateToElementByClassName("events-count"));
                String sportNumber=getText();
                if(i==0){
                    AllSports=AllSports+Integer.parseInt(sportNumber.trim());

                }
                else {
                    sumOfSports = sumOfSports + Integer.parseInt(sportNumber.trim());
                }
            }
            assertTrue(AllSports==sumOfSports);


        }

    }

    @When("^the first event display flag is set to '(.*)'$")
    public void theFirstEventDisplayFlagIsSetToNo(String displayvalue) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        event.setDisplayed(displayvalue);
        getOxifeedHelper().updateEvent(event);
        waitAfterPDSUpdate();
        sleep(100000);
    }

    @Then("^the first event is displayed on the page$")
    public void the_first_event_is_displayed_on_the_page() throws Throwable {
        refresh();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(isDisplayed());
    }

    @Then("^the score should be updated$")
    public void theScoreShouldBeUpdated() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        buildListByClassName("btmarket");
         navigateToListElement(1);
        assertTrue(navigateToElementByClassName("btmarket__livescore-wrapper "));
        buildListByClassName("btmarket__livescore-item ");
        navigateToListElement(0);
        System.out.println(getText());
        assertTrue(getText().contains("1"));


    }


    @Then("^the score should be updated on Daily list$")
    public void theScoreShouldBeUpdatedOnDailyList() throws Throwable {
        scrollToTheBottom();
        waitSportsbook();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        buildListByClassName("btmarket");
        navigateToListElement(1);
        assertTrue(navigateToElementByClassName("btmarket__livescore-wrapper "));
        buildListByClassName("btmarket__livescore-item ");
        navigateToListElement(0);
        System.out.println(getText());
        assertTrue(getText().contains("1"));


    }

    @Then("^the starting Time is correct$")
    public void theStartingTimeIsCorrect() throws Throwable {

        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        buildListByClassName("btmarket");
        navigateToListElement(0);
        buildListByClassName("btmarket__name ");
        navigateToListElement(0);
        assertTrue(navigateToElementByClassName("wh-label "));
        assertTrue(validate(getText()));
    }



        public static final String TIMEMINANDSEC_PATTERN =
                "[0-5][0-9]:[0-5][0-9]";

        /**
         * Validate time in min:sec  format with regular expression
         * @param time time address for validation
         * @return true valid time fromat, false invalid time format
         */
        public boolean validate(final String time){
            Pattern pattern = Pattern.compile(TIMEMINANDSEC_PATTERN);

            Matcher matcher = pattern.matcher(time);
            return matcher.matches();

        }

    @And("^the starting Time is correcton Daily list$")
    public void theStartingTimeIsCorrectonDailyList() throws Throwable {
        scrollToTheBottom();
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        buildListByClassName("btmarket");
        navigateToListElement(0);
        buildListByClassName("btmarket__name ");
        navigateToListElement(0);
        assertTrue(navigateToElementByClassName("wh-label "));
        assertTrue(validate(getText()));
    }

    @And("^the user selects first sport in In-Play page$")
    public void theUserSelectsFirstSportInInPlayPage() throws Throwable {
       navigateToRootElement();
        assertTrue(navigateToElementById("contextual-navigation-menu"));
        assertTrue(buildListByClassName("contextual-nav__item"));
        assertTrue(navigateToListElement(1));
        navigateToChild();
        url=getAttribute("href");
         click();
        waitSportsbook();

    }

    @And("^the user selects first event$")
    public void theUserSelectsFirstEvent() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("in-play_canvas"));
        buildListByPartialId("OB_EV");
        assertTrue(navigateToElementByClassName("btmarket__link-name"));
        click();
        waitSportsbook();
        
    }

    @Then("^the user is the In-Play sport page$")
    public void theUserIsTheInPlaySportPage() throws Throwable {
    sleep(500);
        assertTrue(hasPartialURL(url));
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("header-panel__title"));
        assertTrue(getText().equalsIgnoreCase("All Sports In-Play"));
    }
}



