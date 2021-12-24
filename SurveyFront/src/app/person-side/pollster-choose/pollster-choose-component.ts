import { Pollster } from './../../model/pollster';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PollsterService } from 'src/app/services/pollster-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pollster-choose',
  templateUrl: './pollster-choose.component.html',
  styleUrls: ['./pollster-choose.component.css']
})
export class PollsterChooseComponent implements OnInit {

  constructor(private formBuilder:FormBuilder, private router:Router, private pollsterService:PollsterService) { }

  choosePollsterForm:FormGroup;
  pollsters:Pollster[]=[];
  
  ngOnInit(): void {
      this.createPollsterForm();
      this.getAllPosters();
   
  }

  createPollsterForm(){
      this.choosePollsterForm=this.formBuilder.group({
        pollsterId:['',Validators.required]
      })
  }

  getAllPosters(){
    this.pollsterService.getPollster().subscribe((response)=>{
      this.pollsters=response.data

    })
  }

  selectPollster(){
      if(!this.choosePollsterForm.valid){
        alert("Formu kontrol ediniz.");
        return;
      }
      let data:any=Object.assign({},this.choosePollsterForm.value);
      console.log(data);
      //this.router.navigateByUrl("/choose-survey/"+data.pollsterId);
      this.router.navigate( ['/choose-survey',data.pollsterId]);
  }

}
