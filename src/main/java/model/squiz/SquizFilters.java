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
        "save-ts",
        "assigned_filters"
})
public class SquizFilters {

    @JsonProperty("save-ts")
    private int saveTs;
    @JsonProperty("assigned_filters")
    private List<Filter> assignedFilters = new ArrayList<Filter>();
    @JsonIgnore
    private Filter unassignedFilter;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The saveTs
     */
    @JsonProperty("save-ts")
    public int getSaveTs() {
        return saveTs;
    }

    /**
     *
     * @param saveTs
     * The save-ts
     */
    @JsonProperty("save-ts")
    public void setSaveTs(int saveTs) {
        this.saveTs = saveTs;
    }

    /**
     *
     * @return
     * The assignedFilters
     */
    @JsonProperty("assigned_filters")
    public List<Filter> getAssignedFilters() {
        return assignedFilters;
    }

    /**
     *
     * @param assignedFilters
     * The assigned_filters
     */
    @JsonProperty("assigned_filters")
    public void setAssignedFilters(List<Filter> assignedFilters) {
        this.assignedFilters = assignedFilters;
    }

    /**
     *
     * @return
     * The unassignedFilter
     */
    public Filter getUnassignedFilter() {
        return unassignedFilter;
    }

    /**
     *
     * @param unassignedFilter
     * The unassignedFilters
     */
    public void setUnassignedFilter(Filter unassignedFilter) {
        this.unassignedFilter = unassignedFilter;
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
