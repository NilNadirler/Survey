package com.example.survey.entities.dtos;

import lombok.Data;

@Data
public class AddAnswerDto {

	private String answer;
	
	private int questionId;
	
	private int surveyId;
	
	private int userId;
	
	private int pollsterId;
}