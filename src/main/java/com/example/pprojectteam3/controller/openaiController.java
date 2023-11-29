package com.example.pprojectteam3.controller;

import com.example.pprojectteam3.dto.ChatGPTRequest;
import com.example.pprojectteam3.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/bot")
public class openaiController {
    private final String model;
    private final String apiURL;
    private final WebClient webClient;

    public openaiController(@Value("${openai.model}") String model,
                            @Value("${openai.api.url}") String apiURL,
                            WebClient.Builder webClientBuilder) {
        this.model = model;
        this.apiURL = apiURL;
        this.webClient = webClientBuilder.baseUrl(apiURL).build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = webClient.post()
                .uri("https://api.openai.com/v1/completions")  // 실제 GPT 엔드포인트로 대체
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatGptResponse.class)
                .block(); // 응답을 얻기 위한 블로킹 호출 (실제로는 블로킹하지 않는 코드를 고려해야 함)

        return chatGptResponse != null && !chatGptResponse.getChoices().isEmpty() ?
                chatGptResponse.getChoices().get(0).getMessage().getContent() : "";
    }
}
