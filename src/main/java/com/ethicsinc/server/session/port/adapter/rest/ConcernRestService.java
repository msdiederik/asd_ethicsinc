package com.ethicsinc.server.session.port.adapter.rest;

import com.ethicsinc.server.session.application.service.ConcernApplicationService;
import com.ethicsinc.server.session.domain.model.concern.Priority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/concern")
public class ConcernRestService {
    private final ConcernApplicationService concernApplicationService;

    public ConcernRestService(ConcernApplicationService concernApplicationService) {
        this.concernApplicationService = concernApplicationService;
    }

    @PostMapping("/give_priority")
    public void givePriority(@RequestParam long playerId, @RequestParam long concernId, @RequestParam Priority priority){
        concernApplicationService.givePriority(playerId, concernId, priority);
    }
}
