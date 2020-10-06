package com.ethicsinc.server.session.domain.model.chatmessage;

import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageFactory {
    private final PlayerRepository playerRepository;

    public ChatMessageFactory(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public ChatMessage build(ChatMessageId id, PlayerId sender, String message) {
        return new ChatMessage(id, sender, message, playerRepository);
    }
}
