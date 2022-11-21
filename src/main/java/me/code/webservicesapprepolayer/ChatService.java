package me.code.webservicesapprepolayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Message sendMessage(User user, String content) {
        var message = new Message(content, user);
        return chatRepository.save(message);
    }

    public Message getMessage(String content) {
        return chatRepository.findById(content).orElse(null);
    }
}
