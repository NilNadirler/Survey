package com.example.survey.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.Results.SuccessDataResult;
import com.example.survey.Results.SuccessResult;
import com.example.survey.business.abstracts.AnswerService;
import com.example.survey.dataAccess.AnswerDao;
import com.example.survey.dataAccess.PollsterDao;
import com.example.survey.dataAccess.QuestionDao;
import com.example.survey.dataAccess.SurveyDao;
import com.example.survey.dataAccess.UserDao;
import com.example.survey.entities.Answer;
import com.example.survey.entities.Pollster;
import com.example.survey.entities.Question;
import com.example.survey.entities.Survey;
import com.example.survey.entities.User;
import com.example.survey.entities.dtos.AddAnswerDto;
import com.example.survey.entities.dtos.AnswerCountReport;
import com.example.survey.entities.dtos.AnswerDto;
import com.example.survey.entities.dtos.AnswerReportRatio;
import com.example.survey.entities.dtos.GetAnswerByUserAndSurveyDto;
import com.example.survey.entities.dtos.UpdateAnswerDto;
import com.example.survey.entities.dtos.ViewAnswerDto;


@Service
public class AnswerManager implements AnswerService {

	
	private AnswerDao answerDao;
	private QuestionDao questionDao;
	private SurveyDao surveyDao;
	private PollsterDao pollsterDao;
	private UserDao userDao;

	@Autowired
	public AnswerManager(AnswerDao answerDao, QuestionDao questionDao, SurveyDao surveyDao, PollsterDao pollsterDao,
			UserDao userDao) {
		super();
		this.answerDao = answerDao;
		this.questionDao = questionDao;
		this.surveyDao = surveyDao;
		this.pollsterDao = pollsterDao;
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<ViewAnswerDto>> getAll() {
		
		return new SuccessDataResult<List<ViewAnswerDto>>(this.answerDao.getAllWithDto());
		
	}

	@Override
	public Result add(AddAnswerDto addAnswerDto) {
		List<Answer> checkAnswer=this.answerDao.getAllByUser_IdAndSurvey_Id(addAnswerDto.getUserId(), addAnswerDto.getSurveyId()).stream()
				.filter(item->item.getQuestion().getId()==addAnswerDto.getQuestionId()).collect(Collectors.toList());
		Answer answer= (checkAnswer.toArray().length>0)?checkAnswer.get(0):new Answer();		
		answer.setAnswer(addAnswerDto.getAnswer());
		Question question = this.questionDao.getById(addAnswerDto.getQuestionId());
		answer.setQuestion(question);
		Pollster pollster= this.pollsterDao.getById(addAnswerDto.getPollsterId());
		answer.setPollster(pollster);
		User user =  this.userDao.getById(addAnswerDto.getUserId());
		answer.setUser(user);
		Survey survey = this.surveyDao.getById(addAnswerDto.getSurveyId());
		answer.setSurvey(survey);
		answer=this.answerDao.save(answer);
		return new SuccessResult("added",answer.getId());
	}

	@Override
	public Result update(UpdateAnswerDto updateAnswerDto) {
		Answer answer= this.answerDao.getById(updateAnswerDto.getId());
		answer.setAnswer(updateAnswerDto.getAnswer());
		answer=this.answerDao.save(answer);
		return new SuccessResult("updated",answer.getId());
	}

	@Override
	public Result delete(Answer answer) {
		List<Answer> group=this.answerDao.getAllByUser_IdAndSurvey_Id(answer.getUser().getId(), answer.getSurvey().getId());
		group.stream().forEach(item->{
			this.answerDao.delete(item);
		});
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<Answer> getById(int id) {
		return new SuccessDataResult<Answer>(this.answerDao.getById(id));
		
	}

	@Override
	public DataResult<List<AnswerDto>> getAllByUserAndSurvey(GetAnswerByUserAndSurveyDto getAnswerByUserAndSurveyDto) {
		return new SuccessDataResult<List<AnswerDto>>(this.answerDao.getAllByUser_IdAndSurvey_Id(getAnswerByUserAndSurveyDto.getUserId(),getAnswerByUserAndSurveyDto.getSurveyId()).stream().
				map(answer->AppConfig.modelMapper().map(answer, AnswerDto.class)).collect(Collectors.toList()));
	}

	@Override
	public DataResult<List<AnswerCountReport>> getCountReport() {
		return new SuccessDataResult<List<AnswerCountReport>>(this.answerDao.getCountReport());
	}

	@Override
	public DataResult<List<AnswerReportRatio>> getRatioReport() {
		return new SuccessDataResult<List<AnswerReportRatio>>(this.answerDao.getRatioReport());
	}
}
