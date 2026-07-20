package com.productivitytracker.tracker.aiservice.aiimpl;

import com.productivitytracker.tracker.aiservice.OpenAIService;
import com.productivitytracker.tracker.exception.AiGenerationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Value("${openai.model:gpt-4o-mini}")
    private String model;

    private static final String URL = "https://api.openai.com/v1/chat/completions";

    @Override
    public String generateWeeklySummary(String taskData) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String prompt = """
                Analyze this user's completed tasks and generate:
                1. A 3-sentence weekly productivity summary
                2. Two motivational tips

                TASK DATA:
                """ + taskData;

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "You are a productivity coach."));
        messages.add(Map.of("role", "user", "content", prompt));

        body.put("messages", messages);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map<String, Object>> response =
                    restTemplate.exchange(URL, HttpMethod.POST, request,
                            new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {});

            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null) {
                throw new AiGenerationException("Empty response from AI provider");
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (choices == null || choices.isEmpty()) {
                throw new AiGenerationException("AI provider returned no choices");
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            Object content = message != null ? message.get("content") : null;

            if (content == null) {
                throw new AiGenerationException("AI provider returned an empty message");
            }

            return content.toString();

        } catch (AiGenerationException e) {
            throw e;
        } catch (RestClientException e) {
            log.error("OpenAI weekly summary generation failed", e);
            throw new AiGenerationException("Could not generate weekly summary right now.", e);
        } catch (Exception e) {
            log.error("Unexpected error while generating weekly summary", e);
            throw new AiGenerationException("Could not generate weekly summary right now.", e);
        }
    }
}
