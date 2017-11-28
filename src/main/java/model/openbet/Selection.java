package model.openbet;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Generated;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "order",
        "displayed",
        "channels",
        "currentPriceNum",
        "currentPriceDen",
        "name",
        "status",
        "cashoutPriceNum",
        "cashoutPriceDen",
        "fbResult",
        "csHome",
        "csAway",
        "active",
        "ancestors"
})
public class Selection {

    @JsonProperty("id")
    private String id;
    @JsonProperty("order")
    private int order;
    @JsonProperty("displayed")
    private boolean displayed;
    @JsonProperty("channels")
    private String channels;
    @JsonProperty("currentPriceNum")
    private int currentPriceNum;
    @JsonProperty("currentPriceDen")
    private int currentPriceDen;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("cashoutPriceNum")
    private Object cashoutPriceNum;
    @JsonProperty("cashoutPriceDen")
    private Object cashoutPriceDen;
    @JsonProperty("fbResult")
    private String fbResult;
    @JsonProperty("csHome")
    private Object csHome;
    @JsonProperty("csAway")
    private Object csAway;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("ancestors")
    private List<String> ancestors = new ArrayList<String>();
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
     * The currentPriceNum
     */
    @JsonProperty("currentPriceNum")
    public int getCurrentPriceNum() {
        return currentPriceNum;
    }

    /**
     *
     * @param currentPriceNum
     * The currentPriceNum
     */
    @JsonProperty("currentPriceNum")
    public void setCurrentPriceNum(int currentPriceNum) {
        this.currentPriceNum = currentPriceNum;
    }

    /**
     *
     * @return
     * The currentPriceDen
     */
    @JsonProperty("currentPriceDen")
    public int getCurrentPriceDen() {
        return currentPriceDen;
    }

    /**
     *
     * @param currentPriceDen
     * The currentPriceDen
     */
    @JsonProperty("currentPriceDen")
    public void setCurrentPriceDen(int currentPriceDen) {
        this.currentPriceDen = currentPriceDen;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name.replace("|", "");
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
     * The cashoutPriceNum
     */
    @JsonProperty("cashoutPriceNum")
    public Object getCashoutPriceNum() {
        return cashoutPriceNum;
    }

    /**
     *
     * @param cashoutPriceNum
     * The cashoutPriceNum
     */
    @JsonProperty("cashoutPriceNum")
    public void setCashoutPriceNum(Object cashoutPriceNum) {
        this.cashoutPriceNum = cashoutPriceNum;
    }

    /**
     *
     * @return
     * The cashoutPriceDen
     */
    @JsonProperty("cashoutPriceDen")
    public Object getCashoutPriceDen() {
        return cashoutPriceDen;
    }

    /**
     *
     * @param cashoutPriceDen
     * The cashoutPriceDen
     */
    @JsonProperty("cashoutPriceDen")
    public void setCashoutPriceDen(Object cashoutPriceDen) {
        this.cashoutPriceDen = cashoutPriceDen;
    }

    /**
     *
     * @return
     * The fbResult
     */
    @JsonProperty("fbResult")
    public String getFbResult() {
        return fbResult;
    }

    /**
     *
     * @param fbResult
     * The fbResult
     */
    @JsonProperty("fbResult")
    public void setFbResult(String fbResult) {
        this.fbResult = fbResult;
    }

    /**
     *
     * @return
     * The csHome
     */
    @JsonProperty("csHome")
    public Object getCsHome() {
        return csHome;
    }

    /**
     *
     * @param csHome
     * The csHome
     */
    @JsonProperty("csHome")
    public void setCsHome(Object csHome) {
        this.csHome = csHome;
    }

    /**
     *
     * @return
     * The csAway
     */
    @JsonProperty("csAway")
    public Object getCsAway() {
        return csAway;
    }

    /**
     *
     * @param csAway
     * The csAway
     */
    @JsonProperty("csAway")
    public void setCsAway(Object csAway) {
        this.csAway = csAway;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getOpenbetId() {
        return StringUtils.substringAfter(id, "OB_OU");
    }

    public String getPrice() {
        return getCurrentPriceNum() + "/" + getCurrentPriceDen();
    }

}
