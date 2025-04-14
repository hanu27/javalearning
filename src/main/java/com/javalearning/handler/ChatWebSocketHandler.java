package com.javalearning.handler;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    // Map to store userId -> WebSocketSession
    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Connection established
        System.out.println("New connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Parse the incoming message
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // Example payload: {"type":"register", "userId":"user1"}
        // Example payload: {"type":"message", "to":"user2", "from":"user1", "content":"Hello!"}
        var json = new com.fasterxml.jackson.databind.ObjectMapper().readTree(payload);

        String type = json.get("type").asText();

        if ("register".equals(type)) {
            // Register the user
            String userId = json.get("userId").asText();
            sessions.put(userId, session);
            System.out.println("User registered: " + userId);
        } else if ("message".equals(type)) {
            // Route the message to the recipient
            String to = json.get("to").asText();
            String from = json.get("from").asText();
            String content = json.get("content").asText();

            WebSocketSession recipientSession = sessions.get(to);
            if (recipientSession != null && recipientSession.isOpen()) {
                // Forward the message to the recipient
                String response = String.format("{\"from\":\"%s\", \"content\":\"%s\"}", from, content);
                recipientSession.sendMessage(new TextMessage(response));
            } else {
                System.out.println("User " + to + " is not connected.");
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove the user from the sessions map
        sessions.entrySet().removeIf(entry -> entry.getValue().equals(session));
        System.out.println("Connection closed: " + session.getId());
    }
}