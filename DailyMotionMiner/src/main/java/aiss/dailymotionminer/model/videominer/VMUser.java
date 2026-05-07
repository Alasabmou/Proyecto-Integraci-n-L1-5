package aiss.dailymotionminer.model.videominer;

import java.util.concurrent.ThreadLocalRandom;

public class VMUser {

    private Long id;
    private String name;
    private String user_link;
    private String picture_link;

    public VMUser(String name, String user_link, String picture_link) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.name = name;
        this.user_link = user_link;
        this.picture_link = picture_link;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUser_link() { return user_link; }
    public void setUser_link(String user_link) { this.user_link = user_link; }

    public String getPicture_link() { return picture_link; }
    public void setPicture_link(String picture_link) { this.picture_link = picture_link; }
}