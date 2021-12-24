package com.example.survey.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerReportRatio {
	private String name;
	private String question;
	private String answer;
	private int cnt;
	private String ratio;
}
