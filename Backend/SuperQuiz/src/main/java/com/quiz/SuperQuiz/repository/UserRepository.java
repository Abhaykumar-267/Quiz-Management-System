package com.quiz.SuperQuiz.repository;

import com.quiz.SuperQuiz.entities.User;
import com.quiz.SuperQuiz.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRole(UserRole role);
    User findFirstByEmail(String email);
    Optional<User> findByEmail(String email);
}
