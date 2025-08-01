package com.quiz.SuperQuiz.service.test;

import com.quiz.SuperQuiz.dto.*;
import com.quiz.SuperQuiz.entities.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface TestService {
    TestDTO createTest(TestDTO dto);
    QuestionDTO addQuestionInTest(QuestionDTO dto);
    List<TestDTO> getAllTests();
    TestDetailsDTO getAllQuestionsByTest(Long id);
    TestResultDTO submitTest(SubmitTestDTO request);
    List<TestResultDTO> getAllTestResults();
    List<TestResultDTO> getAllTestResultsOfUser(Long userId);

    void deleteQuestionById(Long id);
    Optional<Test> deleteTestById(Long id);

}
