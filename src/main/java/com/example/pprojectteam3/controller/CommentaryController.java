package com.example.pprojectteam3.controller;

import com.example.pprojectteam3.dto.CommentaryInput;
import com.example.pprojectteam3.dto.CommentaryOutput;
import com.example.pprojectteam3.service.CommentaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentaryController {
    private final CommentaryService commentaryService;
    
//    @PostMapping("/commentary")
//    public Integer commentaryAdd(@RequestBody CommentaryInput commentaryInput) {
//        return commentaryService.addCommentary(commentaryInput);
//    }
    
    @GetMapping("/commentary")
    public List<CommentaryOutput> CommentaryList() {
        return commentaryService.findCommentaries();
    }
    
    @GetMapping(value = "/video", produces = "video/mp4")//MediaType.APPLICATION_OCTET_STREAM_VALUE
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader HttpHeaders headers) throws IOException {
        log.info("VideoController.getVideo");
        UrlResource video = new UrlResource("file:/media/video.mp4");
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
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(resourceRegion);
        }
        long rangeLength = Long.min(chunkSize, contentLength);
        resourceRegion = new ResourceRegion(video, 0, rangeLength);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(resourceRegion);
    }
}
