import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonSideRoutingModule } from './person-side-routing.module';
import { PollsterChooseComponent } from './pollster-choose/pollster-choose-component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SurveyChooseComponent } from './survey-choose/survey-choose.component';
import { UserChooseComponent } from './user-choose/user-choose.component';
import { DynamicQuestionComponent } from './dynamic-question/dynamic-question.component';



@NgModule({
  declarations: [
    PollsterChooseComponent,
    SurveyChooseComponent,
    UserChooseComponent,
    DynamicQuestionComponent
  ],
  imports: [
    CommonModule,
    PersonSideRoutingModule,
    FormsModule,
    ReactiveFormsModule,

  ]
})
export class PersonSideModule { }
