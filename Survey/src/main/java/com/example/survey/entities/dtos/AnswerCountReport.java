package com.example.survey.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCountReport {
	private String first_name;
	private String last_name;
	private String name;
	private int cnt;
}
