import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {EvaluationComponent} from './evaluation/evaluation.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {StagiaireComponent} from './stagiaire/stagiaire.component';
import {AgePipe} from "./age.pipe";
import {EvaluationService} from "./evaluation/evaluation.service";
import {StagiaireService} from "./stagiaire/stagiaire.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EvaluationComponent,
    StagiaireComponent,
    AgePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [EvaluationService, StagiaireService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
