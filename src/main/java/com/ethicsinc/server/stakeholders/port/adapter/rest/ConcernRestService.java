package com.ethicsinc.server.stakeholders.port.adapter.rest;

import com.ethicsinc.server.stakeholders.application.service.ConcernApplicationService;
import com.ethicsinc.server.stakeholders.domain.model.concern.Concern;
import com.ethicsinc.server.stakeholders.domain.model.concern.ConcernDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/concern")
public class ConcernRestService {
    private final ConcernApplicationService concernApplicationService;

    public ConcernRestService(ConcernApplicationService concernApplicationService) {
        this.concernApplicationService = concernApplicationService;
    }

    @PostMapping("/weigh_concern")
    public void weighConcern(@RequestParam long playerId, @RequestParam long concernId, @RequestParam int weight){
        concernApplicationService.weighConcern(playerId, concernId, weight);
    }

    @PostMapping("/select_concern")
    public void selectConcern(@RequestParam String name, @RequestParam String category, @RequestParam String description){
        concernApplicationService.createConcern(name, category, description);
    }

    @GetMapping("/")
    public List<ConcernDTO> getConcerns() {
        List<Concern> concerns = concernApplicationService.getAllConcerns();
        List<ConcernDTO> concernDTOS = new ArrayList<ConcernDTO>();

        for(Concern concern : concerns) {
            concernDTOS.add(concern.mapToDTO());
        }
        return concernDTOS;
    }
}
