package com.example.survey.dataAccess;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.survey.entities.Answer;
import com.example.survey.entities.dtos.AnswerCountReport;
import com.example.survey.entities.dtos.AnswerReportRatio;
import com.example.survey.entities.dtos.ViewAnswerDto;


public interface AnswerDao extends JpaRepository<Answer,Integer> {
	List<Answer> getAllByUser_IdAndSurvey_Id(int userId,int surveyId);
	@Query(name ="get_view_surveys_answer" , nativeQuery = true)
	List<ViewAnswerDto> getAllWithDto();
	@Query(name ="get_count_report" , nativeQuery = true)
	List<AnswerCountReport> getCountReport();
	@Query(name ="get_ratio_report" ,nativeQuery = true)
	List<AnswerReportRatio> getRatioReport();
}
