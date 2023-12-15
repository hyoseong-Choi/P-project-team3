package com.example.pprojectteam3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime time;
    @Column(columnDefinition = "TEXT")
    private String content;
    
    public static Commentary createCommentary(String time, String content) {
        Commentary commentary = new Commentary();
        
        commentary.time = LocalTime.parse("00:00:0" + time);
        commentary.content = content;
        return commentary;
    }
}
