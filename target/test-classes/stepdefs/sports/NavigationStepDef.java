package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.openbet.SportId;
import office.openbet.model.Event;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefs.shared.Selectors;
import util.StepNotMetException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mkoza on 14/04/16.
 */
public class NavigationStepDef extends SportsAutomationScriptingLanguage {

    SportsStepDef sportsStepDef = new SportsStepDef();
    SecondaryHeaderStepDef secondaryHeaderStepDef = new SecondaryHeaderStepDef();


    @Given("^the user has navigated to the (.*) url$")
    public void the_user_has_navigated_to_sportsbook(String sport) throws Throwable {

        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/football/matches");
        waitSportsbook();
        assertTrue(hasPartialURL("/football/matches"));

    }

    @Given("^the user navigates to WilliamHill homepage$")
    public void the_user_is_on_the_William_Hill_Home_page() throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookies");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb");
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb"));
        sleep(9000);
    }

    @Given("^the user has navigated to the InPlay page$")
    public void the_user_opened_the_InPlay_page() throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/in-play/all");
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/in-play/all"));

    }

    @And("^the user has navigated to the football InPlay page$")
    public void the_user_opened_the_football_InPlay_page() throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        clearCookies();
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/football/in-play");
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/football/in-play"));

    }

    @And("^the user has navigated to the football page$")
    public void the_user_opened_the_football_page() {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        clearCookies();
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/football");
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/football"));
    }

    @Given("^the user is on the '(.*)' Daily List page$")
    public void theUserIsOnTheFootballDailyListPage(String sport) throws Throwable {
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        // as we don't have a proper homepage, we go to any migrated sport
        navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/matches");
        waitSportsbook();
        assertTrue(hasPartialURL("/" + sport.toLowerCase() + "/matches"));
    }

