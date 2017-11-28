package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.squiz.Competition;
import model.squiz.Filter;
import model.squiz.SquizFilters;
import model.squiz.next3races.IgnoredCompetition;
import model.squiz.next3races.Next3Races;
import model.squiz.next3races.PromotedEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by israel on 24/02/2015.
 */
public class SquizHelper {

    private final String BASE_URL = "http://whg-sb-build.squiz.co.uk/%s/sportsbook/_cei/sportsbook" +
            "/en-gb-sports/%s";

    private final String FILTERS_REQUEST = BASE_URL + "?SQ_DESIGN_NAME=filters";

    private final String NEXT_3_RACES_REQUEST = BASE_URL + "?SQ_DESIGN_NAME=inplay";

    private static SquizHelper instance = null;

    ObjectMapper jsonMapper;

    String environment;

    String sport;

    protected SquizHelper() {
        jsonMapper = new ObjectMapper();
    }

    public static SquizHelper getInstance() {
        if(instance == null) {
            instance = new SquizHelper();
        }
        return instance;
    }

    public void initialize(String environment, String sport) {
        this.environment = environment;
        this.sport = sport;
    }

    private SquizFilters getFilters() {
        SquizFilters squizFilters = null;
        try {
                squizFilters = jsonMapper.readValue(new URL(
                                String.format(FILTERS_REQUEST, environment, sport)),
                        SquizFilters.class);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return squizFilters;
    }

    public boolean isCompetitionAssigned(String competitionId) {
        for(Filter filter : getFilters().getAssignedFilters())
            for(Competition competition : filter.getCompetitions())
                if(competition.getCompetitionId().equals(competitionId))
                    return true;

        return false;
    }

    public Filter getEventFilter(String competitionId) {

        for(Filter filter : getFilters().getAssignedFilters()) {
            for(Competition competition : filter.getCompetitions())
                if(competition.getCompetitionId().equals(competitionId)) {
                    filter.setCompetitions(Collections.singletonList(competition));
                    return filter;
                }
        }
        return new Filter();
    }

    private Next3Races getNext3Races() {
        Next3Races next3races = null;
        try {
            next3races = jsonMapper.readValue(new URL(
                            String.format(NEXT_3_RACES_REQUEST, environment, sport)),
                    Next3Races.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return next3races;
    }

    public List<PromotedEvent> getNext3RacesPromotedEvents() {
        return getNext3Races().getPromotedEvents();
    }

    public List<IgnoredCompetition> getNext3RacesIgnoredCompetitions() {
        return getNext3Races().getIgnoredCompetitions();
    }

    /*public List<Filter> getExpectedFilters(List<model.openbet.Competition> activeCompetitions) {

        // build activeCompetitions ids
        ArrayList<String> activeCompsIds = new ArrayList<String>();
        for(model.openbet.Competition obComp : activeCompetitions)
            activeCompsIds.add(obComp.getId());

        SquizFilters sportAux = sportFilters;
        for(int i = 0; i < sportAux.getAssignedFilters().size(); i++) {
            Filter filter = sportAux.getAssignedFilters().get(i);
            for(int j = 0; j < filter.getCompetitions().size(); j++) {
                Competition competition = filter.getCompetitions().get(j);
                if(!activeCompsIds.contains(competition.getCompetitionId()))
                    filter.getCompetitions().set(j, null);
            }

            // remove null competitions
            filter.getCompetitions().removeAll(Collections.singleton(null));
            sportAux.getAssignedFilters().set(i, filter);
            if (filter.getCompetitions().isEmpty())
                sportAux.getAssignedFilters().set(i, null);
        }

        // remove null filters
        sportAux.getAssignedFilters().removeAll(Collections.singleton(null));
        return sportAux.getAssignedFilters();
    }*/



}
