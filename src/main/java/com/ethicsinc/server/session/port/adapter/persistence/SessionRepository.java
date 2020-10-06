package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.domain.model.session.SessionId;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends BaseRepository<Session, SessionId>{
    public Session getByPlayerId(PlayerId playerId) throws Exception;
}
