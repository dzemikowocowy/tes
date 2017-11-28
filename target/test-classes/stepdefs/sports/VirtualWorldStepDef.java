package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import stepdefs.shared.Selectors;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by Michal Koza on 09/07/15.
 */

public class VirtualWorldStepDef extends SportsAutomationScriptingLanguage {
    private static final String[] Carousel = new String[]{"Featured", "Horse Racing - Flat",
            "Horse Racing - National Hunt", "Football", "Greyhounds - Flat", "Greyhounds - Hurdles", "Motor racing", "Speedway", "Cycling"};

    private static final String[] CarouselUrls = new String[]{"", "horse-racing/flat",
            "horse-racing/national-hunt", "football", "greyhounds/flat", "greyhounds/jumps", "motor-racing", "speedway", "cycling"};


//    private static final HashMap<String, String> CarouselMap(){
//        HashMap<String, String> Carousel = new HashMap<String, String>();
//        Carousel.put("Featured", "");
//        Carousel.put("Horse Racing - Flat", "horse-racing/flat");
//        Carousel.put("Horse Racing - National Hunt", "horse-racing/national-hunt");
//        Carousel.put("Football", "football");
//        Carousel.put("Greyhounds - Flat", "greyhounds/flat");
//        Carousel.put("Greyhounds - Hurdles", "greyhounds/jumps");
//        Carousel.put("Motor racing", "motor-racing");
//        Carousel.put("Speedway", "speedway");
//        Carousel.put("Cycling", "cycling");
//
//        return Carousel;
//    }


    private static String[] SportsTitles = new String[]{"Horse Racing Flat", "Horse Racing National Hunt",
            "Football", "Greyhounds Flat", "Greyhounds Hurdles", "Motor Racing", "Speedway", "Cycling"};

    @Then("^the mobile 'virtual world' Carousel displayed with correct order$")
    public void theMobileVirtualWorldCarouselDisplayedWithCorrectOrder() throws Throwable {
        List<String> CarouselNames = Arrays.asList(Carousel);
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        buildListByClassName("contextual-nav__title ");
        for (int i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            System.out.println(i + getText());
            assertTrue(getText().contains(CarouselNames.get(i)));
        }
    }

    @And("^Default option in virtual world Carousel is Featured$")
    public void defaultOptionInVirtualWorldCarouselIsFeatured() throws Throwable {
        //check that Featured is active by default
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        assertTrue(navigateToElementByClassName("sidebar__group"));
        assertTrue(navigateToElementByClassName("contextual-nav__inner"));
        assertTrue(navigateToElementByCSS("li.contextual-nav__item--active:nth-child(1)"));
        assertTrue(navigateToElementByClassName("contextual-nav__title"));
        assertTrue(getText().contains("Featured"));


    }


    @And("^the user select '(.*)' from the Virtual World Carousel$")
    public void theUserSelectCarouseloptionFromTheFootbalCarousel(String carouseloption) throws Throwable {
        String url = "";
        assertTrue(navigateToCarouselElement(carouseloption));
        switch (carouseloption.toLowerCase().replaceAll(" ", "")) {
            case "horseracing-flat":
                url = getAttribute("href");
                click();
                break;
            case "horseracing-nationalhunt":
                url = getAttribute("href");
                click();
                break;
            case "football":
                url = getAttribute("href");
                click();
                break;
            case "greyhounds-flat":
                url = getAttribute("href");
                click();
                break;
            case "greyhounds-hurdles ":
                url = getAttribute("href");
                click();
                break;
            case "motorracing":
                url = getAttribute("href");
                click();
                break;
            case "speedway":
                url = getAttribute("href");
                click();
                break;
            case "cycling":
                url = getAttribute("href");
                click();
                break;
        }
        waitSportsbook();
        assertTrue(hasPartialURL(url));
    }

