package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String difficulty, int numQ, String title) {
        
        List<Question> questions = questionDao.findRandomQuestionsByDifficulty(difficulty,numQ);
        
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz inserted", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        List<Question> questionsFromDb = quiz.get().getQuestions();

        List<QuestionWrapper> questionForuser = new ArrayList<>();

        for(Question q : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOptiona1(),q.getOptiona2(),q.getOptiona3(),q.getOptiona4());
            questionForuser.add(qw);
        }

        return new ResponseEntity<>(questionForuser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get(); // Not intended to use
        
        List<Question> questions = quiz.getQuestions();

        int rightans = 0;
        int i = 0;

        for(Response response : responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer())) 
            rightans++;
            i++;
        }

        return new ResponseEntity<>(rightans, HttpStatus.OK);
    }
}
