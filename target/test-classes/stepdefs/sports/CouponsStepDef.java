package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.openbet.SportId;
import office.BackOfficeOxifeed;
import office.openbet.model.BackOfficeConstants;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import util.Timer;

import java.util.List;

/**
 * Created by Michal Koza on 09/07/15.
 */
public class CouponsStepDef extends SportsAutomationScriptingLanguage {



    @Given("^delete new cupon$")
    public void delete_new_cupon() throws Throwable {
        getBackOfficeHelper().deleteCupon();
    }

    @Given("^add new coupon in (.*) with title (.*)$")
    public void add_new_coupon_with_title(String competition, String couponName) throws Throwable {
        getBackOfficeHelper().addNewCupon(competition, couponName, "1");
    }

    @And("^set up event market for coupon in (.*)$")
    public void setUpEventMarketForCouponIn(String competition) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        String marketID = (market.getId()).toString();
        getBackOfficeHelper().setMarketForCupon(event.getName(), marketID);
    }

    @Then("^the user (?:is on|goes to) the (.*) Couppons page$")
    public void the_user_is_on_the_footbal_Cuppons_page(String sportName) throws Throwable {
         navigateToPage1(sbUrl + "/sr-admin-set-white-list-cookies.html");
//     navigateToPage(sbUrl + String.format("/betting/en-gb/%s/coupons", sportName));
        navigateToCupponsPage(SportId.getSport(sportName));
        waitSportsbook();


    }

    @When("^the user selects the '(.*)' Sport$")
    public void The_user_selects_the_Sport(String sportName) throws Throwable {
        assertTrue(openSportPage(SportId.getSport(sportName)));
        waitSportsbook();
    }

    @Then("^the Coupons tab on the Context menu is available$")
    public void the_Coupons_tab_on_the_Context_menu_is_available() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("sidebar__group "));
        assertTrue(navigateToElementByClassName("navCoupons "));
        assertTrue(navigateToElementByHref("/betting/en-gb/football/coupons"));
        click();
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/football/coupons"));

    }

    @Then("^the list of Coupons for the sport is displayed$")
    public void the_list_of_Coupons_for_the_sport_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("coupons-nav"));
        waitSportsbook();

    }

    //temporary change
    @And("^the coupon is displayed on the list$")
    public boolean the_coupon_is_displayed_on_the_list() throws Throwable {
        boolean couponDisplayed=false;
        //get coupon data to confirm  that coupon on list is this which was created in step before
        String cuponId = getBackOfficeHelper().getCupponId();
        String cuponName = getBackOfficeHelper().getCuponName();
        System.out.println("to jest coupon  id "+cuponId);
        // by default  coupon name is Automation Test Coupon
        if (cuponName.isEmpty()) {
            logger.error("coupon is not created or  name of coupon was not found in back officehelper");
            assertTrue(couponDisplayed=false);
        }
//        maximum waitng time for coupons is set for 2 minutes , current  cache for coupons in microservies is every  minute
        for (int i=0;i<20;i++){
            navigateToRootElement();
            assertTrue(navigateToElementById("coupons-nav"));
            if (navigateToElementByHref("/betting/en-gb/football/coupons/competition/OB_CP" + cuponId)) {
                navigateToRootElement();
                assertTrue(navigateToElementById("coupons-nav"));
                navigateToElementByHref("/betting/en-gb/football/coupons/competition/OB_CP" + cuponId);
                if(getText().contains(cuponName)) {
                    couponDisplayed = true;
                    break;
                }
            } else{
                refresh();
                waitSportsbook();
                if(isSportsbookOnDesktop ()){
                    sleep(10000);
                }
                else{
                    sleep(30000);
                }

            }
        }
//        assertTrue(couponDisplayed);

        return couponDisplayed;
    }

    @When("^the user selects the coupon$")
    public void the_user_selects_a_coupon() throws Throwable {
        String cuponId = getBackOfficeHelper().getCupponId();
        String cuponName = getBackOfficeHelper().getCuponName();
        navigateToRootElement();
        assertTrue(navigateToElementById("coupons-nav"));
        if (!navigateToElementByHref("/betting/en-gb/football/coupons/competition/OB_CP" + cuponId)) {
            navigateToPage(sbUrl + String.format("/betting/en-gb/football/coupons/date/OB_CP%s", cuponId));
            waitSportsbook();
            logger.info("cuopn navigation  done my URL  - coupn  doesnt displayed on cooupons list ");
        } else {
            navigateToElementByHref("/betting/en-gb/football/coupons/competition/OB_CP" + cuponId);
            assertTrue(getText().contains(cuponName));
            click();
            waitSportsbook();


        }

    }

    @Then("^the coupon for the sport and market is displayed$")
    public void the_coupon_for_the_sport_and_market_is_displayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("market-group-title"));
        assertTrue(getText().contains(getBackOfficeHelper().getCuponName()));
    }

    @And("^the coupon is Ordered by competition$")
    public void the_coupon_is_Ordered_by_competition() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist"));
        assertTrue(navigateToElementByClassName("-active "));
        if (!getText().contains("Competition")&&isDisplayed()) {
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-competition "));
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-active "));
            assertTrue(getText().contains("Competition"));
        }
        else if(!isDisplayed()){

            navigateToRootElement();
            navigateToElementById("coupon-detail");
            assertTrue(navigateToElementByClassName("filterlist"));
            navigateToChild();
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-competition "));
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-active "));
            assertTrue(getAttribute("data-sort-order").contains("competition"));

        }else
            assertTrue(getText().contains("Competition")||getAttribute("data-sort-order").contains("competition"));
    }

    @And("^the coupon is Ordered by time$")
    public void the_filter_Ordered_by_time() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("filterlist"));
        assertTrue(navigateToElementByClassName("-active "));
        if (!getText().contains("Time")&&isDisplayed()) {
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-date "));
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-active "));
        }
        else if(!isDisplayed()){

            navigateToRootElement();
            navigateToElementById("coupon-detail");
            assertTrue(navigateToElementByClassName("filterlist"));
            navigateToChild();
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-date "));
            click();
            navigateToRootElement();
            assertTrue(navigateToElementByClassName("filterlist"));
            assertTrue(navigateToElementByClassName("-active "));
            assertTrue(getAttribute("data-sort-order").contains("date"));

        }else
            assertTrue(getText().contains("Time")||getAttribute("data-sort-order").contains("date"));
    }

    @Then("^the event contains the detail shown in the 'Coupon Detail' above$")
    public void the_event_contains_the_detail_shown_in_the_Coupon_Detail_above() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementByClassName("event__details"));
    }


    @And("^set up all events market for cupon$")
    public void set_up_all_events_market_for_cupon() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        if (!events.isEmpty()) {
            for (Event event : events) {
                // add the event to OB
                Market market = event.getFirstMarket();
                String marketID = (market.getId()).toString();
                getBackOfficeHelper().setMarketForCupon(event.getName(), marketID);
            }
        }

    }

    @And("^set up all events market for coupon$")
    public void set_up_all_events_market_for_coupon() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        if (!events.isEmpty()) {
            for (Event event : events) {
                // add the event to OB
                Market market = event.getFirstMarket();
                String marketID = (market.getId()).toString();
                getBackOfficeHelper().setMarketForCupon(event.getName(), marketID);
            }
        }

    }



    @And("^add new coupon in (.*) with order (.*) and title (.*)$")
    public void add_new_coupon_with_order_title_Test_Coupon_First(String competition, String order,String couponName) throws Throwable {
        getBackOfficeHelper().addNewCupon(competition,couponName,order);

    }

    @Then("^created coupon is displayed as first on the coupon list$")
    public void created_coupon_is_displayed_as_first_on_the_coupon_list() throws Throwable {
        String cuponName = getBackOfficeHelper().getCuponName();
        navigateToRootElement();
        buildListByClassName("marketmenu__section ");
        System.out.println(getListSize());
        navigateToListElement(0);
        navigateToElementByClassName("sportmenu__link");
        System.out.println(getAttribute("data-coupon-id"));
        assertTrue(getText().contains(cuponName));
    }

    @Then("^created coupon is displayed as last on the coupon list$")
    public void created_coupon_is_displayed_as_last_on_the_coupon_list() throws Throwable {
        String cuponName = getBackOfficeHelper().getCuponName();
        navigateToRootElement();
        buildListByClassName("marketmenu__section ");
        System.out.println(getListSize());
        navigateToListElement(getListSize()-1);
        navigateToElementByClassName("sportmenu__link");
        navigateToChild();
        System.out.println(getText());
        assertTrue(getText().contains(cuponName));
    }

    @Then("^the coupon is no longer displayed on the list$")
    public void the_coupon_is_no_longer_displayed_on_the_list() throws Throwable {
        boolean couponIsNotDisplay= false ;
        if (the_coupon_is_displayed_on_the_list()){
            for (int i=0;i<20;i++){
                refresh();
                waitSportsbook();
                if (!the_coupon_is_displayed_on_the_list()){
                    couponIsNotDisplay= true;
                    break;
                }
                sleep(10000);
            }
        } else {
            couponIsNotDisplay= true;
        }
        assertTrue(couponIsNotDisplay);
    }

    @And("^the event off flag set to yes$")
    public void the_event_off_flag_set_to_yes() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();

        if (!events.isEmpty()) {
            // add the event to OB
            for (Event event : events) {
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
            }

        }
    }

    @When("^the first event market is settled and Event off flag is set to '(.*)'$")
    public void the_first_event_market_is_settled(String arg) throws Throwable {

        Event event = getScenarioContext().getFirstEvent();
        event.setStartTime(Timer.getDate(Timer.DayFilter.Yesterday));
        switch (arg.toLowerCase()) {
            case "no":
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
                break;
            case "n/a":
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NA);
                break;
            case "yes":
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                break;
        }
        BackOfficeOxifeed.getInstance().updateEvent(event);
        for (Market market : event.getMarkets()) {
            for (Selection selection : market.getSelections())
                BackOfficeOxifeed.getInstance().voidSelectionResult(selection);
            BackOfficeOxifeed.getInstance().settleMarket(market);
        }


    }

    @And("^the second event is still visible under the coupon$")
    public void thesecondeventisstillvisibleunderthecoupon() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        String eventId = "OB_EV" + events.get(1).getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
    }



    @Then("^Correct components are displayed in football coupons menu Mobile view$")
    public void correctComponentsAreDisplayedInFootballCouponsMenuMobileView() throws Throwable {
      //Title bar with "Coupons" as title
        navigateToRootElement ();
        assertTrue (navigateToElementById ("coupons-nav"));
        assertTrue (navigateToElementByClassName ("header-panel__title"));
        assertTrue (getText ().contains ("Coupons"));
        //Table with available coupons
        navigateToRootElement ();
        assertTrue (navigateToElementById ("highlights-coupons"));
       buildListByClassName ("marketmenu__section ");
        assertTrue (getListSize ()>0);
    }

    @Then("^correct components are displayed in the coupons page$")
    public void correctcomponentsaredisplayedinthecouponspage() throws Throwable {
        //Title bar with "Coupons" as title
        navigateToRootElement ();
        assertTrue (navigateToElementById ("coupons-nav"));
        assertTrue (navigateToElementByClassName ("header-panel__title"));
        assertTrue (getText ().contains ("Coupons"));

        navigateToRootElement ();
        assertTrue (navigateToElementById ("highlights-coupons"));
        buildListByClassName ("marketmenu__section ");
        assertTrue (getListSize ()>0);
        assertTrue (navigateToListElement (0));

    }

    @And("^the user click on first coupon in football Menu$")
    public void theUserClickOnFirstCouponInFootballMenu() throws Throwable {
        navigateToRootElement ();
        assertTrue (navigateToElementById ("highlights-coupons"));
        buildListByClassName ("marketmenu__section ");
        assertTrue (getListSize ()>0);
        assertTrue (navigateToListElement (0));
        click ();
        sleep (1000);
    }

    /**
     * This method will check components in a coupons.
     * Mobile: Coupons as page title and back button. Coupon name and view by filter. Competition in a collapsible
     * header. Event details
     * Desktop: Coupon name as title and view by filter. Competition in a collapsible header. Event details
     */
    @And("^correct components are displayed in the coupon$")
    public void correctcomponentsaredisplayedinthecoupon() {

    }

    /**
     * This method will check event details in a coupon:
     * Market group, starting time, event name, odd buttons with odd values
     */
    @And("^correct event details in the coupon$")
    public void correcteventdetailsinthecoupon() {

    }

    /**
     * This method will check a coupon in the coupons section in Sport page
     */
    @And("^the coupons section is available in Sport page$")
    public void thecouponssectionisavailableinSportpage() {

    }

    /**
     * This method will check that a coupon is ordered using the default option set up in backoffice
     */
    @And("^the coupon is ordered using the default option$")
    public void thecouponisorderedusingthedefaultoption() {

    }

    /**
     * This method will check that certain market group is not displayed in a coupon
     */
    @And("^the market group (.*) is not longer displayed on the coupon$")
    public void themarketgroupMostRunsOutisnotlongerdisplayedonthecoupon() {

    }

    /**
     * This method will check that certain competition is not displayed in a coupon
     */
    @And("^the competition (.*) is not longer displayed on the coupon$")
    public void thecompetitionIndianPremierLeagueisnotlongerdisplayedonthecoupon() {

    }

    /**
     * This method will check the position of a coupon in the coupons list
     */
    @And("^change the coupon to be displayed (.*) in the list$")
    public void changethecoupontobedisplayedfirstinthelist() {

    }

    /**
     * This method will check the relative order of a group of coupons
     */
    @And("^the coupons are displayed on the list with correct order$")
    public void thecouponsaredisplayedonthelistwithcorrectorder() {

    }

    /**
     * This method will check the relative order of a group of coupons
     */
    @And("^change the order in the coupons$")
    public void changetheorderinthecoupons() {

    }

    /**
     * This method will check the relative order of a group of coupons
     */
    @And("^add the event in the coupon$")
    public void addtheeventinthecoupon() {

    }

    /**
     * This method will send the customer to the coupons page
     */
    @And("^the user navigates to the coupons page$")
    public void theusernavigatestothecouponspage() {

    }

}
