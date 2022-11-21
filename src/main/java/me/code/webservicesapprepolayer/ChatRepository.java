package me.code.webservicesapprepolayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ChatRepository {

    private final Map<String, Message> cache = new HashMap<>();
    private final ChatDaoRepository daoRepository;

    @Autowired
    public ChatRepository(ChatDaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    public Message save(Message message) {
        if (Math.random() <= 0.5) {
            cache.put(message.getContent(), message);
            System.out.println(message.getContent() + " was cached.");
        }

        return daoRepository.save(message);
    }

    public Optional<Message> findById(String content) {
        var message = cache.get(content);
        if (message != null) {
            return Optional.of(message);
        } else {
            return daoRepository.findByContent(content);
        }
    }

}
