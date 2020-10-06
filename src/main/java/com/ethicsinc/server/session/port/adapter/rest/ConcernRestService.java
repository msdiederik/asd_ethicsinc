package com.ethicsinc.server.session.port.adapter.rest;

import org.springframework.web.bind.annotation.*;

public class ConcernRestService {
    private final String url;

    public ConcernRestService() {
        this.url = "http://localhost:8080/api//";
    }
}
