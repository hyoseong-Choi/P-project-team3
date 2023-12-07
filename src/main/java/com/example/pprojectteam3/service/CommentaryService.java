package com.example.pprojectteam3.service;

import com.example.pprojectteam3.dto.CommentaryInput;
import com.example.pprojectteam3.dto.CommentaryOutput;
import com.example.pprojectteam3.entity.Commentary;
import com.example.pprojectteam3.repository.CommentaryRepository;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.theokanning.openai.completion.chat.ChatMessageRole.SYSTEM;
import static com.theokanning.openai.completion.chat.ChatMessageRole.USER;

@Service
@RequiredArgsConstructor
public class CommentaryService {
    private final CommentaryRepository commentaryRepository;
    
    @Value("${OPENAI_API_KEY}")
    private String apiKey;
    
    public List<ChatCompletionChoice> addCommentary(CommentaryInput commentaryInput) {
        OpenAiService openAiService = new OpenAiService(apiKey);
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(SYSTEM.value(), "You are a basketball commentator"));
        messages.add(new ChatMessage(USER.value(), commentaryInput.getTeam() + ", " + commentaryInput.getPlayer() + ", " + commentaryInput.getPosition() + ", " +
                commentaryInput.getAction() + ", " + commentaryInput.getResult() + "\n" +
                "Take this information and make a commentary Like commentating a real basketball game. " +
                "Please limit the number of characters to 200 characters or less. Don't put quotation marks in your sentences"));
        
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(messages)
                .model("gpt-3.5-turbo-1106")
                .build();
        
        List<ChatCompletionChoice> chatCompletionChoices = openAiService.createChatCompletion(chatCompletionRequest).getChoices();
        for (ChatCompletionChoice completionChoice : chatCompletionChoices) {
            commentaryRepository.save(Commentary.createCommentary(commentaryInput.getTime(), completionChoice.getMessage().getContent()/*해설 내용*/));
        }
        return chatCompletionChoices;
    }
    
    public List<CommentaryOutput> findCommentaries() {
        return commentaryRepository.findByOrderByTimeAsc().stream().map(CommentaryOutput::new).collect(Collectors.toList());
    }
}
