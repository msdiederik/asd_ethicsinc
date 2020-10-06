package com.ethicsinc.server.session.port.adapter.rest;

import com.ethicsinc.server.session.application.service.PlayerApplicationService;
import com.ethicsinc.server.session.application.service.SessionApplicationService;
import com.ethicsinc.server.session.domain.model.player.Player;
import com.ethicsinc.server.session.domain.model.player.PlayerId;
import com.ethicsinc.server.session.domain.model.session.Session;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionRestService {
    private final SessionApplicationService sessionApplicationService;
    private final PlayerApplicationService playerApplicationService;

    public SessionRestService(SessionApplicationService sessionApplicationService, PlayerApplicationService playerApplicationService) {
        this.sessionApplicationService = sessionApplicationService;
        this.playerApplicationService = playerApplicationService;
    }

    @PostMapping("/")
    public void createSession(@RequestParam String username){
        sessionApplicationService.createSession(username);
    }

    @GetMapping("/")
    public List<SessionDTO> getSessions() {
        List<Session> sessions = sessionApplicationService.getAllSessions();
        List<SessionDTO> sessionDTOS = new ArrayList<>();

        for(Session session : sessions) {

            List<PlayerDTO> playerDTOS = new ArrayList<>();
            List<Player> players = session.getPlayers();
            for(Player player : players) {
                playerDTOS.add(new PlayerDTO(player.getId().getId(), player.getUsername()));
            }

            sessionDTOS.add(new SessionDTO(session.getCode(), playerDTOS));
        }
        return sessionDTOS;
    }
}
