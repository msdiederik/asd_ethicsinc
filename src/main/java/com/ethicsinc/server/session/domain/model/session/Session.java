package com.ethicsinc.server.session.domain.model.session;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageId;
import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.session.port.adapter.rest.GameRestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Session {
    private SessionId id;
    private String code;
    private List<PlayerId> players;
    private List<ChatMessageId> chat;
    private PlayerRepository playerRepository;

    public Session(long id, PlayerRepository playerRepository) {
        this.id = new SessionId(id);
        this.code = this.generateSessionCode();
        this.players = new ArrayList<>();
        this.chat = new ArrayList<>();
        this.playerRepository = playerRepository;

        GameRestService gameRestService = new GameRestService();
        gameRestService.createGame();
    }

    public void join(Player player) {
        this.players.add(player.getId());
    }

    private String generateSessionCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public SessionId getId() {
        return id;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        for(PlayerId playerId : this.players) {
            Player player = this.playerRepository.getById(playerId);
            players.add(player);
        }
        return players;
    }

    public String getCode(){
        return this.code;
    }
}
