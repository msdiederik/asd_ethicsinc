package com.ethicsinc.server.session.port.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@Service
public class GameRestService {
    @Value("${gameContext.location}")
    private String url;

    @Value("${server.port}")
    private String port;

    private RestTemplate restTemplate;

    public GameRestService() {
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
