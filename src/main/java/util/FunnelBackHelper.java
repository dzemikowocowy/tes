package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openbet.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by israel on 24/02/2015.
 */
public class FunnelBackHelper {

    private static FunnelBackHelper instance = null;

    ObjectMapper jsonMapper;

    private String funnelBackEndpoint;

    protected Logger logger = LogManager.getRootLogger();

    public static FunnelBackHelper getInstance() {
        if(instance == null) {
            instance = new FunnelBackHelper();
        }
        return instance;
    }

    private FunnelBackHelper() {
        jsonMapper = new ObjectMapper();
    }

    public void initialize(String funnelBackEndpoint) {
        this.funnelBackEndpoint = funnelBackEndpoint;
    }

    public List<Event> getActiveEventsBySport(String sportId) {
        List<Event> sportEvents = null;
        try {
            sportEvents = jsonMapper.readValue(new URL(String.format(funnelBackEndpoint +
                            "/sports/%s/events?active=true", sportId)),
                    new TypeReference<List<Event>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sportEvents;
    }

    /**
     * Get the active events by sport id for one week
     * @param sportId
     * @return
     */
    public List<Event> getActiveEventsBySportForOneWeek(String sportId) {
        List<Event> todayEvents = new ArrayList<Event>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat timeFormat = new SimpleDateFormat("'T'HH:mm:ss");
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

            Calendar calendar = Calendar.getInstance();
            String todayDate = dateFormat.format(calendar.getTime());
            String currentTime = timeFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            String oneWeekDate = dateFormat.format(calendar.getTime());
            String dateFromFilter = "&dateFrom=" + todayDate + currentTime;
            String dateToFilter = "&dateTo=" + oneWeekDate + "T23:59:59";
            List<Event> events = jsonMapper.readValue(new URL(
                            String.format(funnelBackEndpoint + "/sports/%s/events?active=true&hasInPlay=false&isInPlay=false%s%s",
                                    sportId, dateFromFilter, dateToFilter)),
                    new TypeReference<List<Event>>() {});
            todayEvents = events;

        } catch (Exception e) {
            if(e.getMessage().contains("400"))
                logger.info("No active events for today class or invalid sport: " + sportId);
            else
                e.printStackTrace();
        }
        return todayEvents;
    }

}
