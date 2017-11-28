package util;

import com.williamhill.whgtf.properties.PropertyReader;

/**
 * Created by martin on 07/07/2016.
 */
public class Jenkins_Inputs extends PropertyReader{

    private static final String VERSION_OF_THE_SPORTSBOOK_API = "sportsbookApi";
    private static final String VERSION_OF_THE_SPORTSBOOK_STATIC = "sportsbookStatic";
    private static final String ENVIRONMENT_BEING_RUN = "env";

    public String getVersionOfTheApplicationAPI() {
        return System.getProperty(VERSION_OF_THE_SPORTSBOOK_API);
    }

    public String getVersionOfTheApplicationStatic() {
        return System.getProperty(VERSION_OF_THE_SPORTSBOOK_STATIC);
    }

    public String getEnvironmentBeingRun() {
        return System.getProperty(ENVIRONMENT_BEING_RUN);
    }

}
