package com.javalearning.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class ChatController {
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        log.info("Received message: {}", message);
        message.setResponse("Message Received at " + LocalDateTime.now());
        log.info("Sending response: {}", message);
        return message;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.send2")
    public void sendMessage2(@Payload ChatMessage message) {
        log.info("Received message sendMessage2: {}", message);
        String reply = "Reply to: " + message;

        // Send the reply to a specific topic
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
