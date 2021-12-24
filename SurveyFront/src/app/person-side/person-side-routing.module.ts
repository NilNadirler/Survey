import { UserChooseComponent } from './user-choose/user-choose.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PollsterChooseComponent } from './pollster-choose/pollster-choose-component';
import { SurveyChooseComponent } from './survey-choose/survey-choose.component';
import { DynamicQuestionComponent } from './dynamic-question/dynamic-question.component';

const routes: Routes = [
  { path: '', component: PollsterChooseComponent },
  { path:'choose-survey/:pollsterId', component: SurveyChooseComponent},
  { path:'choose-user/:pollsterId/:surveyId', component:UserChooseComponent},
  { path:'dynamic-form/:pollsterId/:surveyId/:userId', component:DynamicQuestionComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonSideRoutingModule { }
