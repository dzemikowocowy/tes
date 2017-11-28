package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;

/**
 * Created by Mkoza on 14/04/16.
 */
public class AnimationsStepDef extends SportsAutomationScriptingLanguage {

    SportsStepDef sportsStepDef = new SportsStepDef();

    @And("^the selection button colour changes to blue$")
    public void selectionButtonColourChangeToBlue() throws Throwable {
        Thread.sleep(2000);
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(hasBackgroundColour("#248cb3"));
    }

    @Then("^the selection is active$")
    public void selectionIsActive() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(isActive() || !isDisabled());
    }

    @And("^the selection button colour changes to gray$")
    public void selectionButtonColourChangeToGray() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        sleep(2000);
        assertTrue(hasBackgroundColour("#dadfe5") || hasBackgroundColour("#b4bfc5"));
    }

    @Then("^the selection is inactive$")
    public void selectionIsInactive() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(!isActive() || !isDisabled());
        sleep(2000);
        assertTrue(hasBackgroundColour("#f2f2f2"));
    }

    @And("^the event is disabled$")
    public void theEventIsDisabled() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(getAttribute("class").contains("disabled"));
    }

    @And("^the market is disabled$")
    public void theMarketIsDisabled() throws Throwable {
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(getAttribute("class").contains("disabled"));
        sleep(2000);
    }

    @Then("^the first Selection is inactive$")
    public void theFirstSelectionIsInactive() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(!isActive() || isDisabled());
        assertTrue(hasBackgroundColour("#f2f2f2"));
    }

    @Then("^selection '(.*)' animation displayed$")
    public void selectionIncreaseAnimationDisplayed(String value) throws Throwable {
        SportsStepDef sportsStepDef =new SportsStepDef();
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        if (value.contains("increase")) {
          assertTrue(sportsStepDef.waitForConditionToBeTrue(hasBackgroundColour("#a0d69f"),8));
            assertTrue(hasBackgroundColour("#a0d69f"));
            assertTrue(getAttribute("data-odds").contains(getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getPrice()));
        } else {
         assertTrue(sportsStepDef.waitForConditionToBeTrue(hasBackgroundColour("#ff9999"),8));
          //  assertTrue(hasBackgroundColour("#ff9999"));
            assertTrue(getAttribute("data-odds").contains(getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getPrice()));
        }
    }

    @And("^Goal animation displayed$")
    public void goalAnimationDisplayed() throws Throwable {
        navigateToRootElement();
    }

    @Then("^the first Selection added to betslip is inactive$")
    public void theFirstSelectionAddedToBetslipIsInactive() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(getAttribute("class").contains("disabled"));
        assertTrue(hasBackgroundColour("#248cb3"));
    }

}


