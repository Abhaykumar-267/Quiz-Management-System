package com.quiz.SuperQuiz.entities;

import com.quiz.SuperQuiz.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    public QuestionDTO getDto() {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(id);
        dto.setQuestionText(questionText);
        dto.setOptionA(optionA);
        dto.setOptionB(optionB);
        dto.setOptionC(optionC);
        dto.setOptionD(optionD);
        dto.setCorrectAnswer(correctAnswer);
        return dto;
    }

}
