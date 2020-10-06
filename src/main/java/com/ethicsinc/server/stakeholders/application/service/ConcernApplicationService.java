package com.ethicsinc.server.stakeholders.application.service;

import com.ethicsinc.server.stakeholders.domain.model.concern.*;

import com.ethicsinc.server.stakeholders.domain.model.player.PlayerId;
import com.ethicsinc.server.stakeholders.port.adapter.persistence.ConcernRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.stakeholders.port.adapter.persistence.MemoryConcernRepository;
import com.ethicsinc.server.session.port.adapter.persistence.MemoryPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcernApplicationService {
    private final ConcernRepository concernRepository;
    private final PlayerRepository playerRepository;
    private final ConcernFactory concernFactory;

    public ConcernApplicationService(MemoryConcernRepository memoryConcernRepository, MemoryPlayerRepository memoryPlayerRepository, ConcernFactory concernFactory) {
        this.concernRepository = memoryConcernRepository;
        this.playerRepository = memoryPlayerRepository;
        this.concernFactory = concernFactory;
    }

    public void createConcern (String name, String category, String description) {
        ConcernId concernId = concernRepository.nextId();
        Concern concern = concernFactory.build(concernId, name, category, description);
        concernRepository.save(concern);
    }

    public void weighConcern(long playerId, long concernId, int weight) {
        try {
            Concern concern = concernRepository.getById(new ConcernId(concernId));
            PlayerId sender = new PlayerId(playerId);
            concern.giveWeight(sender, weight);
            concernRepository.save(concern);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Concern> getAllConcerns(){
        return concernRepository.getAll();
    }

}
