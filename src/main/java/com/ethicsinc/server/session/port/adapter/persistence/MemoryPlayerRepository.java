package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryPlayerRepository implements PlayerRepository{
    private long id;
    private List<Player> players;

    MemoryPlayerRepository() {
        this.id = 0;
        this.players = new ArrayList<>();
    }


    @Override
    public long nextId() {
        this.id++;
        return this.id;
    }

    @Override
    public void save(Player player) {
        int playerIndex = this.players.indexOf(player);

        if(playerIndex == -1) {
            this.players.add(player);
        } else {
            this.players.set(playerIndex, player);
        }
    }

    @Override
    public List<Player> getAll() {
        return this.players;
    }

    @Override
    public Player getById(PlayerId playerId) {

        for (Player player : this.players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }
}
