package com.example.survey.business.abstracts;

import java.util.List;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.entities.Answer;
import com.example.survey.entities.dtos.AddAnswerDto;
import com.example.survey.entities.dtos.AnswerCountReport;
import com.example.survey.entities.dtos.AnswerDto;
import com.example.survey.entities.dtos.AnswerReportRatio;
import com.example.survey.entities.dtos.GetAnswerByUserAndSurveyDto;
import com.example.survey.entities.dtos.UpdateAnswerDto;
import com.example.survey.entities.dtos.ViewAnswerDto;

public interface AnswerService {

 	DataResult<List<ViewAnswerDto>> getAll();
 	Result add(AddAnswerDto addAnswerDto);	  
 	Result update(UpdateAnswerDto updateAnswerDto);	  
 	Result delete(Answer Answer);
 	DataResult<Answer> getById(int id);
 	DataResult<List<AnswerDto>> getAllByUserAndSurvey(GetAnswerByUserAndSurveyDto getAnswerByUserAndSurveyDto);
 	DataResult<List<AnswerCountReport>> getCountReport();
 	DataResult<List<AnswerReportRatio>> getRatioReport();
	  
}
