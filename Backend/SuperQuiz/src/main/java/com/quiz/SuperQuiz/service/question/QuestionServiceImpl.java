package com.quiz.SuperQuiz.service.question;

import com.quiz.SuperQuiz.entities.Question;
import com.quiz.SuperQuiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void deleteQuestionById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with ID: " + id);
        }
        questionRepository.deleteById(id);
    }


    @Override
    public Optional<Question> getQuestionById(Long id) {
        Optional<Question> q = questionRepository.findById(id);
        if (q.isPresent()) {
            return q;
        }
        else return null;
    }

    @Override
    public Optional<Question> updateQuestion(Long id, Question updatedQuestion) {
        Optional<Question> q = questionRepository.findById(id);
        if (q.isPresent()){
            questionRepository.save(updatedQuestion);
            return q;
        }
        return null;
    }


}
