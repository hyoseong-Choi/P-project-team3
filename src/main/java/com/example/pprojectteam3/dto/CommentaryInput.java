package com.example.pprojectteam3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentaryInput {
    
    private String time;
    private String team;
    private String player;
    private String position;
    private String action;
    private String result;
}
