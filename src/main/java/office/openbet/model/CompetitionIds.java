package office.openbet.model;

import java.util.LinkedHashMap;
import java.util.Objects;

public class CompetitionIds {

    private CompetitionIds() {
        // utility class
    }

    private static final LinkedHashMap<String, Integer> competitionsMap;
    static {
        competitionsMap = new LinkedHashMap<String, Integer>();

        // Spanish football
        competitionsMap.put("Spanish La Liga Primera",338);

        ////UK Footbal///////////////////
        competitionsMap.put("English Premier League",295);
        competitionsMap.put("English League 1",293);
        competitionsMap.put("English Championship",292);

        ////US Baseball///////////////////
        competitionsMap.put("|MLB|",226);
        competitionsMap.put("MLB",226);

        //American footbal
        competitionsMap.put("|NFL|",195);


        ////Euorope Baseball///////////////////
        competitionsMap.put("|ABL|",13908);
        competitionsMap.put("ABL",13908);

        ////Euorope Basketball///////////////////
        competitionsMap.put("Euroleague",5355);

        // hockey
        competitionsMap.put("|NHL|",378);

        //horses
        competitionsMap.put("Bangor", 251);
        competitionsMap.put("Catterick", 185);
        competitionsMap.put("Bath", 10);
        competitionsMap.put("Doncaster", 254);
        competitionsMap.put("Aintree", 9);
        competitionsMap.put("Poole", 705);
        //Greyhounds
        competitionsMap.put("Oxford", 42); // Greyhounds

    }

    public static Integer getCompetitionId(String competitionName) {
        Integer competitionId = competitionsMap.get(competitionName);

        if(competitionId == null)
            throw new IllegalArgumentException("No competition available with name: " + competitionName);
        else
            return competitionId;
    }

    public static Integer getFirstCompetitionId() {
            return competitionsMap.values().iterator().next();
    }

    public static String getCompetitionName(Integer competitionId) {
        if (competitionsMap.containsValue(competitionId))
            for (final String entry : competitionsMap.keySet())
                if (Objects.equals(competitionsMap.get(entry), competitionId))
                    return entry;

        throw new IllegalArgumentException("No competition available with id: " + competitionId);
    }


}
