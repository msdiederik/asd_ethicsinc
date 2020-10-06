package com.ethicsinc.server.session.domain.model.player;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessage;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageFactory;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageId;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.domain.model.session.SessionFactory;
import com.ethicsinc.server.session.domain.model.session.SessionId;
import com.ethicsinc.server.session.port.adapter.persistence.ChatMessageRepository;
import com.ethicsinc.server.session.port.adapter.persistence.SessionRepository;


public class Player {
    private final PlayerId id;
    private String username;
    private final SessionFactory sessionFactory;
    private final SessionRepository sessionRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageFactory chatMessageFactory;

    Player(PlayerId id, String username,
           SessionRepository sessionRepository,
           SessionFactory sessionFactory,
           ChatMessageRepository chatMessageRepository,
           ChatMessageFactory chatMessageFactory) {
        this.id = id;
        this.username = username;
        this.sessionRepository = sessionRepository;
        this.sessionFactory = sessionFactory;
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageFactory = chatMessageFactory;
    }

    public void createSession(){
        SessionId sessionId = sessionRepository.nextId();
        Session session = sessionFactory.build(sessionId);
        session.join(this);
        this.sessionRepository.save(session);
    }

    public void joinSession(String sessionCode) {
        Session session = sessionRepository.getBySessionCode(sessionCode);
        session.join(this);
        this.sessionRepository.save(session);
    }

    public void sendChatMessage(String message) {
        ChatMessageId id = chatMessageRepository.nextId();
        ChatMessage chatMessage = chatMessageFactory.build(id, this.getId(), message);
        chatMessageRepository.save(chatMessage);

        try {
            Session session = sessionRepository.getByPlayerId(this.id);
            session.sendMessage(chatMessage);
            this.sessionRepository.save(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlayerDTO mapToDTO() {
        return new PlayerDTO(this.id.value(), this.username);
    }

    public PlayerId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
