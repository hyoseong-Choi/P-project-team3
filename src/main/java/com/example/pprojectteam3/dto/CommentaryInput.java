package com.example.pprojectteam3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CommentaryInput {
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "mm:ss")
    private LocalTime time;
    private String team;
    private String player;
    private String position;
    private String action;
    private String result;
}
