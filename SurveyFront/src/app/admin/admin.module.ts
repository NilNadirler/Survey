import { PollsterComponent } from './pollster/pollster.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuestionComponent } from './question/question.component';
import { SurveyComponent } from './survey/survey.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { NavbarComponent } from './navbar/navbar.component';
import { AnswerComponent } from './answer/answer.component';
import { DashboardComponent } from './dashboard/dashboard.component';




@NgModule({
  declarations: [
    PollsterComponent,
    SurveyComponent,
    QuestionComponent,
    NavbarComponent,
    AnswerComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule

  ]
})
export class AdminModule { }
