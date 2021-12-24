import { Question } from './../../model/question';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  addQuestionForm: FormGroup;
  editQuestionForm: FormGroup;
  questionList: Question[] = []
  addModalShow:Boolean=false;
  editModalShow:Boolean=false;
  constructor(private formBuilder: FormBuilder, private questionService: QuestionService) { }

  ngOnInit(): void {

    this.createQuestionForm();
    this.getAllQuestions();
  }


  createQuestionForm() {
    this.addQuestionForm = this.formBuilder.group({
      question: ["", Validators.required],
      optionValue: ["", Validators.required],
      ratio: ["", Validators.required],


    })
    this.editQuestionForm = this.formBuilder.group({
      id: ["", Validators.required],
      question: ["", Validators.required],
      optionValue: ["", Validators.required],
      ratio: ["", Validators.required],
    })
  }


  getAllQuestions() {
    this.questionService.getAllQuestions().subscribe(response => {
      this.questionList = response.data;
    })
  }

  add() {
    if(!this.addQuestionForm.valid){
      alert("formu kontrol ediniz")
      return;
    }
    let question: Question = Object.assign({}, this.addQuestionForm.value)
    this.questionService.addQuestion(question).subscribe(response => {
      alert("Soru Eklendi")
      this.addModalShow=false;
      this.getAllQuestions();

    }, error => {
      alert("Soru eklenemedi")
      console.log(error);
    })
  }
  toggleAddModal(){
    this.addModalShow=!this.addModalShow;
  }

  edit() {
    if(!this.editQuestionForm.valid){
      alert("formu kontrol ediniz")
      return;
    }
    let question: Question = Object.assign({}, this.editQuestionForm.value)
    this.questionService.updateQuestion(question).subscribe(response => {
      alert("Soru güncellendi")
      this.editModalShow=false;
      this.getAllQuestions();

    }, error => {
      alert("Soru güncellenemedi")
      console.log(error);
    })
  }
  toggleEditModal(question:any){
    if(question!=''){
      this.editQuestionForm.controls.id.setValue(question.id);
      this.editQuestionForm.controls.question.setValue(question.question);
      this.editQuestionForm.controls.optionValue.setValue(question.optionValue);
      this.editQuestionForm.controls.ratio.setValue(question.ratio);
    }
    this.editModalShow=!this.editModalShow;
  }

  delete(question:Question){
     this.questionService.deleteQuestion(question.id).subscribe(response=>{
       alert("silindi")
       this.getAllQuestions();
     },error=>{
       alert("silinemedi")
       console.log(error);
     })
  }

}
