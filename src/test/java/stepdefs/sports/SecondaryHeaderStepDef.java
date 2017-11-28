package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.openbet.model.Event;
import office.openbet.model.Selection;
import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.WebDriverException;
import stepdefs.shared.ScenarioHardcodedData;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import util.SportsNavigation;
import util.SportsNavigationHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Created by mkoza 06/04/2016
 */
public class SecondaryHeaderStepDef extends SportsAutomationScriptingLanguage {

    // Classes initializations:
    protected SportsNavigation navigation = SportsNavigationHelper.getNavigation();


    /**
     * Simple step to verify where if the Secondary Header should be displayed or not.
     * @param value
     * @throws Throwable
     */
    @Then("^the second Header it '(is|is not)' displayed$")
    public void secondHeaderDisplay(String value) throws Throwable {

        if(value.equals("is")){

            // Here we veirfy if the Back button is Displayed
            navigateToRootElement();
            assertTrue(navigateToElementByCSS("#back-button"));
            assertTrue(isDisplayed());

            // Here we veirfy if the Title is Displayed
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(".header-panel__title.cap"));
            assertTrue(isDisplayed());

        }else{
            try {

                // This case is for Home Page Desktop and Mobile
                navigateToRootElement();
                assertFalse(navigateToElementByCSS("#back-button"));

            }catch (AssertionError e){

                // And this is for the rest of the pages on Desktop
                navigateToRootElement();
                assertTrue(navigateToElementByCSS("#back-button"));
                assertFalse(isDisplayed());
            }
        }
    }

    /**
     * Simple step to verify if the Given Header is being displayed on the current page Secondary Header Title
     * @param value
     * @throws Throwable
     */
    @Then("^the second Header Title is '(.*)'$")
    public void secondHeaderTitleIs(String value) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".header-panel__content-inner h1"));
        assertTrue(getText().equals(value));
    }

    /**
     * This step will click on the Back Button from the Secondary Header the number of given times passed on the step.
     * @param times
     * @throws Throwable
     */
    @Then("^the user clicks on Second Header Back Button '(.*)' times$")
    public void secondHeaderClickOnBackButton(int times) throws Throwable {
        for(int i=0; i<times; i++) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS("#back-button"));
            click();
            sleep(1000);
        }
    }




}
