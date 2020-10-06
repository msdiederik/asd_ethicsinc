package com.ethicsinc.server.session.port.adapter.messaging;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SessionMessagingService {
    @MessageMapping("/message/{session}")
    public ChatMessageDTO sendChatMessage(@DestinationVariable String session, ChatMessageDTO chatMessageDTO){
        return chatMessageDTO;
    }
}
