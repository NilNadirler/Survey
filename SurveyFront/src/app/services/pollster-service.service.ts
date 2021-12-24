import { Pollster } from './../model/pollster';
import { ListResponseModel } from './../model/listResponseModel';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseModel } from '../model/responseModel';

@Injectable({
  providedIn: 'root'
})
export class PollsterService {

   apiUrl="http://localhost:8080/api/pollsters/";

  constructor(private httpClient:HttpClient) { }

  getPollster():Observable<ListResponseModel<Pollster>>{
    let newPath=this.apiUrl+"getAll"
    return this.httpClient.get<ListResponseModel<Pollster>>(newPath)

  }
  addPollster(pollster:Pollster):Observable<ResponseModel>{
    
    return this.httpClient.post<ResponseModel>(this.apiUrl+"add",pollster)
  }

  updatePollster(pollster:Pollster):Observable<ResponseModel>{
    
    return this.httpClient.post<ResponseModel>(this.apiUrl+"update",pollster)
  }

  deletePollster(id: number): Observable<ResponseModel> {

    return this.httpClient.get<ResponseModel>(this.apiUrl + "delete/" + id)
  }


}
