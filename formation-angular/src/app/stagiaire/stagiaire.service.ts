import {Injectable} from '@angular/core';
import {Stagiaire} from "../model/stagiaire";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StagiaireService {

  private stagiaires: Array<Stagiaire> = new Array<Stagiaire>();

  constructor(private http: HttpClient) {
    this.load();
  }

  findAll(): Array<Stagiaire> {
    return this.stagiaires;
  }

  findById(id: number): Observable<Stagiaire> {
    return this.http.get<Stagiaire>("http://localhost:8080/api/stagiaire/" + id);
  }

  create(stagiaire: Stagiaire) {
    return this.http.post<Stagiaire>("http://localhost:8080/api/stagiaire", stagiaire);
  }

  modify(stagiaire: Stagiaire) {
    return this.http.put<Stagiaire>("http://localhost:8080/api/stagiaire/" + stagiaire.id, stagiaire);
  }

  deleteById(id: number) {
    this.http.delete("http://localhost:8080/api/stagiaire/" + id).subscribe(resp => this.load(), error => console.log(error))
  }

  load() {
    this.http.get<Array<Stagiaire>>("http://localhost:8080/api/stagiaire").subscribe(resp => {
      this.stagiaires = resp;
    }, error => console.log(error))
  }
}
