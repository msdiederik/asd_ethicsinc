package com.ethicsinc.server.session.domain.model.chatmessage;

import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;

public class ChatMessage {
    private final ChatMessageId id;
    private final PlayerRepository playerRepository;
    private PlayerId sender;
    private String message;

    ChatMessage(ChatMessageId id,
                       PlayerId sender,
                       String message,
                       PlayerRepository playerRepository) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.playerRepository = playerRepository;
    }

    public ChatMessageDTO mapToDTO() {
        String sender = null;

        try {
            sender = playerRepository.getById(this.sender).getUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ChatMessageDTO(sender, this.message);
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
