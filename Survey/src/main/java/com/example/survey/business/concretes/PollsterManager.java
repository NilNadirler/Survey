package com.example.survey.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.Results.SuccessDataResult;
import com.example.survey.Results.SuccessResult;
import com.example.survey.business.abstracts.PollsterService;
import com.example.survey.dataAccess.PollsterDao;
import com.example.survey.entities.Pollster;


@Service
public class PollsterManager implements PollsterService{

	@Autowired
	private PollsterDao pollsterDao;
	
	@Override
	public DataResult<List<Pollster>> getAll() {
		return new SuccessDataResult<List<Pollster>>(this.pollsterDao.findAll());
	}

	@Override
	public Result add(Pollster pollster) {
		pollster=this.pollsterDao.save(pollster);
		return new SuccessResult("added",pollster.getId());
	}

	@Override
	public Result update(Pollster pollster) {
		pollster=this.pollsterDao.save(pollster);
		return new SuccessResult("updated",pollster.getId());
	}

	@Override
	public Result delete(Pollster pollster) {
		this.pollsterDao.delete(pollster);
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<Pollster> getById(int id) {
		return new SuccessDataResult<Pollster>(this.pollsterDao.getById(id));
	}

}
