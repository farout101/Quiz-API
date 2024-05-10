package com.telusko.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByDifficultyLevel(String difficultyLevel);

    @Query(value = "SELECT * FROM QUESTION Q WHERE Q.DIFFICULTY_LEVEL=:difficulty ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByDifficulty(String difficulty, int numQ);
}
