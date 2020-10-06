package com.ethicsinc.server.session.domain.model.player;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageFactory;
import com.ethicsinc.server.session.domain.model.session.SessionFactory;
import com.ethicsinc.server.session.port.adapter.persistence.*;
import org.springframework.stereotype.Component;

@Component
public class PlayerFactory {
    private final SessionRepository sessionRepository;
    private final SessionFactory sessionFactory;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageFactory chatMessageFactory;

    public PlayerFactory(SessionRepository sessionRepository,
                         SessionFactory sessionFactory,
                         ChatMessageRepository chatMessageRepository,
                         ChatMessageFactory chatMessageFactory) {
        this.sessionRepository = sessionRepository;
        this.sessionFactory = sessionFactory;
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageFactory = chatMessageFactory;
    }

    public Player build(PlayerId id, String username){
        return new Player(id, username, sessionRepository, sessionFactory, chatMessageRepository, chatMessageFactory);
    }
}
