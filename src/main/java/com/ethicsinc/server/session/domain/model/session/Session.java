package com.ethicsinc.server.session.domain.model.session;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessage;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageFactory;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageId;
import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerDTO;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageDTO;
import com.ethicsinc.server.session.port.adapter.persistence.ChatMessageRepository;
import com.ethicsinc.server.session.port.adapter.persistence.PlayerRepository;
import com.ethicsinc.server.session.port.adapter.rest.GameRestService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Session {
    private final SessionId id;
    private final String code;
    private final List<PlayerId> players;
    private final List<ChatMessageId> chat;
    private final PlayerRepository playerRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageFactory chatMessageFactory;
    private final SimpMessagingTemplate simpMessagingTemplate;

    Session(SessionId id,
            PlayerRepository playerRepository,
            ChatMessageRepository chatMessageRepository,
            ChatMessageFactory chatMessageFactory,
            SimpMessagingTemplate simpMessagingTemplate,
            GameRestService gameRestService) {
        this.id = id;
        this.code = this.generateSessionCode();
        this.players = new ArrayList<>();
        this.chat = new ArrayList<>();
        this.playerRepository = playerRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageFactory = chatMessageFactory;
        this.simpMessagingTemplate = simpMessagingTemplate;

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
            try {
                Player player = this.playerRepository.getById(playerId);
                players.add(player);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return players;
    }

    public boolean isInSession(PlayerId needle) {
        for(PlayerId playerId : this.players) {
            return playerId.equals(needle);
        }
        return false;
    }

    public void sendMessage(ChatMessage chatMessage){
        this.chat.add(chatMessage.getId());
        System.out.println("Now "+this.chat.size()+" messages in chat");
        this.simpMessagingTemplate.convertAndSend("/message/"+this.code, chatMessage.mapToDTO());
    }

    public String getCode(){
        return this.code;
    }

    public SessionDTO mapToDTO() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for(PlayerId playerId : this.players) {
            try {
                Player player = this.playerRepository.getById(playerId);
                playerDTOS.add(player.mapToDTO());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<ChatMessageDTO> chatMessageDTOS = new ArrayList<>();
        for(ChatMessageId chatMessageId : this.chat) {
            try {
                ChatMessage chatMessage = this.chatMessageRepository.getById(chatMessageId);
                chatMessageDTOS.add(chatMessage.mapToDTO());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new SessionDTO(this.code, playerDTOS, chatMessageDTOS);
    }

    public void notifyPlayers(PlayerId playerId) {
        ChatMessageId id = chatMessageRepository.nextId();
        ChatMessage chatMessage = chatMessageFactory.build(id, playerId, "Player joined the game");
        chatMessageRepository.save(chatMessage);

        try {
            this.sendMessage(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
