package com.example.survey.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.business.abstracts.UserService;
import com.example.survey.entities.User;

@RestController
@RequestMapping(value = "/api/users/" )
@CrossOrigin(maxAge = 3600)
public class UsersController {

	@Autowired
	UserService userService;
	
	@GetMapping(value = "getAll")
	public ResponseEntity<?> GetAll(){
		return ResponseEntity.ok(this.userService.getAll());
	}
  
  @PostMapping("add")
  public ResponseEntity<?> Add( @RequestBody User user){
		return ResponseEntity.ok(this.userService.add(user));
	}
	
}
