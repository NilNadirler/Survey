import { AnswerCountDto } from './../model/answerCountReport';
import { ViewAnswerDto } from './../model/ViewAnswerDto';
import { AddAnswerDto } from './../model/addAnswerDto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ListResponseModel } from '../model/listResponseModel';
import { ResponseModel } from '../model/responseModel';
import { Answer } from '../model/answer';
import { Observable } from 'rxjs';
import { AnswerRatioReportDto } from '../model/answerRatioReportDto';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private httpClient:HttpClient) { }

  apiUrl="http://localhost:8080/api/answers/";

  getAnswerById(surveyId:number):Observable<ListResponseModel<Answer>>{
      let newPath=this.apiUrl+"getAllByUserAndSurvey/"+surveyId
      return this.httpClient.get<ListResponseModel<Answer>>(newPath)  
  }

  addAnswer(answer:AddAnswerDto):Observable<ResponseModel>{
    return this.httpClient.post<ResponseModel>(this.apiUrl+"add",answer)
  }    

  getAllByUserAndSurvey(data:any):Observable<ListResponseModel<Answer>>{
    return this.httpClient.post<ListResponseModel<Answer>>(this.apiUrl+"getAllByUserAndSurvey",data)
  } 
  getCountReport():Observable<ListResponseModel<AnswerCountDto>>{
    return this.httpClient.get<ListResponseModel<AnswerCountDto>>(this.apiUrl+"getCountReport")
  } 
  getRatioReport():Observable<ListResponseModel<AnswerRatioReportDto>>{
    return this.httpClient.get<ListResponseModel<AnswerRatioReportDto>>(this.apiUrl+"getRatioReport")
  } 

  getAllAnswer():Observable<ListResponseModel<ViewAnswerDto>>{
    let newPath=this.apiUrl+"getAll"
    return this.httpClient.get<ListResponseModel<ViewAnswerDto>>(newPath)

  }

}
