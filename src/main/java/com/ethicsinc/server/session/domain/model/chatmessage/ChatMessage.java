package com.ethicsinc.server.session.domain.model.chatmessage;

import com.ethicsinc.server.session.domain.model.player.PlayerId;

public class ChatMessage {
    private final ChatMessageId id;
    private PlayerId sender;
    private String message;

    public ChatMessage(ChatMessageId id, PlayerId sender, String message) {
        this.id = id;
        this.sender = sender;
        this.message = message;
    }

    public ChatMessageId getId() {
        return id;
    }

    public PlayerId getSender() {
        return sender;
    }

    public void setSender(PlayerId sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
