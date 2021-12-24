package com.example.survey.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="questions")
public class Question {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="question")
	private String question;

	@Column(name="option_value")
	private String optionValue;
	
	@Column(name="is_ratio")
	private boolean isRatio;
	
    @JsonIgnore
	@ManyToMany(mappedBy = "questions")
	private List<Survey> surveys;
	
    @JsonIgnore
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private Answer answer;
	
	
}
