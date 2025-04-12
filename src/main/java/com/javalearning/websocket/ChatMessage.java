package com.javalearning.websocket;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
    private String response;
    // Getters and setters
}


enum MessageType{
    CHAT;
}

