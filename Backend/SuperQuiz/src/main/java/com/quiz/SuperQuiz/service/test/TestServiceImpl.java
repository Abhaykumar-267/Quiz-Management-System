package com.quiz.SuperQuiz.service.test;

import com.quiz.SuperQuiz.dto.*;
import com.quiz.SuperQuiz.entities.Question;
import com.quiz.SuperQuiz.entities.Test;
import com.quiz.SuperQuiz.entities.TestResult;
import com.quiz.SuperQuiz.entities.User;
import com.quiz.SuperQuiz.repository.QuestionRepository;
import com.quiz.SuperQuiz.repository.TestRepository;
import com.quiz.SuperQuiz.repository.TestResultRepository;
import com.quiz.SuperQuiz.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    public TestDTO createTest(TestDTO dto){
        Test test = new Test();

        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setTime(dto.getTime());

        return testRepository.save(test).getDto();
    }

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDTO addQuestionInTest(QuestionDTO dto){
        Optional<Test> optionalTest = testRepository.findById(dto.getId());
        if (optionalTest.isPresent()){
            Question question = new Question();

            question.setTest(optionalTest.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectAnswer(dto.getCorrectAnswer());
            return questionRepository.save(question).getDto();

        }
        throw new EntityNotFoundException("Test Not Found");
    }

    public List<TestDTO> getAllTests(){
        return testRepository.findAll().stream().peek(
                test -> test.setTime(test.getQuestions().size() * test.getTime()))
                .collect(Collectors.toList()).stream().map(Test::getDto).collect(Collectors.toList());
    }

    public TestDetailsDTO getAllQuestionsByTest(Long id){
        Optional<Test> optionalTest = testRepository.findById(id);
        TestDetailsDTO testDetailsDTO = new TestDetailsDTO();
        if(optionalTest.isPresent()){
            TestDTO testDTO = optionalTest.get().getDto();
            testDTO.setTime(optionalTest.get().getTime() * optionalTest.get().getQuestions().size());

            testDetailsDTO.setTestDTO(testDTO);
            testDetailsDTO.setQuestions(optionalTest.get().getQuestions().stream().map(Question::getDto).toList());
            return testDetailsDTO;
        }
        return testDetailsDTO;
    }

    @Override
    public void deleteQuestionById(Long id) {
         questionRepository.deleteById(id);

    }

    @Override
    public Optional<Test> deleteTestById(Long id) {
        Optional<Test> t = testRepository.findById(id);
        if (t.isPresent()){
            testRepository.deleteById(id);
            return t;
        }
        return null;
    }


    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private UserRepository userRepository;

    public TestResultDTO submitTest(SubmitTestDTO request){
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() -> new EntityNotFoundException("Test Not Found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        int correntAnswers = 0;
        for(QuestionResponse response: request.getResponses()){
            Question question = questionRepository.findById(response.getQuestionId()).orElseThrow(() -> new EntityNotFoundException("Question Not Found"));
            if (question.getCorrectAnswer() != null &&
                    question.getCorrectAnswer().equalsIgnoreCase(response.getSelectedOption())) {
                correntAnswers++;
            }
        }

        int totalQuestions = test.getQuestions().size();
        double percentage = ((double) correntAnswers/totalQuestions)*100;

        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setUser(user);
        testResult.setTotalQuestions(totalQuestions);
        testResult.setCorrectAnswers(correntAnswers);
        testResult.setPercentage(percentage);

        return testResultRepository.save(testResult).getDto();
    }

    public List<TestResultDTO> getAllTestResults(){
        return testResultRepository.findAll().stream().map(TestResult::getDto).collect(Collectors.toList());
    }

    public List<TestResultDTO> getAllTestResultsOfUser(Long userId){
        return testResultRepository.findAllByUserId(userId).stream().map(TestResult::getDto).collect(Collectors.toList());
    }






}
