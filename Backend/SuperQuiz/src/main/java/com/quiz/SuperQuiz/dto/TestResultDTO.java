package com.quiz.SuperQuiz.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResultDTO {
    private Long id;
    private int totalQuestions;
    private int correctAnswers;
    private double percentage;

    private String testName;
    private String userName;

    private LocalDateTime createdAt;


}
