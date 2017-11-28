package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import model.squiz.SquizCarouselData;
import office.BackOffice;
import office.openbet.model.*;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepdefs.shared.Selectors;
import util.SquizVirtualHelper;
import util.StepNotMetException;
import util.Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mkoza 06/04/2016
 */
public class DataStepDef extends SportsAutomationScriptingLanguage {

    private Logger logger = LogManager.getRootLogger();

    public static String[] horses = new String[] {"It's A Close Call", "Final Nudge", "Captain Probus", "Pursuitofhappiness",
            "Abidjan", "Multimedia", "Queen Of Rock", "Sandford Castle",
            "Whatthebutlersaw", "Dreamisi", "Master Hide", "Darsi's Dream", "East Hill"};

    public static String[][] horses2 = new String[][] {
            {"Many Clouds", "1"}, {"Saint Are", "2"}, {"Monbeg Dude", "3"}, {"Alvarado", "4"}, {"Shutthefrontdoor", "5"},
            {"Royale Knight", "6"}, {"Tranquil Sea", "7"}, {"Cause of Causes", "8"}, {"Soll", "9"}, {"Chance Du Roy", "10"},
            {"Mon Parrain", "11"}, {"Pineau De Re", "12"}, {"Owega Star", "13"}, {"Spring Heeled", "14"}, {"Oscar Time", "15"},
            {"First Lieutenant", "16"}, {"Rocky Creek", "17"}, {"Night In Milan", "18"}, {"Dolatulo", "19"}, {"Godsmejudge", "20"},
            {"Rebel Rebellion", "21"}, {"Al Co", "22"}, {"Ballycasey", "23"}, {"Corrin Wood", "24"}, {"The Rainbow Hunter", "25"},
            {"Balthazar King", "26"}, {"Across The Bay", "27"}, {"Rubi Light", "28"}, {"The Druids Nephew", "29"}, {"Bob Ford", "30"},
            {"Super Duy", "31"}, {"Wyck Hill", "32"}, {"Gas Line Boy", "33"}, {"Unioniste", "34"}, {"Portrait King", "35"},
            {"Lord Windermere", "36"}, {"River Choice", "37"}, {"Court By Surprise", "38"}, {"Ely Brown", "39"}, {"Javier Franco", "40"}};

    public static String[] markets = new String[] {"/Win/", "/Place Only/ - 2 /Places/", "/Place Only/ - 3 /Places/", "/Place Only/ - 4 /Places/",
            "/Betting Without/", "/Insure/ - 2 /Places/", "/Insure/ - 3 /Places/", "/Insure/ - 4 /Places/",
            "/Insure/ - /Faller/", "/Match Bets/"};


    private String calculateSelectionRandomPrice() {
        return String.valueOf(new Random().nextInt(20) + 1)
                + "/" + String.valueOf(new Random().nextInt(20) + 1);
    }


