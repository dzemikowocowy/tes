package stepdefs.shared;


import cucumber.api.Scenario;
import office.BackOfficeOxifeed;
import office.openbet.model.BackOfficeConstants;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import util.Timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mkoza on 24/02/2016.
 */

public class ScenarioContext {

    private static ScenarioContext instance = null;

    private ArrayList<Event> events;

    private Scenario scenario;

    private static final Logger logger = Logger
            .getLogger(ScenarioContext.class.getName());

    //private SquizFilters squizFilters;
    private Date timestamp;

    private ScenarioContext() {
        // Exists only to defeat instantiation.
    }

    public static ScenarioContext getInstance() {
        if(instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void setup(Scenario scenario) {
        this.events = new ArrayList<Event>();
        this.scenario = scenario;
    }

    /**
     * Method to be called to reset scenario context values.
     */
    public void cleanup() {
        try {
            for (Event event : getEvents()) {
                // Update events info for settlement
                event.setStartTime(Timer.getDate(Timer.DayFilter.Yesterday));
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_NO);
                BackOfficeOxifeed.getInstance().updateEvent(event);

                // Void selection results and settle markets
                for (Market market : event.getMarkets()) {
                        for (Selection selection : market.getSelections())
                        BackOfficeOxifeed.getInstance().voidSelectionResult(selection);
                    BackOfficeOxifeed.getInstance().settleMarket(market);
                }
                logger.info(String.format("event with id:%s queued for settlement", event.id));
            }
        } catch(Throwable e) {
            // the event has been settled by a step method
        }
        events = new ArrayList<Event>();

    }


    public Scenario getScenario() {
        return scenario;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    /*public SquizFilters getSquizFilters() {
        return this.squizFilters;
    }*/

    public Event getFirstEvent() {
        if(!events.isEmpty())
            return events.get(0);
        else
            throw new IllegalArgumentException("No events set in the scenario context.");
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void setEvents(List<Event> events) {
        this.events.addAll(events);
    }

    /*public void setSquizFilters(SquizFilters squizFilters) {
        this.squizFilters = squizFilters;
    }*/

    public Date getEnvironmentCurrentTime() {
        return timestamp;
    }




}
