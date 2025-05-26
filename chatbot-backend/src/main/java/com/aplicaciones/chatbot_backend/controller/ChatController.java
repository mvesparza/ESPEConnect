package com.aplicaciones.chatbot_backend.controller;

import com.aplicaciones.chatbot_backend.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
public class ChatController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/query")
    public ResponseEntity<String> getChatResponse(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        String response = chatbotService.getResponse(query);
        return ResponseEntity.ok(response);
    }
}
