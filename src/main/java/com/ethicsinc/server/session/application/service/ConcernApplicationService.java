package com.ethicsinc.server.session.application.service;

import com.ethicsinc.server.session.domain.model.concern.Concern;
import com.ethicsinc.server.session.domain.model.concern.ConcernId;
import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.concern.Priority;

import com.ethicsinc.server.session.port.adapter.persistence.ConcernRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.session.port.adapter.persistence.MemoryConcernRepository;
import com.ethicsinc.server.session.port.adapter.persistence.MemoryPlayerRepository;

public class ConcernApplicationService {
    private final ConcernRepository concernRepository;
    private final PlayerRepository playerRepository;

    public ConcernApplicationService(MemoryConcernRepository memoryConcernRepository, MemoryPlayerRepository memoryPlayerRepository) {
        this.concernRepository = memoryConcernRepository;
        this.playerRepository = memoryPlayerRepository;
    }

    public void createConcern (String username, String name, String category, String description) {
        ConcernId concernId = concernRepository.nextId();
        Concern concern = new Concern(concernId, name, category, description, concernRepository);
        concernRepository.save(concern);
    }

    public void givePriority(long playerId, long concernId, Priority priority) {
        try {
            Concern concern = concernRepository.getById(new ConcernId(concernId));
            Player sender = playerRepository.getById(new PlayerId(playerId));
            concern.givePriority(sender, priority);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
