package model.squiz;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "enabled",
        "id",
        "type",
        "gameNameId",
        "text",
        "url",
        "icon",
        "background"
})
public class SquizCarouselData {

    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("gameNameId")
    private String gameNameId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("url")
    private String url;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("background")
    private String background;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The enabled
     */
    @JsonProperty("enabled")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     * The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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
     * The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The gameNameId
     */
    @JsonProperty("gameNameId")
    public String getGameNameId() {
        return gameNameId;
    }

    /**
     *
     * @param gameNameId
     * The gameNameId
     */
    @JsonProperty("gameNameId")
    public void setGameNameId(String gameNameId) {
        this.gameNameId = gameNameId;
    }

    /**
     *
     * @return
     * The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The icon
     */
    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The background
     */
    @JsonProperty("background")
    public String getBackground() {
        return background;
    }

    /**
     *
     * @param background
     * The background
     */
    @JsonProperty("background")
    public void setBackground(String background) {
        this.background = background;
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