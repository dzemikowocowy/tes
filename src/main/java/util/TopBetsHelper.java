package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.openbet.Event;
import model.openbet.TopBet;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Mkoza on 12/02/2016.
 */
public class TopBetsHelper {


    private String environment;

    public void initialize(String environment) {
        this.environment = environment;
    }

    public TopBet getTopBets(String sportId) {
        ObjectMapper mapper = new ObjectMapper();
        List<TopBet> topBets = null;
        try {
            topBets = mapper.readValue(new URL(
                    String.format("http://sports.williamhill-%s.com/bet/en-gb?action=GetTopXSeln&Type=json&NumBets=10&SportIds=%s&Locale=en-gb&Page=1",
                            environment, sportId)), new TypeReference<List<Event>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert topBets != null;
        TopBet topBet = topBets.get(2);
        System.out.println("Name: " + topBet.getName());
        System.out.println("Event Id: " + topBet.getEvId());
        return topBet;
    }

}

