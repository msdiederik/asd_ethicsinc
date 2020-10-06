package com.ethicsinc.server.stakeholders.domain.model.concern;

import java.io.Serializable;

public class ConcernDTO implements Serializable {
    private long id;
    private float priority;

    public ConcernDTO(long id, float priority) {
        this.id = id;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }
}