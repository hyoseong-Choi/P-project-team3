package com.example.pprojectteam3.controller;

import com.example.pprojectteam3.dto.CommentaryInput;
import com.example.pprojectteam3.dto.CommentaryOutput;
import com.example.pprojectteam3.service.CommentaryService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CommentaryController {
    private final CommentaryService commentaryService;
    
    @PostMapping("/commentary")
    public List<ChatCompletionChoice> commentaryAdd(@RequestBody CommentaryInput commentaryInput) {
        return commentaryService.addCommentary(commentaryInput);
    }
    
    @GetMapping("/commentary")
    public List<CommentaryOutput> CommentaryList() {
        return commentaryService.findCommentaries();
    }
    
    @GetMapping(value = "/video", produces = "video/mp4")//MediaType.APPLICATION_OCTET_STREAM_VALUE
    public ResourceRegion getVideo(@RequestHeader HttpHeaders headers) throws IOException {
        
        FileUrlResource video = new FileUrlResource("/home/ubuntu/video.mp4");
        
        ResourceRegion resourceRegion;
        
        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();
        
        Optional<HttpRange> optional = headers.getRange().stream().findFirst();
        HttpRange httpRange;
        if (optional.isPresent()) {
            httpRange = optional.get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            resourceRegion = new ResourceRegion(video, start, rangeLength);
            return resourceRegion;
        }
        long rangeLength = Long.min(chunkSize, contentLength);
        resourceRegion = new ResourceRegion(video, 0, rangeLength);
        log.info("VideoController.getVideo");
        return resourceRegion;
    }
    
//    @GetMapping(value = "/video2", produces = "video/mp4")//MediaType.APPLICATION_OCTET_STREAM_VALUE
//    public Resource getVideo2() throws IOException {
//        return new ByteArrayResource(FileCopyUtils.copyToByteArray(new FileUrlResource("/home/ubuntu/video.mp4").getFile()));
//    }
}
