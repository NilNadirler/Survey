package com.example.survey.dataAccess;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.survey.entities.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> getAllBySurveys_Id(int surveyId);
}
