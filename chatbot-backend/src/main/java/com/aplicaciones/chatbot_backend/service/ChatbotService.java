package com.aplicaciones.chatbot_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

    private final RestTemplate restTemplate;

    @Autowired
    public ChatbotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getResponse(String query) {
        String url = "https://be01-35-229-165-195.ngrok-free.app/chat";

        Map<String, String> body = new HashMap<>();
        body.put("query", query);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return (String) responseEntity.getBody().get("response");
        } else {
            throw new RuntimeException("Error al comunicarse con el chatbot");
        }
    }
}