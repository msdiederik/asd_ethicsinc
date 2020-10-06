package com.ethicsinc.server.session.port.adapter.rest;

import org.springframework.web.client.RestTemplate;
import java.net.URI;

public class GameRestService {
    private final String url;
    private RestTemplate restTemplate;

    public GameRestService() {
        this.url = "http://localhost:8080/api/game/";
        this.restTemplate = new RestTemplate();
    }

    public void createGame(){
        try {
            this.restTemplate.postForLocation(new URI(this.url), 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
