package com.javalearning.websocket;

import com.javalearning.ai.Aistudio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    Aistudio aistudio;

    @MessageMapping("/chat.ask-google")
    @SendTo("/topic/googleapi")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        log.info("Received message: {}", message);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setResponse(aistudio.getGeminiResponse(message.getContent()));
        chatMessage.setSender("support");
        log.info("Sending response: {}", chatMessage);
        return chatMessage;
    }

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//
//    @MessageMapping("/chat.send2")
//    public void sendMessage2(@Payload ChatMessage message) {
//        log.info("Received message sendMessage2: {}", message);
//        String reply = "Reply to: " + message;
//
//        // Send the reply to a specific topic
//        messagingTemplate.convertAndSend("/topic/public", message);
//    }
}
