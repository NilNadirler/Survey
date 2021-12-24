import { SurveyChooseService } from './../../services/survey-choose.service';
import { Survey } from './../../model/survey';
import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-survey-choose',
  templateUrl: './survey-choose.component.html',
  styleUrls: ['./survey-choose.component.css']
})
export class SurveyChooseComponent implements OnInit {

  chooseSurveyForm:FormGroup;
  surveys:Survey[]=[];
  pollsterId:number=0;



  constructor(private chooseSurveyService:SurveyChooseService, private formBuilder:FormBuilder, private router:Router,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllSurvey()
    this.createSurveyForm();
    this.selectSurvey();
    this.pollsterId=this.activatedRoute.snapshot.params['pollsterId'];

  }

  createSurveyForm(){
    this.chooseSurveyForm=this.formBuilder.group({
      surveyId:['', Validators.required]
    })
  }

  getAllSurvey(){
    this.chooseSurveyService.getSurveys().subscribe((response)=>{
      this.surveys=response.data
    })
  }

  selectSurvey(){
    if(!this.chooseSurveyForm.valid){
      alert("Hata")
      return;
    }
    let data:any=Object.assign({},this.chooseSurveyForm.value )
    
    this.router.navigate(['/choose-user', this.pollsterId,data.surveyId ])
  }

}
