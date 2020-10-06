package com.ethicsinc.server.session.port.adapter.rest;

import java.io.Serializable;
import java.util.List;

public class SessionDTO implements Serializable {
    private String code;
    private List<PlayerDTO> players;

    public SessionDTO(String code, List<PlayerDTO> players) {
        this.code = code;
        this.players = players;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
