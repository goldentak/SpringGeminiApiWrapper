package com.example.app.controllers;


import com.example.app.services.GeminiService;
import com.example.app.models.Message;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AiController {
    private final ArrayList<Message> messages = new ArrayList<>();
    private final GeminiService geminiService;

    public AiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/message")
    public Message sendMessage(@RequestBody String text) {
        Message responseMessage = geminiService.sendMessageToGemini(text);
        messages.add(responseMessage);
        return responseMessage;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messages;
    }
}
