package com.ethicsinc.server.session.domain.model.concern;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.port.adapter.persistence.ConcernRepository;

import java.util.ArrayList;
import java.util.List;

public class Concern {
    private final ConcernId id;
    private final String name;
    private final String category;
    private final String description;
    private float averagePriority;
    private final List<Priority> priorities;
    private final ConcernRepository concernRepository;

    public Concern(ConcernId id, String name, String category, String description, ConcernRepository concernRepository) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.priorities = new ArrayList<Priority>();
        this.concernRepository = concernRepository;
    }

    public void givePriority(Player player, Priority priority) {
        priorities.add(priority);
        calculateAverage();
    }

    private void calculateAverage() {
        int totalWeight = 0;
        for(Priority priority : priorities) {
            totalWeight += priority.getWeight();
        }
        averagePriority = totalWeight / priorities.size();
    }

    //Notify
    public void notifyPlayer() {

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
