package com.quiz.SuperQuiz.repository;

import com.quiz.SuperQuiz.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult>  findAllByUserId(Long userId);

    // ✅ Custom method for deletion
    void deleteByCreatedAtBefore(LocalDateTime cutoff);



}
