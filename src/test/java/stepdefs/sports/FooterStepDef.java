package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import stepdefs.shared.Selectors;

import java.util.ArrayList;


/**
 * TODO: Move Xpath and CSS variables to the Selectors Class.
 */
public class FooterStepDef extends SportsAutomationScriptingLanguage {

    Selectors selectors = new Selectors();
    TopBetsStepDef topBetsStepDef = new TopBetsStepDef();
    SportsStepDef sportsStepDef = new SportsStepDef();
    NavigationStepDef navigationStepDef = new NavigationStepDef();

    /**
     * This method will assert if the given title is being displayed on the Sport Page.
     * TODO: Verify later on if we can re-use this method with more page Titles.
     *
     * @param title
     * @throws Throwable
     */
    @Then("^the title '(.*)' is being displayed on the Sport Page$")
    public void givenTitleIsDisplayed(String title) throws Throwable {
        waitSportsbook();
        assertTrue(navigateToElementByXpath("//h1[contains(.,'" + title + "')]"));
    }


    private void verifyFooterQuickLinks(){

        ArrayList<String> cssLocators = new ArrayList<String>();

        cssLocators.add(".quick-links-container .gaming-icon.icon-home");
        cssLocators.add(".gaming-icon.icon-in-play");
        cssLocators.add(".gaming-icon.icon--.sprite-icon.euros-sprite");
        cssLocators.add(".quick-links-container .gaming-icon.icon-football");
        cssLocators.add(".quick-links-container .gaming-icon.icon-horse-racing");
        cssLocators.add(".quick-links-container .gaming-icon.icon-cricket");
        cssLocators.add(".quick-links-container .gaming-icon.icon-greyhounds");
        cssLocators.add(".quick-links-container .gaming-icon.icon-virtual-world");
        cssLocators.add(".x-sell-btn-container a[data-trk-gamename*='Roulette']");
        cssLocators.add(".x-sell-btn-container a[data-trk-gamename*='Blackjack']");
        cssLocators.add(".x-sell-btn-container a[data-trk-gamename*='Wish JP']");
        cssLocators.add(".quick-links-container .search-btn-container");

        for(int i=0; i<cssLocators.size(); i++){
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(cssLocators.get(i)));
        }

    }

    /**
     * Since the quantity of Sports being displayed here may vary we verify that its not empty and these have all the
     * icons, names, etc.
     */
    public void verifyAZsportsFooter(){

        navigateToRootElement();
        assertTrue(buildListByXpath(selectors.A_TO_Z_SPORTS_ELEMENTS_ON_FOOTER));

        for(int i = 0; i<getListSize(); i++){

            // Here we verify if it has the icon
            navigateToRootElement();
            navigateToListElement(i);
            assertTrue(navigateToElementByCSS("span[class*='gaming-icon']"));
            assertTrue(isDisplayed());

            // Here we verify that the title is appearing, its not empty and is displayed
            navigateToRootElement();
            navigateToListElement(i);
            assertTrue(navigateToElementByCSS(".capitalize.ng-binding"));
            assertFalse(getText().equals(""));
            assertTrue(isDisplayed());
        }
    }

    public void verifyExtrasFooter(){

        ArrayList<String> cssLocators = new ArrayList<String>();

        cssLocators.add(".gaming-icon.icon-most-popular-bets2");
        cssLocators.add(".gaming-icon.icon-promotions");
        cssLocators.add(".gaming-icon.icon-tv-schedule");

        for(int i=0; i<cssLocators.size(); i++){
            navigateToRootElement();
            assertTrue(navigateToElementByCSS(cssLocators.get(i)));
            assertTrue(isDisplayed());
        }
    }


    /**
     *  On this method we will verify that all the components are being displayed correctly with all their elements.
     * @throws Throwable
     */
    @And("^the A to Z Sports section is being displayed correctly on the Footer$")
    public void footerIsDisplayedCorrectly() throws Throwable {

        navigationStepDef.clickOnAZsportsOnFooter();

        // Here we verify the Quick Links section
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//span[contains(.,'Quick Links')]"));
        assertEquals("Quick Links", getText());
        assertTrue(isDisplayed());
        verifyFooterQuickLinks();

        // Here we verify the A - Z Sports section
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//section/div[contains(.,'A - Z Sports')]"));
        assertEquals("A - Z Sports", getText());
        assertTrue(isDisplayed());
        verifyAZsportsFooter();

        // Here we verify the Extras section
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//div/div[contains(.,'Extras')]"));
        assertEquals("Extras", getText());
        assertTrue(isDisplayed());
        verifyExtrasFooter();

        // Here we close the A-Z Section of the footer
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#closeLocalNavigationSportsMobile"));
        click();

    }

    /**
     * This method will click on the given Element from the Extras section.
     * Note: The name of the Element should be the same as the one being displayed on the page.
     *
     * @param element
     * @throws Throwable
     */
    @Then("^the user click on '(.*)' from the Extras$")
    public void clickOnElementOnExtras(String element) throws Throwable {

        navigationStepDef.clickOnAZsportsOnFooter();

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//div[@class='localnavigation-extras']//span[contains(.,'" + element + "')]"));
        click();
    }


    /**
     * This method will verify if the current Page has:
     *      The given Title
     *      The given Endpoint on the URL
     *      The given Element (on xpath format for the locator)
     * @param urlEndpoint
     * @param xpathLocator
     */
    private void pageRedirectionsVerifications(String urlEndpoint, String xpathLocator){

        assertTrue(hasPartialURL(urlEndpoint));

        navigateToRootElement();
        assertTrue(navigateToElementByXpath(xpathLocator));

        assertTrue(isDisplayed());
        assertFalse(isHidden());
    }

    @And("^verify Search redirection$")
    public void verifySearchRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("", "//input[@id='search-input']");
    }

    @And("^verify A to Z Sports redirection$")
    public void verifyRedirection() throws Throwable {

        String sportName;

        // Here we get the number of Sports being displayed
        navigateToRootElement();
        assertTrue(buildListByXpath(selectors.A_TO_Z_SPORTS_ELEMENTS_ON_FOOTER));

        for(int i = 0; i<getListSize(); i++){

            // Here we have to call it again everytime or else we will get an "Stale Element Reference Exception"
            // Since we change page at the end of the Test.
            assertTrue(buildListByXpath(selectors.A_TO_Z_SPORTS_ELEMENTS_ON_FOOTER));

            navigateToListElement(i);
            assertTrue(navigateToElementByCSS(".capitalize.ng-binding"));
            sportName = getText();

            click();
            sleep(2000);

            if(sportName.contains("Virtual")){
                assertTrue(hasPartialURL("virtual"));
            }else {
                assertTrue(hasPartialURL(sportName.replace("/ ", "-").replace(" ", "-").toLowerCase()));
            }

            refresh();
            navigationStepDef.clickOnAZsportsOnFooter();
        }
    }

    @And("^verify Top Bets redirection$")
    public void verifyTopBetsRedirection() throws Throwable {
        topBetsStepDef.verifyTopBetsClickToSeeMore();
    }

    @And("^verify Promotions redirection$")
    public void verifyPromotionsRedirection() throws Throwable {
        sleep(2000);
        sportsStepDef.pageRedirectionsVerifications("promotions", "//ul[@class='wf-splash-grid__grid']");
    }

    @And("^verify Schedule redirection$")
    public void verifyScheduleRedirection() throws Throwable {
        sportsStepDef.pageRedirectionsVerifications("schedule/in-play", "//div[@class='swiper-wrapper']");
    }

    @And("^user click on Open Bet from the Footer Menu$")
    public void clickOnFooterOpenBet() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".toolbar__icon.icon-open-bets"));
        click();
    }


    /**
     * TODO: Deal with the Login, Log off, with and without Bets scenarios.
     *
     * @throws Throwable
     */
    @And("^the Open Bets on footer is being displayed correctly$")
    public void verifyOpenBetsFooter() throws Throwable {

        // Here we click on the Open Bets icon on the Footer
        clickOnFooterOpenBet();

        // Verification of the Open Bets Tab
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#openbets-tab"));

        // Verify Open Title
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#openbets-title"));
        assertEquals("Open Bets", getText());

        // Verify close button
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#openbets-header .icon-x"));
        click();
    }

    @And("^click on the Footer Betslip$")
    public void clickOnFooterBetslip(){
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".toolbar__icon.icon-bet-slip"));
        click();
    }

    /**
     * This method will verify that all the Elements are being correctly displayed.
     * @throws Throwable
     */
    @And("^the Betslip on footer is being displayed correctly$")
    public void verifyBetslipFooter() throws Throwable {

        // Click on the Betslip icon on the footer
        clickOnFooterBetslip();

        // Verify that the Tab exists
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#betslip-tab"));

        // Verify Betslip title
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#betslip-title-type"));
        assertEquals("Bet Slip", getText());

        // Verify close button
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#betslip-header .icon-x"));
        click();
    }

    /**
     * This method will just verify if the Search elements are being displayed correctly.
     * @throws Throwable
     */
    @And("^the Search on footer is being displayed correctly$")
    public void verifySearchFooter() throws Throwable {

        // Click on the Search icon on the footer
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".toolbar__icon.icon-search"));
        click();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("input[class='search-input__field']"));

        // Here we close
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#search-close"));
        click();

    }

    /**
     * On this method we will verify if the Roulette icon is displayed on the footer and verify its redirection.
     * @throws Throwable
     */
    @And("^the Roulette on footer is being displayed correctly$")
    public void verifyRouletteFooter() throws Throwable {

        // Click on the Search icon on the footer
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".toolbar__icon.icon-Roulette"));
        click();

        NavigationStepDef navigationStepDef = new NavigationStepDef();
        navigationStepDef.switchToNewTab();
        assertTrue(hasPartialURL("RouletteMobile"));
        navigationStepDef.switchToPreviousTab();
    }


    /**
     * This method will verify that the First Bet / Selection is displayed on the Footer Betslip.
     * @throws Throwable
     */
    @And("^verify that Bet is displayed on the Footer Betslip$")
    public void verifyBetDisplayedBetslipFooter() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//div[contains(@id,'single-bet')])[1]"));
    }

    /**
     * This method will click on the Clear Slip from the Betslip Footer.
     * @throws Throwable
     */
    @And("^click on Clear Slip from Footer Betslip$")
    public void clickOnClearSlipFooterBetslip() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#clear-slip-link"));
        click();
    }

    @Then("^the 'Cash in my bet' message is displayed$")
    public void cashInMyBetMessageDisplayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".cimb-alert-button.betslip-button"));
    }

    @Then("^the 'Please log in to see your open bets' message is displayed$")
    public void logInToSeeYourOpenBetsDisplayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".cimb-alert-button.betslip-button"));
    }

    @Then("^the Mobile Footer is not being displayed$")
    public void mobileFooterNotDisplayed() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("#toolbar"));
        assertFalse(isDisplayed());
    }

    @Then("^scroll down and verify that Footer disappears for a brief time$")
    public void footerDissappearWhileScrolling() throws Throwable {
        scrollDown();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".toolbar.toolbar--hide"));
    }

    @Then("^the user changes the language to '(.*)' from the footer$")
    public void changeLanguageTo(String language) throws Throwable {

        scrollDown();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".languageSelectionToggle"));
        clickJS();

        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//section[@id='languageSelection']//a[contains(.,'" + language + "')]"));
        clickJS();

        sleep(10000);
    }
}


