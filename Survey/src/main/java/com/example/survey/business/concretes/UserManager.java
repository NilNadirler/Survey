package com.example.survey.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.Results.DataResult;
import com.example.survey.Results.Result;
import com.example.survey.Results.SuccessDataResult;
import com.example.survey.Results.SuccessResult;
import com.example.survey.business.abstracts.UserService;
import com.example.survey.dataAccess.UserDao;
import com.example.survey.entities.User;



@Service
public class UserManager implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}

	@Override
	public Result add(User user) {
		User checkUser=this.userDao.getByFirstNameAndLastNameAndGenderAndBirthDate(user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate());
		user=(checkUser==null)?this.userDao.save(user):checkUser;
		return new SuccessResult("added",user.getId());
	}

	@Override
	public Result update(User user) {
		user=this.userDao.save(user);
		return new SuccessResult("updated",user.getId());
	}

	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult("deleted");
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(this.userDao.getById(id));
	}


}
