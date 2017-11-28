package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openbet.Event;
import model.squiz.SquizCarousel;
import model.squiz.SquizCarouselData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Mkoza on 12/02/2016.
 */
public class SquizVirtualHelper {

    private static SquizVirtualHelper instance = null;

    ObjectMapper jsonMapper;

    private String environment;

    protected Logger logger = LogManager.getRootLogger();

    public static SquizVirtualHelper getInstance() {
        if (instance == null) {
            instance = new SquizVirtualHelper();
        }
        return instance;
    }

    private SquizVirtualHelper() {
        jsonMapper = new ObjectMapper();
    }

    public void initialize(String environment) {
        this.environment = environment;
    }

    public Event getEvents(String sport) {
        ObjectMapper mapper = new ObjectMapper();
        List<Event> events = null;
        try {
            events = mapper.readValue(new URL(
                    String.format("http://sports.whg.squiz.co.uk/%s/sportsbook/_designs/js/virtual-data-feeds/%s-data-feed",
                            environment, sport)), new TypeReference<List<Event>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert events != null;
        Event event = events.get(2);
        System.out.println("id" + event.getId());
        System.out.println("name" + event.getName());
        System.out.println("name" + event.getStartDateTime());
        return event;
    }


    public List<SquizCarouselData> getCarousel() {
        ObjectMapper mapper = new ObjectMapper();
        SquizCarousel squizCarousel = null;
        try {
            squizCarousel = mapper.readValue(new URL (
                    String.format("http://whg-sb-build.squiz.co.uk/%s/sportsbook/_cei/sportsbook/en-gb-sports/all-sports/homepage-carousel?SQ_DESIGN_NAME=json",
                            environment )),new TypeReference<SquizCarousel> (){});
        } catch (IOException e) {
            e.printStackTrace ();
        }

        assert squizCarousel  != null;


        return squizCarousel.getData();
    }

    public List <SquizCarouselData> getEnabledCarouselelements(){
        List<SquizCarouselData> carouselElemntsDisplayed =new ArrayList<>();;
        List<SquizCarouselData> data = SquizVirtualHelper.getInstance().getCarousel();
        for (SquizCarouselData carouselElement :data){
            if (carouselElement.isEnabled()){
                carouselElemntsDisplayed.add(carouselElement);
            }
        }
        return carouselElemntsDisplayed;
    }

}
