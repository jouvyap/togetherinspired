package bravostudio.togetherinspired.Model;

import java.io.Serializable;

/**
 * Created by jouvyap on 5/26/16.
 */
public class NewsModel implements Serializable{

    private String title;
    private String tag;
    private String content;

    public NewsModel(String title, String tag, String content) {
        this.title = title;
        this.tag = tag;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
