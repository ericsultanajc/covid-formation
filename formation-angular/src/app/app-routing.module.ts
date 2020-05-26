import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {EvaluationComponent} from "./evaluation/evaluation.component";
import {StagiaireComponent} from "./stagiaire/stagiaire.component";


const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "evaluation", component: EvaluationComponent},
  {path: "stagiaire", component: StagiaireComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
