import { AddQuestionDto } from './../../model/addQuestionDto';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { SurveyChooseService } from 'src/app/services/survey-choose.service';
import { Survey } from 'src/app/model/survey';
import { Question } from 'src/app/model/question';
import { QuestionService } from 'src/app/services/question.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-add-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {

  addSurveyForm: FormGroup
  addModalShow: Boolean = false;
  editModalShow: Boolean = false;
  questionModalShow: Boolean = false;
  editSurveyForm: FormGroup
  surveys: Survey[] = []
  questions: Question[] = []
  surveyId: number = 0

  constructor(private surveyService: SurveyChooseService, private formBuilder: FormBuilder, private questionService: QuestionService) { }


  dropdownList: Question[] = [];
  selectedItems: Question[] = [];
  dropdownSettings: IDropdownSettings = {};

  ngOnInit(): void {
    this.createSurveyForm();
    this.getAllServey();
    this.getAllQuestions();
    this.initializeDropdown();
  }

  initializeDropdown() {
    this.dropdownSettings.singleSelection = false;
    this.dropdownSettings.idField = "id";
    this.dropdownSettings.textField = "question";
    this.dropdownSettings.selectAllText = "Tümünü Seç";
    this.dropdownSettings.unSelectAllText = "Şeçimleri Temizle";
    this.dropdownSettings.allowSearchFilter = true;
    this.dropdownSettings.searchPlaceholderText = "Arama";
    this.dropdownSettings.noDataAvailablePlaceholderText = "Soru bulunamadı";
  }
  getAllQuestions() {
    this.questionService.getAllQuestions().subscribe(response => {
      this.dropdownList = response.data;
    })
  }
  getQuestionBySurveyId() {
    this.questionService.getQuestionsBySurveyId(this.surveyId).subscribe(response => {
      this.selectedItems = response.data;
    });
  }

  createSurveyForm() {
    this.addSurveyForm = this.formBuilder.group({
      name: ["", Validators.required],
    })
    this.editSurveyForm = this.formBuilder.group({
      id: ["", Validators.required],
      name: ["", Validators.required],
    })
  }

  getAllServey() {
    this.surveyService.getSurveys().subscribe(response => {
      this.surveys = response.data;
    })
  }


  add() {
    if (!this.addSurveyForm.valid) {
      alert("Hata")
      return;
    }

    let servey: Survey = Object.assign({}, this.addSurveyForm.value)
    this.surveyService.addSurvey(servey).subscribe(response => {
      alert("anket eklendi")
      this.addModalShow = false;
      this.getAllServey();
    }, error => {
      alert("Anket eklenemedi")
      console.log(error);
    })
  }
  toggleAddModal() {
    this.addModalShow = !this.addModalShow;
  }
  edit() {
    if (!this.editSurveyForm.valid) {
      alert("formu kontrol ediniz")
      return;
    }
    let survey: Survey = Object.assign({}, this.editSurveyForm.value)
    this.surveyService.updateSurvey(survey).subscribe(response => {
      alert("Soru güncellendi")
      this.editModalShow = false;
      this.getAllServey();

    }, error => {
      alert("Soru güncellenemedi")
      console.log(error);
    })
  }

  toggleEditModal(survey: any) {
    if (survey != '') {
      this.editSurveyForm.controls.id.setValue(survey.id);
      this.editSurveyForm.controls.name.setValue(survey.name);

    }
    this.editModalShow = !this.editModalShow;
  }

  toggleQuestionModal(survey: any) {
    if (survey != '') {
      this.surveyId = survey.id;
      this.getQuestionBySurveyId();
    }
    this.questionModalShow = !this.questionModalShow;
  }

  delete(survey: Survey) {
    this.surveyService.deleteSurvey(survey.id).subscribe(response => {
      alert("silindi")
      this.getAllServey();
    }, error => {
      alert("silinemedi")
      console.log(error);
    })
  }

  updatedQuestion() {
    this.surveyService.clearQuestionBySurveyId(this.surveyId).subscribe(response=>{
      this.addQuestionToSurvey();
    },error=>{
      alert("Bir hata ile karşıaşıldı.");
      console.log(error);
    });
  }

  addQuestionToSurvey(){
    var i = 0;
    this.selectedItems.forEach(question => {
      let addDto: AddQuestionDto = {
        questionId: question.id,
        surveyId: this.surveyId
      }
      this.surveyService.addQuestion(addDto).subscribe(response => {
        i++;
      })
    })
    var obj = this;
    var intrval = setInterval(function () {
      if (obj.selectedItems.length <= i) {
        alert("Sorular güncellendi");
        obj.questionModalShow = false;
        clearInterval(intrval);
      }
    }, 1000);
  }



}
