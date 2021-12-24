package com.example.survey.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.Results.SuccessDataResult;
import com.example.survey.Results.SuccessResult;
import com.example.survey.business.abstracts.SurveyService;
import com.example.survey.dataAccess.QuestionDao;
import com.example.survey.dataAccess.SurveyDao;
import com.example.survey.entities.Question;
import com.example.survey.entities.Survey;
import com.example.survey.entities.dtos.AddQuestionDto;


@Service
public class SurveyManager implements SurveyService {

	SurveyDao surveyDao;
	
	QuestionDao questionDao;

	@Autowired
	public SurveyManager(SurveyDao surveyDao, QuestionDao questionDao) {
		super();
		this.surveyDao = surveyDao;
		this.questionDao = questionDao;
	}

	@Override
	public DataResult<List<Survey>> getAll() {
		return new SuccessDataResult<List<Survey>>(this.surveyDao.findAll());
	}

	@Override
	public Result add(Survey survey) {
		survey=this.surveyDao.save(survey);
		return new SuccessResult("added",survey.getId());
	}

	@Override
	public Result update(Survey survey) {
		survey.setQuestions(this.surveyDao.getById(survey.getId()).getQuestions());
		survey=this.surveyDao.save(survey);
		return new SuccessResult("updated",survey.getId());
	}

	@Override
	public Result delete(Survey survey) {
		this.surveyDao.delete(survey);
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<Survey> getById(int id) {
		return new SuccessDataResult<Survey>(this.surveyDao.getById(id));
	}

	@Override
	public Result addQuestion(AddQuestionDto addQuestionDto) {
		Survey survey = this.surveyDao.getById(addQuestionDto.getSurveyId());
		List<Question> questions=survey.getQuestions();
		questions.add(this.questionDao.getById(addQuestionDto.getQuestionId()));
		questions=questions.stream().filter(distinctByKey(Question::getId)).collect(Collectors.toList());
		survey.setQuestions(questions);
		this.surveyDao.save(survey);
		return new SuccessResult("added question");
	}
	
	@Transactional
	@Override
	public Result deleteSurveyQuestion(int surveyId) {
	    Survey survey = this.surveyDao.getById(surveyId);
	    survey.setQuestions(new ArrayList<Question>());
	    this.surveyDao.save(survey);
	    return new SuccessResult("RelationShip is break");
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
}
