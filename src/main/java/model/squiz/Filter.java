package model.squiz;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "filter_name",
        "position",
        "competitions"
})
public class Filter {

    @JsonProperty("filter_name")
    private String filterName;
    @JsonProperty("position")
    private int position;
    @JsonProperty("competitions")
    private List<Competition> competitions = new ArrayList<Competition>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The filterName
     */
    @JsonProperty("filter_name")
    public String getFilterName() {
        return filterName;
    }

    /**
     *
     * @param filterName
     * The filter_name
     */
    @JsonProperty("filter_name")
    public void setFilterName(String filterName) {
        this.filterName = filterName;
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
     * The competitions
     */
    @JsonProperty("competitions")
    public List<Competition> getCompetitions() {
        return competitions;
    }

    /**
     *
     * @param competitions
     * The competitions
     */
    @JsonProperty("competitions")
    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
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
