import { AnswerService } from './../../services/answer.service';
import { Component, OnInit } from '@angular/core';
import { ViewAnswerDto } from 'src/app/model/ViewAnswerDto';

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class AnswerComponent implements OnInit {

  constructor(private answerService:AnswerService) { }

  viewAnswers:ViewAnswerDto[]=[]

  ngOnInit(): void {

    this.getAllAnswers();
  }

  getAllAnswers(){
    this.answerService.getAllAnswer().subscribe(response=>{
      this.viewAnswers=response.data;
    })
  }
}
