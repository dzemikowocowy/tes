package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import office.BackOfficeOxifeed;
import office.openbet.model.*;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.net.io.Util;
import org.apache.commons.lang3.StringUtils;
import stepdefs.shared.ScenarioContext;
import stepdefs.shared.Selectors;
import stepdefs.shared.SharedData;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

/**
 * Created by mkoza 06/04/2016
 */
public class SportsStepDef extends SportsAutomationScriptingLanguage {


    protected SportsNavigation navigation = SportsNavigationHelper.getNavigation();
    private UtilsStepDef utilsStepDef = new UtilsStepDef();

    //selection names for teams
    public static String[] spanishlaligaprimera = new String[]{"Real Madrid", "Barcelona", "Valencia", "Atletico Madrid", "Athletic Bilbao", "Sevilla", "Espanyol",
            "Real Sociedad", "Zaragoza", "Betis", "Deportivo", "Celta Vigo", "Valladolid", "Racing Santander", "Osasuna", "Sporting Gijon", "Oviedo", "Mallorca", "Las Palmas",
            "Villarreal", "Malaga", "Rayo Vallecano", "Granada", "Elche", "CD Malaga", "Hercules", "Getafe", "Tenerife", "Murcia", "Levante", "Salamanca", "Alaves", "Sabadell", "Cadiz",
            "Logrones", "Castellon", "Albacete", "Almeria", "Cordoba", "Compostela", "Recreativo", "Burgos CF", "Pontevedra", "Numancia", "Arenas", "Real Burgos", "Gimnastic", "Extremadura",
            "Merida", "Alcoyano", "Jaen", "Real Burgos", "Gimnastic", "Extremadura", "Merida", "Alcoyano", "Jaen", "Real Union", "AD Almeria", "Europa", "Lleida", "Eibar", "Xerez", "Condal",
            "Atletico Tetuan", "Cultural Leonesa", "Arsenal", "Aston Villa", "Barnsley", "Birmingham City", "Blackburn Rovers", "Blackpool",
            "Birmingham City", "Blackburn Rovers", "Blackpool", "Bolton Wanderers", "Bournemouth", "Bradford City", "Burnley", "Cardiff City",
            "Charlton Athletic", "Chelsea", "Coventry City", "Crystal Palace", "Derby County", "Everton", "Fulham", "Hull City",};

    public static String[] horses = new String[]{"It's A Close Call", "Final Nudge", "Captain Probus",
            "Pursuitofhappiness", "Abidjan", "Multimedia", "Queen Of Rock", "Sandford Castle",
            "Whatthebutlersaw", "Dreamisi", "Master Hide", "Darsi's Dream", "East Hill"};


    @Given("^a '(.*)' event with '(.*)' market and '(.*)' selections with Handicap$")
    public void aEventWithMinutesMarketAndHomeDrawAwaySelectionsWithHandicap(String competition, DataTable marketTable) throws Throwable {
        aEventMarketSelections(competition, marketTable);
    }

    @Given("^a '(.*)' event with following markets$")
    public void aEventWithMarketAndSelections(String competition, DataTable marketTable) throws Throwable {
        aEventMarketSelections(competition, marketTable);
    }

    public void aEventMarketSelections(String competition, DataTable marketTable) throws Throwable {


            //setup a selection names
            List<String> SelectionsNames = Arrays.asList(spanishlaligaprimera);
            //initialize random  to chose one from the selectionnames list
            int random = RandomUtils.nextInt(0, Arrays.asList(spanishlaligaprimera).size() - 2);


            //Define seletction names
            String SelectionHome = SelectionsNames.get(random);
            String SelectionAway = SelectionsNames.get(random + 1);
            String SelectionDraw = "|Draw|";
            String SelectionHomeDraw = SelectionsNames.get(random + 2);


            // set the race start time
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, +76);
            calendar.set(Calendar.AM_PM, Calendar.PM);
            calendar.set(Calendar.MINUTE, 55);
            calendar.add(Calendar.MINUTE, 5);
            // format the start time
            String eventStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(calendar.getTime());

            for (int n=0 ;n<40;n++) {
            // add the event to OB
            Event event = new Event("|Fury today  " + n + SelectionHome + "| vs |Fury away " + SelectionAway + "|", CompetitionIds.getCompetitionId(competition));
            event.setStartTime(eventStartTime);
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
            event.setStatus(BackOfficeConstants.EVENT_STATUS_ACTIVE);
            event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_YES);

