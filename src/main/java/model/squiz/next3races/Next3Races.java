package model.squiz.next3races;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "ts",
        "ignored_competitions",
        "promoted_events"
})
public class Next3Races {

    @JsonProperty("ts")
    private Long ts;
    @JsonProperty("ignored_competitions")
    private List<IgnoredCompetition> ignoredCompetitions = new ArrayList<IgnoredCompetition>();
    @JsonProperty("promoted_events")
    private List<PromotedEvent> promotedEvents = new ArrayList<PromotedEvent>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ts
     */
    @JsonProperty("ts")
    public Long getTs() {
        return ts;
    }

    /**
     *
     * @param ts
     * The ts
     */
    @JsonProperty("ts")
    public void setTs(Long ts) {
        this.ts = ts;
    }

    /**
     *
     * @return
     * The ignoredCompetitions
     */
    @JsonProperty("ignored_competitions")
    public List<IgnoredCompetition> getIgnoredCompetitions() {
        return ignoredCompetitions;
    }

    /**
     *
     * @param ignoredCompetitions
     * The ignored_competitions
     */
    @JsonProperty("ignored_competitions")
    public void setIgnoredCompetitions(List<IgnoredCompetition> ignoredCompetitions) {
        this.ignoredCompetitions = ignoredCompetitions;
    }

    /**
     *
     * @return
     * The promotedEvents
     */
    @JsonProperty("promoted_events")
    public List<PromotedEvent> getPromotedEvents() {
        return promotedEvents;
    }

    /**
     *
     * @param promotedEvents
     * The promoted_events
     */
    @JsonProperty("promoted_events")
    public void setPromotedEvents(List<PromotedEvent> promotedEvents) {
        this.promotedEvents = promotedEvents;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
