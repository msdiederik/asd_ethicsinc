package com.ethicsinc.server.game.port.adapter.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameRestService {
    @PostMapping("/")
    public void createGame(){
        System.out.println("creating game yaya!");
    }
}
