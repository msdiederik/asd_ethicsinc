package com.ethicsinc.server.stakeholders.domain.model.concern;


import com.ethicsinc.server.stakeholders.domain.model.player.PlayerId;
import com.ethicsinc.server.stakeholders.port.adapter.rest.SessionRestClient;

import java.util.ArrayList;
import java.util.List;

public class Concern {
    private final ConcernId id;
    private final String name;
    private final String category;
    private final String description;
    private float averagePriority;
    private final List<Priority> priorities;
    private final SessionRestClient sessionRestClient;

    public Concern(ConcernId id, String name, String category, String description, SessionRestClient sessionRestClient) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.priorities = new ArrayList<Priority>();
        this.sessionRestClient = sessionRestClient;
    }

    public void giveWeight(PlayerId player, int weight) {
        Priority priority = new Priority(player, weight);
        priorities.add(priority);
        calculateAverage();
        notifyPlayers(player);
    }

    private void calculateAverage() {
        int totalWeight = 0;
        for(Priority priority : priorities) {
            totalWeight += priority.getWeight();
        }
        averagePriority = (float)totalWeight / (float)priorities.size();
    }

    //Notify
    public void notifyPlayers(PlayerId playerId) {
       sessionRestClient.notifyPlayers(playerId.value());
    }

    public ConcernDTO mapToDTO() {
        return new ConcernDTO(this.id.value(), getPriority());
    }

    public ConcernId getId() {
        return id;
    }

    public float getPriority() {
        return averagePriority;
    }
}
