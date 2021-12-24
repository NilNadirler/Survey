import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListResponseModel } from '../model/listResponseModel';
import { Question } from '../model/question';
import { ResponseModel } from '../model/responseModel';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private httpClient:HttpClient) { }

  apiUrl="http://localhost:8080/api/questions/";

  getQuestionsBySurveyId(surveyId:number):Observable<ListResponseModel<Question>>{
      let newPath=this.apiUrl+"getAllBySurveyId/"+surveyId
      return this.httpClient.get<ListResponseModel<Question>>(newPath)  
  }

  addQuestion(question:Question):Observable<ResponseModel>{
    
    return this.httpClient.post<ResponseModel>(this.apiUrl+"add",question)
  }    
  
  updateQuestion(question:Question):Observable<ResponseModel>{
    
    return this.httpClient.post<ResponseModel>(this.apiUrl+"update",question)
  } 
  
  deleteQuestion(id:number):Observable<ResponseModel>{
    
    return this.httpClient.get<ResponseModel>(this.apiUrl+"delete/"+id)
  } 
  
 
  getAllQuestions():Observable<ListResponseModel<Question>>{
    let newPath=this.apiUrl+"getAll"
    return this.httpClient.get<ListResponseModel<Question>>(newPath)

  }
}
