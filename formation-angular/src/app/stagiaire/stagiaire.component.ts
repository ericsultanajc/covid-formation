import {Component, OnInit} from '@angular/core';
import {Evaluation} from "../model/evaluation";
import {StagiaireService} from "./stagiaire.service";
import {Stagiaire} from "../model/stagiaire";
import {EvaluationService} from "../evaluation/evaluation.service";
import {Adresse} from "../model/adresse";
import {CommonService} from "../common.service";

@Component({
  selector: 'app-stagiaire',
  templateUrl: './stagiaire.component.html',
  styleUrls: ['./stagiaire.component.scss']
})
export class StagiaireComponent implements OnInit {

  stagiaireForm: Stagiaire = null;
  evaluations: Array<Evaluation> = new Array<Evaluation>();
  civilites: Array<String> = new Array<string>();

  constructor(private stagiaireService: StagiaireService, private evaluationService: EvaluationService, private commonService: CommonService) {
  }

  ngOnInit(): void {
    this.commonService.findAllCivilites().subscribe(resp => this.civilites = resp, err => console.log(err));
  }

  list(): Array<Stagiaire> {
    return this.stagiaireService.findAll();
  }

  add() {
    this.stagiaireForm = new Stagiaire();
    this.stagiaireForm.adresse = new Adresse();
    this.stagiaireForm.evaluation = new Evaluation();

    this.evaluationService.findAllOrphan().subscribe(resp => {
      this.evaluations = resp;
    }, err => console.log(err));

    }

  edit(id: number) {
    this.stagiaireService.findById(id).subscribe(resp => {
      this.stagiaireForm = resp;
      if (this.stagiaireForm.adresse == null) {
        this.stagiaireForm.adresse = new Adresse();
      }
      if (this.stagiaireForm.evaluation == null) {
        this.stagiaireForm.evaluation = new Evaluation();
      }
    }, error => console.log(error));

    this.evaluationService.findAllOrphanWithStagiaire(id).subscribe(resp => {
      this.evaluations = resp;
    }, err => console.log(err));
  }

  save() {
    if (!this.stagiaireForm.id) {
      this.stagiaireService.create(this.stagiaireForm).subscribe(resp => {
          this.stagiaireForm = null;
          this.stagiaireService.load();
        },
        error => console.log(error)
      )
      ;
    } else {
      this.stagiaireService.modify(this.stagiaireForm).subscribe(resp => {
        this.stagiaireForm = null;
        this.stagiaireService.load();
      }, error => console.log(error));
    }
  }

  cancel() {
    this.stagiaireForm = null;
  }

  delete(id: number) {
    this.stagiaireService.deleteById(id);
  }
}
