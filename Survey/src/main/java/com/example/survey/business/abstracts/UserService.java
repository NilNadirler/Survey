package com.example.survey.business.abstracts;

import java.util.List;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.entities.User;


public interface UserService {

	  DataResult<List<User>> getAll();
	  Result add(User user);	  
	  Result update(User user);	  
	  Result delete(User user);
	  DataResult<User> getById(int id);
}
