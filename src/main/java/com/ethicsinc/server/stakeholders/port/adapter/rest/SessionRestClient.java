package com.ethicsinc.server.stakeholders.port.adapter.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class SessionRestClient {
    private final String url;
    private RestTemplate restTemplate;

    public SessionRestClient() {
        this.url = "http://localhost:8080/api/session/notify";
        this.restTemplate = new RestTemplate();
    }

    public void notifyPlayers(long playerId){
        try {
            this.restTemplate.postForLocation(new URI(this.url + "/" + playerId), 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
