package com.platform.certtracker.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Integration service stub for Google Gemini API.
 * Simulates Gemini generateContent calls with token usage tracking.
 */
@Service
public class GeminiClient {

    private static final Logger log = LoggerFactory.getLogger(GeminiClient.class);

    @Value("${gemini.api.key:stub-key}")
    private String apiKey;

    @Value("${gemini.api.model:gemini-1.5-pro}")
    private String model;

    public record GeminiResponse(
            String text,
            String finishReason,
            UsageMetadata usageMetadata
    ) {
        public record UsageMetadata(
                int promptTokenCount,
                int candidatesTokenCount,
                int totalTokenCount
        ) {}
    }

    /**
     * Standard Gemini generateContent stub.
     */
    public GeminiResponse generateContent(String prompt) {
        log.info("[GeminiClient] Generating content with model={}, promptLength={}", model, prompt.length());
        
        int promptTokens = prompt.length() / 4;
        int candidateTokens = 110;
        return new GeminiResponse(
                "Simulated Gemini response for prompt: " + (prompt.length() > 60 ? prompt.substring(0, 60) + "..." : prompt),
                "STOP",
                new GeminiResponse.UsageMetadata(promptTokens, candidateTokens, promptTokens + candidateTokens)
        );
    }

    /**
     * Gemini generateContent with system instruction stub.
     */
    public GeminiResponse generateContentWithSystemInstruction(String systemInstruction, String prompt) {
        log.info("[GeminiClient] Generating content with system instruction. Instruction length={}", systemInstruction.length());
        
        int promptTokens = (prompt.length() + systemInstruction.length()) / 4;
        int candidateTokens = 130;
        return new GeminiResponse(
                "Simulated Gemini guided response.",
                "STOP",
                new GeminiResponse.UsageMetadata(promptTokens, candidateTokens, promptTokens + candidateTokens)
        );
    }
}
