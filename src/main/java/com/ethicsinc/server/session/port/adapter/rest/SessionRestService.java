package com.ethicsinc.server.session.port.adapter.rest;

import com.ethicsinc.server.session.application.service.SessionApplicationService;
import com.ethicsinc.server.session.domain.model.session.Session;
import com.ethicsinc.server.session.domain.model.session.SessionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionRestService {
    private final SessionApplicationService sessionApplicationService;

    public SessionRestService(SessionApplicationService sessionApplicationService) {
        this.sessionApplicationService = sessionApplicationService;
    }

    @PostMapping("/")
    public void createSession(@RequestParam String username){
        sessionApplicationService.createSession(username);
    }

    @GetMapping("/")
    public List<SessionDTO> getSessions() {
        List<Session> sessions = sessionApplicationService.getAllSessions();
        List<SessionDTO> sessionDTOS = new ArrayList<>();

        for(Session session : sessions) {
            sessionDTOS.add(session.mapToDTO());
        }

        return sessionDTOS;
    }

    @GetMapping("/getOne")
    public SessionDTO getSession(@RequestParam String sessionCode) {
        Session session = this.sessionApplicationService.getSessionBySessionCode(sessionCode);
        return session.mapToDTO();
    }

    @PutMapping("/")
    public void joinSession(@RequestParam String username,@RequestParam String sessionCode) {
        sessionApplicationService.joinSession(username,sessionCode);
    }

    @GetMapping("/notify")
    public void notifyPlayer(@RequestParam long playerId) {
        System.out.println("notifying players");
    }
}
