package util;

import cucumber.api.Pending;

/**
 * Created by israel on 10/02/2016.
 */
// We're deliberately not extending CucumberException (which is used to signal fatal errors)

@Pending
public class StepNotMetException extends RuntimeException {

    public static final String NO_SELECTIONS_IN_CONTEXT = "No selections in the scenario context. " +
            "Add a step like 'Given there is an in-play event with an active selection'.";

    public static final String NO_MARKETS_IN_CONTEXT = "No markets in the scenario context. " +
            "Add a step like 'Given there is an in-play event with an active market'.";

    public static final String NO_EVENTS_IN_CONTEXT = "No events in the scenario context. " +
            "Add a step like 'Given there is an in-play event with an active selection'.";

    public StepNotMetException() {
        this("TODO: implement me");
    }

    public StepNotMetException(String message) {
        super(message);
    }
}
