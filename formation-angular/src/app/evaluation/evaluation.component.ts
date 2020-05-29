import {Component, OnInit} from '@angular/core';
import {EvaluationService} from "./evaluation.service";
import {Evaluation} from "../model/evaluation";
import {Observable} from "rxjs";

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss']
})
export class EvaluationComponent implements OnInit {

  evaluationForm: Evaluation = null;

  constructor(private evaluationService: EvaluationService) {
  }

  ngOnInit(): void {
  }

  list(): Array<Evaluation> {
    return this.evaluationService.findAll();
  }

  add() {
    this.evaluationForm = new Evaluation();
  }

  edit(id: number) {
    this.evaluationService.findById(id).subscribe(resp => this.evaluationForm = resp, error => console.log(error));
  }

  save(){
    if (!this.evaluationForm.id) {
      this.evaluationService.create(this.evaluationForm).subscribe(resp => {
          this.evaluationForm = null;
          this.evaluationService.load();
        },
        error => console.log(error)
      )
      ;
    } else {
      this.evaluationService.modify(this.evaluationForm).subscribe(resp => {
        this.evaluationForm = null;
        this.evaluationService.load();
      }, error => console.log(error));
    }
  }

  cancel() {
    this.evaluationForm = null;
  }

  delete(id: number) {
    this.evaluationService.deleteById(id);
  }
}
