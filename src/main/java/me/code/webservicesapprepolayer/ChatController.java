package me.code.webservicesapprepolayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;

    @Autowired
    public ChatController(ChatService chatService, UserRepository userRepository) {
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(
            @RequestBody String content,
            @RequestHeader String userId
    ) {
        System.out.println("ABC");
        var user = userRepository.findById(userId).get();
        var message = chatService.sendMessage(user, content);

        var dto = new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getDate(),
                user.getId()
        );

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-message/{content}")
    public ResponseEntity<MessageDTO> getMessage(
            @PathVariable String content
    ) {
        long time = System.nanoTime();
        var message = chatService.getMessage(content);
        long finished = System.nanoTime();
        long took = finished - time;
        System.out.println("Took " + took + "ns");
        var dto = new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getDate(),
                ""
        );

        return ResponseEntity.ok(dto);
    }
}
