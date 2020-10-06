package com.ethicsinc.server.session.application.service;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerFactory;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.port.adapter.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionApplicationService {
    private final PlayerRepository playerRepository;
    private final SessionRepository sessionRepository;
    private final PlayerFactory playerFactory;

    public SessionApplicationService(MemoryPlayerRepository memoryPlayerRepository,
                                     MemorySessionRepository memorySessionRepository,
                                     PlayerFactory playerFactory) {
        this.playerRepository = memoryPlayerRepository;
        this.sessionRepository = memorySessionRepository;
        this.playerFactory = playerFactory;
    }

    public void createSession (String username) {
        PlayerId playerId = playerRepository.nextId();
        Player player = playerFactory.build(playerId, username);
        player.createSession();
        playerRepository.save(player);
    }

    public List<Session> getAllSessions(){
        return sessionRepository.getAll();
    }

    public Player getPlayerById(PlayerId playerId) {
        Player player = null;
        try {
            player = playerRepository.getById(playerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return player;
    }

    public void sendChatMessage(long playerId, String message) {
        try {
            Player sender = playerRepository.getById(new PlayerId(playerId));
            sender.sendChatMessage(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
