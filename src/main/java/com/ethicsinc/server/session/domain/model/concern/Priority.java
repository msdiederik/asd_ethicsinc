package com.ethicsinc.server.session.domain.model.concern;

import com.ethicsinc.server.session.domain.model.player.Player;

public class Priority {
    private final Player player;
    private final int weight;

    public Priority(Player player, int weight) {
        this.player = player;
        this.weight = weight;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWeight() {
        return weight;
    }

}
