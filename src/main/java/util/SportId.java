package util;

import java.util.LinkedHashMap;

/**
 * Created by israel on 03/03/2015.
 */
public class SportId {

    private static final LinkedHashMap<String, String> pdsSportIds;
    static {
        pdsSportIds = new LinkedHashMap<String, String>();
        pdsSportIds.put("football", "OB_SP9");
        pdsSportIds.put("tennis", "OB_SP24");
        pdsSportIds.put("basketball", "OB_SP27");
        pdsSportIds.put("golf", "OB_SP11");
        pdsSportIds.put("baseball", "OB_SP2");
        pdsSportIds.put("virtual", "OB_SP9899");
    }

    private static final LinkedHashMap<String, String> funnelBackSportIds;
    static {
        funnelBackSportIds = new LinkedHashMap<String, String>();
        funnelBackSportIds.put("football", "9");
        funnelBackSportIds.put("tennis", "24");
        funnelBackSportIds.put("basketball", "27");
        funnelBackSportIds.put("golf", "11");
        funnelBackSportIds.put("baseball", "2");
        funnelBackSportIds.put("virtual", "9899");
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

    public static String getPdsSportId(String sport) {
        sport = sport.toLowerCase().replace(" ", "-");
        return pdsSportIds.get(sport);
    }

    public static String getFunnelBackSportId(String sport) {
        sport = sport.toLowerCase().replace(" ", "-");
        return funnelBackSportIds.get(sport);
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
