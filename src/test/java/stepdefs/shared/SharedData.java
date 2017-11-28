package stepdefs.shared;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mkoza on 19/02/16.
 */
public class SharedData {

    public static boolean inPlayMatches;

    public static String inPlayMatchId;

    public static String currentBalance;

    public static String addedStake;

    public static String betsnumber;

    public static Double totalStake;

    public static  boolean eachway;

    public static String blurbmessage;

    public static String nameOfLastTopBet;

    public static List<WebElement> topBetsNames;

    public static String previousBetPrice;

    public static String selectionId;

    public static int eventId;

    public static String eventName;

    public static int marketId;

    public static String originalTime;

    public static String handicapValue;

    public static boolean clearData = true;

    public static HashMap<Integer, String> topBetsBackendMap;

    public static HashMap<Integer, String> topBetsFrontendMap;

    public static String squiz_sport_used;

    public static String squiz_type_used;

    public static String squiz_start_date_used;

    public static String squiz_end_date_used;



    public static void resetSharedData() {
        inPlayMatches = false;
        inPlayMatchId = "";
        currentBalance = "";
        addedStake="";
        betsnumber="";
        totalStake=0.00;
        eachway=false;
        blurbmessage="";
        nameOfLastTopBet = "";
        topBetsNames = null;
        previousBetPrice = "";
        selectionId = "";
        eventId = 0;
        eventName = "";
        marketId = 0;
        originalTime = "";
        handicapValue = "";
        clearData = true;
        topBetsFrontendMap = null;
        topBetsBackendMap = null;
        squiz_sport_used = "";
        squiz_type_used = "";
        squiz_start_date_used = "";
        squiz_end_date_used = "";
    }
}
