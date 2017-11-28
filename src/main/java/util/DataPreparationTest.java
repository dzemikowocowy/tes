package util;

import com.williamhill.whgtf.properties.PropertyReader;
import office.BackOfficeOxifeed;
import office.openbet.model.*;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class DataPreparationTest {

    public String openbetUsername ="auto_fury";
    public String openbetPassword = "tester123";
    public String oxifeedUrl = "http://10.211.66.20/oxifeed";

    public static ArrayList<Event> events = new ArrayList<Event>();

    public static String[] NFL = new String[]{"Chicago Bears", "Green Packers",
            "Kansas City Chiefs", "New York Jets",};
    public static String[] NBA = new String[]{"Chicago Bulls", "Utah Jazz",
            "Houston Rockets", "Miami Heat","Chicago Bulls", "Utah Jazz",
            "Houston Rockets", "Miami Heat",};
    public static String[] PolishBascketball = new String[]{"Asseco Gdynia", "Anvwil Wroclawek",
            "Trefl Sopot", "Jezioro Tarnobrzeg",};
    public static String[] UKFootballTeams = new String[]{"Man Utd", "Nevcastle",
            "Crystal Palace", "Aston Villa", "Suderland", "Arsenal", "Liverpool", "Stoke",};

    public static String[] jockeys = new String[]{"Rawlinson, Alistair", "Egan, J", "Quinlan, Mr R P",
            "Fox, K", "Sousa, S De", "Broome, Miss A", "O'Connell, B T", "Twiston-Davies, W",
            "Best, J", "O'Brien, Paul", "Zechner, K", "Catton, Mr B", "Birkett, Shelley"};

    public static String[] greyhounds = new String[]{"Jogon Jenny", "Louliediem Gem", "Jayms Brightwood",
            "Witcombe Triumph", "No Picnic", "Our Girl Una", "Aintgottwobob ", "Mottos Rogue",
            "Minnies Trixie", "Crucial Diva", "Chickadee", "Chilly Baja", "Primo Lisa"};

    public static String[] USBaseball = new String[]{"Toronto Blue Jays", "Kansas City Royals", "Chicago Cubs",
            "New York Mets", "Detroit Tigers", "Cincinnati Reds", "Houston Astros", "Miami Marlins",
            "Washington Nationals", "Crucial Diva", "Toronto Blue Jays", "Seattle Mariners", "Pittsburgh Pirates"};

    public static String[] AustralianBaseball = new String[]{"Adelaide Bite", "Brisbane Bandits", "Canberra Cavalry",
            "Melbourne Aces", "Perth Heat", "Sydney Blue Sox", "Sydney Astros"};

    public static int numberOfEvents = 5;
    public static String competition = "Spanish La Liga Primera";
    public static int numberOfSelection = 2;
    public static String eventType = "in_play";
    //public static String sport = System.getProperty("sport");
    public static String marketType = "90 Minutes";
    public static String eventPrefix = "Fury test";


    public static void main(String[] args) {
        DataPreparationTest dataPrep = new DataPreparationTest();


        competition = competition.replaceAll("_"," ");
        marketType = marketType.replaceAll("_"," ");

        System.out.println("rtwe");
        String homeSelection = "";
        String awaySelection = "";
        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, +6);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.MINUTE, 55);



        // loop and create number of races.
        for (int i = 0; i < numberOfEvents; i++) {
            calendar.add(Calendar.MINUTE, 5);
            // format the start time
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(calendar.getTime());
            String raceDisplayedStartTime = new SimpleDateFormat("h:mm")
                    .format(calendar.getTime());
            String eventDisplayedStartTime = new SimpleDateFormat("ss")
                    .format(calendar.getTime());

            // add the event to OB
            Event event = new Event(eventPrefix+i+competition,
                    CompetitionIds.getCompetitionId(competition));
            event.setStartTime(raceStartTime);
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
            Event boEvent = dataPrep.getOxifeedHelper().addEvent(event);


            String market_group = marketType;


            Market market = new Market("|"+market_group+"|");


            Market boMarket = dataPrep.getOxifeedHelper().addMarket(boEvent.getId(), market);
            boEvent.addMarket(boMarket);


            List<String> SelectionsNames = Arrays.asList(jockeys);

            switch (competition.replaceAll(" ", "")) {
                case "|NBA|":
                    SelectionsNames = Arrays.asList(NBA);
                    break;
                case "|PolishCup|":
                    SelectionsNames = Arrays.asList(PolishBascketball);
                    break;
                case "EnglishPremierLeague":
                    SelectionsNames = Arrays.asList(UKFootballTeams);
                    break;
                case "EnglishLeague1":
                    SelectionsNames = Arrays.asList(UKFootballTeams);
                    break;
                case "EnglishChampionship":
                    SelectionsNames = Arrays.asList(UKFootballTeams);
                    break;
                case "|NFL|":
                    SelectionsNames = Arrays.asList(NFL);
                    break;
                case "|AFL|":
                    SelectionsNames = Arrays.asList(NFL);
                    break;
                case "|MLB|":
                    SelectionsNames = Arrays.asList(USBaseball);
                    break;
                case "|ABL|":
                    SelectionsNames = Arrays.asList(AustralianBaseball);
                    break;

            }
            // adding selections to Win/Win market groups
            if (market.getMarketGroup().contains("|Match Bets|")) {
                for (int k = 0; k < 2; k++) {
                    Selection selection = new Selection();
                    selection.setPrice((k + 2) + "/1");
                    if (k == 0) {
                        selection.setType(SelectionTypeEnum.home.getType());
                        selection.setName(StringUtils.substringBefore(market.getName(), "|vs|").trim());
                    } else if (k == 1) {
                        selection.setType(SelectionTypeEnum.away.getType());
                        selection.setName(StringUtils.substringAfter(market.getName(), "|vs|").trim());
                    }
                    dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
                }
            } else if (market.getMarketGroup().contains("|Betting Without|")) {
                // set the number of selections to create.
                for (int k = 0; k < numberOfSelection; k++) {
                    if (!market.getName().contains(SelectionsNames.get(k))) {
                        Selection selection = new Selection();
                        selection.setName(SelectionsNames.get(k));
                        selection.setPrice((k + 2) + "/1");
                        Selection boSelection = dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
                        boMarket.addSelection(boSelection);
                    }
                }

            } else if (market.getMarketGroup().contains("|Money Line|") || market.getMarketGroup().contains("|Money Line Live|")) {

                for (int k = 0; k < 2; k++) {
                    Selection selection = new Selection();
                    selection.setPrice((k + 2) + "/1");
                    int selectionNameNumber=k+i;
                    if (selectionNameNumber>=SelectionsNames.size()-1)
                        selectionNameNumber=k;
                    if (k == 0) {
                        selection.setType(SelectionTypeEnum.home.getType());
                        selection.setName(SelectionsNames.get(selectionNameNumber));
                        homeSelection = SelectionsNames.get(selectionNameNumber);
                    } else if (k == 1) {
                        selection.setType(SelectionTypeEnum.away.getType());
                        selection.setName(SelectionsNames.get(selectionNameNumber));
                        awaySelection = SelectionsNames.get(selectionNameNumber);
                    }
                    dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);

                }

            } else if (market.getMarketGroup().contains("|Run Line|") || market.getMarketGroup().contains("|Run Line Live|")) {

                for (int k = 0; k < 2; k++) {
                    Selection selection = new Selection();
                    selection.setPrice((k + 2) + "/1");
                    if (k == 0) {
                        selection.setType(SelectionTypeEnum.home.getType());
                        selection.setName(SelectionsNames.get(k + i));

                    } else if (k == 1) {
                        selection.setType(SelectionTypeEnum.away.getType());
                        selection.setName(SelectionsNames.get(k + i));

                    }
                    dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);

                }
                boMarket.setHandicap("5.5");
                dataPrep.getOxifeedHelper().updateMarket(boMarket);

            } else if (market.getMarketGroup().contains("|Total Runs|") || market.getMarketGroup().contains("|Total Runs Live|")) {
                // set the number of selections to create.
                for (int k = 0; k < numberOfSelection; k++) {
                    Selection selection = new Selection();
                    if (k > 0) {
                        selection.setType(SelectionTypeEnum.high.getType());
                        selection.setName("|Over|");
                        homeSelection = SelectionsNames.get(k + i);
                    } else {
                        selection.setType(SelectionTypeEnum.low.getType());
                        selection.setName("|Under|");
                        awaySelection = SelectionsNames.get(k + i);
                    }
//                            selection.setName(SelectionsNames.get(k));
                    selection.setRunnerOrder(String.valueOf(k + 1));
                    // We set selection price if market pricing conditions are met
                    if (market.getStartPrice().equalsIgnoreCase(BackOfficeConstants.MARKET_SP_YES) &&
                            market.getLivePrice().equalsIgnoreCase(BackOfficeConstants.MARKET_LP_NO))
                        selection.setPrice(StringUtils.EMPTY);
                    else
                        selection.setPrice((k + 2) + "/1");

                    Selection boSelection;
                    if (!selection.getPrice().isEmpty())
                        boSelection = dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
                    else {
                        // we are not able to add selections with empty prices using oxifeed.
                        selection.setOrder(String.valueOf(k));
                        boSelection = dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
//                            boSelection = getBackOfficeHelper().addSelection(boMarket.getId(), selection);
                    }
                    boMarket.setHandicap("5.5");
                    boMarket.addSelection(boSelection);
                }

            } else {
                // set the number of selections to create.
                for (int k = 0; k < numberOfSelection; k++) {
                    Selection selection = new Selection();
                    selection.setName(SelectionsNames.get(k));
                    selection.setRunnerOrder(String.valueOf(k + 1));
                    // We set selection price if market pricing conditions are met
                    if (market.getStartPrice().equalsIgnoreCase(BackOfficeConstants.MARKET_SP_YES) &&
                            market.getLivePrice().equalsIgnoreCase(BackOfficeConstants.MARKET_LP_NO))
                        selection.setPrice(StringUtils.EMPTY);
                    else
                        selection.setPrice((k + 2) + "/1");

                    Selection boSelection;
                    if (!selection.getPrice().isEmpty())
                        boSelection = dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
                    else {
                        // we are not able to add selections with empty prices using oxifeed.
                        selection.setOrder(String.valueOf(k));
                        boSelection = dataPrep.getOxifeedHelper().addSelection(boMarket.getId(), selection);
//                            boSelection = getBackOfficeHelper().addSelection(boMarket.getId(), selection);
                    }
                    boMarket.addSelection(boSelection);
                }
            }

            // add the event to the context.
