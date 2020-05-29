import {Personne} from "./personne";
import {NiveauEtude} from "./niveauEtude";

export class Stagiaire extends Personne {
  dtNaissance: Date;
  niveauEtude: NiveauEtude;

  constructor(dtNaissance?: Date, niveauEtude?: NiveauEtude) {
    super();
    this.dtNaissance = dtNaissance;
    this.niveauEtude = niveauEtude;
  }
}
