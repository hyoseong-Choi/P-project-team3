package com.example.pprojectteam3.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ChatGptResponse {
    private List<Choice> choices;
    public List<Choice> getChoices() {
        return choices;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {

        private int index;
        private Message message;

    }
}
