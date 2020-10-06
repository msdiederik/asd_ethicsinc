package com.ethicsinc.server.session.port.adapter.rest;

import com.ethicsinc.server.session.application.service.ConcernApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class ConcernRestService {
    private final ConcernApplicationService concernApplicationService;

    public ConcernRestService(ConcernApplicationService concernApplicationService) {
        this.concernApplicationService = concernApplicationService;
    }

    @PostMapping("/message")
    public void sendMessage(@RequestParam long playerId, @RequestParam long concernId, @RequestParam float weight){
        concernApplicationService.weighConcern(playerId, concernId, weight);
    }
}