    @And("^the user click on back button$")
    public void clickOnBackButton() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".gaming-icon.icon-back.icon-medium"));
        click();
    }

    @And("^the user click on a meeting$")
    public void clickOnAMeeting() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(//button[@class='button-standard race-meeting__selection'])[2]"));

//        assertTrue(buildListByCSS(Selectors.PAGE_MEETINGS));
//        getElementList().get(2);

        click();
    }


    @And("^the user verify that '(.*)' is highlighted on the carousel$")
    public void verifyIfCarouselOptionIsHighlighted(String carouselOption){
        navigateToRootElement();
        if(carouselOption.equals("Featured")){
            assertTrue(navigateToElementByCSS(Selectors.FEATURED_OPTION_SELECTED));
        }else {
            assertTrue(navigateToElementByXpath("//li[contains(@class,'active')]" + String.format(Selectors.CAROUSEL_OPTION, carouselOption)));
        }
    }

    @And("^the user click on the Next 8 button$")
    public void clickOnNext8button() throws Throwable {
        navigateToRootElement();
        navigateToElementByCSS("button[data-name='Next 8']");
        click();
    }

    @And("^the three next events are displayed$")
    public void threeNextEventsAreDisplayed() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByCSS(".racecard__button.racecard__button--footer.ng-scope"));
        assertTrue(getListSize() == 3);
    }

    @And("^the user verify the Horse Racing National Hunt event title on the Featured page$")
    public void verifyHorseRacingNationalHuntFeaturedPageTitle() throws Throwable {

//        NavigationStepDef navigationStepDef= new NavigationStepDef();

        String sectionTitle;

        navigateToRootElement();
        assertTrue(buildListByCSS(Selectors.PAGE_MEETINGS));
        navigateToListElement(1);
        sectionTitle = getAttribute("data-name") + " Weston Vale";

        clickOnBackButton();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("section[data-ng-repeat*='VWHORSE_HUNT'] h2"));

        assertTrue(getText().contains(sectionTitle));

    }

    @And("^the user place a single bet from a meeting and verify that betslip is updated$")
    public void placeSingleBetOnVirtualFromMeeting() throws Throwable {

        clickOnAMeeting();

        navigateToRootElement();
        assertTrue(buildListByCSS(Selectors.PAGE_SELECTIONS));
        navigateToListElement(2);
        click();

        navigateToRootElement();
        assertTrue(navigateToElementById("mobile-betslip-count"));
        sleep(1000);
        assertTrue(Integer.parseInt(getText()) == 1);

    }

    @And("^the user click on '(.*)' selections from Football$")
    public void clickOnSelectionsFromFootball(int selectionNumber){

        navigateToRootElement();
        assertTrue(buildListByCSS("[id^='OB_EV']:not(.disabled-event) " +
                "[id^='OB_OU'][data-status='A']:not(.disabled)"));
        if (getListSize() < selectionNumber) {
            logger.info("Not enough selections available, asked for " + selectionNumber + ", number available " + getListSize());
            logger.info("Using " + getListSize() + " selections");
            selectionNumber = getListSize();
        }
        for(int i=1; i<=selectionNumber; i++){
            navigateToRootElement();
            assertTrue(buildListByClassName("event"));
            assertTrue(buildListByCSS("[id^='OB_EV']:not(.disabled-event) " +
                    "[id^='OB_OU'][data-status='A']:not(.disabled)"));
            assertTrue(navigateToListElement(i));
            navigateToRootElement();
            assertTrue(buildListByCSS("[id^='OB_OU'][data-status='A']:not(.disabled)"));
            assertTrue(navigateToListElement(i));
            click();
        }
        navigateToRootElement();
        assertTrue(navigateToElementById("mobile-betslip-count"));
        sleep(1000);
        assertTrue(Integer.parseInt(getText()) == selectionNumber);
    }

    @And("^the user click on the Market: '(.*)'$")
    public void clickOnMarket(String market){
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//span[@class='inner ng-binding' and contains(.,'" + market + "')]"));
        click();
    }

    @Then("^user click on View All Football Events from Featured page$")
    public void clickViewAllFootballEvents() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".link-viewall"));
        click();
    }

    @Then("^user click on View All Horse Racing National Hunt Events from Featured page$")
    public void clickViewAllHRNationalHuntEvents() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("a[data-ng-if*='VWHORSE_HUNT']"));
        click();
    }

    @Then("^user click on Next Off Races bar$")
    public void clickOnNextOffRacesBar() throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".race-nav__group-header-name.ng-binding"));
        click();
    }


    @Then("^user click on a View Full Card button$")
    public void clickOnViewFullCard() throws Throwable {
        navigateToRootElement();
        assertTrue(buildListByCSS(".racecard__button.racecard__button--footer.ng-scope"));
        assertTrue(getListSize() == 3);
        navigateToListElement(1);
        click();
    }

    // This step is for Mobile only.
    @And("^the user navigates thru all the elements on the Virtual World Carousel$")
    public void theUserNavigatesAllCarousel() throws Throwable {

        List<String> CarouselNames = Arrays.asList(Carousel);
        List<String> CarouselEndpoints = Arrays.asList(CarouselUrls);

        for (int i = 1; i < CarouselNames.size(); i++) {

            navigateToRootElement();

            // Here we click on the Carousel Option
            assertTrue(navigateToCarouselElement(CarouselNames.get(i)));
            click();

            // Here we assert that the Option selected is highlighted on the carousel
            verifyIfCarouselOptionIsHighlighted(CarouselNames.get(i));

            // Here we assert that the URL contains the right endpoint
            assertTrue(hasPartialURL(CarouselEndpoints.get(i)));

            navigateToRootElement();

            // Here we verify that the section have selections
            if (CarouselNames.get(i).equals("Football")) {
                assertTrue(navigateToElementByCSS(Selectors.FOOTBALL_MATCH_BETTING_SECTION));
                click();
                navigateToRootElement();
                assertTrue(navigateToElementByXpath(Selectors.FOOTBALL_FIRST_SELECTION));
            } else {
                assertTrue(navigateToElementByXpath(Selectors.CAROUSEL_SECTION_FIRST_SELECTION));
            }

        }

    }


    @Then("^Countdown clock and in play event is available for all the options in carousel$")
    public void countdownClockAndInPlayIsAvailableForAllCarouseloptions() throws Throwable {

        List<String> CarouselNames = Arrays.asList(Carousel);

        String time_1;
        String time_2;

        for (int i = 1; i < CarouselNames.size(); i++) {

            navigateToRootElement();

            // If here gets true, then it has the time, if not, it means its In Play.
            if (navigateToElementByXpath(String.format(Selectors.CAROUSEL_OPTION, CarouselNames.get(i)) + "/..//span[@class='wh-badge wh-badge--primary wh-badge--right ng-scope']")) {

                //So, if it has time, we are going to verify that its not stopped
                time_1 = getText();
                sleep(1000);
                time_2 = getText();

                assertFalse(time_1.equals(time_2));
            } else {
                navigateToRootElement();
                assertTrue(navigateToElementByXpath(String.format(Selectors.CAROUSEL_OPTION, CarouselNames.get(i)) + "/..//i[@class='wh-badge__icon icon-tv']"));
            }
        }
    }


    @Then("^verify that the section with the countdown becomes In Play icon when countdown finishes")
    public void countdownClockBecomesInPlay() throws Throwable {
        List<String> CarouselNames = Arrays.asList(Carousel);
        int counter = 1;
        Boolean validation = false;
        do {
            navigateToRootElement();
            if (navigateToElementByXpath(String.format(Selectors.CAROUSEL_OPTION, CarouselNames.get(counter)) + "/..//span[contains(.,':')]")) {
                sleep((Integer.parseInt(getAttribute("value"))*1000) + 2000);
                navigateToRootElement();
                assertTrue(navigateToElementByXpath(String.format(Selectors.CAROUSEL_OPTION, CarouselNames.get(counter)) + "/..//i[@class='wh-badge__icon icon-tv']"));
                validation = true;
            }
            counter++;
        } while (validation == false);
    }


    @Then("^Countdown clock is available for '(.*)' in carousel if event is not in progress$")
    public void countdownClockIsAvailableForCarouseloptionInCarouselIfEventIsNotInProgress(String carouseloption) throws Throwable {
        String time;
        String nextEventTime = "";
        int carouselNextEventStartedSeconds = 0;
        String nextRaceStartedForMinutes = "";
        String currentTime = "";

        switch (carouseloption.toLowerCase().replaceAll(" ", "")) {
            case "horseracing-flat":
                assertTrue(navigateToCarouselElement(carouseloption));
                navigateToParent();
                // if Tv Icon appears wait   refresh site and  navigate to clock again
                if (navigateToTabByClassName("wh-badge__icon ") || navigateToTabByClassName("icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        waitSportsbook();
                        assertTrue(navigateToCarouselElement(carouseloption));
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }

                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();

                }
                break;
            case "horseracing-nationalhunt":
                assertTrue(navigateToCarouselElement(carouseloption));
                navigateToParent();
                // if Tv Icon appears wait   refresh site and  navigate to clock again
                if (navigateToTabByClassName("wh-badge__icon ") || navigateToTabByClassName("icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        waitSportsbook();
                        assertTrue(navigateToCarouselElement(carouseloption));
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();
                }
                break;
            case "football":
                assertTrue(navigateToCarouselElement(carouseloption));
                navigateToParent();
                assertTrue(navigateToElementByClassName("wh-badge "));
                if (getValue() != null) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        navigateToRootElement();
                        navigateToCarouselElement(carouseloption);
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                }

                //Ho many seconds to next race
                assertTrue(navigateToCarouselElement(carouseloption));
                navigateToParent();
                assertTrue(navigateToElementByClassName("wh-badge "));
                carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                nextEventTime = whenNextFootballEventStart(nextEventTime);
                //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                //get current time  -1 h
                currentTime = getCurrentTime();


                break;
            case "greyhounds-flat":
                if (navigateToTabByClassName("wh-badge__icon icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        navigateToCarouselElement(carouseloption);
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();
                }
                break;
            case "greyhounds-hurdles ":
                if (navigateToTabByClassName("wh-badge__icon icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        navigateToCarouselElement(carouseloption);
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();
                }
                break;
            case "speedway":
                if (navigateToTabByClassName("wh-badge__icon icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        navigateToCarouselElement(carouseloption);
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();
                }
                break;
            case "cycling":
                if (navigateToTabByClassName("wh-badge__icon icon-tv")) {
                    for (int i = 0; i < 2; i++) {
                        sleep(900);
                        refresh();
                        navigateToCarouselElement(carouseloption);
                        time = getText();
                        if (!time.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    //Ho many seconds to next race
                    assertTrue(navigateToCarouselElement(carouseloption));
                    navigateToParent();
                    assertTrue(navigateToElementByClassName("wh-badge "));
                    carouselNextEventStartedSeconds = Integer.parseInt(getValue());
                    nextEventTime = whenNextVirtualHorseRaceStarted(nextEventTime);
                    //How many  minutes and seconds to next race
//                    assertTrue(navigateToCarouselElement(carouseloption));
//                    assertTrue(navigateToElementByTag("span"));
//                    nextRaceStartedForMinutes=getText();
                    //get current time  -1 h
                    currentTime = getCurrentTime();
                }
                break;
        }
        System.out.println("diference beteen times   " + getSecondsBeetweenTime(nextEventTime, currentTime));
        System.out.println("current itme  " + currentTime);
        System.out.println("next event " + nextEventTime);
        System.out.println("time to next event " + carouselNextEventStartedSeconds);
        assertTrue(getSecondsBeetweenTime(nextEventTime, currentTime) - 5 <= carouselNextEventStartedSeconds && carouselNextEventStartedSeconds <= getSecondsBeetweenTime(nextEventTime, currentTime) + 5);

    }

    private String getCurrentTime() {
        String currentTime;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        currentTime = new SimpleDateFormat("kk:mm:ss")
                .format(calendar.getTime());
        return currentTime;
    }

    private String getCurrentday(int day) {
        String currentday;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        currentday = new SimpleDateFormat("yyyy-MM-dd")
                .format(calendar.getTime());
        return currentday;
    }

    private boolean navigateToCarouselElement(String carouseloption) {
        boolean f = false;
        navigateToRootElement();
        assertTrue(navigateToElementById("carousel"));
        assertTrue(navigateToElementByClassName("sidebar__group"));
        assertTrue(navigateToElementByClassName("contextual-nav__inner"));
        switch (carouseloption.toLowerCase().replaceAll(" ", "")) {
            case "horseracing-flat":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWHORSE_FLATS"));
                f = true;
                break;
            case "horseracing-nationalhunt":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWHORSE_HUNT"));
                f = true;
                break;
            case "football":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWFOOTBALL"));
                f = true;
                break;
            case "greyhounds-flat":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWGREYHOUNDS_FLATS"));
                f = true;
                break;
            case "greyhounds-hurdles":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWGREYHOUNDS_JUMPS"));
                f = true;
                break;
            case "motorracing":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWMOTOR_RACING"));
                f = true;
                break;
            case "speedway":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWSPEEDWAY"));
                f = true;
                break;
            case "cycling":
                assertTrue(navigateToElementByAttributeValue("data-sport-code", "VWCYCLING"));
                f = true;
                break;
        }
        return f;
    }

    private long getSecondsBeetweenTime(String dateTime1, String dateTime2) throws Throwable {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        Date date1;
        Date date2;

        date1 = sdf.parse(dateTime1);
        date2 = sdf.parse(dateTime2);
        if (date1.before(date2)) {

            calendar.add(Calendar.DATE, 1);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTime1 = getCurrentday(1) + "" + dateTime1;
            dateTime2 = getCurrentday(0) + "" + dateTime2;
            date1 = sdf.parse(dateTime1);
            date2 = sdf1.parse(dateTime2);

            return (Math.abs(date1.getTime() - date2.getTime()) / 1000);
        } else {
            return (Math.abs(date1.getTime() - date2.getTime()) / 1000);
        }
    }

    private String whenNextVirtualHorseRaceStarted(String nextEventTime) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("virtual-horses-canvas"));
        assertTrue(navigateToElementByClassName("race-subnav"));
        buildListByClassName("race-meeting__nav");
        int counter = getListSize();
        for (int i = 0; i < counter; i++) {
            navigateToRootElement();
            assertTrue(navigateToElementById("virtual-horses-canvas"));
            assertTrue(navigateToElementByClassName("race-subnav"));
            buildListByClassName("race-meeting__nav");
            navigateToListElement(i);
            buildListByClassName("race-meeting__item ");
            navigateToListElement(1);
            assertTrue(navigateToChild());
            if (nextEventTime.isEmpty()) {
                nextEventTime = getAttribute("data-name") + ":00";

            } else {

                SimpleDateFormat ra = new SimpleDateFormat("HH:mm:ss");
                Date yourDate = ra.parse(getAttribute("data-name") + ":00");
                Time date1 = new Time(yourDate.getTime());

                Date previusRacTime = ra.parse(nextEventTime);
                Time date2 = new Time(previusRacTime.getTime());
                if (date2.after(date1)) {
                    nextEventTime = getAttribute("data-name") + ":00";
                }
            }
        }
        return nextEventTime;
    }

    private String whenNextFootballEventStart(String nextEventTime) throws Throwable {
        navigateToRootElement();
        assertTrue(navigateToElementById("market-collections"));
        buildListByClassName("marketmenu__section ");
        navigateToListElement(1);
        click();

        navigateToRootElement();
        assertTrue(navigateToElementById("comp-"));
        buildListByClassName("markettable__event ");
        navigateToListElement(0);
        nextEventTime = getAttribute("data-startdate");
        nextEventTime = nextEventTime.substring(11, 19);

        return nextEventTime;
    }

    @Then("^the TV icon is displayed for each event with correct colour$")
    public void theTVIconIsDisplayedForEachEventWithCorrectColour() throws Throwable {
        navigateToRootElement();
        buildListByClassName("race-header");
        for (int i = 0; i < getListSize(); i++) {
            navigateToListElement(i);
            navigateToElementByTag("a");
//            navigateToElementByTag ("i");
//           // System.out.println (getAttribute ("data-ng-if"));
//            assertTrue (hasBackgroundColour ("#248CB3")||hasBackgroundColour ("#ffd400"));
//            "race-header__toolbar-item ng-scope"
//            class="race-header__toolbar-item race-header__toolbar-item--tv ng-scope"
//            race-header__toolbar-item ng-scope
////            assertTrue (hasBackgroundColour ("#248CB3")||hasBackgroundColour ("#ffd400"));

        }


    }

    @Then("^Virtual World homepage components displayed$")
    public void virtualWorldHomepageComponentsDisplayed() throws Throwable {
        //veirfy Title bar with "Virtual World" as title
        navigateToRootElement();
        assertTrue(navigateToElementById("virtual-featured-wrapper"));
        assertTrue(navigateToElementByClassName("header-panel__content-inner"));
        assertTrue(getText().contains("Virtual World"));
    }

    @And("^Virtual World Notification Bar appears$")
    public void virtualWorldNotificationBarAppears() throws Throwable {
        navigateToRootElement();
        navigateToElementById("virtual-featured-wrapper");
        assertTrue(navigateToElementByClassName("race-event__view-message"));
        assertTrue(navigateToElementByClassName("icon-tv"));
    }

    @And("^user click on  first avaibale tv icon$")
    public void whenUserClickOnFirstAvaibaleTvIcon() throws Throwable {
        String url;
        navigateToRootElement();
        buildListByClassName("race-header");
        navigateToRootElement();
        navigateToListElement(1);
        navigateToElementByTag("a");
        url = getAttribute("href");
        click();
        waitSportsbook();
        assertTrue(hasPartialURL(url));
    }

    @Then("^correct Virtual sport name is displayed on the top$")
    public void correctVirtualSportNameIsDisplayedOnTheTop() throws Throwable {
        List<String> sportsTitles = Arrays.asList(SportsTitles);
        navigateToRootElement();
        assertTrue(buildListByClassName("sectionheading__column "));
        for (int i = 0; i < getListSize(); i++) {
            assertTrue(getText().contains(sportsTitles.get(i)));
        }
    }

    @Then("^click on the first View Full Event on Virtual World home page$")
    public void clickOnTheFirstViewFullEventOnVirtualWorldHomePage() throws Throwable {
        String url;
        navigateToRootElement();
        buildListByClassName("racecard__button--footer ");
        navigateToListElement(0);
        url = getAttribute("href");
        click();
        waitSportsbook();
        assertTrue(hasPartialURL(url));
    }

    @Given("^virtualfrom squiz$")
    public void virtualfromSquiz() throws Throwable {
        System.out.println(getVirtualSquizHelper().getEvents("horses").getName());
    }

    @When("^the user click on the event from virtual footbal area$")
    public void theUserClickOnTheEventFromVirtualFootbalArea() throws Throwable {

    }

    @Then("^the user is redirected to the event page$")
    public void theUserIsRedirectedToTheEventPage() throws Throwable {

    }

    @Then("^Total amount of markets is displayed on the right of event name$")
    public void totalAmountOfMarketsIsDisplayedOnTheRightOfEventName() throws Throwable {

    }

    @Then("^data title  selection  and mareket for the virtual footbal event are correct$")
    public void dataTitleSelectionAndMareketForTheVirtualFootbalEventAreCorrect() throws Throwable {

    }



    @Then("^meetings are display with races on them$")
    public void meetingsDisplayedWithRaces(){
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".race-subnav__group-header-name.ng-binding"));
        navigateToRootElement();
        buildListByCSS(".button-standard.race-meeting__selection");
        assertTrue(!listEmpty());

    }

    @Then("^user click on one of the races$")
    public void clickOnARace(){
        buildListByCSS(".button-standard.race-meeting__selection");
        assertTrue(!listEmpty());
    }

    @Then("^user verify that the section tile is '(.*)'$")
    public void verifySectionTitleIsDisplayed(String title){
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(Selectors.VIRTUAL_SECTION_TITLE, title)));
    }

    @Then("^user verify that the event tile is '(.*)'$")
    public void verifyEventTitleIsDisplayed(String title){
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(Selectors.VIRTUAL_EVENT_TITLE, title)));
    }

    @Then("^the event title is the same as the name of the event clicked$")
    public void verifyEventTitleIsSameAsEventClicked(){
        String title;
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".race-subnav__group-header-name.ng-binding"));
        String meetingGroupTitle = getText();
        assertTrue(navigateToElementByXpath("(//button[@class='button-standard race-meeting__selection'])[2]"));
        String meetingButtonText = getAttribute("data-name");
        click();
        title = meetingButtonText + " " + meetingGroupTitle;
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(Selectors.VIRTUAL_EVENT_TITLE, title)));
    }


    @Then("^the navigation arrows for the selections are displayed$")
    public void navigationArrowsDisplayedForSelections(){
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.NAVIGATION_ARROW_NEXT));
        click();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.NAVIGATION_ARROW_PREVIOUS));
    }

    @Then("^the user view the full event$")
    public void viewFullCardButton() {
        navigateToRootElement();
        assertTrue(buildListByCSS("section[data-ng-repeat*='VWHORSE_HUNT'] .racecard__button.racecard__button--footer ng-scope"));
        click();
    }

    @Then("^the selections are order by their odd values$")
    public void selectionsOrderByOddValues(){

        Double selection_1_price;
        Double selection_2_price;

        navigateToRootElement();

        if(hasPartialURL("apps/virtual")) {
            navigateToRootElement();
            assertTrue(buildListByCSS("section[data-ng-repeat*='VWHORSE_HUNT'] .oddsbutton"));
        }else{
            navigateToRootElement();
            assertTrue(buildListByCSS(Selectors.PAGE_SELECTIONS));
        }

        int j = 1;
        for (int i = 2; i <= getListSize(); i++) {
            navigateToListElement(j);
            selection_1_price = fromFractionToDouble(getAttribute("data-odds"));
            navigateToListElement(i);
            selection_2_price = fromFractionToDouble(getAttribute("data-odds"));
            assertTrue(selection_1_price <= selection_2_price);
            j++;
        }
    }


    public Double fromFractionToDouble(String fraction){

        Double number_1 = Double.parseDouble(fraction.split(Pattern.quote("/"))[0]);
        Double number_2 = Double.parseDouble(fraction.split(Pattern.quote("/"))[1]);

        return number_1 / number_2;

    }




    /**
     * The purpuse of this method is to make us save some lines of code due to the repeat for each of the 6 sections.
     * @param cssSection
     */
    public void verifyFootballSections(String cssSection) throws ParseException {

        refresh();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(cssSection));
        click();

        navigateToRootElement();
        assertTrue(navigateToElementByXpath(Selectors.VIRTUAL_FOOTBALL_TITLE));

        navigateToRootElement();
        verifySelectionsOrderByTimeFootball();

        navigateToRootElement();
        assertTrue(navigateToElementByXpath(Selectors.FOOTBALL_FIRST_SELECTION));

        verifySelectionsOnFootball();

    }

    /**
     * This method just verify the Banner at the bottom of the Section.
     */
    @Then("^the Virtual Banner is displayed at the bottom$")
    public void verifyBanner(){
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.SECTION_BOTTOM_BANNER));
    }

    /**
     * This method just verify the Video Player of the Section - User not logged in.
     */
    @Then("^the user will be able to see only the Demo video on Football$")
    public void verifyVideoPlayerUserNotLoggedInFootball(){
        refresh();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.FOOTBALL_CORRECT_SCORE_SECTION));
        click();

        navigateToRootElement();
        assertTrue(navigateToElementByCSS("section[ng-if*='virtual.dataValid']"));

    }

    @Then("^the user will be able to see only the Demo video$")
    public void verifyVideoPlayerUserNotLoggedIn(){
        navigateToRootElement();
        assertTrue(navigateToElementByCSS("section[ng-if*='virtual.dataValid']"));
    }

    /**
     * This method just verify the Video Player of the Section - User not logged in.
     */
    @Then("^the user will be able to see only the football event video$")
    public void verifyVideoPlayerUserLoggedInFootball(){

        refresh();
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(Selectors.FOOTBALL_CORRECT_SCORE_SECTION));
        click();

        navigateToRootElement();
        assertTrue(navigateToFrameByClass("video-cont"));
        assertTrue(navigateToFrameByClass("mwEmbedKalturaIframe"));
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".videoHolder .alert-container")); // here verify the event video.

    }

    @Then("^the user will be able to see only the event video$")
    public void verifyVideoPlayerUserLoggedIn(){

        navigateToRootElement();
        assertTrue(navigateToFrameByClass("video-cont"));
        assertTrue(navigateToFrameByClass("mwEmbedKalturaIframe"));
        navigateToRootElement();
        assertTrue(navigateToElementByCSS(".videoHolder .alert-container")); // here verify the event video.

    }

    /**
     * This method just verify that the Selections are order by time for the Football Section of the Virtual World.
     */
    public void verifySelectionsOrderByTimeFootball() throws ParseException {
        SimpleDateFormat ra = new SimpleDateFormat("dd MMM HH:mm");
        buildListByCSS("section[id^='OB_EV'] time");
        int j = 0;
        for (int i = 1; i < getListSize(); i++) {
            navigateToListElement(j);
            Date date1 = new SimpleDateFormat("dd MMM HH:mm").parse(getText());
            navigateToListElement(i);
            Date date2 = new SimpleDateFormat("dd MMM HH:mm").parse(getText());
            assertTrue(date2.after(date1));
            j++;
        }
    }


    /**
     * This method will check the names of the events as well as the total amoun of markets available is the same as the one displayed on the name.
     */
    public void verifySelectionsOnFootball() {

        int betNumber;

        navigateToRootElement();
        buildListByCSS(Selectors.FOOTBALL_SELECTION);

        for (int i = 1; i <= getListSize(); i++) {

            // Here we assert that the selection has a title.
            navigateToRootElement();
            assertTrue(navigateToElementByXpath("(//span[@class='event__title ng-binding'])[" + i + "]"));

            betNumber = Integer.parseInt(getText().split(Pattern.quote("+"))[1].split(" ")[0]);

            // Then we click on the + button
            assertTrue(navigateToElementByXpath("(//i[@class='icon-plus'])[" + i + "]"));
            click();

            // remove this after the bug QAPD-1058 is fixed
            navigateToRootElement();
            navigateToElementByCSS(".fl.ng-binding");
            if(getText().equals("Match Betting")){
                betNumber = betNumber + 1;
            }

            navigateToRootElement();
            buildListByCSS(".header-dropdown.header-dropdown__subheader");
            assertTrue(betNumber == getListSize());

            navigateToRootElement();
            assertTrue(navigateToElementByCSS(".icon-minus"));
            click();

        }
    }

    @Then("^the title of the current page is '(.*)'$")
    public void verifyCurrentPageTitle(String title) throws Throwable {
        titleHasPartialText(title);
    }


    @Then("^verify all the Football components$")
    public void verifyFootballComponents() throws Throwable {

        //Here we verify the Market title
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(Selectors.MARKET_TITLE));

        // Sections + Selections verifications
        verifyFootballSections(Selectors.FOOTBALL_MATCH_BETTING_SECTION);
        verifyFootballSections(Selectors.FOOTBALL_CORRECT_SCORE_SECTION);
        verifyFootballSections(Selectors.FOOTBALL_TOTAL_GOALS_SECTION);
        verifyFootballSections(Selectors.FOOTBALL_TOTAL_GOALS_UNDER_OVER_SECTION);
        verifyFootballSections(Selectors.FOOTBALL_ASIAN_HANDICAP_SECTION);
        verifyFootballSections(Selectors.FOOTBALL_DOUBLE_CHANCE_SECTION);

        // Banner at the bottom
        verifyBanner();
    }

    @Then("^the user will click on Load more until it becomes Show less$")
    public void clickLoadMoreUntilBecomesShowLess(){
        int listSize = 12;
        try {
            do {
                navigateToRootElement();
                assertTrue(navigateToElementByXpath("//a[contains(.,'Load more ...')]"));
                click();
                navigateToRootElement();
                assertTrue(buildListByCSS(Selectors.PAGE_MEETINGS));
                assertTrue(getListSize() > listSize);
                assertTrue((getListSize() - listSize) <= 12);
                listSize = getListSize();
            } while (navigateToElementByXpath("//a[contains(.,'Load more ...')]"));
        }catch (StaleElementReferenceException e){}
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//a[contains(.,'Show less')]"));
    }

    @Then("^the user will click on Show less until it becomes Load more$")
    public void clickShowLessUntilBeomcesLoadMore(){
        int listSize;
        try {
            do {
                navigateToRootElement();
                assertTrue(buildListByCSS(Selectors.PAGE_MEETINGS));
                listSize = getListSize();
                navigateToRootElement();
                assertTrue(navigateToElementByXpath("//a[contains(.,'Show less')]"));
                click();
                navigateToRootElement();
                assertTrue(buildListByCSS(Selectors.PAGE_MEETINGS));
//                assertTrue(getListSize() < listSize);
//                assertTrue((listSize - 12) == getListSize());

                assertTrue(getListSize() < listSize);
                assertTrue((getListSize() - listSize) <= 12);

            } while (navigateToElementByXpath("//a[contains(.,'Show less')]"));
        }catch (StaleElementReferenceException e){}
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//a[contains(.,'Load more ...')]"));
    }

}


