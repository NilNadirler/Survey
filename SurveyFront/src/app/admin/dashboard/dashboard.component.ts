import { AnswerRatioReportDto } from './../../model/answerRatioReportDto';
import { HttpClient } from '@angular/common/http';
import { AnswerService } from './../../services/answer.service';
import { AnswerCountDto } from './../../model/answerCountReport';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  countReports:AnswerCountDto[]= []
  ratioReport:AnswerRatioReportDto[]= []
  constructor(private answerService:AnswerService) { }

  ngOnInit(): void {
    this.getCountReport();
    this.getRetioReport();
  }
  getCountReport(){
    this.answerService.getCountReport().subscribe(
      okResult=>{
        this.countReports=okResult.data
      },
      errorResult=>{
        alert("Bir hata ile karşılaşıldı.");
        console.log(errorResult);
      }
    )
  }
  getRetioReport(){
    this.answerService.getRatioReport().subscribe(
      okResult=>{
        this.ratioReport=okResult.data
      },
      errorResult=>{
        alert("Bir hata ile karşılaşıldı.");
        console.log(errorResult);
      }
    )
  }

}
