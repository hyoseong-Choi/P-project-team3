package com.example.pprojectteam3.controller;

import com.example.pprojectteam3.dto.CommentaryInput;
import com.example.pprojectteam3.dto.CommentaryOutput;
import com.example.pprojectteam3.service.CommentaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentaryController {
    private final CommentaryService commentaryService;
    
    @PostMapping("/commentary")
    public Integer commentaryAdd(@RequestBody CommentaryInput commentaryInput) {
        return commentaryService.addCommentary(commentaryInput);
    }
    
    @GetMapping("/commentary")
    public List<CommentaryOutput> CommentaryList() {
        return commentaryService.findCommentaries();
    }
    
}
