package com.ethicsinc.server.stakeholders.domain.model.concern;

import com.ethicsinc.server.stakeholders.domain.model.player.PlayerId;

public class Priority {
    private final PlayerId player;
    private final int weight;

    public Priority(PlayerId player, int weight) {
        this.player = player;
        this.weight = weight;
    }

    public PlayerId getPlayer() {
        return player;
    }

    public int getWeight() {
        return weight;
    }

}
