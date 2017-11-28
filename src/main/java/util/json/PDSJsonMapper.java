package util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openbet.Event;

import java.net.URL;
import java.util.List;

/**
 * Created by israel on 24/02/2015.
 */
public class PDSJsonMapper {

    public static void main(String[] args) {


        try {
            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            List<Event> events = mapper.readValue(new URL("http://10.210.200.187:9291/catalogs/OB_CG0/sports/OB_SP60/events"), new TypeReference<List<Event>>(){});
            Event event = events.get(1);
            System.out.println(event.getId());
            System.out.println(event.getName());
            System.out.println(event.getMarketCountInPlay());
            System.out.println(event.getMarketCountPreMatch());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
