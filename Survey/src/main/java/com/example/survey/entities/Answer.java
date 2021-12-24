package com.example.survey.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.survey.entities.dtos.AnswerCountReport;
import com.example.survey.entities.dtos.AnswerReportRatio;
import com.example.survey.entities.dtos.ViewAnswerDto;

import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import lombok.Data;

@Data
@Entity
@Table(name="answers")
@NamedNativeQuery(
	    name = "get_ratio_report",
	    query = "SELECT surveys.name,questions.question,tbl.answer,tbl.cnt,'%'+CONVERT(nvarchar,tbl.ratio) as 'ratio' FROM("
	    				+ "SELECT "
	    				+ "      [survey_id]"
	    				+ "      ,[question_id]"
	    				+ "      ,[answer]"
	    				+ "	  ,count(*) as 'cnt'"
	    				+ "	  ,"
	    				+ "		CASE"
	    				+ "		WHEN try_cast(answer as int)  is not null THEN ("
	    				+ "				SELECT (SUM(try_cast(answer as float))*100)/(count(*)*10)  FROM [Survey].[dbo].[answers] tbl2 "
	    				+ "				WHERE "
	    				+ "				tbl2.survey_id=tbl.survey_id "
	    				+ "				and tbl2.question_id=tbl.question_id "
	    				+ "				)"
	    				+ "		ELSE"
	    				+ "		  count(answer)*(100/"
	    				+ "			("
	    				+ "				SELECT count(*) FROM [Survey].[dbo].[answers] tbl2 "
	    				+ "				WHERE "
	    				+ "				tbl2.survey_id=tbl.survey_id "
	    				+ "				and tbl2.question_id=tbl.question_id "
	    				+ "				)"
	    				+ "			)"
	    				+ "		END as 'ratio'"
	    				+ "  FROM [Survey].[dbo].[answers] tbl"
	    				+ "  LEFT JOIN questions ON tbl.question_id=questions.id"
	    				+ "  where questions.is_ratio=1"
	    				+ "  group by survey_id,question_id,answer) as tbl"
	    				+ "  INNER JOIN surveys ON tbl.survey_id=surveys.id"
	    				+ "  INNER JOIN questions ON tbl.question_id=questions.id",
	    resultSetMapping = "answer_ratio_report"
	)
	@SqlResultSetMapping(
	    name = "answer_ratio_report",
	    classes = @ConstructorResult(
	        targetClass = AnswerReportRatio.class,
	        columns = {
	            @ColumnResult(name = "name", type = String.class),
	            @ColumnResult(name = "question", type = String.class),
	            @ColumnResult(name = "answer", type = String.class),
	            @ColumnResult(name = "cnt", type = Integer.class),
	            @ColumnResult(name = "ratio", type = String.class)
	        }
	    )
	)

@NamedNativeQuery(
	    name = "get_count_report",
	    query = "Select  pollsters.first_name,pollsters.last_name,surveys.name,tbl.cnt From( "
	    				+ " SELECT "
	    				+ "	pollster_id, "
	    				+ "	survey_id, "
	    				+ "	count(survey_id)/(SELECT  CASE WHEN count(*)=0 THEN 1 ELSE count(*) END  FROM [Survey].[dbo].[surveys_questions]  where surveys_id=tbl.survey_id ) as 'cnt' "
	    				+ "  FROM [Survey].[dbo].[answers] tbl "
	    				+ "  GROUP BY pollster_id,survey_id) as tbl "
	    				+ "  INNER JOIN pollsters ON tbl.pollster_id=pollsters.id "
	    				+ "  INNER JOIN surveys ON tbl.survey_id=surveys.id",
	    resultSetMapping = "answer_count_report"
	)
	@SqlResultSetMapping(
	    name = "answer_count_report",
	    classes = @ConstructorResult(
	        targetClass = AnswerCountReport.class,
	        columns = {
	            @ColumnResult(name = "first_name", type = String.class),
	            @ColumnResult(name = "last_name", type = String.class),
	            @ColumnResult(name = "name", type = String.class),
	            @ColumnResult(name = "cnt", type = Integer.class),

	        }
	    )
	)
	@NamedNativeQuery(
		name = "get_view_surveys_answer",
		query = "select "
				+ "	tbl.id,"
				+ "	tbl.pollster_id as 'pollsterId',"
				+ "	tbl.survey_id as 'surveyId',"
				+ "	tbl.user_id as 'userId',"
				+ "	tbl.questions,"
				+ "	tbl.answers,"
				+ "	surveys.name,"
				+ "	pollsters.first_name as 'pollsterName',"
				+ "	pollsters.last_name as 'pollsterLastName',"
				+ "	users.first_name as 'userName',"
				+ "	users.last_name as 'userLastName' "
				+ "from ("
				+ "  SELECT "
				+ "		MIN(glb.id)as 'id',"
				+ "		pollster_id,"
				+ "		survey_id,"
				+ "		user_id,"
				+ "	   STUFF((SELECT '--' + q.question "
				+ "			  FROM  answers ans"
				+ "			INNER JOIN questions q ON ans.question_id=q.id"
				+ "			  WHERE "
				+ "			  ans.pollster_id = glb.pollster_id and"
				+ "			  ans.survey_id = glb.survey_id and"
				+ "			  ans.user_id = glb.user_id"
				+ "			  FOR XML PATH('')), 1, 1, '') as 'questions',"
				+ "	   STUFF((SELECT '--' + ans.answer "
				+ "			  FROM  answers ans"
				+ "			  WHERE "
				+ "			  ans.pollster_id = glb.pollster_id and"
				+ "			  ans.survey_id = glb.survey_id and"
				+ "			  ans.user_id = glb.user_id"
				+ "			  FOR XML PATH('')), 1, 1, '') as 'answers'"
				+ "  FROM answers glb"
				+ "  GROUP BY pollster_id,survey_id,user_id) tbl "
				+ "INNER JOIN pollsters ON tbl.pollster_id=pollsters.id "
				+ "INNER JOIN surveys ON tbl.survey_id=surveys.id "
				+ "INNER JOIN users ON tbl.user_id=users.id "
				+ "order by tbl.id",
			    resultSetMapping = "view_answer_by_survey"
		)
	@SqlResultSetMapping(
	    name = "view_answer_by_survey",
	    classes = @ConstructorResult(
	        targetClass = ViewAnswerDto.class,
	        columns = {
	            @ColumnResult(name = "id", type = Integer.class),
	            @ColumnResult(name = "pollsterId", type = Integer.class),
	            @ColumnResult(name = "surveyId", type = Integer.class),
	            @ColumnResult(name = "userId", type = Integer.class),
	            @ColumnResult(name = "questions", type = String.class),
	            @ColumnResult(name = "answers", type = String.class),
	            @ColumnResult(name = "name", type = String.class),
	            @ColumnResult(name = "pollsterName", type = String.class),
	            @ColumnResult(name = "pollsterLastName", type = String.class),
	            @ColumnResult(name = "userName", type = String.class),
	            @ColumnResult(name = "userLastName", type = String.class)

	        }
	    )
	)
public class Answer {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="answer")
	private String answer;
	
	@OneToOne
	@JoinColumn(name="question_id",referencedColumnName = "id")
	private Question question;
	
	@OneToOne
	@JoinColumn(name="pollster_id",referencedColumnName = "id")
	private Pollster pollster;
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private  User user;
	
	
	@OneToOne
	@JoinColumn(name="survey_id",referencedColumnName = "id")
	private Survey survey;
}
