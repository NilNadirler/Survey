package com.example.survey.business.abstracts;

import java.util.List;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.entities.Pollster;



public interface PollsterService {

	  DataResult<List<Pollster>> getAll();
	  Result add(Pollster pollster);	  
	  Result update(Pollster pollster);	  
	  Result delete(Pollster pollster);
	  DataResult<Pollster> getById(int id);
}
