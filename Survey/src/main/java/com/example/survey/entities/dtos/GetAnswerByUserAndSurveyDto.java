package com.example.survey.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnswerByUserAndSurveyDto {

	private int surveyId;
	
	private int userId;
	
}
