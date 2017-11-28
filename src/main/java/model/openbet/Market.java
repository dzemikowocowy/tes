package model.openbet;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "status",
        "channels",
        "order",
        "sort",
        "displayed",
        "hasInPlay",
        "hcapMakeup",
        "hcapValue",
        "eachWay",
        "eachWayPlaces",
        "eachWayFactorNum",
        "eachWayFactorDen",
        "blurb",
        "marketGroupId",
        "marketGroupName",
        "active",
        "ancestors",
        "selections"
})
public class Market {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("channels")
    private String channels;
    @JsonProperty("order")
    private int order;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("displayed")
    private boolean displayed;
    @JsonProperty("hasInPlay")
    private boolean hasInPlay;
    @JsonProperty("hcapMakeup")
    private Object hcapMakeup;
    @JsonProperty("hcapValue")
    private Object hcapValue;
    @JsonProperty("eachWay")
    private boolean eachWay;
    @JsonProperty("eachWayPlaces")
    private Object eachWayPlaces;
    @JsonProperty("eachWayFactorNum")
    private Object eachWayFactorNum;
    @JsonProperty("eachWayFactorDen")
    private Object eachWayFactorDen;
    @JsonProperty("blurb")
    private String blurb;
    @JsonProperty("marketGroupId")
    private String marketGroupId;
    @JsonProperty("marketGroupName")
    private String marketGroupName;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("ancestors")
    private List<String> ancestors = new ArrayList<String>();
    @JsonProperty("selections")
    private List<Selection> selections = new ArrayList<Selection>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name.replace("|", "");
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The channels
     */
    @JsonProperty("channels")
    public String getChannels() {
        return channels;
    }

    /**
     * @param channels The channels
     */
    @JsonProperty("channels")
    public void setChannels(String channels) {
        this.channels = channels;
    }

    /**
     * @return The order
     */
    @JsonProperty("order")
    public int getOrder() {
        return order;
    }

    /**
     * @param order The order
     */
    @JsonProperty("order")
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return The sort
     */
    @JsonProperty("sort")
    public String getSort() {
        return sort;
    }

    /**
     * @param sort The sort
     */
    @JsonProperty("sort")
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return The displayed
     */
    @JsonProperty("displayed")
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * @param displayed The displayed
     */
    @JsonProperty("displayed")
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    /**
     * @return The hasInPlay
     */
    @JsonProperty("hasInPlay")
    public boolean isHasInPlay() {
        return hasInPlay;
    }

    /**
     * @param hasInPlay The hasInPlay
     */
    @JsonProperty("hasInPlay")
    public void setHasInPlay(boolean hasInPlay) {
        this.hasInPlay = hasInPlay;
    }

    /**
     * @return The hcapMakeup
     */
    @JsonProperty("hcapMakeup")
    public Object getHcapMakeup() {
        return hcapMakeup;
    }

    /**
     * @param hcapMakeup The hcapMakeup
     */
    @JsonProperty("hcapMakeup")
    public void setHcapMakeup(Object hcapMakeup) {
        this.hcapMakeup = hcapMakeup;
    }

    /**
     * @return The hcapValue
     */
    @JsonProperty("hcapValue")
    public Object getHcapValue() {
        return hcapValue;
    }

    /**
     * @param hcapValue The hcapValue
     */
    @JsonProperty("hcapValue")
    public void setHcapValue(Object hcapValue) {
        this.hcapValue = hcapValue;
    }

    /**
     * @return The eachWay
     */
    @JsonProperty("eachWay")
    public boolean isEachWay() {
        return eachWay;
    }

    /**
     * @param eachWay The eachWay
     */
    @JsonProperty("eachWay")
    public void setEachWay(boolean eachWay) {
        this.eachWay = eachWay;
    }

    /**
     * @return The eachWayPlaces
     */
    @JsonProperty("eachWayPlaces")
    public Object getEachWayPlaces() {
        return eachWayPlaces;
    }

    /**
     * @param eachWayPlaces The eachWayPlaces
     */
    @JsonProperty("eachWayPlaces")
    public void setEachWayPlaces(Object eachWayPlaces) {
        this.eachWayPlaces = eachWayPlaces;
    }

    /**
     * @return The eachWayFactorNum
     */
    @JsonProperty("eachWayFactorNum")
    public Object getEachWayFactorNum() {
        return eachWayFactorNum;
    }

    /**
     * @param eachWayFactorNum The eachWayFactorNum
     */
    @JsonProperty("eachWayFactorNum")
    public void setEachWayFactorNum(Object eachWayFactorNum) {
        this.eachWayFactorNum = eachWayFactorNum;
    }

    /**
     * @return The eachWayFactorDen
     */
    @JsonProperty("eachWayFactorDen")
    public Object getEachWayFactorDen() {
        return eachWayFactorDen;
    }

    /**
     * @param eachWayFactorDen The eachWayFactorDen
     */
    @JsonProperty("eachWayFactorDen")
    public void setEachWayFactorDen(Object eachWayFactorDen) {
        this.eachWayFactorDen = eachWayFactorDen;
    }

    /**
     * @return The blurb
     */
    @JsonProperty("blurb")
    public String getBlurb() {
        return blurb;
    }

    /**
     * @param blurb The blurb
     */
    @JsonProperty("blurb")
    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    /**
     * @return The marketGroupId
     */
    @JsonProperty("marketGroupId")
    public String getMarketGroupId() {
        return marketGroupId;
    }

    /**
     * @param marketGroupId The marketGroupId
     */
    @JsonProperty("marketGroupId")
    public void setMarketGroupId(String marketGroupId) {
        this.marketGroupId = marketGroupId;
    }

    /**
     * @return The marketGroupName
     */
    @JsonProperty("marketGroupName")
    public String getMarketGroupName() {
        return marketGroupName;
    }

    /**
     * @param marketGroupName The marketGroupName
     */
    @JsonProperty("marketGroupName")
    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
    }

    /**
     * @return The active
     */
    @JsonProperty("active")
    public boolean isActive() {
        return active;
    }

    /**
     * @param active The active
     */
    @JsonProperty("active")
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return The ancestors
     */
    @JsonProperty("ancestors")
    public List<String> getAncestors() {
        return ancestors;
    }

    /**
     * @param ancestors The ancestors
     */
    @JsonProperty("ancestors")
    public void setAncestors(List<String> ancestors) {
        this.ancestors = ancestors;
    }

    /**
     * @return The selections
     */
    @JsonProperty("selections")
    public List<Selection> getSelections() {
        return selections;
    }

    /**
     * @param selections The selections
     */
    @JsonProperty("selections")
    public void setSelections(List<Selection> selections) {
        this.selections = selections;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<Selection> getSelectionsSortedByPrice() {
        Collections.sort(getSelections(), new Comparator<Selection>() {
            public int compare(Selection s1, Selection s2) {
                Float s1Price = Float.valueOf(s1.getCurrentPriceNum()) / Float.valueOf(s1.getCurrentPriceDen());
                Float s2Price = Float.valueOf(s2.getCurrentPriceNum()) / Float.valueOf(s2.getCurrentPriceDen());
                return s1Price.compareTo(s2Price);
            }
        });
        return getSelections();
    }

}
