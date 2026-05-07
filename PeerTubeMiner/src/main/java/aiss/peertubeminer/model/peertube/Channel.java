package aiss.peertubeminer.model.peertube;

import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "displayName",
    "description",
    "createdAt",
    "url",
    "host",
    "avatars"
})
@Generated("jsonschema2pojo")
public class Channel {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("displayName")
    private String displayName;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("url")
    private String url;
    @JsonProperty("host")
    private String host;
    @JsonProperty("avatars")
    private List<Avatar> avatars;

    @JsonProperty("id")
    public Integer getId() { return id; }

    @JsonProperty("id")
    public void setId(Integer id) { this.id = id; }

    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("name")
    public void setName(String name) { this.name = name; }

    @JsonProperty("displayName")
    public String getDisplayName() { return displayName; }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    @JsonProperty("description")
    public String getDescription() { return description; }

    @JsonProperty("description")
    public void setDescription(String description) { this.description = description; }

    @JsonProperty("createdAt")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @JsonProperty("url")
    public String getUrl() { return url; }

    @JsonProperty("url")
    public void setUrl(String url) { this.url = url; }

    @JsonProperty("host")
    public String getHost() { return host; }

    @JsonProperty("host")
    public void setHost(String host) { this.host = host; }

    @JsonProperty("avatars")
    public List<Avatar> getAvatars() { return avatars; }

    @JsonProperty("avatars")
    public void setAvatars(List<Avatar> avatars) { this.avatars = avatars; }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", url='" + url + '\'' +
                ", host='" + host + '\'' +
                ", avatars=" + avatars +
                '}';
    }
}