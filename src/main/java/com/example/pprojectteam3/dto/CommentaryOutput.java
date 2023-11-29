package com.example.pprojectteam3.dto;

import com.example.pprojectteam3.entity.Commentary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
public class CommentaryOutput {
    
    private String time;
    private String content;
    
    public CommentaryOutput(Commentary commentary) {
        this.time = commentary.getTime().format(DateTimeFormatter.ofPattern("mm:ss"));
        this.content = commentary.getContent();
    }
}
