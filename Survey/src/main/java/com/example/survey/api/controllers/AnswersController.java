package com.example.survey.api.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.survey.business.abstracts.AnswerService;

import com.example.survey.entities.dtos.AddAnswerDto;

import com.example.survey.entities.dtos.GetAnswerByUserAndSurveyDto;
import com.example.survey.entities.dtos.UpdateAnswerDto;


@RestController
@RequestMapping(value = "/api/answers/" )
@CrossOrigin(maxAge = 3600)
public class AnswersController {

	@Autowired
	AnswerService answerService;
	
	@GetMapping(value = "getAll")
	public ResponseEntity<?> GetAll(){
		return ResponseEntity.ok(this.answerService.getAll());
	}
	

	@PostMapping("add")
	public ResponseEntity<?> Add( @RequestBody AddAnswerDto addAnswerDto){
		return ResponseEntity.ok(this.answerService.add(addAnswerDto));
	}
	@PostMapping("update")
	public ResponseEntity<?> Add( @RequestBody UpdateAnswerDto updateAnswerDto){
		return ResponseEntity.ok(this.answerService.update(updateAnswerDto));
	}
	@PostMapping("getAllByUserAndSurvey")
	public ResponseEntity<?> GetAllByUserAndSurvey( @RequestBody GetAnswerByUserAndSurveyDto getAnswerByUserAndSurveyDto){
		return ResponseEntity.ok(this.answerService.getAllByUserAndSurvey(getAnswerByUserAndSurveyDto));
	}
	@GetMapping(value = "getCountReport")
	public ResponseEntity<?> GetCountReport(){
		return ResponseEntity.ok(this.answerService.getCountReport());
	}
	@GetMapping(value = "getRatioReport")
	public ResponseEntity<?> GetRatioReport(){
		return ResponseEntity.ok(this.answerService.getRatioReport());
	}
	@GetMapping("delete/{id}")
	public ResponseEntity<?> Delete( @PathVariable int id){
		return ResponseEntity.ok(this.answerService.delete(this.answerService.getById(id).getData()));
	}
	
}

