package com.ethicsinc.server.stakeholders.domain.model.concern;
import com.ethicsinc.server.stakeholders.port.adapter.rest.SessionRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcernFactory {
    private final SessionRestClient sessionRestClient;

    @Autowired
    public ConcernFactory(SessionRestClient sessionRestClient) {
        this.sessionRestClient = sessionRestClient;
    }

    public Concern build(ConcernId id, String name, String category, String description) {
        return new Concern(id, name, category, description, sessionRestClient);
    }
}

