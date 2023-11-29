package com.example.pprojectteam3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String content;
    
    public static Commentary createCommentary(LocalTime time, String content) {
        Commentary commentary = new Commentary();
        commentary.time = time;
        commentary.content = content;
        return commentary;
    }
}
