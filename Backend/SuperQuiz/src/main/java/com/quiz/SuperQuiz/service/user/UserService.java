package com.quiz.SuperQuiz.service.user;

import com.quiz.SuperQuiz.entities.User;

public interface UserService {

    User createUser(User user);
    Boolean hasUserWithEmail(String email);
    User login(User user);

}
