import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ListResponseModel } from '../model/listResponseModel';
import { User } from '../model/user';
import { ResponseModel } from '../model/responseModel';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl="http://localhost:8080/api/users/";
  constructor(private httpClient:HttpClient) { }

  getUsers():Observable<ListResponseModel<User>>{
    let newPath=this.apiUrl+"getAll"
    return this.httpClient.get<ListResponseModel<User>>(newPath)     
  }

  addUser(user:User):Observable<ResponseModel>{
    
    return this.httpClient.post<ResponseModel>(this.apiUrl+"/add",user)
  }

}
