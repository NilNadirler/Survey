package com.example.survey.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entities.Survey;

public interface SurveyDao extends JpaRepository<Survey, Integer> {

}
