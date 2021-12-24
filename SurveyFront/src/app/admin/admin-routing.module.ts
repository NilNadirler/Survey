
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnswerComponent } from './answer/answer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PollsterComponent } from './pollster/pollster.component';
import { QuestionComponent } from './question/question.component';
import { SurveyComponent } from './survey/survey.component';


const routes: Routes = [
  { path: 'pollster', component: PollsterComponent },
  { path: 'survey', component: SurveyComponent },
   {path:'question',component:QuestionComponent},
   {path:'answer',component:AnswerComponent},
   {path:'dashboard',component:DashboardComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
