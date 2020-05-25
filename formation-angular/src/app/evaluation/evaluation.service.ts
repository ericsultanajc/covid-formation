import {Injectable} from '@angular/core';
import {Evaluation} from "../model/evaluation";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  private evaluations: Array<Evaluation> = new Array<Evaluation>();

  constructor(private http: HttpClient) {
    this.load();
  }

  findAll(): Array<Evaluation> {
    return this.evaluations;
  }

  findById(id: number): Observable<Evaluation> {
    return this.http.get<Evaluation>("http://localhost:8080/api/evaluation/" + id);
  }

  create(evaluation: Evaluation) {
    return this.http.post<Evaluation>("http://localhost:8080/api/evaluation", evaluation);
  }

  modify(evaluation: Evaluation) {
    return this.http.put<Evaluation>("http://localhost:8080/api/evaluation/" + evaluation.id, evaluation);
  }

  deleteById(id: number) {
    this.http.delete("http://localhost:8080/api/evaluation/" + id).subscribe(resp => this.load(), error => console.log(error))
  }

  load() {
    this.http.get<Array<Evaluation>>("http://localhost:8080/api/evaluation").subscribe(resp => {
      this.evaluations = resp;
    }, error => console.log(error))
  }
}
