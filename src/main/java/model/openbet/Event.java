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
        "channels",
        "country",
        "startDateTime",
        "blurb",
        "sort",
        "displayed",
        "hasInPlay",
        "flags",
        "order",
        "settled",
        "ancestors",
        "streamingAvailable",
        "streamingProviders",
        "birDelay",
        "minBet",
        "maxMultipleBet",
        "maxBet",
        "cashoutAvailable",
        "active",
        "isInPlay",
        "updatedAt",
        "marketCountInPlay",
        "marketCountPreMatch",
        "marketCountActiveInPlay",
        "marketCountActivePreMatch",
        "markets",
        "marketCountActiveTotal",
        "sys_isVoid",
        "sys_isOff"
})
public class Event {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("channels")
    private String channels;
    @JsonProperty("country")
    private String country;
    @JsonProperty("startDateTime")
    private String startDateTime;
    @JsonProperty("blurb")
    private String blurb;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("displayed")
    private boolean displayed;
    @JsonProperty("hasInPlay")
    private boolean hasInPlay;
    @JsonProperty("flags")
    private String flags;
    @JsonProperty("order")
    private long order;
    @JsonProperty("settled")
    private boolean settled;
    @JsonProperty("ancestors")
    private List<String> ancestors = new ArrayList<String>();
    @JsonProperty("streamingAvailable")
    private boolean streamingAvailable;
    @JsonProperty("streamingProviders")
    private List<Object> streamingProviders = new ArrayList<Object>();
    @JsonProperty("birDelay")
    private Object birDelay;
    @JsonProperty("minBet")
    private double minBet;
    @JsonProperty("maxMultipleBet")
    private double maxMultipleBet;
    @JsonProperty("maxBet")
    private double maxBet;
    @JsonProperty("cashoutAvailable")
    private boolean cashoutAvailable;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("isInPlay")
    private boolean isInPlay;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("marketCountInPlay")
    private long marketCountInPlay;
    @JsonProperty("marketCountPreMatch")
    private long marketCountPreMatch;
    @JsonProperty("marketCountActiveInPlay")
    private long marketCountActiveInPlay;
    @JsonProperty("marketCountActivePreMatch")
    private long marketCountActivePreMatch;
    @JsonProperty("markets")
    private List<Market> markets = new ArrayList<Market>();
    @JsonProperty("marketCountActiveTotal")
    private long marketCountActiveTotal;
    @JsonProperty("sys_isVoid")
    private boolean sysIsVoid;
    @JsonProperty("sys_isOff")
    private boolean sysIsOff;
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
     * The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The startDateTime
     */
    @JsonProperty("startDateTime")
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     *
     * @param startDateTime
     * The startDateTime
     */
    @JsonProperty("startDateTime")
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
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
     * The hasInPlay
     */
    @JsonProperty("hasInPlay")
    public boolean isHasInPlay() {
        return hasInPlay;
    }

