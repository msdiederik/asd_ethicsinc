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
        return playerRepository.getById(playerId);
    }
}
