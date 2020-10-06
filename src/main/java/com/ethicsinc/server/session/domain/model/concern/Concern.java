package com.ethicsinc.server.session.domain.model.concern;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.port.adapter.persistence.ConcernRepository;

public class Concern {
    private final ConcernId id;
    //private final ConcernFactory concernFactory;
    private final ConcernRepository concernRepository;

    public Concern(ConcernId id, ConcernRepository concernRepository) {
        this.id = id;
        this.concernRepository = concernRepository;
    }

    public void weighConcern(Player player, float weight) {
        //apply weight
        calculateAverage();
    }

    private void calculateAverage() {
        //calculate average weight.
    }

    public ConcernDTO mapToDTO() {
        return new ConcernDTO(this.id.value());
    }

    public ConcernId getId() {
        return id;
    }

    public int getPriority() {
        // or getAverage().
        return 0;
    }
}