    /**
     *
     * @param hasInPlay
     * The hasInPlay
     */
    @JsonProperty("hasInPlay")
    public void setHasInPlay(boolean hasInPlay) {
        this.hasInPlay = hasInPlay;
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
     * The order
     */
    @JsonProperty("order")
    public long getOrder() {
        return order;
    }

    /**
     *
     * @param order
     * The order
     */
    @JsonProperty("order")
    public void setOrder(long order) {
        this.order = order;
    }

    /**
     *
     * @return
     * The settled
     */
    @JsonProperty("settled")
    public boolean isSettled() {
        return settled;
    }

    /**
     *
     * @param settled
     * The settled
     */
    @JsonProperty("settled")
    public void setSettled(boolean settled) {
        this.settled = settled;
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
     * The streamingAvailable
     */
    @JsonProperty("streamingAvailable")
    public boolean isStreamingAvailable() {
        return streamingAvailable;
    }

    /**
     *
     * @param streamingAvailable
     * The streamingAvailable
     */
    @JsonProperty("streamingAvailable")
    public void setStreamingAvailable(boolean streamingAvailable) {
        this.streamingAvailable = streamingAvailable;
    }

    /**
     *
     * @return
     * The streamingProviders
     */
    @JsonProperty("streamingProviders")
    public List<Object> getStreamingProviders() {
        return streamingProviders;
    }

    /**
     *
     * @param streamingProviders
     * The streamingProviders
     */
    @JsonProperty("streamingProviders")
    public void setStreamingProviders(List<Object> streamingProviders) {
        this.streamingProviders = streamingProviders;
    }

    /**
     *
     * @return
     * The birDelay
     */
    @JsonProperty("birDelay")
    public Object getBirDelay() {
        return birDelay;
    }

    /**
     *
     * @param birDelay
     * The birDelay
     */
    @JsonProperty("birDelay")
    public void setBirDelay(Object birDelay) {
        this.birDelay = birDelay;
    }

    /**
     *
     * @return
     * The minBet
     */
    @JsonProperty("minBet")
    public double getMinBet() {
        return minBet;
    }

    /**
     *
     * @param minBet
     * The minBet
     */
    @JsonProperty("minBet")
    public void setMinBet(double minBet) {
        this.minBet = minBet;
    }

    /**
     *
     * @return
     * The maxMultipleBet
     */
    @JsonProperty("maxMultipleBet")
    public double getMaxMultipleBet() {
        return maxMultipleBet;
    }

    /**
     *
     * @param maxMultipleBet
     * The maxMultipleBet
     */
    @JsonProperty("maxMultipleBet")
    public void setMaxMultipleBet(double maxMultipleBet) {
        this.maxMultipleBet = maxMultipleBet;
    }

    /**
     *
     * @return
     * The maxBet
     */
    @JsonProperty("maxBet")
    public double getMaxBet() {
        return maxBet;
    }

    /**
     *
     * @param maxBet
     * The maxBet
     */
    @JsonProperty("maxBet")
    public void setMaxBet(double maxBet) {
        this.maxBet = maxBet;
    }

    /**
     *
     * @return
     * The cashoutAvailable
     */
    @JsonProperty("cashoutAvailable")
    public boolean isCashoutAvailable() {
        return cashoutAvailable;
    }

    /**
     *
     * @param cashoutAvailable
     * The cashoutAvailable
     */
    @JsonProperty("cashoutAvailable")
    public void setCashoutAvailable(boolean cashoutAvailable) {
        this.cashoutAvailable = cashoutAvailable;
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
     * The isInPlay
     */
    @JsonProperty("isInPlay")
    public boolean isIsInPlay() {
        return isInPlay;
    }

    /**
     *
     * @param isInPlay
     * The isInPlay
     */
    @JsonProperty("isInPlay")
    public void setIsInPlay(boolean isInPlay) {
        this.isInPlay = isInPlay;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The marketCountInPlay
     */
    @JsonProperty("marketCountInPlay")
    public long getMarketCountInPlay() {
        return marketCountInPlay;
    }

    /**
     *
     * @param marketCountInPlay
     * The marketCountInPlay
     */
    @JsonProperty("marketCountInPlay")
    public void setMarketCountInPlay(long marketCountInPlay) {
        this.marketCountInPlay = marketCountInPlay;
    }

    /**
     *
     * @return
     * The marketCountPreMatch
     */
    @JsonProperty("marketCountPreMatch")
    public long getMarketCountPreMatch() {
        return marketCountPreMatch;
    }

    /**
     *
     * @param marketCountPreMatch
     * The marketCountPreMatch
     */
    @JsonProperty("marketCountPreMatch")
    public void setMarketCountPreMatch(long marketCountPreMatch) {
        this.marketCountPreMatch = marketCountPreMatch;
    }

    /**
     *
     * @return
     * The marketCountActiveInPlay
     */
    @JsonProperty("marketCountActiveInPlay")
    public long getMarketCountActiveInPlay() {
        return marketCountActiveInPlay;
    }

    /**
     *
     * @param marketCountActiveInPlay
     * The marketCountActiveInPlay
     */
    @JsonProperty("marketCountActiveInPlay")
    public void setMarketCountActiveInPlay(long marketCountActiveInPlay) {
        this.marketCountActiveInPlay = marketCountActiveInPlay;
    }

    /**
     *
     * @return
     * The marketCountActivePreMatch
     */
    @JsonProperty("marketCountActivePreMatch")
    public long getMarketCountActivePreMatch() {
        return marketCountActivePreMatch;
    }

    /**
     *
     * @param marketCountActivePreMatch
     * The marketCountActivePreMatch
     */
    @JsonProperty("marketCountActivePreMatch")
    public void setMarketCountActivePreMatch(long marketCountActivePreMatch) {
        this.marketCountActivePreMatch = marketCountActivePreMatch;
    }

    /**
     *
     * @return
     * The markets
     */
    @JsonProperty("markets")
    public List<Market> getMarkets() {
        return markets;
    }

    /**
     *
     * @param markets
     * The markets
     */
    @JsonProperty("markets")
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    /**
     *
     * @return
     * The marketCountActiveTotal
     */
    @JsonProperty("marketCountActiveTotal")
    public long getMarketCountActiveTotal() {
        return marketCountActiveTotal;
    }

    /**
     *
     * @param marketCountActiveTotal
     * The marketCountActiveTotal
     */
    @JsonProperty("marketCountActiveTotal")
    public void setMarketCountActiveTotal(long marketCountActiveTotal) {
        this.marketCountActiveTotal = marketCountActiveTotal;
    }

    /**
     *
     * @return
     * The sysIsVoid
     */
    @JsonProperty("sys_isVoid")
    public boolean isSysIsVoid() {
        return sysIsVoid;
    }

    /**
     *
     * @param sysIsVoid
     * The sys_isVoid
     */
    @JsonProperty("sys_isVoid")
    public void setSysIsVoid(boolean sysIsVoid) {
        this.sysIsVoid = sysIsVoid;
    }

    /**
     *
     * @return
     * The sysIsOff
     */
    @JsonProperty("sys_isOff")
    public boolean isSysIsOff() {
        return sysIsOff;
    }

    /**
     *
     * @param sysIsOff
     * The sys_isOff
     */
    @JsonProperty("sys_isOff")
    public void setSysIsOff(boolean sysIsOff) {
        this.sysIsOff = sysIsOff;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getCompetitionId() {
        return getAncestors().get(0);
    }

    public String getClassId() {
        return getAncestors().get(1);
    }

    public Market getFirstMarket() {
        if(!getMarkets().isEmpty())
            return getMarkets().get(0);
        else
            throw new IllegalArgumentException(
                    String.format("The event %s doesn't contain any markets.", getId()));
    }

}
