package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openbet.Competition;
import model.openbet.Event;
import model.openbet.Market;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by israel on 24/02/2015.
 */
public class PDSHelper {

    private static PDSHelper instance = null;

    ObjectMapper jsonMapper;

    private String pdsEndpoint;

    protected Logger logger = LogManager.getRootLogger();

    public static PDSHelper getInstance() {
        if(instance == null) {
            instance = new PDSHelper();
        }
        return instance;
    }

    private PDSHelper() {
        jsonMapper = new ObjectMapper();
    }

    public void initialize(String pdsEndpoint) {
        this.pdsEndpoint = pdsEndpoint;
    }

    public List<Event> getTodayActiveEventsByClass(String classId) {
        List<Event> todayEvents = new ArrayList<Event>();
        try {
            Calendar calendar = Calendar.getInstance();
            String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            String dateFromFilter = "&dateFrom=" + todayDate + "T00:00:00";
            String dateToFilter = "&dateTo=" + todayDate + "T23:59:59";
            List<Event> events = jsonMapper.readValue(new URL(
                            String.format("%s/catalogs/OB_CG0/classes/%s/events?active=true%s%s",
                                    pdsEndpoint, classId, dateFromFilter, dateToFilter)),
                    new TypeReference<List<Event>>() {});
            todayEvents = events;


            // fill the events and sort the markets
            //fillMarketsAndSelections(pdsEndpoint);
            //sortEventsMarkets();
        } catch (Exception e) {
            if(e.getMessage().contains("400"))
                logger.info("No active events for today class or invalid class: " + classId);
            else
                e.printStackTrace();
        }
        return todayEvents;
    }

    /**
     * Get the active events by class id between today's current time in UK and today at 23:59:59
     * @param classId
     * @return
     */
    public List<Event> getCurrentTodayActiveEventsByClass(String classId) {
        List<Event> todayEvents = new ArrayList<Event>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat timeFormat = new SimpleDateFormat("'T'HH:mm:ss");
            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Calendar calendar = Calendar.getInstance();
            String todayDate = dateFormat.format(calendar.getTime());
            String currentTime = timeFormat.format(calendar.getTime());
            String dateFromFilter = "&dateFrom=" + todayDate + currentTime;
            String dateToFilter = "&dateTo=" + todayDate + "T23:59:59";
            List<Event> events = jsonMapper.readValue(new URL(
                            String.format("%s/catalogs/OB_CG0/classes/%s/events?active=true%s%s",
                                    pdsEndpoint, classId, dateFromFilter, dateToFilter)),
                    new TypeReference<List<Event>>() {});
            todayEvents = events;

        } catch (Exception e) {
            if(e.getMessage().contains("400"))
                logger.info("No active events for today class or invalid class: " + classId);
            else
                e.printStackTrace();
        }
        return todayEvents;
    }


    public List<Event> getEventsByClass(String classId) {
        List<Event> classEvents = null;
        try {
            classEvents = jsonMapper.readValue(new URL(String.format(pdsEndpoint +
                            "/catalogs/OB_CG0/classes/%s/events?active=true", classId)),
                    new TypeReference<List<Event>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classEvents;
    }

    public List<Competition> getActiveCompetitions(String pdsSportId) {
        List<Competition> activeCompetitions = null;
        try {
            activeCompetitions = jsonMapper.readValue(new URL(
                    String.format(pdsEndpoint + "/catalogs/OB_CG0/sports/%s/competitions?active=true",
                            pdsSportId)), new TypeReference<List<Competition>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return activeCompetitions;
    }

    public List<String> getCompetitionsList(List<Event> events) {
        LinkedHashSet<String> competitions = new LinkedHashSet<String>();
        for(Event event : events)
            competitions.add(event.getCompetitionId());

        return new ArrayList<String>(competitions);
    }



    public List<Event> getMatches(List<Event> events) {
        ArrayList<Event> matches = new ArrayList<Event>();
        for (Event event : events)
            if (event.getSort().equals("MTCH"))
                matches.add(event);

        return matches;
    }

    public List<Event> getOffFlagNoEvents(List<Event> events) {
        ArrayList<Event> matches = new ArrayList<Event>();
        for (Event event : events)
            if (!event.isSysIsOff())
                matches.add(event);

        return matches;
    }

    public List<Event> getTournaments(List<Event> events) {
        ArrayList<Event> tournaments = new ArrayList<Event>();
        for (Event event : events)
            if (event.getSort().equals("TNMT"))
                tournaments.add(event);

        return tournaments;
    }

    public int getEventsPerCompetition(String compId, List<Event> events) {
        int counter = 0;
        for (Event event : events)
            if(event.getCompetitionId().equals(compId))
                counter++;

        return counter;
    }

    public ArrayList<String> getCompetitionsIds(List<Event> events) {
        ArrayList<String> compIds = new ArrayList<String>();
        for (Event event : events)
            compIds.add(event.getCompetitionId());

        return compIds;
    }

    public Event getEventById(String eventId) {
        Event event = null;
        try {
            event = jsonMapper.readValue(new URL(String.format(pdsEndpoint + "/catalogs/OB_CG0/events/%s",
                    eventId)), Event.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }


    private List<Event> fillMarketsAndSelections(List<Event> events) {
        try {
            for (int i = 0; i < events.size(); i++) {
                Event event = jsonMapper.readValue(new URL(String.format(pdsEndpoint + "/catalogs/OB_CG0/events/%s",
                        events.get(i).getId())), Event.class);
                events.set(i, event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    public void sortEventsMarkets(List<Event> events) {
        for(Event event : events) {
            Collections.sort(event.getMarkets(), new Comparator<Market>() {
                public int compare(Market m1, Market m2) {
                    return m1.getOrder() - m2.getOrder();
                }
            });
        }
    }

    /**
     * Sort an array of events by their start date.
     * @param events
     */
    public void sortEventsByStartDate(List<Event> events) {
        final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.000+0000");
        Collections.sort(events, new Comparator<Event>() {
            public int compare(Event e1, Event e2) {
                try {
                    return dateParser.parse(e1.getStartDateTime())
                            .compareTo(dateParser.parse(e2.getStartDateTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

}
