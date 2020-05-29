import {Component, OnInit} from '@angular/core';
import {StagiaireService} from "./stagiaire.service";
import {Stagiaire} from "../model/stagiaire";
import {Evaluation} from "../model/evaluation";
import {Adresse} from "../model/adresse";

@Component({
  selector: 'app-stagiaire',
  templateUrl: './stagiaire.component.html',
  styleUrls: ['./stagiaire.component.scss']
})
export class StagiaireComponent implements OnInit {
  stagiaireForm: Stagiaire = null;


  constructor(private stagiaireService: StagiaireService) {
  }

  ngOnInit(): void {
  }

  list(): Array<Stagiaire> {
    return this.stagiaireService.findAll();
  }

  add() {
    this.stagiaireForm = new Stagiaire();
    this.stagiaireForm.adresse = new Adresse();
  }

  edit(id: number) {
    this.stagiaireService.findById(id).subscribe(resp => {
      this.stagiaireForm = resp;
      if (!this.stagiaireForm.adresse) {
        this.stagiaireForm.adresse = new Adresse();
      }
    }, error => console.log(error))
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

  delete(id
           :
           number
  ) {
    this.stagiaireService.deleteById(id);
  }
}
