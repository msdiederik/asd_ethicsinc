package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends BaseRepository<Player, PlayerId> {
}
