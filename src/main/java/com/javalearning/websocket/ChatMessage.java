package com.javalearning.websocket;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String userName;
    private MessageType type;
    private String response;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    // Getters and setters
}


enum MessageType{
    CHAT,
    REGISTER;
}

