package com.ethicsinc.server.session.port.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@Service
public class GameRestClient {
    @Value("${gameContext.location}")
    private String location;

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;

    private RestTemplate restTemplate;

    public GameRestClient() {
        this.restTemplate = new RestTemplate();
    }

    public void createGame(){
        try {
            this.restTemplate.postForLocation(new URI("http://"+address +":"+ port+"/"+location), 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
