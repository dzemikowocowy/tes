package stepdefs.shared;

import com.williamhill.whgtf.testrunner.CucumberGlobalHooks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import office.BackOffice;

import static stepdefs.shared.SharedData.resetSharedData;

/**
 * Created by atanas on 02/12/14.
 */
public class SharedSteps {

    @Before
    public void before(Scenario scenario) {
        ScenarioContext.getInstance().setup(scenario);
        new CucumberGlobalHooks().before(scenario);
        resetSharedData();
    }
    @After
    public void after(Scenario scenario) {
        new CucumberGlobalHooks().after(scenario);
        if(SharedData.clearData) {
            ScenarioContext.getInstance().cleanup();
        }
        BackOffice.getInstance().cleanCupons();
        BackOffice.getInstance().quit();

    }
}