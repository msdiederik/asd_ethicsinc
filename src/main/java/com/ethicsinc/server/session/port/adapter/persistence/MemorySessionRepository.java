package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.domain.model.session.SessionId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemorySessionRepository implements SessionRepository{
    private long id;
    private List<Session> sessions;

    MemorySessionRepository(){
        this.id = 0;
        this.sessions = new ArrayList<>();
    }

    @Override
    public SessionId nextId() {
        this.id++;
        return new SessionId(this.id);
    }

    @Override
    public void save(Session session){
        int sessionIndex = this.sessions.indexOf(session);

        if(sessionIndex == -1) {
            this.sessions.add(session);
        } else {
            this.sessions.set(sessionIndex, session);
        }
    }

    @Override
    public List<Session> getAll() {
        return this.sessions;
    }

    @Override
    public Session getById(SessionId sessionId) throws Exception {
        for(Session session : this.sessions) {
            if(session.getId() == sessionId){
                return session;
            }
        }
        throw new Exception("No session found with id: "+sessionId);
    }

    @Override
    public Session getByPlayerId(PlayerId playerId) throws Exception {
        for(Session session : sessions) {
            if(session.isInSession(playerId)){
                return session;
            }
        }
        throw new Exception("No session found with playerId: "+playerId.value());
    }
}
