package com.ethicsinc.server.session.domain.model.player;

import java.io.Serializable;

public class PlayerDTO implements Serializable {
    private long id;
    private String username;

    public PlayerDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
