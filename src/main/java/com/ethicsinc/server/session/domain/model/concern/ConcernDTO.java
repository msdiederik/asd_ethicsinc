package com.ethicsinc.server.session.domain.model.concern;

import java.io.Serializable;

public class ConcernDTO implements Serializable {
    private long id;

    public ConcernDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}