package com.example.survey.business.abstracts;

import java.util.List;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.entities.Survey;
import com.example.survey.entities.dtos.AddQuestionDto;

public interface SurveyService {


	  DataResult<List<Survey>> getAll();
	  Result add(Survey survey);	  
	  Result update(Survey survey);	  
	  Result delete(Survey survey);
	  DataResult<Survey> getById(int id);
	  Result addQuestion(AddQuestionDto addQuestionDto);
	  Result deleteSurveyQuestion(int surveyId);
	  
}