            Event boEvent = getOxifeedHelper().addEvent(event);
            int counter = 0;
            // loop for each table row adding the defined market
            List<Map<String, String>> marketRows = marketTable.asMaps(String.class, String.class);
            for (Map<String, String> marketRow : marketRows) {
                String markettype = marketRow.get("market_type");
                String handicap = marketRow.get("handicap");
                String BIR = marketRow.get("BIR");
                // add the market to OB
                Market market = new Market(markettype.replace('\\', '|'));
                market.setStatus(BackOfficeConstants.MARKET_STATUS_ACTIVE);
                if (BIR != null) {
                    market.setBir(BIR);
                } else {
                    market.setBir(BackOfficeConstants.MARKET_BIR_NO);
                }
                market.setOrder(String.valueOf(counter));
                counter++;
                if (handicap != null) {
                    market.setHandicap(handicap);
                }
                Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
                boEvent.addMarket(boMarket);


                //CREATE SELECTION BASE OF TYPE
                switch (marketRow.get("selection_Type")) {
                    case "Home/Draw/Away":
                        for (int k = 0; k < 3; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.home.getType());
                                selection.setName("|Fury ECT home " + SelectionHome + "|");

                            } else if (k == 1) {
                                selection.setType(SelectionTypeEnum.draw.getType());
                                selection.setName(SelectionDraw);

                            } else {
                                selection.setType(SelectionTypeEnum.away.getType());
                                selection.setName("|Fury ECT away " + SelectionAway + "|");
                            }

                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case "Home/Away/Line":
                        for (int k = 0; k < 3; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.home.getType());
                                selection.setName("|Fury ECT home " + SelectionHome + "|");

                            } else if (k == 1) {
                                selection.setType(SelectionTypeEnum.line.getType());
                                selection.setName("Line");

                            } else {
                                selection.setType(SelectionTypeEnum.away.getType());
                                selection.setName("|Fury ECT away " + SelectionAway + "|");
                            }

                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case "HomeDraw/DrawAway/HomeAway":
                        for (int k = 0; k < 3; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.homehome.getType());
                                selection.setName("|Fury ECT home " + SelectionHome + "|- |Draw|");

                            } else if (k == 1) {
                                selection.setType(SelectionTypeEnum.homedraw.getType());
                                selection.setName("|Draw| - |Fury ECT away " + SelectionHomeDraw + "|");

                            } else {
                                selection.setType(SelectionTypeEnum.homeaway.getType());
                                selection.setName("|Fury ECT home " + SelectionHome + "|- |GSV ECT away " + SelectionAway + "|");
                            }
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case "Over/Middle/Under":
                        for (int k = 0; k < 3; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.over.getType());
                                selection.setName("|Over|");

                            } else if (k == 1) {
                                selection.setType(SelectionTypeEnum.homedraw.getType());
                                selection.setName("|Middle|");

                            } else {
                                selection.setType(SelectionTypeEnum.under.getType());
                                selection.setName("|Under|");
                            }
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        //middle type can not be set up by oxifeed  that why middle is set up  after adding a selection   by phantomjs
                        getBackOfficeHelper().setSelectionToMiddle(boEvent.getFirstMarket().getSelectionByIndex(1).getId());
                        break;
                    case "High/Low":
                        for (int k = 0; k < 2; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.low.getType());
                                selection.setName("Over");

                            } else {
                                selection.setType(SelectionTypeEnum.high.getType());
                                selection.setName("Under");
                            }
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case "Home/Away":
                        for (int k = 0; k < 2; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 1) + "/" + (k + 2));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.home.getType());
                                selection.setName(SelectionHome);

                            } else {
                                selection.setType(SelectionTypeEnum.away.getType());
                                selection.setName(SelectionAway);

                            }
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case "Odd/Even":
                        for (int k = 0; k < 2; k++) {
                            Selection selection = new Selection();
                            selection.setPrice((k + 2) + "/" + (k + 3));

                            if (k == 0) {
                                selection.setType(SelectionTypeEnum.odd.getType());
                                selection.setName(SelectionHome);

                            } else {
                                selection.setType(SelectionTypeEnum.even.getType());
                                selection.setName(SelectionAway);

                            }
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    case " HomeScore/DrawScore/AwayScore":
                        //create home selections
                        for (int i = 1; i < 8; i++) {
                            Selection selection1 = new Selection();
                            selection1.setName(String.format(SelectionHome + "%s-%s", i, i - 1));
                            selection1.setType(BackOfficeConstants.SelectionTypes.score.toString());
                            market.addSelection(getOxifeedHelper().addScoreSelection(market.getId(), selection1));
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection1);
                            boMarket.addSelection(boSelection);

                            Selection selection2 = new Selection();
                            selection2.setName(String.format(SelectionHome + "%s-%s", i + 1, i - 1));
                            selection2.setType(BackOfficeConstants.SelectionTypes.score.toString());
                            market.addSelection(getOxifeedHelper().addScoreSelection(market.getId(), selection2));
                            boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection2);
                            boMarket.addSelection(boSelection);
                        }

                        // create Draw selections
                        for (int i = 0; i < 5; i++) {
                            Selection selection1 = new Selection();
                            selection1.setName(String.format(SelectionDraw + "%s-%s", i, i));
                            selection1.setType(BackOfficeConstants.SelectionTypes.score.toString());
                            market.addSelection(getOxifeedHelper().addScoreSelection(market.getId(), selection1));
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection1);
                            boMarket.addSelection(boSelection);
                        }

                        // create Away selections
                        for (int i = 1; i < 8; i++) {
                            Selection selection1 = new Selection();
                            selection1.setName(String.format(SelectionAway + "%s-%s", i - 1, i));
                            selection1.setType(BackOfficeConstants.SelectionTypes.score.toString());
                            market.addSelection(getOxifeedHelper().addScoreSelection(market.getId(), selection1));
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection1);
                            boMarket.addSelection(boSelection);

                            Selection selection2 = new Selection();
                            selection2.setName(String.format(SelectionAway + "%s-%s", i - 1, i + 1));
                            selection2.setType(BackOfficeConstants.SelectionTypes.score.toString());
                            market.addSelection(getOxifeedHelper().addScoreSelection(market.getId(), selection2));
                            boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection2);
                            boMarket.addSelection(boSelection);
                        }
                        break;
                    default:
                        //any other combination of selections
                        String[] arrayOfSelections = convertToArrayOfSelections(marketRow.get("selection_Type"));
                        for (int i = 0; i < arrayOfSelections.length; i++) {
                            Selection selection = new Selection();
                            selection.setPrice((i + 2) + "/" + (i + 3));
                            selection.setName(arrayOfSelections[i]);
                            selection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(selection);
                        }
                }
            }
            // add the event to the context.
            getScenarioContext().addEvent(boEvent);
      //       getBackOfficeHelper().setEventCountry(boEvent.getId(),"USA");
        }
            //verify that event  exist in PDS and is ready to test zmien to
            checkEventsReadyForTesting(getScenarioContext().getEvents());


    }

    /**
     * Creates event(s) with one market, and a number of selections
     */
    @And("a '(.*)' race with (\\d+) (?:horses|greyhounds|selections) and the following markets:$")
    public void and_a_race_with_the_following_markets(String meetingName, int selections, DataTable marketTable) throws Throwable {
        and_n_races_with_the_following_markets(1, meetingName, selections, marketTable);
    }

    @And("^(\\d+) '(.*)' races with (\\d+) (?:horses|greyhounds|selections) and the following markets:$")
    public void and_n_races_with_the_following_markets(int races, String meetingName, int selections, DataTable marketTable) throws Throwable {

        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 1);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.MINUTE, 55);
        //calendar.set(Calendar.SECOND, 0);

        // loop and create number of races.
        for (int i = 0; i < races; i++) {
            calendar.add(Calendar.MINUTE, 5);
            // format the start time
            String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(calendar.getTime());
            String raceDisplayedStartTime = new SimpleDateFormat("h:mm")
                    .format(calendar.getTime());

            // add the event to OB
            Event event = new Event(raceDisplayedStartTime + " " + meetingName,
                    CompetitionIds.getCompetitionId(meetingName));
            event.setStartTime(raceStartTime);
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
            Event boEvent = getOxifeedHelper().addEvent(event);

            // loop for each table row adding the defined market
            List<Map<String, String>> marketRows = marketTable.asMaps(String.class, String.class);
            for (int j = 0; j < marketRows.size(); j++) {
                Map<String, String> marketRow = marketRows.get(j);

                String market_group = marketRow.get("market_group");
                String name = marketRow.get("name");
                String order = marketRow.get("order");
                String startPrice = marketRow.get("SP");
                String livePrice = marketRow.get("LP");
                String guaranteedPrice = marketRow.get("GP");
                String earlyPrice = marketRow.get("early_prices");
                String eachWay = marketRow.get("each_way");
                String eachWayPlaces = marketRow.get("each_way_places");
                String eachWayPlacesAt = marketRow.get("each_way_places_at");
                String blurbText = marketRow.get("blurb");

                Market market = new Market(market_group.replace('\\', '|'));
                if (name != null)
                    market.setName(name.replace('\\', '|'));
                if (order != null)
                    market.setOrder(order);
                else
                    market.setOrder(String.valueOf(j));
                if (startPrice != null)
                    market.setStartPrice(startPrice);
                if (livePrice != null)
                    market.setLivePrice(livePrice);
                if (guaranteedPrice != null)
                    market.setGuaranteedPrice(guaranteedPrice);
                if (earlyPrice != null)
                    market.setEarlyPrices(earlyPrice);
                if (eachWay != null)
                    market.setEachWay(eachWay);
                if (eachWayPlaces != null)
                    market.setEachWayPlaces(eachWayPlaces);
                if (eachWayPlacesAt != null)
                    market.setEachWayPlacesAt(eachWayPlacesAt);

                Market boMarket = getOxifeedHelper().addMarket(boEvent.getId(), market);
                boEvent.addMarket(boMarket);
                // market blurb can't be set with oxifeed. We use PhantomJS.
                if (blurbText != null) {
                    market.setBlurb(blurbText);
                    getBackOfficeHelper().setMarketBlurb(boMarket.getId(), blurbText);
                }


                List<String> horseSelections = Arrays.asList(horses);
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
                        getOxifeedHelper().addSelection(boMarket.getId(), selection);
                    }
                } else if (market.getMarketGroup().contains("|Betting Without|")) {
                    // set the number of selections to create.
                    for (int k = 0; k < selections; k++) {
                        if (!market.getName().contains(horseSelections.get(k))) {
                            Selection selection = new Selection();
                            selection.setName(horseSelections.get(k));
                            selection.setPrice((k + 2) + "/1");
                            Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                            boMarket.addSelection(boSelection);
                        }
                    }
                } else {
                    // set the number of selections to create.
                    for (int k = 0; k < selections; k++) {
                        Selection selection = new Selection();
                        selection.setName(horseSelections.get(k));
                        selection.setRunnerOrder(String.valueOf(k + 1));
                        // We set selection price if market pricing conditions are met
                        if (market.getStartPrice().equalsIgnoreCase(BackOfficeConstants.MARKET_SP_YES) &&
                                market.getLivePrice().equalsIgnoreCase(BackOfficeConstants.MARKET_LP_NO))
                            selection.setPrice(StringUtils.EMPTY);
                        else
                            selection.setPrice((k + 2) + "/1");

                        Selection boSelection;
                        if (!selection.getPrice().isEmpty())
                            boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                        else {
                            // we are not able to add selections with empty prices using oxifeed.
                            selection.setOrder(String.valueOf(k));
                            boSelection = getBackOfficeHelper().addSelection(boMarket.getId(), selection);
                        }
                        boMarket.addSelection(boSelection);
                    }
                }
            }

            // add the event to the context.
            getScenarioContext().addEvent(boEvent);
        }

        checkEventsReadyForTesting(getScenarioContext().getEvents());
    }


    public String[] convertToArrayOfSelections(String selections) throws Throwable {
        return selections.split("/");
    }

    @When("^add new (in-play|pre-match) market '(.*)' with selections type '(.*)' to created event$")
    public void addNewMarketOutrightToCreatedEvent(String birFlag, String markettype, String selectionType) throws Throwable {
        Event event = ScenarioContext.getInstance().getFirstEvent();
        Market market = new Market(markettype);
        market.setStatus(BackOfficeConstants.MARKET_STATUS_ACTIVE);

        if (birFlag.equals("in-play")) {
            market.setBir(BackOfficeConstants.MARKET_BIR_YES);
        } else {
            market.setBir(BackOfficeConstants.MARKET_BIR_NO);
        }

        Market boMarket = getOxifeedHelper().addMarket(event.getId(), market);
        event.addMarket(boMarket);


        //CREATE SELECTION BASE OF TYPE
        switch (selectionType) {
            case "Home/Draw/Away":
                for (int k = 0; k < 3; k++) {
                    Selection selection = new Selection();
                    selection.setPrice((k + 1) + "/" + (k + 2));

                    if (k == 0) {
                        selection.setType(SelectionTypeEnum.home.getType());
                        selection.setName("|Fury ECT home " + "Home" + "|");

                    } else if (k == 1) {
                        selection.setType(SelectionTypeEnum.draw.getType());
                        selection.setName("Draw");

                    } else {
                        selection.setType(SelectionTypeEnum.away.getType());
                        selection.setName("|Fury ECT away " + "Away" + "|");
                    }

                    Selection boSelection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                    boMarket.addSelection(boSelection);
                }
                break;
            default:
                //any other combination of selections
                String[] arrayOfSelections = convertToArrayOfSelections(selectionType);
                for (int i = 0; i < arrayOfSelections.length; i++) {
                    Selection selection = new Selection();
                    selection.setPrice((i + 2) + "/" + (i + 3));
                    selection.setName(arrayOfSelections[i]);
                    selection = getOxifeedHelper().addSelection(boMarket.getId(), selection);
                    boMarket.addSelection(selection);
                }
        }
    }

    @And("^the event is (in-play|pre-match)$")
    public void andTheEventIsInPlay(String eventType) throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        int timeToSetUp = +2;
        int updateTime = 9000;
        String offFlag = BackOfficeConstants.EVENT_OFF_FLAG_NO;
        if (eventType.contains("in-play")) {
            timeToSetUp = -4;
            offFlag = BackOfficeConstants.EVENT_OFF_FLAG_YES;
            updateTime = 90000;
        }
        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, timeToSetUp);
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());

        if (!events.isEmpty()) {
            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {

                event.setStartTime(raceStartTime);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();

                event.setOffFlag(offFlag);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
            }
        }
        sleep(updateTime);
    }

    @And("^the event and market are in-play$")
    public void andTheEventAndMarketIsInPlay() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -4);
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());
        if (!events.isEmpty()) {

            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {
                System.out.println(event.getId());
                System.out.println(event.getName());
                event.setStartTime(raceStartTime);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                getOxifeedHelper().updateEvent(event);
                for (Market market : event.getMarkets()) {
                    market.setBir(BackOfficeConstants.MARKET_BIR_YES);
                    getOxifeedHelper().updateMarket(market);
                    waitAfterPDSUpdate();
                }
            }
        }
        sleep(90000);
    }

    @And("^the event is in-play in Football page$")
    public void andTheEventIsInPlayInFootballPage() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();

        // set the race start time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -4);
        calendar.add(Calendar.MONTH, -4);
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());

        if (!events.isEmpty()) {
            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {
                System.out.println(event.getId());
                System.out.println(event.getName());
                event.setStartTime(raceStartTime);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
                event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                getOxifeedHelper().updateEvent(event);
                for (Market market : event.getMarkets()) {
                    market.setBir(BackOfficeConstants.MARKET_BIR_YES);
                    getOxifeedHelper().updateMarket(market);
                    waitAfterPDSUpdate();
                }
            }
        }
        sleep(10000);
    }

    @Then("^the correct event name is displayed$")
    public void theCorrectEventNameIsDisplayed() throws Throwable {
        String eventname = getScenarioContext().getFirstEvent().getName().replace("|", "");
        navigateToRootElement();
        assertTrue(navigateToElementById("contentarea"));
        assertTrue(navigateToElementByTag("header"));
        if (navigateToElementByClassName("h1")) {
            //verify that on event page correct event name will display  this is working only for one event
            String eventNameFromPage = getText();
            assertTrue(eventNameFromPage.contains(eventname));

        } else {
            navigateToRootElement();
            assertTrue(navigateToElementById("contentarea"));
            navigateToElementByClassName("header-panel");
            assertTrue(navigateToElementByClassName("header-panel__title"));
            assertTrue(getText().contains(eventname));

        }
    }

    @Then("^the correct event name is displayed on in play list$")
    public void theCorrectEventNameIsDisplayedOnInPlay() throws Throwable {
        String eventname = getScenarioContext().getFirstEvent().getName().replace("|", "");
        String eventId = "OB_EV" + ScenarioContext.getInstance().getFirstEvent().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById("contentarea"));
        assertTrue(navigateToElementByTag("header"));
        if (navigateToElementByClassName("h1")) {
            //verify that on event page correct event name will display  this is working only for one event
            String eventNameFromPage = getText();
            assertTrue(eventNameFromPage.contains(eventname));
        } else {
            navigateToRootElement();
            assertTrue(navigateToElementById(eventId));
            buildListByClassName("btmarket");
            navigateToListElement(1);
            navigateToElementByClassName("btmarket__name ");
            String eventNameFromPage = getAttribute("title");
            assertTrue(eventNameFromPage.contains(eventname));
        }
    }

    @Then("^the correct event name is displayed on competition list$")
    public void theCorrectEventNameIsDisplayedOnCompetitionList() throws Throwable {
        String eventname = getScenarioContext().getFirstEvent().getName().replace("|", "");
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        sleep(5000);
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//div[@id='" + eventId + "']"));
        buildListByClassName("btmarket");
        navigateToListElement(1);
        assertTrue(navigateToElementByClassName("btmarket__name "));
        String nameFromThePage = getAttribute("title");
        assertTrue(nameFromThePage.contains(eventname));
    }


    @Then("^the correct event name is displayed in (highlights|in-play) section on the football page$")
    public void theCorrectEventNameIsDisplayedFootballPage(final String section) throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String eventname = getScenarioContext().getFirstEvent().getName().replace("|", "");
        navigateToRootElement();
        if ("highlights".equalsIgnoreCase(section)) {
            navigateToElementById("match-highlights");
        } else {
            navigateToElementById("in-play-now");
        }
        navigateToElementById(eventId);
        navigateToElementByCSS(".btmarket__content-marginrs a");
        String eventNameFromPage = getAttribute("title");
        assertTrue(eventNameFromPage.contains(eventname));
    }

    @Then("^the correct event name is displayed in (highlights|in-play) section$")
    public void theCorrectEventNameIsDisplayedinSection(final String section) throws Throwable {
        theCorrectEventNameIsDisplayedFootballPage(section);
    }

    @And("^the correct market name is displayed$")
    public void theCorrectMarketNameIsDisplayed() throws Throwable {

        String marketname = getScenarioContext().getFirstEvent().getFirstMarket().getName().replace("|", "");
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        navigateToRootElement();
        //market name navigation  is in different  name  for coupons page
        String cuponName = getBackOfficeHelper().getCuponName();
        assertTrue(navigateToElementById("contentarea"));
        assertTrue(navigateToElementById(marketId));
        if (!navigateToElementByClassName("header-dropdown")) {
            navigateToRootElement();
            //verify that on coupon event page market name is correct this is working only for first event market
            assertTrue(navigateToElementById(competitionId));
            assertTrue(navigateToElementByClassName("btmarket__name "));
        }
        String marketNameFromPage = getText();
        assertTrue(marketname.contains(marketNameFromPage));

    }

    @And("^the correct market name is displayed on In Play list$")
    public void theCorrectMarketNameIsDisplayedOnInPlayAll() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String marketname = getScenarioContext().getFirstEvent().getFirstMarket().getName().replace("|", "");
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        navigateToRootElement();
        //market name navigation  is in different  name  for coupons page
        assertTrue(navigateToElementById("contentarea"));
        assertTrue(navigateToElementById(marketId));
        navigateToRootElement();
        assertTrue(navigateToElementById(competitionId));
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToParent());
        assertTrue(navigateToElementByClassName("btmarket__name "));
        String marketNameFromPage = getText();
        assertTrue(marketname.contains(marketNameFromPage));

    }

    @And("^markets and selections are displayed correctly$")
    public void checkMarketsAndSelections() {
        List<Market> marketList = getScenarioContext().getFirstEvent().getMarkets();
        String eventId = getScenarioContext().getFirstEvent().getPdsId();
        String marketName;
        String marketNameFromPage;
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        int marketListSize = 0;
        //iterate over the markets
        for (Market market : marketList) {
            marketName = market.getName().replace("|", "");
            navigateToRootElement();
            navigateToElementById(eventId);
            navigateToParent();
            navigateToParent();
            navigateToElementByClassName("header-dropdown");
            marketNameFromPage = getText();
            assertTrue(marketName.contains(marketNameFromPage));
            //Check all the selection of the market
            List<Selection> selections = market.getSelections();
            String marketId = "OB_MA" + market.getId();
            navigateToRootElement();
            //assertTrue(navigateToElementById("contentarea"));
            assertTrue(navigateToElementById(marketId));
            //build list of all selections belongs to created market on the page
            buildListByPartialId("OB_OU");
            int i = 0;
            for (Selection selection : selections) {
                assertTrue(getListSize() == selections.size());
                navigateToRootElement();
                navigateToListElement(i);
                assertTrue(getAttribute("data-odds").contains(selection.getPrice()));
                i = i + 1;

            }

            String cuponName = getBackOfficeHelper().getCuponName();
            navigateToRootElement();
            if (cuponName == null) {
                assertTrue(navigateToElementById("contentarea"));
                assertTrue(navigateToElementById(marketId));
            } else {
                assertTrue(navigateToElementById(competitionId));
                assertTrue(navigateToElementByClassName("events-wrapper"));
                assertTrue(navigateToElementByClassName("btmarket"));
                assertTrue(navigateToElementByClassName("btmarket__actions"));
            }

            market.setOrder(market.getOrder().toString() + 100);
            getOxifeedHelper().updateMarket(market);
            if (marketListSize < marketList.size() - 1) {
                sleep(240000);
                refresh();
                marketListSize++;
            }

        }
        if (marketList.size() == 0) {
            throw new PendingException("Market list has no elements");
        }
    }


    @And("^markets and selections are displayed correctly in (in-play|highlights) section$")
    public void checkMarketsAndSelections2(final String section) {
        boolean ok = false;
        List<Market> marketList = getScenarioContext().getFirstEvent().getMarkets();
        String eventId = getScenarioContext().getFirstEvent().getPdsId();
        String marketName;
        String marketId;
        String marketNameFromPage = null;
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        int marketListSize = 0;


        for (Market market : marketList) {
            marketName = market.getName().replace("|", "");
            marketId = "OB_MA" + market.getId();
            navigateToRootElement();
            if ("in-play".equalsIgnoreCase(section)) {
                navigateToElementById("in-play-now");
            } else {
                navigateToElementById("match-highlights");
            }
            buildListByClassName("header-dropdown");
            for (int i = 0; i < getListSize(); i++) {
                navigateToListElement(i);
                if (getText().contains(marketName)) {
                    navigateToParent();
                    buildListByClassName("btmarket__wrapper ");
                    navigateToListElement(i);
                    assertTrue(navigateToElementById(eventId));
                    break;
                }
            }

            String cuponName = getBackOfficeHelper().getCuponName();
            navigateToRootElement();
            if (cuponName == null) {
                assertTrue(navigateToElementById("contentarea"));
                assertTrue(navigateToElementById(marketId));
            } else {
                assertTrue(navigateToElementById(competitionId));
                assertTrue(navigateToElementByClassName("events-wrapper"));
                assertTrue(navigateToElementByClassName("btmarket"));
                assertTrue(navigateToElementByClassName("btmarket__actions"));
            }


        }

        if (marketList.size() == 0) {
            throw new PendingException("Market list has no elements");
        }
    }

    @And("^the correct market name is displayed on competition list$")
    public void theCorrectMarketNameIsDisplayedOnCompetitionList() throws Throwable {
        String eventID = "OB_EV" + ScenarioContext.getInstance().getFirstEvent().getId();
        String marketName = getScenarioContext().getFirstEvent().getFirstMarket().getName().replace("|", "");
        navigateToRootElement();
        assertTrue(navigateToElementById("competitions-list"));
        assertTrue(navigateToElementByClassName("marketmain"));
        assertTrue(navigateToElementByClassName("matches"));
        assertTrue(navigateToElementById(eventID));
        navigateToParent();
        assertTrue(navigateToElementByClassName("mg-header"));
        assertTrue(navigateToElementByClassName("btmarket__content"));
        assertTrue(getText().contains(marketName));
    }

    @And("^the correct selections displayed with correct order$")
    public void theCorrectSelectionsDisplayedWithCorrectOrder() throws Throwable {
        String selectionPricefromPage;
        String selectionPrice;
        List<Market> markets = getScenarioContext().getFirstEvent().getMarkets();
        for (Market market : markets) {
            List<Selection> selections = market.getSelections();
            String marketId = "OB_MA" + market.getId();
            navigateToRootElement();
            //assertTrue(navigateToElementById("contentarea"));
            assertTrue(navigateToElementById(marketId));
            //build list of all selections belongs to created market on the page
            buildListByPartialId("OB_OU");
            int i = 0;
            for (Selection selection : selections) {
                assertTrue(getListSize() == selections.size());
                navigateToRootElement();
                navigateToListElement(i);
                selectionPricefromPage = getAttribute("data-odds");
                selectionPrice = selection.getPrice();

                assertTrue(selectionPricefromPage.contains(selectionPrice));
                i = i + 1;

            }
        }
    }

    @And("^the selections labels displayed$")
    public void theSelectionsLabelsDisplayed() throws Throwable {
        String competitionId = "comp-OB_TY" + getScenarioContext().getFirstEvent().getCompetitionId();
        navigateToRootElement();
        List<Market> marketList = getScenarioContext().getFirstEvent().getMarkets();
        for (Market market : marketList) {
            String marketId = "OB_MA" + market.getId();
            String cuponName = getBackOfficeHelper().getCuponName();
            if (cuponName == null) {
                assertTrue(navigateToElementById("contentarea"));
                assertTrue(navigateToElementById(marketId));
            } else {
                assertTrue(navigateToElementById(competitionId));
                assertTrue(navigateToElementByClassName("events-wrapper"));
                assertTrue(navigateToElementByClassName("btmarket"));
                assertTrue(navigateToElementByClassName("btmarket__actions"));
            }
            buildListByClassName("btmarket__name");
            for (int i = 0; i < getListSize(); i++) {
                navigateToListElement(i);
                assertTrue(getText().length() > 0);

            }

        }
    }


    @And("^Handicap value displayed correctly")
    public void handicapValueDisplayedCorrectlyFor() throws Throwable {
        Boolean handiPositive = false;
        List<Market> marketList = getScenarioContext().getFirstEvent().getMarkets();
        for (Market market : marketList) {
            String marketId = "OB_MA" + market.getId();
            String BackOfficeHandiCap = market.getHandicap();
            List<Selection> selections = market.getSelections();
            String BackOfficeMarketType = market.getMarketGroup();
            if (Integer.parseInt(BackOfficeHandiCap) > 0) {
                handiPositive = true;
            }

            navigateToRootElement();
            assertTrue(navigateToElementById("contentarea"));
            assertTrue(navigateToElementById(marketId));
            buildListByClassName("btmarket__name");
            for (int i = 0; i < getListSize(); i++) {
                navigateToListElement(i);
                assertTrue(navigateToElementByClassName("selectionhandicap"));
                switch (BackOfficeMarketType) {
                    case "|Handicap|":
                        if (selections.size() == 3) {
                            if (handiPositive) {
                                if (i < 2) {
                                    assertTrue(getText().contains("+" + BackOfficeHandiCap));
                                } else {
                                    assertTrue(getText().contains("-" + BackOfficeHandiCap));
                                }
                            } else {
                                if (i < 2) {
                                    assertTrue(getText().contains(BackOfficeHandiCap));
                                } else {
                                    assertTrue(getText().contains(BackOfficeHandiCap.replace("-", "+")));
                                }
                            }
                        }
                        break;
                    case "|Spread|":
                        if (selections.size() == 2) {
                            if (handiPositive) {
                                if (i < 1) {
                                    assertTrue(getText().contains("+" + BackOfficeHandiCap));
                                } else {
                                    assertTrue(getText().contains("-" + BackOfficeHandiCap));
                                }
                            } else {
                                if (i < 1) {
                                    assertTrue(getText().contains(BackOfficeHandiCap));
                                } else {
                                    assertTrue(getText().contains(BackOfficeHandiCap.replace("-", "+")));
                                }
                            }
                        }
                        break;
                    case "|Half Over/Under Goals|":
                        if (selections.size() == 2) {
                            assertTrue(getText().contains(BackOfficeHandiCap));
                        }
                        break;
                }
            }

        }
    }

    @And("^click on the first selection to the betslip$")
    public void addFirstSelectionToTheBetslip() throws Throwable {
        navigateTofirstSelection();
        click();
    }

    public void navigateTofirstSelection() throws Throwable {
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        //assertTrue(navigateToElementById("contentarea"));
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
    }

    @And("^the event is suspended$")
    public void theEvenIsSuspended() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        if (!events.isEmpty()) {
            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {
                event.setStatus(BackOfficeConstants.EVENT_STATUS_SUSPENDED);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
            }

        }
    }

    @And("^the market is (suspended|active)$")
    public void theMarketIsSuspended(String option) throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        if (!events.isEmpty()) {
            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {
                Market market = event.getFirstMarket();
                if (option.contains("active")) {
                    market.setStatus(BackOfficeConstants.MARKET_STATUS_ACTIVE);
                } else {
                    market.setStatus(BackOfficeConstants.MARKET_STATUS_SUSPENDED);
                }
                getOxifeedHelper().updateEvent(event);
                getOxifeedHelper().updateMarket(market);
                waitAfterPDSUpdate();
            }
        }
    }

    @And("^the first selection is suspended$")
    public void theFirstSelectionIsSuspended() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        if (!events.isEmpty()) {
            // add the event to OB and update off fla and bir to yes  (change the pre match to in play)
            for (Event event : events) {
                Selection selection = event.getFirstMarket().getFirstSelection();
                selection.setStatus(BackOfficeConstants.SELECTION_STATUS_SUSPENDED);
                getOxifeedHelper().updateEvent(event);
                getOxifeedHelper().updateSelection(selection);
                waitAfterPDSUpdate();
            }
        }
    }

    @And("^the first selection price '(.*)'$")
    public void theFirstSelectionPriceIncrese(String operation) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        Selection selection = market.getFirstSelection();
        String selectionPrice = selection.getPrice();
        char[] stakeValues = selectionPrice.toCharArray();
        String orginalStakeValue = "";
        String updatedStakeValue = "";
        int i = 0;
        for (char stakeValue : stakeValues) {
            if (i == 0 && operation.contains("increase")) {
                orginalStakeValue = String.valueOf(stakeValue);
                updatedStakeValue = String.valueOf(Integer.parseInt(Character.toString(stakeValue)) + 2);
            } else if (i == 2 && operation.contains("decrease")) {
                orginalStakeValue = String.valueOf(stakeValue);
                updatedStakeValue = String.valueOf(Integer.parseInt(Character.toString(stakeValue)) + 2);
            }
            i = i + 1;
        }
        selectionPrice = selectionPrice.replaceAll(orginalStakeValue, updatedStakeValue);
        selection.setPrice(selectionPrice);
        getOxifeedHelper().updateSelection(selection);
        waitAfterPDSUpdate();

        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        String priceAfter = getAttribute("data-odds");
        assertFalse(priceAfter.equals(orginalStakeValue));
    }

    @Then("^update goal score$")
    public void updateGoalScore() throws Throwable {
        JmsMessageTransmitter incidentSender = new JmsConfig(footballQueueName, jmsConnectionString).createJmsMessageTransmitter();
        incidentSender.sendMessage(getOxifootballHelper().updateScore("" + getScenarioContext().getFirstEvent().getId()));
        sleep(15000);
    }

    @And("^remove all selections from the betslip$")
    public void removeSelectionFromTheBetslip() throws Throwable {
        // executeScript("window.WH.betslip.clear();");
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("betslip-content"));
        if (isHidden()) {
            executeScript("$('#open-betslip')[0].click()");
        }
        assertTrue(navigateToRootElement());
        assertTrue(navigateToElementById("betslip-content"));
        assertTrue(buildListByClassName("remove-bet"));

        for (int i = 0; i < getListSize(); i++) {
            assertTrue(navigateToListElement(i));
            click();
            sleep(50);
        }
    }

    @Then("^the selection price increased$")
    public void theSelectionPriceIncreased() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String selectionId = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionId));
        navigateToParent();
        navigateToElementByTag("button");
        assertTrue(hasBackgroundColour("#a0d69f"));
        assertTrue(getAttribute("data-odds").contains(getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getPrice()));


    }


    @Then("^the first event is not longer visible$")
    public void theFirstEventIsNotLongerVisible() throws Throwable {
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        navigateToRootElement();
        if ((navigateToElementById(eventId))) {
            navigateToRootElement();
            assertTrue(navigateToElementById(eventId));
            assertFalse(isDisplayed());
        } else
            assertFalse(navigateToElementById(eventId));
    }

    @And("^the event start date is set up for tomorrow$")
    public void theEventStartDateIsSetUpForTomorrow() throws Throwable {
        theEventStartTimeIsSetUpForHoursFromNow(8);
    }

    @And("^the event start time is set up for '(\\d+)' hours from now$")
    public void theEventStartTimeIsSetUpForHoursFromNow(int time) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        // set the race start time
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, +1);
        //calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.HOUR, +time);
        // format the start time
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());
        // add the event to OB
        event.setStartTime(raceStartTime);
        getOxifeedHelper().updateEvent(event);
        waitAfterPDSUpdate();
        getOxifeedHelper().updateEvent(event);
        Market market = event.getFirstMarket();
        getOxifeedHelper().updateMarket(market);
        waitAfterPDSUpdate();
    }

    @And("^the all events start date is set up for tomorrow$")
    public void theAllEventsStartDateIsSetUpForTomorrow() throws Throwable {
        List<Event> events = getScenarioContext().getEvents();

        // set the race start time
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, +1);
        calendar.add(Calendar.HOUR, +4);
        //calendar.set(Calendar.SECOND, 0);
        // format the start time
        String raceStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());
        if (!events.isEmpty()) {
            for (Event event : events) {
                // add the event to OB
                event.setStartTime(raceStartTime);
                getOxifeedHelper().updateEvent(event);
                waitAfterPDSUpdate();
                getOxifeedHelper().updateEvent(event);
                Market market = event.getFirstMarket();
                getOxifeedHelper().updateMarket(market);
                waitAfterPDSUpdate();
            }
        }
    }

    @And("^the selection price changed$")
    public void theSelectionPriceChanged() throws Throwable {

        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        String marketId = "OB_MA" + getScenarioContext().getFirstEvent().getFirstMarket().getId();
        String selectionID = "OB_OU" + getScenarioContext().getFirstEvent().getFirstMarket().getFirstSelection().getId();
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionID));
        String priceBefore = getAttribute("data-odds");
        change_first_selection_price("5/1");
        waitSportsbook();
        sleep(5000);
        navigateToRootElement();
        assertTrue(navigateToElementById(eventId));
        assertTrue(navigateToElementById(marketId));
        assertTrue(navigateToElementById(selectionID));
        String priceAfter = getAttribute("data-odds");
        assertFalse(priceAfter.equals(priceBefore));

    }

    private void change_first_selection_price(String price) throws Throwable {
        Event event = getScenarioContext().getFirstEvent();
        Market market = event.getFirstMarket();
        Selection selection = market.getFirstSelection();
        selection.setPrice(price);
        getOxifeedHelper().updateSelection(selection);
        waitAfterPDSUpdate();

    }

    @When("^the user clicks the first active selection$")
    public void the_user_clicks_the_first_active_selection() throws Throwable {
        // add first active selection to the slip
        if (isSportsbookOnDesktop()) {
            assertTrue(navigateToRootElement());
            assertTrue(waitForPresenceOfElementByCss("[id^='OB_EV']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-status='A']:not(.disabled-market) [id^='OB_OU'][data-status='A']:not(.disabled)"));
            assertTrue(navigateToElementByCSS("[id^='OB_EV']:not(.disabled-event) " +
                    "[id^='OB_MA'][data-status='A']:not(.disabled-market) [id^='OB_OU'][data-status='A']:not(.disabled)"));
        } else {
            assertTrue(navigateToRootElement());
            assertTrue(navigateToElementById("topbets"));
            assertTrue(navigateToElementByClassName("topbets-prop"));
        }
        assertTrue(click());
        sleep(2000);
    }

    @And("^the user expanded competition for created event$")
    public void theUserExpandedCompetitionForCreatedEvent() throws Throwable {
        String CompetitionId = "OB_TY" + ScenarioContext.getInstance().getFirstEvent().getCompetitionId();
        int listsize;
        int regionexapnded = 0;

        navigateToRootElement();
        assertTrue(navigateToElementById("competitions-list"));
        assertTrue(navigateToElementByClassName("marketmain"));
        saveCurrentElementPosition();
        assertTrue(navigateToElementByAttributeValue("data-name", "Matches"));
        click();
        restoreElementPosition();
        assertTrue(navigateToElementByClassName("matches"));
        buildListByClassName("header-dropdown ");
        listsize = getListSize();
        for (int i = 0; i < listsize; i++) {
            navigateToRootElement();
            assertTrue(navigateToListElement(i));
            if (getAttribute("class").contains("level1")) {
                assertTrue(navigateToElementByClassName("header__toolbar"));
                navigateToChild();
                if (!getAttribute("class").contains("expanded")) {
                    click();
                    sleep(2000);
                    regionexapnded = regionexapnded + 1;

                }
            }
        }
        navigateToRootElement();
        assertTrue(navigateToElementByClassName("matches"));
        buildListByClassName("grid ");

        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            navigateToChild();
            if (isDisplayed()) {
                click();
                sleep(2000);
            }
        }
        navigateToRootElement();
        assertTrue(navigateToElementByAttributeValue("data-competition-id", CompetitionId));
        navigateToParent();
        navigateToChild();
        click();
        sleep(10000);

        navigateToRootElement();
        assertTrue(navigateToElementByClassName("matches"));
        buildListByClassName("grid ");

        for (int i = 0; getListSize() > i; i++) {
            navigateToListElement(i);
            navigateToChild();
            if (isDisplayed()) {
                click();
                sleep(2000);
            }
        }
    }

    @Then("^the correct event name is displayed on the daily list$")
    public void theEventIsDisplayedOnTheDailyList() throws Throwable {
        scrollToTheBottom();
        String eventname = getScenarioContext().getFirstEvent().getName().replace("|", "");
        String eventId = "OB_EV" + getScenarioContext().getFirstEvent().getId();
        sleep(4000);
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("//div[@id='" + eventId + "']"));
        buildListByClassName("btmarket");
        navigateToListElement(1);
        assertTrue(navigateToElementByClassName("btmarket__name "));
        String nameFromThePage = getAttribute("title");
        assertTrue(nameFromThePage.contains(eventname));
    }

    @And("^the correct market name is displayed on daily list$")
    public void theCorrectMarketNameIsDisplayedOnDailyList() throws Throwable {
        String eventID = "OB_EV" + ScenarioContext.getInstance().getFirstEvent().getId();
        String marketName = getScenarioContext().getFirstEvent().getFirstMarket().getName().replace("|", "");
        navigateToRootElement();
        assertTrue(navigateToElementById(eventID));
        navigateToParent();
        assertTrue(navigateToElementByClassName("btmarket__content"));
        assertTrue(getText().contains(marketName));

    }

    @And("^the correct selections displayed with correct order on daily list$")
    public void theCorrectSelectionsDisplayedWithCorrectOrderOnDailyList() throws Throwable {
        List<Market> markets = getScenarioContext().getFirstEvent().getMarkets();
        for (Market market : markets) {
            List<Selection> selections = market.getSelections();
            String marketId = "OB_MA" + market.getId();
            navigateToRootElement();
            //assertTrue(navigateToElementById("contentarea"));
            assertTrue(navigateToElementById(marketId));
            //build list of all selections belongs to created market on the page
            buildListByPartialId("OB_OU");
            int i = 0;
            for (Selection selection : selections) {
                assertTrue(getListSize() == selections.size());
                navigateToRootElement();
                navigateToListElement(i);
                assertTrue(getAttribute("data-odds").contains(selection.getPrice()));
                i = i + 1;

            }
        }
    }

    @And("^set a blurb message '(.*)' for market$")
    public void setABlurbMessageForMarket(String message) throws Throwable {
        int marketId = ScenarioContext.getInstance().getFirstEvent().getFirstMarket().getId();
        getBackOfficeHelper().setMarketBlurb(marketId, message);
        SharedData.blurbmessage = message;
    }


    /**
     * This method given a Fraction, will return a decimal.
     * E.g.: 10/2 -> 5.0 or "EVS" to "0.0"
     *
     * @param fractionInput
     * @return
     */
    public static double fromFractionToDecimal(String fractionInput) {

        if (fractionInput.equals("0.0") | fractionInput.equals("EVS")) {
            return 0.0;
        } else {
            return (double) Integer.parseInt(fractionInput.split("/")[0]) / Integer.parseInt(fractionInput.split("/")[1]);
        }
    }


    /**
     * This method will return the Current time of the given Event ID.
     *
     * @param eventId
     * @return
     */
    public String getEventCurrentTime(String eventId) {

        navigateToRootElement();
        assertTrue(navigateToElementByXpath(String.format(Selectors.TOP_BET_TIME_BY_EVENT_ID, eventId)));

        return getAttribute("datetime").replace("T", " ").replace(".000Z", "");
    }


    /**
     * This method will wait until boolean condition is met, or until the waiting seconds is over.
     *
     * @param condition
     * @param waitingSeconds
     */
    public Boolean waitForConditionToBeTrue(boolean condition, int waitingSeconds) {
        Boolean value = false;
        for (int i = 0; i < waitingSeconds * 10; i++) {
            if (condition) {
                value = true;
                break;
            }
            sleep(100);
        }

//        int looper = 0;
//        while(!condition || looper == waitingSeconds){
//            sleep(1000);
//            looper++;
//        }


        return value;
    }

    /**
     * This method will wait for the boolean condition to be true or for the waiting seconds to end.
     * Note: Every 5 seconds will refresh the page.
     *
     * @param condition
     * @param waitingSeconds
     */
    public void waitForConditionToBeTrueWithRefresh(boolean condition, int waitingSeconds) {

        int looper = 0;

        // Here we assure that at least it will be refreshed once the page.
        refresh();

        while (!condition || looper == waitingSeconds) {
            sleep(1000);
            looper++;
            if (looper % 5 == 0) {
                refresh();
            }
        }
    }

    /**
     * This method will place a single bet
     */
    @And("^the user places a bet$")
    public void theuserplacesabet() {

    }

    /**
     * This method will settle the first event displayed
     */
    @And("^the first event is settled$")
    public void thefirsteventissettled() {

    }

    /**
     * This method will hide the first event displayed
     */
    @And("^the first event display flag is set to no$")
    public void thefirsteventdisplayflagissettono() {
        ScenarioContext.getInstance().getFirstEvent().setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_NO);
    }


    /**
     * This method will settle certain market
     */
    @And("^market '(.*)' is settled$")
    public void marketIsSettled(String marketName) {
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            for (Market market : event.getMarkets()) {
                if (market.getName().replaceAll("|", "").contains(marketName)) {

                    event.setStartTime(util.Timer.getDate(util.Timer.DayFilter.Yesterday));
                    event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
                    event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_NO);
                    getOxifeedHelper().updateEvent(event);
                    waitAfterPDSUpdate();

                    for (Selection selection : market.getSelections())
                        BackOfficeOxifeed.getInstance().voidSelectionResult(selection);
                    BackOfficeOxifeed.getInstance().settleMarket(market);
                }
            }
        }
    }

    /**
     * This method will check that certain market is displayed or not displayed
     */
    @And("^market '(.*)' (is not|is) displayed$")
    public void marketIsNotOrIsDisplayed(String marketName, String option) {
        Boolean marketDisplayed = false;
        sleep(5000);
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            for (Market market : event.getMarkets()) {
                if (market.getName().replaceAll("|", "").contains(marketName)) {
                    String marketId = "OB_MA" + market.getId();
                    String competitionId = "comp-OB_TY" + event.getCompetitionId();
                    navigateToRootElement();
                    navigateToElementById(marketId);
                    if (!navigateToElementByClassName("header-dropdown")) {
                        navigateToRootElement();
                        //verify that on coupon event page market name is correct this is working only for first event market
                        assertTrue(navigateToElementById(competitionId));
                        assertTrue(navigateToElementByClassName("btmarket__name "));
                    }
                    String marketNameFromPage = getText();
                    navigateToRootElement();
                    assertTrue(navigateToElementById("contentarea"));
                    marketDisplayed=(navigateToElementById(marketId)&&isDisplayed()&&marketName.contains(marketNameFromPage));

                    break;
                }
            }
        }
        if (option.contains("not")) {
            assertFalse(marketDisplayed);
        } else {
            assertTrue(marketDisplayed);
        }
    }


    @And("^market '(.*)' display flag is set to '(no|yes)'$")
    public void marketDisplayFlagIsSetToNoOrYes(String marketName, String option) throws Throwable {
        String displayFlag = BackOfficeConstants.MARKET_DISPLAYED_NO;
        if (option.contains("yes")) {
            displayFlag = BackOfficeConstants.MARKET_DISPLAYED_YES;
        }

        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            for (Market market : event.getMarkets()) {
                if (market.getName().replaceAll("|", "").contains(marketName)) {
                    market.setDisplayed(displayFlag);
                    getOxifeedHelper().updateMarket(market);
                    waitAfterPDSUpdate();
                    break;
                }
            }
        }
    }


    /**
     * This method will verify if the current Page has:
     * The given Endpoint on the URL
     * The given Element (on xpath format for the locator)
     *
     * @param urlEndpoint
     * @param xpathLocator
     */
    public void pageRedirectionsVerifications(String urlEndpoint, String xpathLocator) {
        assertTrue(hasPartialURL(urlEndpoint));
        navigateToRootElement();
        assertTrue(navigateToElementByXpath(xpathLocator));
        assertFalse(isHidden());
    }


    /**
     * On this Step we increase the price of a Selection and verify that this one changes, then we revert the price
     * to its original one.
     *
     * @throws Throwable
     */
    @And("^user increase the price of the first selection displayed and verify it$")
    public void increasePriceOfFirstSelectionDisplayed() throws Throwable {

        // Here we get all the data from the First Selection Displayed
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(" + Selectors.ACTIVE_SELECTIONS + ")[1]"));

        String selectionId = getAttribute("id");
        String selectionName = getAttribute("data-name");

        // Here we modify the price of the bet to be EVS / 0.0
        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, "0.0");
        sleep(10000);
        String previousPrice = getAttribute("data-odds");

        // Once its EVS, we increase the Bet
        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, "2.1");
        sleep(10000);
        String newPrice = getAttribute("data-odds");

        try {
            assertTrue(0.0 < fromFractionToDecimal(getAttribute("data-odds")));
        } catch (AssertionError e) {
            assertTrue(Double.parseDouble(newPrice) > Double.parseDouble(previousPrice));
        }
        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, previousPrice);

    }

    /**
     * On this Step we decrease the price of a Selection and verify that this one changes, then we revert the price
     * to its original one.
     *
     * @throws Throwable
     */
    @And("^user decrease the price of the first selection displayed and verify it$")
    public void decreasePriceOfFirstSelectionDisplayed() throws Throwable {

        // Here we get all the data from the First Selection Displayed
        navigateToRootElement();
        assertTrue(navigateToElementByXpath("(" + Selectors.ACTIVE_SELECTIONS + ")[1]"));

        String selectionId = getAttribute("id");
        String selectionName = getAttribute("data-name");


        // Here we modify the price of the bet to be EVS / 0.0
        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, "2.1");
        sleep(10000);
        String previousPrice = getAttribute("data-odds");

        // Once its EVS, we increase the Bet
        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, "0.0");
        sleep(10000);
        String newPrice = getAttribute("data-odds");

        try {
            assertTrue(fromFractionToDecimal(getAttribute("data-odds")) < 2.1);
        } catch (AssertionError e) {
            assertTrue(Double.parseDouble(newPrice) < Double.parseDouble(previousPrice));
        }

        utilsStepDef.modifySelectionFractionPrice(selectionName, selectionId, previousPrice);

    }

    @And("^the user updated handicap value to '(.*)' for '(.*)' market$")
    public void theUserUpdatedHandicapValueTo(String arg0, String marketName) throws Throwable {
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            for (Market market : event.getMarkets()) {
                if (market.getName().replaceAll("|", "").contains(marketName)) {
                    market.setHandicap(arg0);
                    getOxifeedHelper().updateMarket(market);
                    waitAfterPDSUpdate();
                    String BackOfficeHandiCap = market.getHandicap();
                    assertTrue(BackOfficeHandiCap.contains(arg0));
                    break;
                }
            }
        }
    }

    @And("^handicap value displayed correctly for '(.*)' market$")
    public void handicapValueDisplayedCorrectly(String marketName) throws Throwable {
        Boolean handiPositive = false;
        List<Event> events = getScenarioContext().getEvents();
        for (Event event : events) {
            for (Market market : event.getMarkets()) {
                if (market.getName().replaceAll("|", "").contains(marketName)) {
                    String marketId = "OB_MA" + market.getId();
                    String BackOfficeHandiCap = market.getHandicap();
                    List<Selection> selections = market.getSelections();
                    if (Integer.parseInt(BackOfficeHandiCap) > 0) {
                        handiPositive = true;
                    }
                    //navigate to selection on the page
                    navigateToRootElement();
                    assertTrue(navigateToElementById("contentarea"));
                    assertTrue(navigateToElementById(marketId));
                    buildListByClassName("btmarket__name");
                    for (int i = 0; i < getListSize(); i++) {
                        navigateToListElement(i);
                        assertTrue(navigateToElementByClassName("selectionhandicap"));
                        if (selections.get(0).getType().contains("home")) {
                            if (selections.size() == 3) {
                                //home/away/line
                                if (handiPositive) {
                                    if (i < 2) {
                                        assertTrue(getText().contains("+" + BackOfficeHandiCap));
                                    } else {
                                        assertTrue(getText().contains("-" + BackOfficeHandiCap));
                                    }
                                } else {
                                    if (i < 2) {
                                        assertTrue(getText().contains(BackOfficeHandiCap));
                                    } else {
                                        assertTrue(getText().contains(BackOfficeHandiCap.replace("-", "+")));
                                    }
                                }
                            } else {
                                //home/away
                                if (handiPositive) {
                                    if (i < 1) {
                                        assertTrue(getText().contains("+" + BackOfficeHandiCap));
                                    } else {
                                        assertTrue(getText().contains("-" + BackOfficeHandiCap));
                                    }
                                } else {
                                    if (i < 1) {
                                        assertTrue(getText().contains(BackOfficeHandiCap));
                                    } else {
                                        assertTrue(getText().contains(BackOfficeHandiCap.replace("-", "+")));
                                    }
                                }
                            }
                        } else {
                            //high/low
                            assertTrue(getText().contains(BackOfficeHandiCap));
                        }
                        if (i == 2) {
                            break;
                        }
                    } //selection navigatiopn  for
                    break;
                }//market founded
            }//for markets
        }// events

    }


}










