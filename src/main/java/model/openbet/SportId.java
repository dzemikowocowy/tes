package model.openbet;

import java.util.LinkedHashMap;

/**
 * Created by israel on 03/03/2015.
 */
public class SportId {

    private static final LinkedHashMap<String, String> racingPdsClassId;
    static {
        racingPdsClassId = new LinkedHashMap<String, String>();
        racingPdsClassId.put("horse-racing", "OB_CL2");
        racingPdsClassId.put("greyhounds", "OB_CL3");
        racingPdsClassId.put("virtuals", "OB_SP9899");
    }

    private static final LinkedHashMap<String, String> antepostPdsClassId;
    static {
        antepostPdsClassId = new LinkedHashMap<String, String>();
        antepostPdsClassId.put("horse-racing", "OB_CL13");
        antepostPdsClassId.put("greyhounds", "OB_CL17");
    }

    private static final LinkedHashMap<String, String> specialsPdsClassId;
    static {
        specialsPdsClassId = new LinkedHashMap<String, String>();
        specialsPdsClassId.put("horse-racing", "OB_CL323");
        specialsPdsClassId.put("greyhounds", "OB_CL386");
    }

    public static String getRacingPdsClassId(String sport) {
        sport = sport.toLowerCase().replace(" ", "-");
        return racingPdsClassId.get(sport);
    }

    public static String getAntepostPdsClassId(String sport) {
        sport = sport.toLowerCase().replace(" ", "-");
        return antepostPdsClassId.get(sport);
    }

    public static String getSpecialsPdsClassId(String sport) {
        sport = sport.toLowerCase().replace(" ", "-");
        return specialsPdsClassId.get(sport);
    }

    public static String getSport(String sport) {
        return sport.toLowerCase().trim().replace(" ", "-");
    }
}
