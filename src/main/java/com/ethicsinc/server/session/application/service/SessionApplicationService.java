package com.ethicsinc.server.session.application.service;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.port.adapter.persistence.MemoryPlayerRepository;
import com.ethicsinc.server.session.port.adapter.persistence.MemorySessionRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.session.port.adapter.persistence.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionApplicationService {
    private final PlayerRepository playerRepository;
    private final SessionRepository sessionRepository;

    public SessionApplicationService(MemoryPlayerRepository memoryPlayerRepository, MemorySessionRepository memorySessionRepository) {
        this.playerRepository = memoryPlayerRepository;
        this.sessionRepository = memorySessionRepository;
    }

    public void createSession (String username) {
        long playerId = playerRepository.nextId();
        Player player = new Player(playerId, username, sessionRepository, playerRepository);
        player.createSession();
        playerRepository.save(player);
    }

    public void joinSession (String username, String sessionCode) {
        long playerId = playerRepository.nextId();
        Player player = new Player(playerId, username, sessionRepository, playerRepository);
        player.joinSession(sessionCode);
        playerRepository.save(player);
    }

    public List<Session> getAllSessions(){
        return sessionRepository.getAll();
    }
}
