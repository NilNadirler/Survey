package com.example.survey.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewAnswerDto {

	private int id;
	
	private int pollsterId;
	
	private int surveyId;
	
	private int userId;
	
	private String questions;

	private String answers;
	
	private String name;
	
	private String pollsterName;
	
	private String pollsterLastName;

	private String userName;
	
	private String userLastName;

	
	
}
