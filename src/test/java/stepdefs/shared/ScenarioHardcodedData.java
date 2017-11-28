package stepdefs.shared;


import cucumber.api.Scenario;
import office.BackOfficeOxifeed;
import office.openbet.model.BackOfficeConstants;
import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import util.Timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mkoza on 24/02/2016.
 */

public class ScenarioHardcodedData {

    private static ScenarioHardcodedData instance = null;

    public static int eventId = 754;
    public static int marketId = 6373;
    public static String selectionId = "OB_OU28781";

    public static String selectionTypeOfSport = "|Football|";
    public static String selectionSportCategory = "|European Major Leagues|";
    public static String selectionSportSubCategory = "|Spanish La Liga Primera|";
    public static String selectionMatch = "|Top Bets Cordoba| vs |Top Bets Compostela|";
    public static String selectionMarket = "|Handicap|";
    public static String selection = "Top Bets Cordoba";
    public static String manualWeight = "800";

    public static String selectionName = "|Top Bets Cordoba|";

}
