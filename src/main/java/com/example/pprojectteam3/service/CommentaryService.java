package com.example.pprojectteam3.service;
import com.example.pprojectteam3.dto.CommentaryInput;
import com.example.pprojectteam3.dto.CommentaryOutput;
import com.example.pprojectteam3.entity.Commentary;
import com.example.pprojectteam3.repository.CommentaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;



import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentaryService {
    private final CommentaryRepository commentaryRepository;
    private final WebClient webClient;





    public Integer addCommentary(CommentaryInput commentaryInput) {
        //여기에 만드시면 됩니다.

        return commentaryRepository.save(Commentary.createCommentary(commentaryInput.getTime(), "abc"/*해설 내용 임시로 abc추가*/)).getId();
    }

    public List<CommentaryOutput> findCommentaries() {
        return commentaryRepository.findByOrderByTimeAsc().stream().map(CommentaryOutput::new).collect(Collectors.toList());
    }
}

