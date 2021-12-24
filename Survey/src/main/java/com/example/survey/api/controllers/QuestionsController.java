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

import com.example.survey.business.abstracts.QuestionService;
import com.example.survey.entities.Question;



@RestController
@RequestMapping(value = "/api/questions/" )
@CrossOrigin(maxAge = 3600)
public class QuestionsController {

	@Autowired
	QuestionService questionService;

	@GetMapping(value = "getAll")
	public ResponseEntity<?> GetAll(){
		return ResponseEntity.ok(this.questionService.getAll());
	}

	@GetMapping(value = "getAllBySurveyId/{id}")
	public ResponseEntity<?> GetAllBySurveyId(@PathVariable int id){
		return ResponseEntity.ok(this.questionService.getAllBySurveyId(id));
	}

	@PostMapping("add")
	public ResponseEntity<?> Add( @RequestBody Question question){
		return ResponseEntity.ok(this.questionService.add(question));
	}
	@PostMapping("update")
	public ResponseEntity<?> Update( @RequestBody Question question){
		return ResponseEntity.ok(this.questionService.update(question));
	}
	@GetMapping("delete/{id}")
	public ResponseEntity<?> Delete( @PathVariable int id){
		return ResponseEntity.ok(this.questionService.delete(this.questionService.getById(id).getData()));
	}
		
}

