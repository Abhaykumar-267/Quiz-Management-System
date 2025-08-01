package com.quiz.SuperQuiz.service.question;

import com.quiz.SuperQuiz.entities.Question;

import java.util.Optional;

public interface QuestionService {
    public void deleteQuestionById(Long id);

    public Optional<Question> getQuestionById(Long id);
    public Optional<Question> updateQuestion(Long id, Question updatedQuestion);

}
