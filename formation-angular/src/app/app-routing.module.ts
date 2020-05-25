import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {EvaluationComponent} from "./evaluation/evaluation.component";


const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "evaluation", component: EvaluationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
