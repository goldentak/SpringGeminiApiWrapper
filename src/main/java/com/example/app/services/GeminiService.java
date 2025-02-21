package com.example.app.services;


import com.example.app.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public Message sendMessageToGemini(String text){
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(Map.of(
                        "parts", List.of(Map.of("text", text))
                ))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            Map response = restTemplate.postForObject(API_URL + apiKey, requestEntity, Map.class);

            String geminiResponse = parseGeminiResponse(response);
            return new Message(text, geminiResponse);

        } catch (HttpClientErrorException e) {
            return new Message(text, "Ошибка клиента: " + e.getStatusCode());
        } catch (ResourceAccessException e) {
            return new Message(text, "Ошибка доступа: сервис Gemini недоступен.");
        } catch (Exception e) {
            return new Message(text, "Ошибка при обработке запроса.");
        }
    }

    private String parseGeminiResponse(Map response) {
        try {
            List contents = (List) response.get("candidates");
            if (contents != null && !contents.isEmpty()) {
                Map firstCandidate = (Map) contents.get(0);
                List parts = (List) ((Map) firstCandidate.get("content")).get("parts");
                if (parts != null && !parts.isEmpty()) {
                    return (String) ((Map) parts.get(0)).get("text");
                }
            }
        } catch (Exception e) {
            return "Ошибка при обработке ответа от AI.";
        }
        return "Пустой ответ от AI.";
    }

}
