
import { AddQuestionDto } from './../model/addQuestionDto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListResponseModel } from '../model/listResponseModel';
import { ResponseModel } from '../model/responseModel';
import { Survey } from '../model/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyChooseService {

  apiUrl = "http://localhost:8080/api/surveys/";
  constructor(private httpClient: HttpClient) { }

  getSurveys(): Observable<ListResponseModel<Survey>> {
    let newPath = this.apiUrl + "getAll"
    return this.httpClient.get<ListResponseModel<Survey>>(newPath)
  }

  addSurvey(survey: Survey): Observable<ResponseModel> {

    return this.httpClient.post<ResponseModel>(this.apiUrl + "add", survey)
  }

  updateSurvey(survey: Survey): Observable<ResponseModel> {

    return this.httpClient.post<ResponseModel>(this.apiUrl + "update", survey)
  }

  deleteSurvey(id: number): Observable<ResponseModel> {

    return this.httpClient.get<ResponseModel>(this.apiUrl + "delete/" + id)
  }


  getSurveyById(surveyId: number): Observable<ListResponseModel<Survey>> {
    let newPath = this.apiUrl + "getById/" + surveyId
    return this.httpClient.get<ListResponseModel<Survey>>(newPath)
  }

  addQuestion(question: AddQuestionDto): Observable<ResponseModel> {
    return this.httpClient.post<ResponseModel>(this.apiUrl + "add-question", question)
  }
  clearQuestionBySurveyId(surveyId: number): Observable<ResponseModel> {
    return this.httpClient.get<ResponseModel>(this.apiUrl + "clear-questions/" + surveyId)
  }
}
