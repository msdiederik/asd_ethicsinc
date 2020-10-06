package com.ethicsinc.server.session.domain.model.player;

import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.session.port.adapter.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Player {
    private final PlayerId id;
    private String username;
    private final SessionRepository sessionRepository;
    private final PlayerRepository playerRepository;

    public Player(long id, String username, SessionRepository sessionRepository, PlayerRepository playerRepository) {
        this.id = new PlayerId(id);
        this.username = username;
        this.sessionRepository = sessionRepository;
        this.playerRepository = playerRepository;
    }

    public void createSession(){
        long sessionId = sessionRepository.nextId();
        Session session = new Session(sessionId, playerRepository);
        session.join(this);
        this.sessionRepository.save(session);
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
