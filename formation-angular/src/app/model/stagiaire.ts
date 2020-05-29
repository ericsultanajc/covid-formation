import {Personne} from "./personne";
import {Adresse} from "./adresse";
import {Evaluation} from "./evaluation";

export class Stagiaire extends Personne {
  dtNaissance: Date;
  niveauEtude: string;
  evaluation: Evaluation;

  constructor(id?: number, version?: number, civilite?: string, nom?: string, prenom?: string, email?: string, telephone?: string, adresse?: Adresse, dtNaissance?: Date, niveauEtude?: string) {
    super(id, version, civilite, nom, prenom, email, telephone, adresse);
    this.dtNaissance = dtNaissance;
    this.niveauEtude = niveauEtude;
  }
}
