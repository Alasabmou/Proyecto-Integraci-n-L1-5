package aiss.dailymotionminer.model.videominer;

import java.time.LocalDate;
import java.util.UUID;

public class VMComment {

    private String id;
    private String text;
    private LocalDate createdOn;

    public VMComment(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.createdOn = LocalDate.now();
    }

    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void settext(String text) {
        this.text = text;
    }
    public void setCreated(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "VMComment [Id=" +id+ "text=" + text + "createdOn=" + createdOn+"]";
    } 

    
}