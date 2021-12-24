package com.example.survey.business.abstracts;

import java.util.List;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.entities.Question;

public interface QuestionService {

	 DataResult<List<Question>> getAll();
	 DataResult<List<Question>> getAllBySurveyId(int id);
	  Result add(Question question);	  
	  Result update(Question question);	  
	  Result delete(Question question);
	  DataResult<Question> getById(int id);
}
