package com.ethicsinc.server.session.domain.model.session;

import com.ethicsinc.server.session.port.adapter.persistence.ChatMessageRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {
    private final PlayerRepository playerRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate template;

    @Autowired
    public SessionFactory(PlayerRepository playerRepository,
                          ChatMessageRepository chatMessageRepository, SimpMessagingTemplate template) {
        this.playerRepository = playerRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.template = template;
    }

    public Session build(SessionId sessionId) {
        return new Session(sessionId, playerRepository, chatMessageRepository, template);
    }
}
