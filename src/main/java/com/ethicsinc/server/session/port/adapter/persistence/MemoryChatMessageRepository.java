package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessage;
import com.ethicsinc.server.session.domain.model.chatmessage.ChatMessageId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryChatMessageRepository implements ChatMessageRepository{
    private long id;
    private List<ChatMessage> chatMessages;

    public MemoryChatMessageRepository() {
        this.id = 0;
        this.chatMessages = new ArrayList<>();
    }

    @Override
    public ChatMessageId nextId() {
        this.id++;
        return new ChatMessageId(this.id);
    }

    @Override
    public void save(ChatMessage chatMessage) {
        int index = chatMessages.indexOf(chatMessage);
        if(index == -1){
            this.chatMessages.add(chatMessage);
        } else {
            this.chatMessages.set(index, chatMessage);
        }
    }

    @Override
    public List<ChatMessage> getAll() {
        return this.chatMessages;
    }

    @Override
    public ChatMessage getById(ChatMessageId chatMessageId) throws Exception {
        for(ChatMessage chatMessage : this.chatMessages){
            if(chatMessage.getId().equals(chatMessageId)){
                return chatMessage;
            }
        }
        throw new Exception("No chatmessage found with id: "+chatMessageId.value());
    }
}
