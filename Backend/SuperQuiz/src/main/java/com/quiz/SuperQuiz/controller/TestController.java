package com.quiz.SuperQuiz.controller;

import com.quiz.SuperQuiz.dto.QuestionDTO;
import com.quiz.SuperQuiz.dto.SubmitTestDTO;
import com.quiz.SuperQuiz.dto.TestDTO;
import com.quiz.SuperQuiz.entities.Question;
import com.quiz.SuperQuiz.repository.QuestionRepository;
import com.quiz.SuperQuiz.repository.TestRepository;
import com.quiz.SuperQuiz.repository.TestResultRepository;
import com.quiz.SuperQuiz.service.question.QuestionService;
import com.quiz.SuperQuiz.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestResultRepository testResultRepository;

    @PostMapping("/create-test")
    public ResponseEntity<?> createTest(@RequestBody TestDTO dto) {
        try{
            return new ResponseEntity<>(testService.createTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-question")
    public ResponseEntity<?> addQuestionInTest(@RequestBody QuestionDTO dto) {
        try{
            return new ResponseEntity<>(testService.addQuestionInTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-test")
    public ResponseEntity<?> getAllTest() {
        try{
            return new ResponseEntity<>(testService.getAllTests(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAllQuestions(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(testService.getAllQuestionsByTest(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitTest(@RequestBody SubmitTestDTO dto) {
        try{
            return new ResponseEntity<>(testService.submitTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin-view-result")
    public ResponseEntity<?> getAllTestResults() {
        try{
            return new ResponseEntity<>(testService.getAllTestResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/User-view-result/{id}")
    public ResponseEntity<?> getAllTestResultsOfUser(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(testService.getAllTestResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/question-test/{id}")
    public void deleteQuestionInTest(@PathVariable Long id) {
        testService.deleteQuestionById(id);
    }

    @DeleteMapping("/test/{id}")
    public String deleteTest(@PathVariable Long id) {
        var td = testService.deleteTestById(id);
        if(td.isPresent()) {
            return "Successfully deleted";
        }
        return "Test not found";
    }


    @Autowired
    private QuestionService questionService;

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok().build();
    }


//    @GetMapping("/getQuestion/{id}")
//    public ResponseEntity<?> getQuestion(@PathVariable Long id) {
//        return questionService.getQuestionById(id)
//                .map(question -> ResponseEntity.ok().body(question))  // Return JSON
//                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 if not found
//    }


    @Autowired
    private QuestionRepository questionRepository;
    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
    }




    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        try {
            Optional<Question> question = questionService.updateQuestion(id, updatedQuestion);
            if (question==null){
//                return ResponseEntity.notFound().build();
                return ResponseEntity.badRequest().body("Question is not found");
            }
            return ResponseEntity.ok().body("sucessfully updated");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("not updated");
        }
    }




}