//	@When("^the user selects '(.*)' from sports menu$")
//	public void theUserSelectsSportFromSportsMenu(String sportName) throws Throwable {
//		switch (sportName) {
//            case "greyhounds":
//                sportName = sportName + "/meetings";
//                break;
//            case "horse-racing":
//                sportName = sportName + "/meetings";
//                break;
//            case "in-play":
//                sportName = sportName + "/all";
//                break;
//            case "virtual world":
//                sportName = "apps/virtual";
//                break;
//            case "top bets":
//                sportName = "apps/top-bets";
//                break;
//            case "home":
//                sportName = StringUtils.EMPTY;
//                break;
//        }
//        //expand  sports navigation bar
//        if (isSportsbookOnDesktop()) {
//            // test running on full desktop size
//            navigateToRootElement();
//            navigateToElementByClassName("localnavigation__button-showmore");
//            click();
//            navigateToRootElement();
//            assertTrue(navigateToElementByClassName("localnavigation__nonmobile"));
//            assertTrue(navigateToElementByClassName("cw"));
//
//        } else {
//            navigateToRootElement();
//            assertTrue(navigateToElementByCSS("#sports-menu"));
//            click();
//            navigateToRootElement();
//            assertTrue(navigateToElementById("localNavigationMobileSports"));
//        }
//        if (StringUtils.EMPTY.equalsIgnoreCase(sportName)) {
//            navigateToRootElement();
//            assertTrue(navigateToElementByXpath("//div[@id='localNav']/a"));
//            clickJS();
//        } else {
//            assertTrue(navigateToElementByCSS("span[class*='gaming-icon icon-" + sportName + "']"));
//            click();
//        }
//        waitSportsbook();
//    }


    @When("^the user selects '(.*)' from sports menu$")
    public void theUserSelectsSportFromSportsMenu(String sportName) throws Throwable {

        if(isSportsbookOnDesktop()){
            assertTrue(clickOnElementFromSportsbookMenu(sportName));
        }else{
            assertTrue(clickOnElementFromAZsection(sportName));
        }

        sleep(20000);
    }

    @Then("^the user click on '(.*)' from the A-Z section$")
    public boolean clickOnElementFromAZsection(String option) throws Throwable {

        boolean successfullClick = false;

        clickOnAZsportsOnFooter();

        navigateToRootElement();
        assertTrue(navigateToElementById("localNavigationMobileSports"));
        assertTrue(buildListByCSS(".localnavigation__column-dropdown .capitalize.ng-binding"));

        for (WebElement element : getElementList()) {
            if(element.getText().toLowerCase().equals(option.toLowerCase())){
                element.click();
                successfullClick = true;
                break;
            }
        }
        return successfullClick;
    }

    public boolean clickOnElementFromSportsbookMenu(String option) throws Throwable {

        boolean successfullClick = false;

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#localnavigationButtonShowMore"));
        click();

        navigateToRootElement();
        assertTrue(buildListByCSS("#localNav .capitalize.ng-binding"));

        for (WebElement element : getElementList()  ) {
            if(element.getText().equals(option)){
                element.click();
                successfullClick = true;
                break;
            }
        }

        return successfullClick;
    }

    @When("^user click on A - Z Sports from Footer$")
    public void clickOnAZsportsOnFooter(){
        // Here we click on the A - Z Sports
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#sports-menu"));
        sleep(2000);
        click();
    }

    @Then("^the user is on the In Play All Page$")
    public void the_user_is_on_the_In_Play_Page() throws Throwable {
        waitSportsbook();
        assertTrue(hasPartialURL("/in-play/all"));

    }

    @When("^the user has navigated to the '(.*)' page for created event$")
    public void theUserHasNavigatedToTheFootballPageForCreatedEvent(String sport) throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/" + eventId);
        waitSportsbook();
        navigateToRootElement();
        waitForVisibilityOfElementById(eventId);
    }


	@When("^the user has navigated to the '(.*)' created meeting market event$")
	public void theUserHasNavigatedToMeetingMarket(String sport) throws Throwable {
		String eventId="";

		List<Event> events = getScenarioContext().getEvents();
		for (Event event : events) {
			if(event.getName().contains("Meeting Markets")){
				 eventId = "OB_EV" + event.getId();
				break;
			}
		}
		navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
		navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/meetings/markets/" + eventId);
		waitSportsbook();
		navigateToRootElement();
		waitForVisibilityOfElementById(eventId);
	}


	@Given("^the user (?:is on|goes to) the (.*) meetings page$")
	public void the_user_is_on_the_sport_meetings_page(String sportName) throws Throwable {
		assertTrue(navigateToMeetingsPage(SportId.getSport(sportName)));
	}

	@When("^the customer selects the race$")
	public void the_customer_selects_the_race() throws Throwable {
		String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();

		navigateToRootElement();
		assertTrue(navigateToElementByCSS(
				String.format("[data-meetingmarketsid='%s'], [data-entityid='%s']", eventId, eventId)));
		clickJS();
		assertTrue(waitForVisibilityOfElementByClassName("race-event"));
		waitSportsbook();
	}

	@And("^the user select correct market collection$")
	public void theUserSelectCorrectMarketCollection() throws Throwable {
		//events depand of set up in squiz could appears in diferent  collection
		// to make sure that  you opened page  where your event displayed
		boolean marketfound=false;
		String marketId = "OB_MA"+getScenarioContext ().getFirstEvent().getFirstMarket().getId();
		navigateToRootElement();
		if(!navigateToElementById(marketId)){
			navigateToRootElement();
			assertTrue(navigateToElementById("marketCollectionItemsWrapper"));
			buildListByClassName("swiper-slide");
			for(int i=0;i>getListSize();i++){
				navigateToListElement(i);
				click();
				navigateToRootElement();
				if(navigateToElementById(marketId)){
					marketfound=true;
					break;
				}

            }
            assertTrue(marketfound);
        }
    }

    @And("^the user can collapse and expand market$")
    public void theUserCanColapseAndExpandMarket() throws Throwable {
        //to verify that you are able to colapse  and expand  by defauld market is always exapnd
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        if (getBackOfficeHelper().getCuponName() == null) {
            assertTrue(navigateToElementById("contentarea"));
            assertTrue(navigateToElementById(marketId));
        } else {

            assertTrue(navigateToElementById(competitionId));
        }
        assertTrue(navigateToElementByClassName("header__side"));
        saveCurrentElementPosition();
        assertTrue(navigateToElementByClassName("icon-arrow-up-slim "));
        click();
        restoreElementPosition();
        navigateToElementByCSS(".button-clear:not(.-expanded)");
        click();
    }

    @When("^the user has navigated to the '(.*)' coupon page for created event$")
    public void theUserHasNavigatedToTheFootballCouponPageForCreatedEvent(String sport) throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String cuponId = getBackOfficeHelper().getCupponId();

        if (device.isEmpty()) {
            navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
            navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/coupons/competition/OB_CP" + cuponId);
        } else {
            navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/coupons/competition/OB_CP" + cuponId);

        }
        waitSportsbook();
        for (int i = 0; i < 10; i++) {

            navigateToRootElement();
            if (!navigateToElementById(eventId)) {
                refresh();
                sleep(20000);
            } else {
                break;
            }
        }

    }

    @And("^scoreboards displayed$")
    public void scoreboardsDisplayed() throws Throwable {

        waitForPresenceOfElementById("scoreboard");
    }

    @And("^the customer click on the event link$")
    public void the_customer_select_the_event() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        buildListByClassName("btmarket");
        navigateToListElement(1);
        navigateToElementByClassName("btmarket__name ");
        click();
        waitSportsbook();

    }

    @And("the event is displayed on the (homepage|football page)")
    public void the_customer_select_the_event_after_waiting(final String page) throws Throwable {
        String eventId = getScenarioContext().getFirstEvent().getPdsId();
        boolean displayed = false;
        int attempts = 0;
        do {
            navigateToRootElement();
            displayed = navigateToElementById(eventId);
            if (!displayed) {
                sleep(30000);
                refresh();
                attempts++;
            }
        } while (!displayed && attempts < 15);
        if (!displayed) {
            throw new StepNotMetException("The event is not displayed in the " + page + " after waiting 7.5 minutes");
        }


    }


    @Then("^the user is on the selected event page$")
    public void theUserIsOnTheSelectedEventPage() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        System.out.println(getScenarioContext().getFirstEvent().getName());
        String eventName = getScenarioContext().getFirstEvent().getName().toLowerCase().replaceAll(" ", "-").replace(":", "").replace("|", "");
        String event = "/" + eventId;
        assertTrue(hasPartialURL(event));
    }

    @Then("^the user clicks on William Hill icon in the header$")
    public void clickOnWHIconHeader() {
        navigateToRootElement();
        navigateToElementByCSS(".header__logo");
        click();
        waitSportsbook();
    }

    @Then("^the user clicks on the show more link and the page is loaded$")
    public void clickOnShowMore() {
        if (isSportsbookOnDesktop()) {
            navigateToRootElement();
            navigateToElementByCSS(".sectionfooter_text");
            String href = getAttribute("href");
            click();
            assertTrue(hasPartialURL(href));
        } else {
            navigateToRootElement();
            navigateToElementByCSS(".topbets-buttons a");
            String href = getAttribute("href");
            click();
            assertTrue(hasPartialURL(href));
        }
    }

    @And("^he clicks on close mobile menu$")
    public void clickOnCloseButton() {
        navigateToRootElement();
        assertTrue(navigateToElementById("localNavigationMobileSports"));
        assertTrue(navigateToElementById("closeLocalNavigationSportsMobile"));
        click();
    }

    @And("^he clicks on (home|Betting|Vegas|Casino) button in mobile navigation header$")
    public void clickOnButton(final String button) {
        if ("home".equalsIgnoreCase(button)) {
            assertTrue(navigateToRootElement());
            assertTrue(navigateToElementById("productnavigation-mobile"));
            assertTrue(navigateToElementByClassName("home-prod-nav-btn"));
        } else {
            throw new NotImplementedException("Not implemented yet");
        }
        click();
        waitSportsbook();
    }

    @And("^the user navigates to '(.*)' (competitions|daily list) page$")
    public void theUserHasNavigatedFootballCompetitionPage(String sport, String page) throws Throwable {
        String pageURL = "";

        switch (page) {
            case "competitions":
                pageURL = "competitions";
                break;
            case "daily list":
                pageURL = "matches";
                break;
        }
        navigateToPage(sbUrl + "/sr-admin-set-white-list-cookie");
        navigateToPage(sbUrl + "/betting/en-gb/" + sport.toLowerCase() + "/" + pageURL);
        waitSportsbook();
        navigateToRootElement();
    }

    @And("^user verify that the user is able to add selections to the betslip$")
    public void addSelectionToBetslip() throws Throwable {

        navigateToRootElement();
        assertTrue(buildListByCSS(".topbets-prop"));
        navigateToListElement(1);
        click();

        navigateToRootElement();
        assertTrue(navigateToElementById("mobile-betslip-count"));
        sleep(1000);
        assertTrue(Integer.parseInt(getText()) == 1);
    }

    /**
     * This is just a simple Step to verify if the given text is in the current Page Url.
     *
     * @param endpoint
     * @throws Throwable
     */
    @And("^the url contains '(.*)'$")
    public void urlContains(String endpoint) throws Throwable {
        assertTrue(hasPartialURL(endpoint));
    }

    /**
     * This method will switch to the new Tab (without closing the previous one!).
     */
    public void switchToNewTab() {

        sleep(2000);

        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(tabs2.get(1));
    }

    /**
     * This method will switch to the previous Tab and closing the new one.
     */
    public void switchToPreviousTab() {

        sleep(2000);

        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));

    }


    public void navigateToCarouselElement(String carouselElement) {
        navigateToRootElement();
        try {
            assertTrue(navigateToElementByXpath("//nav[@id='carousel']//p[contains(.,'" + carouselElement + "')]"));
        } catch (AssertionError e) {
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("//nav[@id='carousel']//span[contains(.,'" + carouselElement + "')]"));
        }
    }

    /**
     * @param option
     * @throws Throwable
     * @Mobile-Only! The text sent on the Step has to be the same one used on the Carousel.
     * E.g.: Send "Euro 2016" not "euro 2016".
     */
    @Then("^the user click on '(.*)' from the Carousel menu$")
    public void clickOnElementFromCarousel(String option) throws Throwable {
        navigateToCarouselElement(option);
        click();
    }


    /**
     * @throws Throwable
     * @Mobile-Only! Simple step to verify if the options of the carousel on the HomePage Mobile are the ones that we expect to be.
     */
    @Then("^all the correct options are being displayed on the Carousel menu$")
    public void clickOnElementFromCarousel() throws Throwable {

        List<String> carouselOptions = new ArrayList<String>();
        carouselOptions.add("In-Play");
        carouselOptions.add("Football");
        carouselOptions.add("Horses");
        carouselOptions.add("Roulette");
        carouselOptions.add("Virtual");
        carouselOptions.add("Blackjack");
        carouselOptions.add("Wish JP");
        carouselOptions.add("Live");
        carouselOptions.add("Top Bets");
        carouselOptions.add("Promotions");
        carouselOptions.add("Tennis");
        carouselOptions.add("Golf");
        carouselOptions.add("Cricket");
        carouselOptions.add("Greyhounds");
        carouselOptions.add("Baseball");
        carouselOptions.add("Darts");
        carouselOptions.add("Boxing");
        carouselOptions.add("R League");

        // Here we get the list of WebElements from the Carousel
        navigateToRootElement();
        assertTrue(buildListByCSS("#carousel li"));

        for (int i = 0; i < carouselOptions.size(); i++) {
            navigateToListElement(i);
            assertEquals(getText(), carouselOptions.get(i));
        }
    }


    @Then("^the user can horizontally slide to the sides on the Carousel menu$")
    public void horizontalSlideCarouselMenu() throws Throwable {

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//nav[@class='contextual-nav contextual-nav--hide-for-xlarge']//li)[1]"));
        isDisplayed();
        assertFalse(isHidden());

        //
        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        WebElement element = driver.findElement(By.xpath("(//nav[@class='contextual-nav contextual-nav--hide-for-xlarge']//li)[19]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//nav[@class='contextual-nav contextual-nav--hide-for-xlarge']//li)[19]"));
        isDisplayed();
        assertFalse(isHidden());

    }


    /**
     * On this Step user can verify if the Home Page Carousel is being displayed or not according to the Input.
     *
     * @param option
     * @throws Throwable
     */
    @Then("^the Home Page Carousel '(is|is not)' being displayed$")
    public void homePageCarouselDisplay(String option) throws Throwable {

        navigateToRootElement();

        if (option.equals("is")) {
            assertTrue(navigateToElementByCSS("main .contextual-nav__inner.grid.grid--aitems-start"));
            assertTrue(isDisplayed());
        } else {
            try {
                assertTrue(navigateToElementByCSS("main .contextual-nav__inner.grid.grid--aitems-start"));
                assertFalse(isDisplayed());
            } catch (AssertionError e) {
                assertFalse(navigateToElementByCSS("main .contextual-nav__inner.grid.grid--aitems-start"));
            }
        }
    }

    /**
     * @param option
     * @throws Throwable
     * @Desktop-Only The text sent on the Step has to be the same one used on the Left Hand menu.
     * E.g.: Send "Search" not "search".
     */
    @Then("^the user click on '(.*)' from the left hand menu$")
    public void clickOnElementFromLeftHandMenu(String option) throws Throwable {

        // we were getting StaleElementReferenceException, therefore:
        refresh();
        sleep(5000);

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//nav[@id='sidebar-left']//span[contains(.,'" + option + "')]"));
        click();

        // This time is due to the loading times from the German version of the site that takes more than the UK version
        sleep(10000);
    }

    /**
     * @param option
     * @throws Throwable
     * @Desktop-Only The text sent on the Step has to be the same one used on the Right Hand menu.
     * E.g.: Send "Bet Slip" not "bet slip".
     */
    @Then("^the user click on '(.*)' from the right hand menu$")
    public void clickOnElementFromRightHandMenu(String option) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//nav[@class='sidebar -toolbar -to-right']//a[contains(.,'" + option + "')]"));
        click();
    }

    /**
     * @param option
     * @throws Throwable
     * @Mobile-Only! The text sent on the Step has to be the same one used on the Carousel from Sports Page.
     * E.g.: Send "Daily List" not "daily list".
     */
    @Then("^the user clicks on '(.*)' from the Sports Page Carousel$")
    public void clickOnElementFromSportsPageCarousel(String option) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//main[@class='maincenter highlights']//span[contains(.,'" + option + "')]"));
        assertTrue(isDisplayed());
        click();

        // Sleep added due to the load time from the DE version.
        sleep(20000);
    }

    /**
     * @Mobile-only
     * On this method will click on the "Live", "Top Bets", etc Tabs.
     * @param tabName
     * @throws Throwable
     */
    @Then("^the user clicks on the '(.*)' tab$")
    public void userClicksOnGivenTab(String tabName) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//div[contains(@class,'tabs tabs')]//a[contains(.,'" + tabName + "')]"));
        click();
    }

    /**
     * This method will click on Accept on the displayed Alert and will also return the text displayed on the Alert.
     *
     * @return
     */
    public String checkAlert() {
        WebDriver driver = MultiThreadedDriverFactory.getCurrentDriver();
        String alertText = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
        } catch (Exception e) {
        }
        return alertText;
    }

    @Then("^verify In-Play redirection$")
    public void verifyInPlayRedirection() {
        sportsStepDef.pageRedirectionsVerifications("/in-play", "//body[@data-page='inPlay']");
    }

    @And("^verify Home Page redirection$")
    public void verifyHomePageRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("", "(//a[@title='William Hill Sports homepage'])[1]");
    }

    @And("^verify Football redirection$")
    public void verifyFootballRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("en-gb/football", "//h1[contains(.,'Football Betting Highlights')]");
    }

    @And("^verify Euro 2016 redirection$")
    public void verifyEuroRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("en-gb/football", "//h1[contains(.,'Football Betting Highlights')]");
    }

    @And("^verify Horse Racing redirection$")
    public void verifyHorseRacingRedirection() throws Throwable {
        if(isSportsbookOnDesktop()) {
            sportsStepDef.pageRedirectionsVerifications("horse-racing/meetings", "(//h1[contains(.,'Horse Racing')])[1]");
        }else {
            sportsStepDef.pageRedirectionsVerifications("horse-racing/meetings", "(//h1[contains(.,'Meetings')])[1]");
        }
    }

    @And("^verify Cricket redirection$")
    public void verifyCricketRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("en-gb/cricket", "//h1[contains(.,'Cricket Betting Highlights')]");
    }

    @And("^verify Greyhounds redirection$")
    public void verifyGreyhoundsRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("greyhounds/meetings", "//h1[contains(.,'Greyhounds Racing')]");
    }

    @And("^verify Virtual World redirection$")
    public void verifyVirtualWorldRedirection() throws Throwable {

        //TODO: When Virtual Sports is back, add the XPATH locator for the unique page element.
        sportsStepDef.pageRedirectionsVerifications("apps/virtual", "//div[@class='main']");
    }

    @And("^verify Coupons redirection$")
    public void verifyCouponsRedirection() throws Throwable {
        if(isSportsbookOnDesktop()) {
            sportsStepDef.pageRedirectionsVerifications("/coupons", "//li[contains(@class, 'active')]//i[contains(@class,'coupons')]");
        }else{
            secondaryHeaderStepDef.secondHeaderTitleIs("Coupons");
            urlContains("/coupons");
        }
    }


    @Then("^the user is in football page$")
    public void theUserIsInFootballPage() throws Throwable {
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/football"));
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("header-panel__title"));
        assertTrue(getText().equalsIgnoreCase("Football Betting Highlights"));

    }

    @And("^the user navigates to '(.*)' Sport page$")
    public void theUserNavigatesToSportPage(String sportName) {
        navigateToPage(sbUrl + "/betting/en-gb/" + sportName);
        waitSportsbook();
        assertTrue(hasPartialURL("/betting/en-gb/" + sportName));
    }
}