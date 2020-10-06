package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessage;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageId;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends BaseRepository<ChatMessage, ChatMessageId> {
}
