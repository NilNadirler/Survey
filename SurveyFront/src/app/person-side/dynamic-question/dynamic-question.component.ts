import { GetAnswerDto } from './../../model/getAnswerDto';
import { AnswerService } from './../../services/answer.service';
import { AddAnswerDto } from './../../model/addAnswerDto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from './../../model/question';
import { QuestionService } from './../../services/question.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dynamic-question',
  templateUrl: './dynamic-question.component.html',
  styleUrls: ['./dynamic-question.component.css']
})
export class DynamicQuestionComponent implements OnInit {

  questionList: Question[] = []
  questionForm: FormGroup
  answerForm: FormGroup
  answerDto: AddAnswerDto
  btn:Boolean=true

  constructor(private questionService: QuestionService, private router:Router,
    private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder,
    private answerService: AnswerService) { }

  ngOnInit(): void {
    this.createAnswerForm();
    let surveyId = this.activatedRoute.snapshot.params['surveyId'];
    this.getQuestionsbySurveyId(surveyId);
  }
  createQuestionForm(data: Question[]) {
    let form: any = {
    };
    data.forEach(q => {
      form["question-" + q.id] = ["", Validators.required];
    });
    this.questionForm = this.formBuilder.group(form);
    this.checkAnswer();
  }
  checkAnswer(){
    let surveyId = this.activatedRoute.snapshot.params['surveyId'];
    let userId = this.activatedRoute.snapshot.params['userId'];
    let getAnswerDto:GetAnswerDto={
      surveyId:surveyId,
      userId:userId
    }
    this.answerService.getAllByUserAndSurvey(getAnswerDto).subscribe(response=>{
      response.data.forEach(ans=>{
        this.questionForm.controls["question-"+ans.questionId].setValue(ans.answer);
        this.btn=false
      });
    });
  }
  createAnswerForm() {
    this.answerForm = this.formBuilder.group({
      answer: ["", Validators.required],
      questionId: ["", Validators.required],
      surveyId: ["", Validators.required],
      userId: ["", Validators.required],
      pollsterId: ["", Validators.required]
    });
    let surveyId = this.activatedRoute.snapshot.params['surveyId'];
    let pollsterId = this.activatedRoute.snapshot.params['pollsterId'];
    let userId = this.activatedRoute.snapshot.params['userId'];
    this.answerForm.controls.surveyId.setValue(surveyId);
    this.answerForm.controls.pollsterId.setValue(pollsterId);
    this.answerForm.controls.userId.setValue(userId);
  }

  getQuestionsbySurveyId(surveyId: number) {
    this.questionService.getQuestionsBySurveyId(surveyId).subscribe((response) => {
      this.createQuestionForm(response.data);
      this.questionList = response.data;
    });
  }
  jsonToString(object: any) {
    return JSON.stringify(object);
  }
  stringToJson(text: string) {
    return JSON.parse(text);
  }
  save() {
    let questionData: any = Object.assign({}, this.questionForm.value);
    let i: number = 0;
    Object.keys(questionData).forEach(key => {
      let answerData: AddAnswerDto = Object.assign({}, this.answerForm.value);
      answerData.answer = questionData[key];
      let id: any = key.split('-')[1];
      answerData.questionId = id;
      this.answerService.addAnswer(answerData).subscribe(response => {
        i++;
      })
    });
    var obj=this;
    var intrval=setInterval(function(){
      if(Object.keys(questionData).length<=i){
        alert("Anket cevapları kaydedildi");
        obj.router.navigate(['']);
        clearInterval(intrval);
      }
    },1000);

  }
}
