package com.java.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
