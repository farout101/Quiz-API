package com.telusko.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDiffifulty(@PathVariable String difficulty) {
        return questionService.getQuestionsByDiffifulty(difficulty);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
