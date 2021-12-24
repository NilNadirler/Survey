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

import com.example.survey.business.abstracts.PollsterService;
import com.example.survey.entities.Pollster;

@RestController
@RequestMapping(value = "/api/pollsters/" )
@CrossOrigin(maxAge = 3600)
public class PollstersController {

	@Autowired
	PollsterService pollsterService;
	
	@GetMapping(value = "getAll")
	public ResponseEntity<?> GetAll(){
		return ResponseEntity.ok(this.pollsterService.getAll());
	}

	@PostMapping("add")
	public ResponseEntity<?> Add( @RequestBody Pollster pollster){
		return ResponseEntity.ok(this.pollsterService.add(pollster));
	}
	@PostMapping("update")
	public ResponseEntity<?> Update( @RequestBody Pollster pollster){
		return ResponseEntity.ok(this.pollsterService.update(pollster));
	}
	@GetMapping("delete/{id}")
	public ResponseEntity<?> Delete( @PathVariable int id){
		return ResponseEntity.ok(this.pollsterService.delete(this.pollsterService.getById(id).getData()));
	}
	
}
