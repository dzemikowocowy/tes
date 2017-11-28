package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.BackOfficeOxifeed;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;

import java.util.List;
import java.util.Map;

/**
 * Created by mkoza 31/05/2016
 */
public class MarketCollectionsStepDef extends SportsAutomationScriptingLanguage {

    private int checkMarketInMarketcolection(String markeColection, List<Event> events, String markettype, int foundedMarketsnumber) {
        int result = 0;
        sleep(5000);
        refresh();
        waitSportsbook();
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(markeColection)) {
                for (Event event : events) {
                    for (Market market : event.getMarkets()) {
                        if (market.getName().contains(markettype)) {
                            click();
                            waitSportsbook();
                            navigateToRootElement();
                            assertTrue(navigateToElementById("OB_MA" + market.getId()));
                            result = foundedMarketsnumber + 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Then("^the following markets displayed in correct Market Collections$")
    public void theFollowingMarketsDisplayedInCorrectMarketCollections(DataTable marketTable) throws Throwable {
        sleep(5000);
      refresh();
        waitSportsbook();
        int foundedMarketsnumber = 0;
        int numberofMarkets;
        List<Event> events = getScenarioContext().getEvents();
        List<Map<String, String>> marketRows = marketTable.asMaps(String.class, String.class);
        numberofMarkets = marketRows.size();
        for (Map<String, String> marketRow : marketRows) {
            String markettype = marketRow.get("market_type").replace('\\', '|');
            String marketCollection = marketRow.get("MarketCollections");
            foundedMarketsnumber = checkMarketInMarketcolection(marketCollection, events, markettype, foundedMarketsnumber);
        }
        assertTrue(foundedMarketsnumber == numberofMarkets);

    }

    /**
     * This method selects certain collection and verify the collection is displayed as selected
     */
    @And("^the user selects the market collection '(.*)'$")
    public void theUserSelectsTheMarketCollectionOthers(String marketCollection) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(marketCollection)) {
                saveCurrentElementPosition();
                click();
                break;
            }
        }
        restoreElementPosition();
        navigateToChild();
        assertTrue(isActive());
    }

    /**
     * This method will verify that certain collection is displayed as selected
     */
    @Then("^the market collection '(.*)' is selected$")
    public void theMarketCollectionIsSelected(String marketCollection ) throws Throwable {
        Boolean correctCollectionIsActive=false;
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(marketCollection)) {
                navigateToChild();
                assertTrue(isActive());
                correctCollectionIsActive=true;
                break;
            }
        }
        assertTrue(correctCollectionIsActive);
    }

    /**
     * This method will verify that certain collection is not displayed
     */
    @And("^the market collection '(.*)' is not displayed$")
    public void theMarketCollectionOthersIsNotDisplayed(String marketCollection) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(marketCollection)) {
                saveCurrentElementPosition();
                assertFalse(isDisplayed());
                break;
            }
        }
    }


    @And("^the user selects market collection '(.*)'$")
    public void theUserSelectsMarketCollection(String marketCollection) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(marketCollection)) {
                saveCurrentElementPosition();
                click();
                break;
            }
        }
    }

    /**
     * This method will verify that certain collection is displayed
     */
    @And("^the market collection '(.*)' is displayed$")
    public void theMarketCollectionIsDisplayed(String marketCollection) throws Throwable {
        Boolean collectionIsDisplayed=false;
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getText().contains(marketCollection)) {
                saveCurrentElementPosition();
                collectionIsDisplayed=true;
                break;
            }
        }
        assertTrue(collectionIsDisplayed);
    }

    /**
     * This method will check collection scroller is displayed in event page
     */
    @Then("^the collection scroller is displayed$")
         public void theMarketCollectionScrollerIsDisplayed() throws Throwable {
        int a=0;
        int i;
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for(i=0 ; getListSize() > i; i++){
            navigateToListElement(i);
            if(getAttribute("class").contains("displayNone")){
                a=a+1;
            }
        }
        assertFalse(i==a);

    }

    /**
     * This method will check the message that should be displayed when collection is empty
     */
    @Then("^the message \"no markets in this collection available\" is displayed$")
    public void theMessageNoMarketsInThisCollectionAvailableIsDisplayed(){

    }

    /**
     * This method will check collection scroller is not displayed in event page
     */
    @Then("^the collection scroller is not displayed$")
    public void theCollectionScrollerIsNotDisplayed() throws Throwable {
        int a=0;
        int i;
        navigateToRootElement();
        assertTrue(navigateToElementById("marketCollectionItemsList"));
        buildListByClassName("filter-list__item ");
        for(i=0; getListSize() > i; i++){
            navigateToListElement(i);
            if(getAttribute("class").contains("displayNone")){
                a=a+1;
            }
        }
        assertTrue(i==a);
    }

}


