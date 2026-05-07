package aiss.peertubeminer.model.peertube;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "displayName",
    "name",
    "url",
    "host",
    "avatars"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("host")
    private String host;
    
    // Cambiado de Pictures a Avatar
    @JsonProperty("avatars")
    private List<Avatar> avatars;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty("avatars")
    public List<Avatar> getAvatars() {
        return avatars;
    }

    @JsonProperty("avatars")
    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id").append('=').append(((this.id == null)?"<null>":this.id)).append(',');
        sb.append("displayName").append('=').append(((this.displayName == null)?"<null>":this.displayName)).append(',');
        sb.append("name").append('=').append(((this.name == null)?"<null>":this.name)).append(',');
        sb.append("url").append('=').append(((this.url == null)?"<null>":this.url)).append(',');
        sb.append("host").append('=').append(((this.host == null)?"<null>":this.host)).append(',');
        sb.append("avatars").append('=').append(((this.avatars == null)?"<null>":this.avatars)).append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}