package bravostudio.togetherinspired.Model;

import java.io.Serializable;

/**
 * Created by jouvyap on 5/28/16.
 */
public class ForumModel implements Serializable{
    private String speaker;
    private String time;
    private String title;

    public ForumModel(String speaker, String time, String title) {
        this.speaker = speaker;
        this.time = time;
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
