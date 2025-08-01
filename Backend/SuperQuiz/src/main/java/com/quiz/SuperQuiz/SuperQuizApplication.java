package com.quiz.SuperQuiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuperQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperQuizApplication.class, args);
	}

}
