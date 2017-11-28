package model.openbet;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "status",
        "displayed",
        "order",
        "blurb",
        "channels",
        "flags",
        "sort",
        "ancestors",
        "active"
})
public class OBClass {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("displayed")
    private boolean displayed;
    @JsonProperty("order")
    private int order;
    @JsonProperty("blurb")
    private String blurb;
    @JsonProperty("channels")
    private String channels;
    @JsonProperty("flags")
    private String flags;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("ancestors")
    private List<String> ancestors = new ArrayList<String>();
    @JsonProperty("active")
    private boolean active;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The displayed
     */
    @JsonProperty("displayed")
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     *
     * @param displayed
     * The displayed
     */
    @JsonProperty("displayed")
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    /**
     *
     * @return
     * The order
     */
    @JsonProperty("order")
    public int getOrder() {
        return order;
    }

    /**
     *
     * @param order
     * The order
     */
    @JsonProperty("order")
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     *
     * @return
     * The blurb
     */
    @JsonProperty("blurb")
    public String getBlurb() {
        return blurb;
    }

    /**
     *
     * @param blurb
     * The blurb
     */
    @JsonProperty("blurb")
    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    /**
     *
     * @return
     * The channels
     */
    @JsonProperty("channels")
    public String getChannels() {
        return channels;
    }

    /**
     *
     * @param channels
     * The channels
     */
    @JsonProperty("channels")
    public void setChannels(String channels) {
        this.channels = channels;
    }

    /**
     *
     * @return
     * The flags
     */
    @JsonProperty("flags")
    public String getFlags() {
        return flags;
    }

    /**
     *
     * @param flags
     * The flags
     */
    @JsonProperty("flags")
    public void setFlags(String flags) {
        this.flags = flags;
    }

    /**
     *
     * @return
     * The sort
     */
    @JsonProperty("sort")
    public String getSort() {
        return sort;
    }

    /**
     *
     * @param sort
     * The sort
     */
    @JsonProperty("sort")
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     *
     * @return
     * The ancestors
     */
    @JsonProperty("ancestors")
    public List<String> getAncestors() {
        return ancestors;
    }

    /**
     *
     * @param ancestors
     * The ancestors
     */
    @JsonProperty("ancestors")
    public void setAncestors(List<String> ancestors) {
        this.ancestors = ancestors;
    }

    /**
     *
     * @return
     * The active
     */
    @JsonProperty("active")
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    @JsonProperty("active")
    public void setActive(boolean active) {
        this.active = active;
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
