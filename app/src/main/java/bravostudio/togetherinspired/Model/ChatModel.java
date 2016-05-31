package bravostudio.togetherinspired.Model;

import java.io.Serializable;

/**
 * Created by jouvyap on 5/29/16.
 */
public class ChatModel implements Serializable {

    private String chatSender;
    private String chatTimestamp;
    private String chatMessage;

    public ChatModel(String chatSender, String chatTimestamp, String chatMessage) {
        this.chatSender = chatSender;
        this.chatTimestamp = chatTimestamp;
        this.chatMessage = chatMessage;
    }

    public String getChatSender() {
        return chatSender;
    }

    public void setChatSender(String chatSender) {
        this.chatSender = chatSender;
    }

    public String getChatTimestamp() {
        return chatTimestamp;
    }

    public void setChatTimestamp(String chatTimestamp) {
        this.chatTimestamp = chatTimestamp;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
