package com.ethicsinc.server.session.port.adapter.rest;

import com.ethicsinc.server.session.application.service.PlayerApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
public class PlayerRestService {
    private final PlayerApplicationService playerApplicationService;

    public PlayerRestService(PlayerApplicationService playerApplicationService) {
        this.playerApplicationService = playerApplicationService;
    }

    @PostMapping("/message")
    public void sendMessage(@RequestParam long playerId, @RequestParam String message){
        playerApplicationService.sendMessage(playerId, message);
    }
}
