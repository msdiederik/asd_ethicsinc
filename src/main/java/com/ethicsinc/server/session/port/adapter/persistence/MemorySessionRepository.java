package com.ethicsinc.server.session.port.adapter.persistence;

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
    public long nextId() {
        this.id++;
        return this.id;
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
    public Session getById(SessionId sessionId) {
        for(Session session : this.sessions) {
            if(session.getId() == sessionId){
                return session;
            }
        }
        return null;
    }

    @Override
    public Session findByCode(String code) {
        for(Session session : this.sessions) {
            if(session.getCode().equals(code)) {
                return session;
            }
        }
        return null;
    }
}
