package com.ethicsinc.server.session.domain.model.chatmessage;

import java.io.Serializable;

public class ChatMessageDTO implements Serializable {
    private String sender;
    private String message;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
