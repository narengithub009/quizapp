package com.java.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.quizapp.dao.QuizDao;
import com.java.quizapp.dao.questionDao;
import com.java.quizapp.model.Question;
import com.java.quizapp.model.QuestionWrapper;
import com.java.quizapp.model.Quiz;
import com.java.quizapp.model.ResponseWrapper;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	@Autowired
	questionDao questionDao;
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQ);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("sucessfully created quiz",HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Question> questionsFromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers=new ArrayList<>();
		for(Question q:questionsFromDB) {
			QuestionWrapper questionWrapper= new QuestionWrapper(q.getId(), q.getQuestionTitle(), 
								q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUsers.add(questionWrapper)	;		
		}
		return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
	}
	public ResponseEntity<Integer> calculateResponse(Integer id, List<ResponseWrapper> responses) {
		
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		
		int right=0;
		int i=0;
		for(ResponseWrapper responseWrapper:responses) {
			if(responseWrapper.getResponse().equals(questions.get(i).getRightAnswer()))
					right++;
			//i++;
		}
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}
}
