package com.example.pprojectteam3.dto;

import com.example.pprojectteam3.entity.Commentary;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CommentaryOutput {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm:ss")
    private LocalTime time;
    private String content;
    
    public CommentaryOutput(Commentary commentary) {
        this.time = commentary.getTime();
        this.content = commentary.getContent();
    }
}
