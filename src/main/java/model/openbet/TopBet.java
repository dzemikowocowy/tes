package model.openbet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name",
        "ev_mkt_id",
        "ev_oc_id",
        "ev_id",
        "mkt_name",
        "sport_id",
        "lp_num",
        "lp_den",
        "fb_result",
        "weight",
        "sort",
        "ew_avail",
        "ew_places",
        "ew_fac_num",
        "ew_fac_den",
        "hcap_desc",
        "tooltip_desc",
        "sport_name",
        "category_id",
        "category_name",
        "class_id",
        "class_name",
        "type_id",
        "type_name",
        "ev_name",
        "ev_startDateTime",
        "ev_status",
        "ev_displayorder",
        "mkt_type",
        "mkt_status",
        "mkt_channels",
        "mkt_displayorder",
        "mkt_place_avail",
        "mkt_forecast_avail",
        "mkt_tricast_avail",
        "mkt_sp_avail",
        "mkt_gp_avail",
        "mkt_co_avail",
        "oc_result",
        "oc_runnerNumber",
        "oc_status",
        "oc_odds",
        "oc_oddsDecimal",
        "oc_channels",
        "oc_displayorder"
})
public class TopBet {

    @JsonProperty("name")
    private String name;
    @JsonProperty("ev_mkt_id")
    private int ev_mkt_id;
    @JsonProperty("ev_oc_id")
    private int ev_oc_id;
    @JsonProperty("ev_id")
    private int ev_id;
    @JsonProperty("mkt_name")
    private String mkt_name;
    @JsonProperty("sport_id")
    private int sport_id;
    @JsonProperty("lp_num")
    private int lp_num;
    @JsonProperty("lp_den")
    private int lp_den;
    @JsonProperty("fb_result")
    private String fb_result;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("ew_avail")
    private String ew_avail;
    @JsonProperty("ew_places")
    private String ew_places;
    @JsonProperty("ew_fac_num")
    private String ew_fac_num;
    @JsonProperty("ew_fac_den")
    private String ew_fac_den;
    @JsonProperty("hcap_desc")
    private String hcap_desc;
    @JsonProperty("tooltip_desc")
    private String tooltip_desc;
    @JsonProperty("sport_name")
    private String sport_name;
    @JsonProperty("category_id")
    private int category_id;
    @JsonProperty("category_name")
    private String category_name;
    @JsonProperty("class_id")
    private int class_id;
    @JsonProperty("class_name")
    private String class_name;
    @JsonProperty("type_id")
    private int type_id;
    @JsonProperty("type_name")
    private String type_name;
    @JsonProperty("ev_name")
    private String ev_name;
    @JsonProperty("ev_startDateTime")
    private String ev_startDateTime;
    @JsonProperty("ev_status")
    private String ev_status;
    @JsonProperty("ev_displayorder")
    private int ev_displayorder;
    @JsonProperty("mkt_type")
    private String mkt_type;
    @JsonProperty("mkt_status")
    private String mkt_status;
    @JsonProperty("mkt_channels")
    private String mkt_channels;
    @JsonProperty("mkt_displayorder")
    private int mkt_displayorder;
    @JsonProperty("mkt_place_avail")
    private String mkt_place_avail;
    @JsonProperty("mkt_forecast_avail")
    private String mkt_forecast_avail;
    @JsonProperty("mkt_tricast_avail")
    private String mkt_tricast_avail;
    @JsonProperty("mkt_sp_avail")
    private String mkt_sp_avail;
    @JsonProperty("mkt_gp_avail")
    private String mkt_gp_avail;
    @JsonProperty("mkt_co_avail")
    private String mkt_co_avail;
    @JsonProperty("oc_result")
    private String oc_result;
    @JsonProperty("oc_runnerNumber")
    private String oc_runnerNumber;
    @JsonProperty("oc_status")
    private String oc_status;
    @JsonProperty("oc_odds")
    private String oc_odds;
    @JsonProperty("oc_oddsDecimal")
    private String oc_oddsDecimal;
    @JsonProperty("oc_channels")
    private String oc_channels;
    @JsonProperty("oc_displayorder")
    private int oc_displayorder;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("ev_id")
    public int getEvId() {
        return ev_id;
    }


}
