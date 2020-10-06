package com.ethicsinc.server.session.application.service;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.port.adapter.persistence.MemoryPlayerRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerApplicationService {
    private final PlayerRepository playerRepository;

    public PlayerApplicationService(MemoryPlayerRepository memoryPlayerRepository) {
        this.playerRepository = memoryPlayerRepository;
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

    public void sendMessage(long playerId, String message) {
        try {
            Player sender = playerRepository.getById(new PlayerId(playerId));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
