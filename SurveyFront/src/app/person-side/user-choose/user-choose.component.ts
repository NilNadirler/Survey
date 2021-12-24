
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-choose',
  templateUrl: './user-choose.component.html',
  styleUrls: ['./user-choose.component.css']
})
export class UserChooseComponent implements OnInit {

   userForm:FormGroup;
   user:User[]=[];
   pollsterId:number=0;
   surveyId:number=0;

  constructor(private userService:UserService, private formBuilder:FormBuilder, private router:Router,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {

      this.getUser();
      this.createUserForm();
      this.addUser();

      this.pollsterId= this.activatedRoute.snapshot.params["pollsterId"]
      this.surveyId= this.activatedRoute.snapshot.params["surveyId"]

  }

   createUserForm(){
      this.userForm=this.formBuilder.group({
        firstName:["",Validators.required],
        lastName:["",Validators.required],
        gender:["",Validators.required],
        birthDate:["",Validators.required]
      })
   }

  getUser(){
        this.userService.getUsers().subscribe((response)=>{
          this.user=response.data
        })
  }

  addUser(){
     if(!this.userForm.valid)
     {
       alert("Hata")
       return;
     }
     let data:User=Object.assign({},this.userForm.value)
     this.userService.addUser(data).subscribe(response=>{
        this.router.navigate(['dynamic-form',this.pollsterId,this.surveyId,response.id]);
     })
     
  }

}
