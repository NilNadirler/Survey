package com.example.survey.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.Results.SuccessDataResult;
import com.example.survey.Results.SuccessResult;
import com.example.survey.business.abstracts.QuestionService;
import com.example.survey.dataAccess.QuestionDao;
import com.example.survey.entities.Question;


@Service
public class QuestionManager implements QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	@Override
	public DataResult<List<Question>> getAll() {
		return new SuccessDataResult<List<Question>>(this.questionDao.findAll());
	}

	@Override
	public Result add(Question question) {
		question=this.questionDao.save(question);
		return new SuccessResult("added",question.getId());
	}

	@Override
	public Result update(Question question) {
		question=this.questionDao.save(question);
		return new SuccessResult("updated",question.getId());
	}

	@Override
	public Result delete(Question question) {
		this.questionDao.delete(question);
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<Question> getById(int id) {
		return new SuccessDataResult<Question>(this.questionDao.getById(id));
	}

	@Override
	public DataResult<List<Question>> getAllBySurveyId(int id) {
		return new SuccessDataResult<List<Question>>(this.questionDao.getAllBySurveys_Id(id));
	}

}
