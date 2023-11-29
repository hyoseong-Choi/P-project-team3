package com.example.pprojectteam3.repository;

import com.example.pprojectteam3.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
    List<Commentary> findByOrderByTimeAsc();
}
