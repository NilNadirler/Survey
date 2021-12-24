package com.example.survey.dataAccess;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entities.User;

public interface UserDao extends JpaRepository<User,Integer> {
	User getByFirstNameAndLastNameAndGenderAndBirthDate(String firstName,String lastName,int gender, Date birthDate);
}
