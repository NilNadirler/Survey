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

import com.example.survey.business.abstracts.SurveyService;
import com.example.survey.entities.Survey;
import com.example.survey.entities.dtos.AddQuestionDto;

@RestController
@RequestMapping(value = "/api/surveys/" )
@CrossOrigin(maxAge = 3600)
public class SurveysController {

	@Autowired
	SurveyService surveyService;
	
	@GetMapping(value = "getAll")
	public ResponseEntity<?> GetAll(){
		return ResponseEntity.ok(this.surveyService.getAll());
	}

	@PostMapping("add")
	public ResponseEntity<?> Add( @RequestBody Survey survey){
			return ResponseEntity.ok(this.surveyService.add(survey));
	}
	@PostMapping("add-question")
	public ResponseEntity<?> AddQuestion( @RequestBody AddQuestionDto addQuestionDto){
		return ResponseEntity.ok(this.surveyService.addQuestion(addQuestionDto));
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<?> getById (@PathVariable int id){
		return ResponseEntity.ok(this.surveyService.getById(id));
	}
	@PostMapping("update")
	public ResponseEntity<?> Update( @RequestBody Survey question){
		return ResponseEntity.ok(this.surveyService.update(question));
	}
	@GetMapping("delete/{id}")
	public ResponseEntity<?> Delete( @PathVariable int id){
		return ResponseEntity.ok(this.surveyService.delete(this.surveyService.getById(id).getData()));
	}

	@GetMapping("clear-questions/{id}")
	public ResponseEntity<?> clearQuestions (@PathVariable int id){
		return ResponseEntity.ok(this.surveyService.deleteSurveyQuestion(id));
	}
}
