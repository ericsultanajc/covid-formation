import {Adresse} from "./adresse";
import {Civilite} from "./civilite";

export class Personne {
  id: number;
  version: number;
  civilite : Civilite;
  nom:string;
  prenom:string;
  email:string;
  telephone:string
  adresse: Adresse;

  constructor(id?: number, version?: number, civilite?: Civilite, nom?: string, prenom?: string, email?: string, telephone?: string, adresse?: Adresse) {
    this.id = id;
    this.version = version;
    this.civilite = civilite;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.adresse = adresse;
  }
}
