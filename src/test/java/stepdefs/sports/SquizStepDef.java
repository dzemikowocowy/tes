package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import office.openbet.model.Event;
import squiz.Squiz;
import squiz.SquizContentEditorPage;
import squiz.SquizDisplayConfigurationPage;
import stepdefs.shared.SharedData;
import util.Timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by Javier Garcia on 10/05/16.
 */

public class SquizStepDef extends SportsAutomationScriptingLanguage {



    @And("^event is enabled in Squiz for (Highlights|In-Play Highlights) and sport '(.*)'$")
    public void event_is_enabled_to_be_displayed_in_squiz(final String type, final String sport) throws Throwable {

        Event event = getScenarioContext().getFirstEvent();
        Calendar calendar = Calendar.getInstance();
        Squiz squizPage = new Squiz();
        squizPage.load();
        sleep(2000);
        SquizContentEditorPage contentEditorPage = squizPage.login();
        sleep(2000);
        SquizDisplayConfigurationPage displayConfigurationPage = contentEditorPage.clickOnLinkByDisplayedName(sport);
        sleep(2000);

        // Here we store the Sport that we used
        SharedData.squiz_sport_used = sport;

        displayConfigurationPage.clickOnTabByName(type);
        sleep(1000);

        // Here we store the Type that we used
        SharedData.squiz_type_used = type;

        // calculate start and end dates for event search
        Date eventDate = Timer.getDate(event.getStartTime());

        calendar.setTime(eventDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, +2);
        String endDate = dateFormat.format(calendar.getTime());

        displayConfigurationPage.setStartDate(startDate);
        // Here we store the Start Date that we used
        SharedData.squiz_start_date_used = startDate;

        displayConfigurationPage.setEndDate(endDate);
        // Here we store the End Date that we used
        SharedData.squiz_end_date_used = endDate;

        boolean isDisplayed = false;
        int attempts = 0;
        do {
            displayConfigurationPage.clickOnGetEventsButton();
            isDisplayed = displayConfigurationPage.isEventDisplayed(event.getPdsId());
            if(!isDisplayed) {
                Timer.sleep(10, TimeUnit.SECONDS);
                attempts++;
            }
        } while(!isDisplayed && attempts < 30) ;


        if(!isDisplayed) {
            throw new Exception("The event is not displayed on Squiz after 300 secs.");
        }

        sleep(2000);

        displayConfigurationPage.setUnassignedEventStartDate(event.getPdsId(),"01/01/2016");
        displayConfigurationPage.setUnassignedEventEndDate(event.getPdsId(),"01/01/2116");
        displayConfigurationPage.setHoursStart(event.getPdsId(),"00");
        displayConfigurationPage.setMinutesStart(event.getPdsId(),"00");
        displayConfigurationPage.setHoursEnd(event.getPdsId(),"00");
        displayConfigurationPage.setMinutesEnd(event.getPdsId(),"00");


        displayConfigurationPage.clickOnAddEventButton();
        sleep(1000);
        displayConfigurationPage.clickOnSaveButton();
        sleep(2000);
        displayConfigurationPage.clickOnPushJSONButton();

    }


    /**
     * This method will remove all the Events on the "getScenarioContext" that were used on the Squiz.
     *  Method in Beta, not in use yet.
     *
     * @throws InterruptedException
     */
    private void cleanAllTheEventsOnSquiz() throws InterruptedException {

        // Here we navigate to the Squiz Page
        Squiz squizPage = new Squiz();
        squizPage.load();
        sleep(2000);

        //We login
        SquizContentEditorPage contentEditorPage = squizPage.login();
        sleep(2000);

        // We go to the Sport that we used before
        SquizDisplayConfigurationPage displayConfigurationPage = contentEditorPage.clickOnLinkByDisplayedName(SharedData.squiz_sport_used);
        sleep(2000);

        // Also to the Type used before
        displayConfigurationPage.clickOnTabByName(SharedData.squiz_type_used);
        sleep(1000);

        // Here we set the previous dates as well
        displayConfigurationPage.setStartDate(SharedData.squiz_start_date_used);
        displayConfigurationPage.setEndDate(SharedData.squiz_end_date_used);

        // Click on the Get Events button
        displayConfigurationPage.clickOnGetEventsButton();

        // Here we delete the events that we used
        ArrayList<Event> events = getScenarioContext().getEvents();

        for(int i=0; i<events.size(); i++){
            navigateToRootElement();
            navigateToElementByXpath("//div[contains(@id,'" + events.get(i).getId() + "')]//i");
            click();
        }
    }
}