//            getScenarioContext().addEvent(boEvent);
            if (!awaySelection.isEmpty() && !homeSelection.isEmpty()){
                event.setName(i + competition + "away" + awaySelection + " @ home" + homeSelection);
                dataPrep.getOxifeedHelper().updateEvent(event);

        }
        events.add(boEvent);
        }
        if(!eventType.isEmpty()) {
            if (eventType.toLowerCase().contains("in_play")) {
                dataPrep.the_all_events_are_in_play();
            }
        }
    }


//        checkEventsReadyForTesting(getScenarioContext().getEvents());





    public void the_all_events_are_in_play() {

        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -4);
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());

        if (!events.isEmpty()) {
            // add the event to OB
            for (Event event : events) {
                System.out.println(event.getId());
                System.out.println(event.getName());
                event.setStartTime(raceStartTime);
                getOxifeedHelper().updateEvent(event);
                Timer.sleep(2, TimeUnit.SECONDS);
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                getOxifeedHelper().updateEvent(event);
                Market market = event.getFirstMarket();
                market.setBir(BackOfficeConstants.MARKET_BIR_YES);
                getOxifeedHelper().updateMarket(market);
                Timer.sleep(2, TimeUnit.SECONDS);
            }

        }
    }

    public String getProperty(String prop) {
        PropertyReader pr = new PropertyReader();
        return pr.getProperty(prop);
    }

    protected BackOfficeOxifeed getOxifeedHelper() {
        BackOfficeOxifeed.getInstance().initialize(oxifeedUrl, openbetUsername, openbetPassword);
        return BackOfficeOxifeed.getInstance();
    }



}

