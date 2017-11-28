package model.squiz;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "competition_name",
        "position",
        "competition_id"
})
public class Competition {

    @JsonProperty("competition_name")
    private String competitionName;
    @JsonProperty("position")
    private int position;
    @JsonProperty("competition_id")
    private String competitionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The competitionName
     */
    @JsonProperty("competition_name")
    public String getCompetitionName() {
        return competitionName;
    }

    /**
     *
     * @param competitionName
     * The competition_name
     */
    @JsonProperty("competition_name")
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    /**
     *
     * @return
     * The position
     */
    @JsonProperty("position")
    public int getPosition() {
        return position;
    }

    /**
     *
     * @param position
     * The position
     */
    @JsonProperty("position")
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     *
     * @return
     * The competitionId
     */
    @JsonProperty("competition_id")
    public String getCompetitionId() {
        return competitionId;
    }

    /**
     *
     * @param competitionId
     * The competition_id
     */
    @JsonProperty("competition_id")
    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
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