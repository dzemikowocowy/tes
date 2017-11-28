package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.squiz.SquizCarouselData;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriverException;
import stepdefs.shared.Selectors;
import util.SquizVirtualHelper;

import java.util.List;

import java.util.HashMap;

/**
 * Created by Michal Koza on 09/07/15.
 */
public class HomePageStepDef extends SportsAutomationScriptingLanguage {

    private static String bannerlink = "";
    private NavigationStepDef navigationStepDef = new NavigationStepDef();
    SportsStepDef sportsStepDef = new SportsStepDef();
    private BetslipStepdefs betslipStepdefs = new BetslipStepdefs();

    @Given("^the user is on the William Hill Home page$")
    public void the_user_is_on_the_William_Hill_Home_page() throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookies.html");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb");
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb"));
    }

    @And("^user open the Betslip$")
    public void openBetslip() throws Throwable {
        navigateToRootElement();
        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            assertTrue(navigateToElementByCSS("#open-betslip"));
            click();
        } else {
            // Take the footer method to click Betslip and put it here once its merged.
            assertTrue(navigateToElementByCSS(".toolbar__icon.icon-bet-slip"));
            click();
        }
    }

    @And("^the betslip is open$")
    public void the_betslip_is_open() throws Throwable {
        if (isSportsbookOnDesktop()) {

            navigateToRootElement();
            navigateToElementById("open-betslip");
            if (isDisplayed())
                // test running on full desktop size
                click();
            else {
                // test running on small display
                navigateToRootElement();
                assertTrue(navigateToElementByClassName("localnavigation__right"));
                assertTrue(navigateToElementByClassName("-primary"));
                click();
            }
        } else {

            navigateToRootElement();
            navigateToElementById("mobile-betslip-link");
            click();

        }

        sleep(500);

//        assertTrue(navigateToRootElement());
//        if(device != null){
//            assertTrue(navigateToElementByClassName("localnavigation__right"));
//            assertTrue(navigateToElementByCSS("button.localnavigation__button.-option.toggle-betslip.-primary"));
//        }
//        else{
//            assertTrue(navigateToElementById("open-betslip"));
//        }
//        assertTrue(click());
//        assertTrue(sleep(500));
//        assertTrue(hasAttributeValue("class", "-active"));
    }

    @And("^the user has logged into sportsbook$")
    public void the_user_has_logged_into_sportsbook_as_a_user() throws Throwable {

        String chmSelector = Selectors.sbSelectors.get("button.coathangerman");
        String chmLiSelector = Selectors.sbSelectors.get("button.coathangerman.logged-in");

        sleep(5000);

        navigateToRootElement();
        assertTrue(navigateToElementByCSS(chmSelector));
        assertTrue(click());

        navigateToRootElement();

        assertTrue(navigateToElementByName("username"));
        assertTrue(clear());
        logger.info("Logging in as " + sportsbookUsername);
        assertTrue(typeIn(sportsbookUsername));

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByName("password"));
        assertTrue(clear());
        assertTrue(typeIn(sportsbookPassword));

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("inline-inputs"));
        assertTrue(navigateToElementByTag("button"));
        click();
        navigateToRootElement();
        waitForVisibilityOfElementByCss(chmLiSelector);
    }

    /**
     * On this method we verify that the user has logged in successfully to the application by verifying that the
     * user balance is displayed.
     * @throws Throwable
     */
    @And("^the user verify that was successfully logged in$")
    public void loggedInVerification() throws Throwable {

        navigateToRootElement();

        if (isSportsbookOnDesktop()) {
            assertTrue(navigateToElementByCSS("#userBalance"));
        } else {
            assertTrue(navigateToElementByCSS("#balanceLink"));
        }

        assertTrue(isDisplayed());
        assertFalse(isHidden());
        assertTrue(getText().contains("£"));
    }

    /**
     * Simple Step to click on the Logout button.
     * @throws Throwable
     */
    @And("^the user logs out from Sportsbook$")
    public void logOut() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".icon-accountLI"));
        click();
        sleep(1000);
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#logoutLink"));
        click();
        sleep(1000);
    }

    /**
     * On this Step we verify that the user was successfully logged out by checking if the Balance is displayed
     * or not.
     * @throws Throwable
     */
    @And("^the user verify that was successfully logged out$")
    public void loggedOutVerification() throws Throwable {
        refresh();
        navigateToRootElement();
        if (isSportsbookOnDesktop()) {
            assertTrue(navigateToElementByCSS("#userBalance"));
            assertTrue(isHidden());
        } else {
            assertTrue(navigateToElementByCSS("#balanceLink"));
            assertTrue(isHidden());
        }
    }

    @Then("^the user select Home from the sports header menu$")
    public void the_user_select_home_page_from_the_header_navigation() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("localnavigation__nonmobile"));
        assertTrue(navigateToElementByClassName("cw"));
        navigateToChild();
        assertTrue(getText().equals("Home"));
        click();
        waitSportsbook();
    }

    @Then("^the user is on the William Hill Sportsbook Home Page$")
    public void the_user_is_on_the_William_Hill_Sportsbook_Home_Page() throws Throwable {
        assertTrue(hasPartialURL("en-gb"));
    }

    @When("^he selects Horse Racing Banner$")
    public void he_selects_horseRacing_banner() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("publishing-banner__image"));
        navigateToChild();
        bannerlink = getAttribute("href");
        click();
        waitSportsbook();
        assertTrue(hasPartialURL(bannerlink));

    }

    @Then("^the user is on the sport page related with banner$")
    public void the_user_is_on_the_sport_page_related_with_banner() throws Throwable {
        // Express the Regexp above with the code you wish you had

    }

    @When("^user click in View All In-Play button$")
    public void user_click_in_View_All_In_Play_button() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("in-play-link"));
        click();
        waitSportsbook();
    }


    private void change_first_selection_price(String price) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        Selection selection = market.getFirstSelection();
        selection.setPrice(price);
        getOxifeedHelper().updateSelection(selection);
        waitAfterPDSUpdate();

    }

    @Then("^the user is on the selected sport page$")
    public void the_user_is_on_the_selected_sport_page() throws Throwable {

    }

    @When("^the user click in the View All link from the '(.*)' sports panel footer$")
    public void the_user_click_in_the_View_All_sport_link_from_the_non_in_play_sports_panel_footer(String eventType) throws Throwable {
        Boolean element = false;
        String sportName = "";
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("tabs__panels"));
        assertTrue(buildListByClassName("tabs__panels-panel"));
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getAttribute("data-panel").contains("panel-" + eventType)) {
                // navigateToElementById("sport-events");
                buildListByClassName("sectionfooter_text");
                for (int n = 0; getListSize() > n; n++) {
                    navigateToListElement(n);
                    sportName = getText();
                    if (isDisplayed()) {
                        click();
                        waitSportsbook();
                        element = true;
                        break;
                    }
                }
            }

            if (element) {
                break;
            }
        }

        assertTrue(hasPartialURL(sportName.toLowerCase().replaceAll("view all ", "").replaceAll(" ", "-")));
    }


    @When("^th user click in  first View Full Cards button$")
    public void th_user_click_in_first_View_Full_Cards_button() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("racing-next-events"));
        assertTrue(buildListByClassName("race-event--highlights"));
        assertTrue(navigateToListElement(0));
        assertTrue(navigateToElementByClassName("racecard-footer"));
        assertTrue(navigateToElementByClassName("racecard__button "));
        String fullcard = getAttribute("href");
        click();
        waitSportsbook();
        assertTrue(hasPartialURL(fullcard));

    }

    @And("^the user change a price format to '(.*)'$")
    public void the_user_change_a_price_format_to(String arg1) throws Throwable {
        boolean priceFormat = false;
        navigateToRootElement();
//        assertTrue(navigateToElementByClassName("prefooter__price-filter"));
        assertTrue(navigateToElementByClassName("odds-format__toggle"));
        assertTrue(click());
        navigateToRootElement();
        assertTrue(navigateToElementById("odds-format-filter"));
        assertTrue(buildListByClassName("odds-format__button"));
        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            if (getAttribute("data-odds-format").equals(arg1.toLowerCase())) {
                priceFormat = true;
                click();
                waitSportsbook();
                break;
            }

        }

        assertTrue(priceFormat);
    }

    @Then("^the selection format price displayed as '(.*)'$")
    public void the_selection_format_price_displayed_as_images_homepage_homePage_png(String imagePath) throws Throwable {

        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementById(marketId));
        assertTrue(validateCurrentElementByImage(imagePath));
    }


    @And("^the user click in William Hill Logo$")
    public void the_user_click_in_William_Hill_Logo() throws Throwable {
        navigateToRootElement();
        navigateToElementById("header");
        navigateToElementById("cw");
        assertTrue(navigateToChild());
        click();
        waitSportsbook();
    }

    @When("^he selects Top Banner$")
    public void he_selects_Top_Banner() throws Throwable {
        navigateToRootElement();
        buildListByClassName("banners-container");
        navigateToListElement(0);
        navigateToChild();
        bannerlink = getAttribute("href");
        System.out.println(bannerlink);
        click();
        waitSportsbook();
//        assertTrue(hasPartialURL(bannerlink));
    }

    @When("^he selects Second Banner$")
    public void he_selects_Second_Banner() throws Throwable {
        navigateToRootElement();
        buildListByClassName("banners-container");
        navigateToListElement(1);
        navigateToChild();
        bannerlink = getAttribute("href");
        System.out.println(bannerlink);
        click();
        waitSportsbook();
//       assertTrue(hasPartialURL(bannerlink));

    }


    @Then("^the (in-play|highlights|next of) section (is|is not) displayed$")
    public void an_section_is_displayed(String section, String displayed) throws Throwable {

        boolean isDisplayed = "is".compareToIgnoreCase(displayed) == 0;
        String selection = "";
        switch (section) {
            case "in-play":
                selection = ".inplay";
                break;

            case "highlights":
                selection = ".tabs__panels-panel";
                break;

            case "next of":
                selection = ".racing-next-events";
                break;

            case "left bar":
                selection = ".sidebar__group";
                break;

            case "top bets":
                selection = ".tabs__panels";
                break;
        }

        if (isDisplayed) {
            assertTrue(navigateToElementByCSS(selection));
        } else {
            assertFalse(navigateToElementByCSS(selection));
        }

    }

    @Then("^home page is displayed$")
    public void pageIsDisplayed() throws Throwable {
        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            an_section_is_displayed("in-play", "is");
            navigateToRootElement();
            an_section_is_displayed("highlights", "is");
            navigateToRootElement();
            an_section_is_displayed("left bar", "is");
            navigateToRootElement();
            an_section_is_displayed("top bets", "is");
        } else {
            navigateToRootElement();
            an_section_is_displayed("top bets", "is");
            navigateToRootElement();
            carousel_is_displayed("is");
        }
    }

    @And("^the user clicks on the first event$")
    public void clickOnFirstEvent() throws Throwable {
        assertTrue(navigateToRootElement());
        Event event = getFirstEventToClick();
        getScenarioContext().addEvent(event);
        if (!isSportsbookOnDesktop()) {
            assertTrue(navigateToRootElement());
            assertTrue(navigateToElementByCSS("a[data-name='In Play']"));
            click();
        }
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByCSS("a[title='" + event.getName() + "']"));
        assertTrue(clickAndWaitSportsbook());
    }

    /**
     * This Step will take from the Scenario Context of the preious Step the Title and will assert it against the one
     * displayed on the current page.
     * @throws Throwable
     */
    @And("^the event page has the correct Title$")
    public void eventPageHasCorrectTitle() throws Throwable {
        String title = getScenarioContext().getFirstEvent().getName();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".header-panel__title"));
        assertEquals(getText(), title);
    }

    @And("^the event page has a Market block$")
    public void eventPageHasMarketBlock() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//div[contains(@class,'btmarket__actions')])[1]"));
    }

    public Event getFirstEventToClick() {

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//div[contains(@data-entityid,'OB_EV')])[1]"));
        String id = getAttribute("id");
        assertTrue(navigateToElementByXpath("(//div[contains(@data-entityid,'OB_EV')])[1]//a"));
        String name = getAttribute("title");

        return new Event(id, name);
    }

    @And("^the user clicks on the first event with active Selection$")
    public void clickOnFirstEventWithActiveSelection() throws Throwable {

        if (!isSportsbookOnDesktop()) {
            assertTrue(navigateToRootElement());
            assertTrue(navigateToElementByCSS("a[data-name='In Play']"));
            click();
        }

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//button[contains(@id, 'OB_OU') and not(contains(@class,'disabled'))])[1]/../../..//a"));
        click();

    }

    @When("^the price format is set to (Fraction|Decimal|American)$")
    public void the_price_format_is_set_to_odd_format(String oddFormat) throws Throwable {
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementByClassName("odds-format__toggle"));
        assertTrue(click());
        assertTrue(waitForPresenceOfElementByCss("#odds-format-filter .odds-format.-expanded"));

        assertTrue(sleep(200));

        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("odds-format-filter"));
        assertTrue(navigateToElementByCSS(".odds-format"));
        assertTrue(navigateToElementByCSS(String.format("button[data-odds-format='%s']", oddFormat.toLowerCase())));
        assertTrue(click());
        assertTrue(waitForPresenceOfElementByCss("#odds-format-filter .odds-format:not(.-expanded)"));

    }

    @Then("^the selection prices are displayed in (Fraction|Decimal|American)$")
    public void the_selection_prices_are_displayed_in(String oddFormat) throws Throwable {
        assertTrue(navigateToRootElement());
        String price;
        int counter = 0;
        assertTrue(buildListByCSS("[id^='OB_MA']:not(.displayNone) .betbutton__odds"));
        while (navigateToNextListElement()) {

            if (!(getText() == null)) {
                price = getText();
            } else {
                price = getAttribute("data-odds");
            }

            navigateToListElement(counter);

            if (oddFormat.equals("Fraction"))
                assertTrue("Expected a fractional price but got: " + price, price.matches("\\d+[/]\\d+|EVS")); // 23/4 or EVS
            else if (oddFormat.equals("Decimal"))
                assertTrue("Expected a decimal price but got: " + price, price.matches("\\d+[.]\\d+")); // 23.4
            else if (oddFormat.equals("American"))
                assertTrue("Expected an american price but got: " + price, price.matches("[+-]\\d+")); // +23.4 -23.4
            else
                throw new IllegalArgumentException("Valid values: 'Fraction' 'Decimal' 'American'");

            counter++;
        }
    }

    @And("^the carousel (is|is not) displayed$")
    public void carousel_is_displayed(String isDisplayed) {
        if (isDisplayed.equals("is")) {
            assertTrue(navigateToElementById("carousel"));
        } else {
            assertFalse(navigateToElementById("carousel"));
        }
    }

    @And("^verify Roulette redirection$")
    public void verifyRouletteRedirection() throws Throwable {
        try {
            NavigationStepDef navigationStepDef = new NavigationStepDef();
            navigationStepDef.switchToNewTab();
            sportsStepDef.pageRedirectionsVerifications("Roulette", "//body");
            navigationStepDef.switchToPreviousTab();
        } catch (WebDriverException | AssertionError | IndexOutOfBoundsException z) {
            assertTrue(navigationStepDef.checkAlert().contains("In order to play games you need to be logged in. Please log in and try again"));
        }
    }

    @And("^verify Blackjack redirection$")
    public void verifyBlackJackRedirection() throws Throwable {
        try {

            NavigationStepDef navigationStepDef = new NavigationStepDef();
            navigationStepDef.switchToNewTab();
            sportsStepDef.pageRedirectionsVerifications("BlackjackMobile", "//body");
            navigationStepDef.switchToPreviousTab();

        } catch (WebDriverException | AssertionError z) {
            assertTrue(navigationStepDef.checkAlert().contains("In order to play games you need to be logged in. Please log in and try again"));
        }
    }

    @And("^verify Wish JP redirection$")
    public void verifyWishJPRedirection() throws Throwable {

        NavigationStepDef navigationStepDef = new NavigationStepDef();
        navigationStepDef.switchToNewTab();

        try {
            sportsStepDef.pageRedirectionsVerifications("GenieJackpotsMobile", "//body");
        } catch (AssertionError e) {
            sportsStepDef.pageRedirectionsVerifications("WishUponAJackpotMobile", "//body");
        }
        navigationStepDef.switchToPreviousTab();
    }

    /**
     * This is the form for the Registration Step, in which we will just need to send an email and a username.
     * @param email
     * @param userName
     */
    public void registerNewAccount(String email, String userName) {

        navigateToFrameByClass("mfp-iframe");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-firstName"));
        typeIn("First Name");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-lastName"));
        typeIn("Last Name");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#dobSelectorDay"));
        typeIn("1");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#dobSelectorMonth"));
        typeIn("01");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#dobSelectorYear"));
        typeIn("1980");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-email"));
        typeIn(email);

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-mobile"));
        typeIn("0848656566");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-countryCode"));
        selectDropdownOptionByValue("229");

//        navigateToRootElement();
//        assertTrue(navigateToElementByCSS("#enter-manually"));
//        click();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-street1"));
        typeIn("Address Line");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-city"));
        typeIn("Town");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-postcode"));
        typeIn("RG1");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-username"));
        typeIn(userName);

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-password"));
        typeIn("Tester123");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-response"));
        typeIn("Ronaldinho");

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("a[ng-click*='no']"));
        click();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#input-acceptTerms"));
        click();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#button-createAccount"));
        click();
    }

    /**
     * Just a random String generator.
     * @param lenght
     * @return
     */
    public String generateRandomString(int lenght) {
        return RandomStringUtils.randomNumeric(lenght);
    }

    /**
     * On this method we will register a new user on Sportsbook.
     * @throws Throwable
     */
    @And("^the user register a new account on Sportsbook$")
    public void accountCreation() throws Throwable {

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#joinLink"));
        click();

        registerNewAccount("test_" + generateRandomString(5) + "@williamhill.com", "tester" + generateRandomString(5));

        sleep(10000);

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#close"));
        click();

        sleep(5000);
    }

    /**
     * This method will just add a single active selection to the Betslip.
     * @throws Throwable
     */
    @And("^the user adds the first available selection to the Betslip$")
    public void addFirstAvailableSelectionToBetslip() throws Throwable {

        // On the german site, PP2 is taking more time to load Live than what it should.
        sleep(10000);

        navigateToRootElement();
        assertTrue(buildListByXpath(Selectors.ACTIVE_SELECTIONS));
        sleep(2000);

        while (navigateToNextListElement()) {
            click();
            if (betslipStepdefs.theBetslipIconHasXbets("1")) {
                break;
            }
        }
    }

    /**
     * This method will add multiple available selections to the Betslip.
     * Note: might add also from the same market.
     * @param numberOfSelections
     * @throws Throwable
     */
    @And("^the user adds '(.*)' selections to the Betslip$")
    public void addAvailableSelectionsToBetslip(String numberOfSelections) throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByXpath(Selectors.ACTIVE_SELECTIONS));
        sleep(2000);

        while (navigateToNextListElement()) {
            click();
            if (betslipStepdefs.theBetslipIconHasXbets(numberOfSelections)) {
                break;
            }
        }
    }

    /**
     * On this Method we add "X" amount of selections to the betslip from different markets in order to get for example a Double bet
     * on the Betslip.
     * @param numberOfSelections
     * @throws Throwable
     */
    @And("^the user adds '(.*)' selections to the Betslip from different markets$")
    public void addAvailableSelectionsToBetslipFromMultipleMarkets(String numberOfSelections) throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByXpath(Selectors.ACTIVE_SELECTIONS));

        String previousMarket = "";

        for(int i = 0; i<getListSize(); i++) {

            navigateToRootElement();
            navigateToListElement(i);

            if(!previousMarket.equals(getAttribute("data-market"))){
                click();
                if (betslipStepdefs.theBetslipIconHasXbets(numberOfSelections)) {
                    break;
                }
            }
            previousMarket = getAttribute("data-market");
        }
    }


    @Then("^carousel elements setup in squiz displayed on home page carousel$")
    public void carouselElementsSetupInSquizDisplayedOnHomePageCarousel() throws Throwable {
            String caruoseltype = "";
            int carouselelemntsAmount = 0;
            List<SquizCarouselData> carouselElemntsDisplayed = getVirtualSquizHelper().getEnabledCarouselelements();
            Boolean elementfoud=false;

            navigateToRootElement();
            assertTrue(buildListByCSS("#carousel li"));
            assertTrue(carouselElemntsDisplayed.size() == getListSize());
            for (SquizCarouselData carouselElement : carouselElemntsDisplayed) {
                caruoseltype = carouselElement.getType();
                for (int i = 0; i < getListSize(); i++) {
                    elementfoud=false;
                    navigateToRootElement();
                    navigateToListElement(i);
                    switch (caruoseltype) {
                        case "game":
                            if(getText().toLowerCase().contains(carouselElement.getGameNameId().replace("|","").toLowerCase())) {
                                carouselelemntsAmount = carouselelemntsAmount + 1;
                                elementfoud=true;
                            }
                            break;
                        case "sport":
                            if(getText().toLowerCase().contains(carouselElement.getText().toLowerCase())) {
                                carouselelemntsAmount = carouselelemntsAmount + 1;
                                elementfoud=true;
                            }
                            break;
                        case "custom":
                            if(getText().toLowerCase().contains("live")) {
                                carouselelemntsAmount = carouselelemntsAmount + 1;
                                elementfoud=true;
                            }
                            break;
                        case "event":
                            if(getText().toLowerCase().contains(carouselElement.getText().toLowerCase())) {
                                carouselelemntsAmount = carouselelemntsAmount + 1;
                                elementfoud=true;
                            }
                            break;
                        case "other":
                            if(getText().toLowerCase().contains(carouselElement.getText().toLowerCase())) {
                                carouselelemntsAmount = carouselelemntsAmount + 1;
                                elementfoud=true;
                            }
                            break;
                    }
                    if(elementfoud){
                        break;
                    }
                }

            }

            assertTrue(carouselElemntsDisplayed.size()==carouselelemntsAmount);
        }

}