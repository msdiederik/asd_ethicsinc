package com.ethicsinc.server.session.domain.model.session;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageDTO;
import com.ethicsinc.server.session.domain.model.player.PlayerDTO;

import java.io.Serializable;
import java.util.List;

public class SessionDTO implements Serializable {
    private String code;
    private List<PlayerDTO> players;
    private List<ChatMessageDTO> chat;

    public SessionDTO(String code, List<PlayerDTO> players, List<ChatMessageDTO> chat) {
        this.code = code;
        this.players = players;
        this.chat = chat;
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

    public List<ChatMessageDTO> getChat() {
        return chat;
    }

    public void setChat(List<ChatMessageDTO> chat) {
        this.chat = chat;
    }
}
