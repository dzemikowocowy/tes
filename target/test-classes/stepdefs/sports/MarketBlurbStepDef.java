package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.Then;
import stepdefs.shared.ScenarioContext;
import stepdefs.shared.SharedData;

/**
 *  Created by Michal Koza on 09/07/15.
 */
public class MarketBlurbStepDef extends SportsAutomationScriptingLanguage {




    @Then("^On Home Page the correct blurb message displayed$")
    public void theCorrectBlurbMessageDisplayedhomePage() throws Throwable {
        String blurbmassege= SharedData.blurbmessage;
        String eventID="OB_EV"+ScenarioContext.getInstance().getFirstEvent().getId();

        navigateToRootElement();
        navigateToElementById("match-highlights");
        assertTrue(navigateToElementById(eventID));
        assertTrue(navigateToElementByClassName("btmarket__blurb"));
        assertTrue(getText().equals(blurbmassege));
    }

    @Then("^the correct blurb message displayed")
    public void theCorrectBlurbMessage() throws Throwable {
        String blurbmassege= SharedData.blurbmessage;
        String eventID="OB_EV"+ScenarioContext.getInstance().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventID));
        assertTrue(navigateToElementByClassName("btmarket__blurb"));
        assertTrue(getText().equals(blurbmassege));


    }
}