    @Given("^Data Meeting Markets$")
    public void datameetingmarkets() throws Throwable {

        String meetingName="Bath";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 2);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.MINUTE, 55);
        //calendar.set(Calendar.SECOND, 0);
        //calendar.add(Calendar.DAY_OF_WEEK, 3);


        // create pro racecard
        for (int i = 1; i <= 3; i++) {
            calendar.add(Calendar.MINUTE, 5);
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(calendar.getTime());
            String raceDisplayedStartTime = new SimpleDateFormat("h:mm")
                    .format(calendar.getTime());
            createProRaceCard(meetingName,
                    raceDisplayedStartTime + " " + meetingName,
                    raceStartTime);
        }

        // create standard racecard
        for (int i = 1; i <= 1; i++) {
            calendar.add(Calendar.MINUTE, 5);
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(calendar.getTime());
            String raceDisplayedStartTime = new SimpleDateFormat("h:mm")
                    .format(calendar.getTime());
            createRaceCard(meetingName,
                    raceDisplayedStartTime + " " + meetingName,
                    raceStartTime);
        }

        // create meeting markets
        if (meetingName.equals("Bangor") || meetingName.equals("Bath")) {
            //calendar.add(Calendar.DAY_OF_WEEK, 1);
            calendar.set(Calendar.HOUR, 8);
            calendar.set(Calendar.AM_PM, Calendar.PM);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
            createMeetingMarketsWithCollections(meetingName, meetingName, raceStartTime);
        }
        else {
            calendar.set(Calendar.HOUR, 8);
            calendar.set(Calendar.AM_PM, Calendar.PM);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
           createMeetingMarketsWithoutCollections(meetingName, meetingName, raceStartTime);
        }

    }

    public void createMeetingMarketsWithCollections(String competition, String meetingName, String startTime) {
        Event event = new Event();
        event.setName(meetingName + "Meeting Markets");
        event.setSort(BackOfficeConstants.EVENT_TYPE_TOURNAMENT);
        event.setCompetitionId(CompetitionIds.getCompetitionId(competition));
        event.setStartTime(startTime);
        event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
        event.setStatus(BackOfficeConstants.EVENT_STATUS_ACTIVE);
        event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_YES);
        Event boEvent = getOxifeedHelper().addEvent(event);

        for (int j = 1; j <= 9; j++) {
            String mName = "Race Winning Distance| - |3 Way";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 4; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                boMarket.addSelection(boSelection);
            }
        }

        for (int j = 1; j <= 2; j++) {
            String mName = "Trainer Challenge Forecast";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 50; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                boMarket.addSelection(boSelection);
            }
        }

        for (int j = 1; j <= 2; j++) {
            String mName = "Trainer Challenge Straight Forecast";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 50; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                boMarket.addSelection(boSelection);
            }
        }

        for (int j = 1; j <= 20; j++) {
            String mName = "Jockey Match Bets";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 2; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                if(k==1)
                    selection.setType(SelectionTypeEnum.home.getType());
                else if(k==2)
                    selection.setType(SelectionTypeEnum.away.getType());
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }

        for (int j = 1; j <= 20; j++) {
            String mName = "Trainer Match Bets";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 2; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                if(k==1) {
                    selection.setType(SelectionTypeEnum.home.getType());
                }
                else if(k==2) {
                    selection.setType(SelectionTypeEnum.away.getType());
                }
                Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                boMarket.addSelection(boSelection);
            }
        }

        for (int j = 1; j <= 20; j++) {
            String mName = "Horse Match Bets";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            for (int k = 1; k <= 2; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                if(k==1)
                    selection.setType(SelectionTypeEnum.home.getType());
                else if(k==2)
                    selection.setType(SelectionTypeEnum.away.getType());
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }

        getScenarioContext().addEvent(boEvent);
        checkEventsReadyForTesting(getScenarioContext().getEvents());

    }

    public void createMeetingMarketsWithoutCollections(String competition, String meetingName, String startTime) {
        Event event = new Event();
        event.setName(meetingName + " Daily Meeting Markets");
        event.setSort(BackOfficeConstants.EVENT_TYPE_TOURNAMENT);
        event.setCompetitionId(CompetitionIds.getCompetitionId(competition));
        event.setStartTime(startTime);
        event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
        Event boEvent = getOxifeedHelper().addEvent(event);

        for (int j = 1; j <= 9; j++) {
            String mName = "Win";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);


            for (int k = 1; k <= 4; k++) {
                Selection selection = new Selection();
                selection.setName(String.format(meetingName + " %s %s", mName, k));
                selection.setPrice(calculateSelectionRandomPrice());
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }

        getScenarioContext().addEvent(boEvent);
        checkEventsReadyForTesting(getScenarioContext().getEvents());
    }

    public void createProRaceCard(String competition, String raceName, String startTime) {
        Event event = new Event();
        event.setName(raceName);
        event.setCompetitionId(CompetitionIds.getCompetitionId(competition));
        event.setStartTime(startTime);
        event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
        event.setStatus(BackOfficeConstants.EVENT_STATUS_ACTIVE);
        event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_YES);
        Event boEvent = getOxifeedHelper().addEvent(event);


        int numberOfHorses = 8;

        if(competition.equals("251") || competition.equals("10")) { // Bangor or Bath
            addMarket(boEvent.getId(), "/Win/", "/Win/", 0, numberOfHorses);
            addMarket(boEvent.getId(), "/Place Only/ - 2 /Places/", "/Place Only/ - 2 /Places/", 1, numberOfHorses);
            addMarket(boEvent.getId(), "/Place Only/ - 3 /Places/", "/Place Only/ - 3 /Places/", 2, numberOfHorses);
            //addMarket(boEvent.getId(), "/Place Only/ - 4 /Places/", "/Place Only/ - 4 /Places/", 3, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - 2 /Places/", "/Insure/ - 2 /Places/", 4, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - 3 /Places/", "/Insure/ - 3 /Places/", 5, numberOfHorses);
            //addMarket(boEvent.getId(), "/Insure/ - 4 /Places/", "/Insure/ - 4 /Places/", 6, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - /Faller/", "/Insure/ - /Faller/", 7, numberOfHorses);
            addMarket(boEvent.getId(), "/Betting Without/", "/Betting Without/ It's A Close Call", 8, numberOfHorses);
            addMarket(boEvent.getId(), "/Betting Without/", "/Betting Without/ It's A Close Call /and/ Final Nudge", 9, numberOfHorses);
            addMarket(boEvent.getId(), "/Match Bets/", "It's A Close Call /vs/ Final Nudge", 10, 2);
            addMarket(boEvent.getId(), "/Match Bets/", "It's A Close Call /vs/ Captain Probus", 11, 2);
            addMarket(boEvent.getId(), "/Match Bets/", "Final Nudge /vs/ Captain Probus", 12, 2);
        }

        else if(competition.equals("254")) { // Doncaster
            addMarket(boEvent.getId(), "/Win/ Unassigned", "/Win/", 0, numberOfHorses);
            addMarket(boEvent.getId(), "/Place Only/ - 2 /Places/ Unassigned", "/Place Only/ - 2 /Places/", 1, numberOfHorses);
            addMarket(boEvent.getId(), "/Place Only/ - 3 /Places/ Unassigned", "/Place Only/ - 3 /Places/", 2, numberOfHorses);
            //addMarket(boEvent.getId(), "/Place Only/ - 4 /Places/ Unassigned", "/Place Only/ - 4 /Places/", 3, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - 2 /Places/ Unassigned", "/Insure/ - 2 /Places/", 6, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - 3 /Places/ Unassigned", "/Insure/ - 3 /Places/", 7, numberOfHorses);
            //addMarket(boEvent.getId(), "/Insure/ - 4 /Places/ Unassigned", "/Insure/ - 4 /Places/", 8, numberOfHorses);
            addMarket(boEvent.getId(), "/Insure/ - /Faller/ Unassigned", "/Insure/ - /Faller/", 4, numberOfHorses);
            addMarket(boEvent.getId(), "/Betting Without/ Unassigned", "/Betting Without/ It's A Close Call", 5, numberOfHorses);
            addMarket(boEvent.getId(), "/Betting Without/ Unassigned", "/Betting Without/ It's A Close Call /and/ Final Nudge", 9, numberOfHorses);
            addMarket(boEvent.getId(), "/Match Bets/ Unassigned", "It's A Close Call /vs/ Final Nudge", 10, 2);
            addMarket(boEvent.getId(), "/Match Bets/ Unassigned", "It's A Close Call /vs/ Captain Probus", 11, 2);
            addMarket(boEvent.getId(), "/Match Bets/ Unassigned", "Final Nudge /vs/ Captain Probus", 12, 2);
        }

        else if(competition.equals("9")) { // Aintree
            addMarketWithRunnerOrder(boEvent.getId(), "/Win/", "/Win/", 0);
            addMarketWithRunnerOrder(boEvent.getId(), "/Place Only/ - 2 /Places/", "/Place Only/ - 2 /Places/", 1);
            addMarketWithRunnerOrder(boEvent.getId(), "/Place Only/ - 3 /Places/", "/Place Only/ - 3 /Places/", 2);
            //addMarketWithRunnerOrder(boEvent.getId(), "/Place Only/ - 4 /Places/", "/Place Only/ - 4 /Places/", 3);
            addMarketWithRunnerOrder(boEvent.getId(), "/Insure/ - 2 /Places/", "/Insure/ - 2 /Places/", 4);
            addMarketWithRunnerOrder(boEvent.getId(), "/Insure/ - 3 /Places/", "/Insure/ - 3 /Places/", 5);
            //addMarketWithRunnerOrder(boEvent.getId(), "/Insure/ - 4 /Places/", "/Insure/ - 4 /Places/", 6);
            addMarketWithRunnerOrder(boEvent.getId(), "/Insure/ - /Faller/", "/Insure/ - /Faller/", 7);
            addMarketWithRunnerOrder(boEvent.getId(), "/Betting Without/", "/Betting Without/ Many Clouds", 8);
            addMarketWithRunnerOrder(boEvent.getId(), "/Betting Without/", "/Betting Without/ Many Clouds /and/ Saint Are", 9);
            addMarketWithRunnerOrder(boEvent.getId(), "/Match Bets/", "Many Clouds /vs/ Saint Are", 10);
            addMarketWithRunnerOrder(boEvent.getId(), "/Match Bets/", "Many Clouds /vs/ Monbeg Dude", 11);
            addMarketWithRunnerOrder(boEvent.getId(), "/Match Bets/", "Saint Are /vs/ Monbeg Dude", 12);
        }

        getScenarioContext().addEvent(boEvent);
        checkEventsReadyForTesting(getScenarioContext().getEvents());

    }

    private void addMarketWithRunnerOrder(int eventId, String marketGroup, String marketName, int order) {

        marketGroup = marketGroup.replace("/", "|");
        marketName = marketName.replace("/", "|");

        Market market = new Market(marketGroup);
        market.setMarketGroup(marketGroup);
        market.setName(marketName);
        market.setOrder(String.valueOf(order));
        Market boMarket = getOxifeedHelper().addMarket(eventId, market);



        if(marketGroup.contains("|Match Bets|")) {
            for (int k = 0; k < 2; k++) {
                Selection selection = new Selection();
                selection.setPrice((k + 2) + "/1");
                if(k==0) {
                    selection.setType(SelectionTypeEnum.home.getType());
                    selection.setName(StringUtils.substringBefore(marketName, "|vs|").trim());
                } else if(k==1) {
                    selection.setType(SelectionTypeEnum.away.getType());
                    selection.setName(StringUtils.substringAfter(marketName, "|vs|").trim());
                }
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }

        else if(marketGroup.contains("|Betting Without|")) {
            for (int k = 0; k < horses2.length; k++) {
                if(!marketName.contains(horses2[k][0])) {
                    Selection selection = new Selection();
                    selection.setName(horses2[k][0]);
                    selection.setPrice((k + 2) + "/1");
                    //selection.setRunnerOrder(horses2[k][1]);
                    getOxifeedHelper().addSelection(boMarket.getId(), selection);
                }
            }
        }
        else {
            for (int k = 0; k < horses2.length; k++) {
                Selection selection = new Selection();
                selection.setName(horses2[k][0]);
                selection.setPrice((k + 2) + "/1");
                //selection.setRunnerOrder(horses2[k][1]);
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }
    }


    private void addMarket(int eventId, String marketGroup, String marketName, int order, int selections) {

        marketGroup = marketGroup.replace("/", "|");
        marketName = marketName.replace("/", "|");

        Market market = new Market(marketGroup);
        market.setMarketGroup(marketGroup);
        market.setName(marketName);
        market.setOrder(String.valueOf(order));
        Market boMarket = getOxifeedHelper().addMarket(eventId, market);


        List<String> horseSelections = Arrays.asList(horses);
        if(marketGroup.contains("|Match Bets|")) {
            for (int k = 0; k < 2; k++) {
                Selection selection = new Selection();
                selection.setPrice((k + 2) + "/1");
                if(k==0) {
                    selection.setType(SelectionTypeEnum.home.getType());
                    selection.setName(StringUtils.substringBefore(marketName, "|vs|").trim());
                } else if(k==1) {
                    selection.setType(SelectionTypeEnum.away.getType());
                    selection.setName(StringUtils.substringAfter(marketName, "|vs|").trim());
                }
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }
        else if(marketGroup.contains("|Betting Without|")) {
            for (int k = 0; k < selections; k++) {
                if(!marketName.contains(horseSelections.get(k))) {
                    Selection selection = new Selection();
                    selection.setName(horseSelections.get(k));
                    selection.setPrice((k + 2) + "/1");
                    getOxifeedHelper().addSelection(boMarket.getId(), selection);
                }
            }
        }
        else {
            for (int k = 0; k < selections; k++) {
                Selection selection = new Selection();
                selection.setName(horseSelections.get(k));
                selection.setPrice((k + 2) + "/1");
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }
    }

    public  void createRaceCard(String competition, String raceName, String startTime) {
        Event event = new Event();
        event.setName(raceName);
        event.setCompetitionId(CompetitionIds.getCompetitionId(competition));
        event.setStartTime(startTime);
        event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
        event.setStatus(BackOfficeConstants.EVENT_STATUS_ACTIVE);
        event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_YES);
        Event boEvent = getOxifeedHelper().addEvent(event);

        for (int j = 1; j <= 1; j++) {
            String mName = "Win";
            Market market = new Market(String.format("|%s|", mName));
            market.setMarketGroup(String.format("|%s|", mName));
            market.setName(String.format("|%s %s|", mName, j));
            market.setOrder(String.valueOf(j));
            Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);


            List<String> horseSelections = Arrays.asList(horses);
            for (int k = 0; k < horseSelections.size(); k++) {
                Selection selection = new Selection();
                selection.setName(horseSelections.get(k));
                selection.setPrice(calculateSelectionRandomPrice());
                getOxifeedHelper().addSelection(boMarket.getId(), selection);
            }
        }
        getScenarioContext().addEvent(boEvent);
        checkEventsReadyForTesting(getScenarioContext().getEvents());

    }




    @And("^there is an? (pre-match|in-play)(?: event with an|) active selection$")
    public void thereIsAnActiveEventWithAnActiveSelection(String eventType) throws Throwable {
        // get the in-play/pre-match event selector
        final String selectionSelector;
        if (eventType.equals("pre-match"))
            selectionSelector = Selectors.PRE_MATCH_ACTIVE_SELECTION;
        else
            selectionSelector = Selectors.IN_PLAY_ACTIVE_SELECTION;

        // build list of selections and pick a random one
        assertTrue(navigateToRootElement());
        if (buildListByCSS(selectionSelector)) {
            navigateToListElement(RandomUtils.nextInt(0, getListSize()));
            // save the selection id and price for later use
            Event event = new Event();
            Market market = new Market();
            event.setPdsId(getAttribute("data-event"));
            Selection selection = new Selection();
            selection.setPdsId(getAttribute("id"));
            selection.setPrice(getAttribute("data-odds"));
            market.addSelection(selection);
            event.addMarket(market);
            getScenarioContext().addEvent(event);
        } else
            throw new StepNotMetException(String.format("No %s active events have been set.", eventType));
    }


    /**
     * @param eventType
     * @throws Throwable
     */
    public void modifyBetOfGivenEvent(String eventType) throws Throwable {


        navigateToElementByXpath("(//li[@class='topbets__list-item']//button)[1]");
        // save the selection id and price for later use
        Event event = new Event();
        Market market = new Market();
        event.setPdsId(getAttribute("data-event"));
        Selection selection = new Selection();
        selection.setPdsId(getAttribute("id"));
        selection.setPrice(getAttribute("data-odds"));
        market.addSelection(selection);
        event.addMarket(market);
        getScenarioContext().addEvent(event);


    }


    /**
     * This method will modify the Start Time of the Event given the EventId, depending on what the user
     * sends as time if its going to be set to 3hs from actual time or if its going to be set ina specific time.
     * <p>
     * The time String should have this format "yyyy-MM-dd HH:mm:ss".
     *
     * @param eventId
     * @param time
     */
    public void modifyEventTime(int eventId, String time) throws ParseException {

        Event event = new Event();
        event.setId(eventId);

        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, +3);

        // format the start time
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

        if (time == "") {
            event.setStartTime(startTime);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(time);

            startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

            event.setStartTime(startTime);
        }

        getOxifeedHelper().updateEvent(event);

    }


    public void modifyEventTimeToNow(int eventId) throws ParseException {

        Event event = new Event();
        event.setId(eventId);

        // set the race start time
        Calendar calendar = Calendar.getInstance();

        // format the start time
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

        event.setStartTime(startTime);

        getOxifeedHelper().updateEvent(event);

    }


    public void modifyHandicapValue(int marketId, String handicapValue){

        Market market = new Market();
        market.setId(marketId);

        market.setHandicap(handicapValue);

        getOxifeedHelper().updateMarket(market);
    }


    public void modifyMarketDisplayFlagValue(int marketId, boolean display) {

        Market market = new Market();
        market.setId(marketId);

        if (display) {
            market.setDisplayed("yes");
        } else {
            market.setDisplayed("no");
        }

        getOxifeedHelper().updateMarket(market);
    }


    public void modifySelectionEventFlagValue(int eventId, boolean display) {

        Event event = new Event();
        event.setId(eventId);

        if (display) {
            event.setDisplayed(BackOfficeConstants.SELECTION_DISPLAYED_YES);
        } else {
            event.setDisplayed(BackOfficeConstants.SELECTION_DISPLAYED_NO);
        }

        getOxifeedHelper().updateEvent(event);
    }

    public boolean verifyIfEventIsInPlay(int eventId) {

        Event event = new Event();
        event.setId(eventId);

        return event.isInPlay();
    }

    public void setEventAndMarketToInPlayOrPreMatch(int eventId, int marketId, boolean inPlay) {

        // Here we set the Event and Market
        Event event = new Event();
        event.setId(eventId);

        if(inPlay) {
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
        }else{
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
        }

        getOxifeedHelper().updateEvent(event);

        Market market = new Market();
        market.setId(marketId);

        // Here we set the values according to the boolean value
        if(inPlay) {
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
        }else{
            market.setBir(BackOfficeConstants.MARKET_BIR_NO);
        }

        // Here we send those changes
        getOxifeedHelper().updateMarket(market);

    }


    @Then("^clear fury$")
    public void clearFuryData(){

         getBackOfficeHelper().cleanFuryData();
         ArrayList<Event> events = BackOffice.getInstance().cleanFuryData();
            for(int i=0;i<events.size();i++){
                Event event=(events.get(i));
                event.setStartTime(Timer.getDate(Timer.DayFilter.Yesterday));
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_NO);
                getOxifeedHelper().updateEvent(event);

        }

    }




    @Given("^get squiz$")
    public void getSquiz() throws Throwable {
        String carouseltype = "";
        int carouselelemntsAmount = 0;
        List<SquizCarouselData> carouselElemntsDisplayed = SquizVirtualHelper.getInstance().getEnabledCarouselelements();
        Boolean elementfoud=false;

        navigateToRootElement();
        assertTrue(buildListByCSS("#carousel li"));
        assertTrue(carouselElemntsDisplayed.size() == getListSize());
        for (SquizCarouselData carouselElement : carouselElemntsDisplayed) {
            carouseltype = carouselElement.getType();
            for (int i = 0; i < getListSize(); i++) {
                elementfoud=false;
                navigateToRootElement();
                navigateToListElement(i);
                switch (carouseltype) {
                    case "game":
                        if(getText().toLowerCase().contains(carouselElement.getGameNameId().replace("|","").replace(StringUtils.LF," ").toLowerCase())) {
                            carouselelemntsAmount = carouselelemntsAmount + 1;
                            elementfoud=true;
                        }
                        break;
                    case "sport":
                        if(getText().toLowerCase().contains(carouselElement.getText().replace(StringUtils.LF," ").toLowerCase())) {
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
                        if(getText().toLowerCase().contains(carouselElement.getText().replace(StringUtils.LF," ").toLowerCase())) {
                            carouselelemntsAmount = carouselelemntsAmount + 1;
                            elementfoud=true;
                        }
                        break;
                    case "other":
                        if(getText().toLowerCase().contains(carouselElement.getText().replace(StringUtils.LF," ").toLowerCase())) {
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


