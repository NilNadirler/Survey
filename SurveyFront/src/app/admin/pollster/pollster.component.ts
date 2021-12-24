import { Pollster } from './../../model/pollster';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PollsterService } from 'src/app/services/pollster-service.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-pollster',
  templateUrl: './pollster.component.html',
  styleUrls: ['./pollster.component.css']
})
export class PollsterComponent implements OnInit {
  
  addModalShow: Boolean = false;
  editModalShow: Boolean = false;
  pollsters:Pollster[]=[]
  addPollsterForm:FormGroup
  editPollsterForm:FormGroup
  constructor( private pollsterService:PollsterService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
          this.createPollsterForm();
          this.getAllPollster();
  }

  createPollsterForm(){
    this.addPollsterForm=this.formBuilder.group({
      firstName:["",Validators.required],
      lastName:["",Validators.required],
    })
    this.editPollsterForm = this.formBuilder.group({
      id: ["", Validators.required],
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
     
    })
  }

  getAllPollster() {
    this.pollsterService.getPollster().subscribe(response => {
     this.pollsters=response.data;
    })
  }
  add(){
    if(!this.addPollsterForm.valid)
    {
      alert("Hata")
      return;
    }
  
      let pollster:Pollster= Object.assign({},this.addPollsterForm.value) 
      this.pollsterService.addPollster(pollster).subscribe(response=>{
        response.success
      })
    }
    toggleAddModal(){
      this.addModalShow=!this.addModalShow;
    }
    toggleEditModal(pollster: any) {
      if (pollster != '') {
        this.editPollsterForm.controls.id.setValue(pollster.id);
        this.editPollsterForm.controls.firstName.setValue(pollster.firstName);
        this.editPollsterForm.controls.lastName.setValue(pollster.lastName);
  
      }
      this.editModalShow = !this.editModalShow;
    }
    edit() {
      if (!this.editPollsterForm.valid) {
        alert("formu kontrol ediniz")
        return;
      }
      let pollster: Pollster = Object.assign({}, this.editPollsterForm.value)
      this.pollsterService.updatePollster(pollster).subscribe(response => {
        alert("anket güncellendi")
        this.editModalShow = false;
        this.getAllPollster();
  
      }, error => {
        alert("anket güncellenemedi")
        console.log(error);
      })
    }
    delete(pollster: Pollster) {
      this.pollsterService.deletePollster(pollster.id).subscribe(response => {
        alert("silindi")
        this.getAllPollster();
      }, error => {
        alert("silinemedi")
        console.log(error);
      })
    }
  
  }
    



