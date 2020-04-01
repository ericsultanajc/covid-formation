package sopra.formation.test;

import java.util.Date;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Personne;
import sopra.formation.model.Salle;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.UE;

public class Test {

	public static void main(String[] args) {
		Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
		kevin.setCivilite(Civilite.M);
		kevin.setNom("BOUGIS");
		kevin.setPrenom("Kévin");
		kevin.setTelephone("0625570704");
		kevin.setDtNaissance(new Date());
		kevin.setNiveauEtude(NiveauEtude.BAC_8);

		Adresse adrKevin = new Adresse();

		adrKevin.setRue("5bis avenue villemejan");
		adrKevin.setComplement("Résidence Diderot - Appt 8");
		adrKevin.setCodePostal("33600");
		adrKevin.setVille("PESSAC");

		kevin.setAdresse(adrKevin);

		Personne manon = new Stagiaire("charles.manon@yahoo.com");
		manon.setCivilite(Civilite.MME);
		manon.setNom("CHARLES");
		manon.setPrenom("Manon");
		manon.setTelephone("0635244332");
		((Stagiaire) manon).setDtNaissance(new Date());
		((Stagiaire) manon).setNiveauEtude(NiveauEtude.BAC_5);
		
		manon.setAdresse(new Adresse("21 avenue du Colonel Pierre Bourgoin", "", "33127", "MARTIGNAS SUR JALLE"));

		Salle wim = new Salle("San Fransisco");
		wim.setCapacite(14);
		wim.setVideoProjecteur(true);

		wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);

		Filiere covid = new Filiere("COVID");
		covid.setIntitule("JAVA SPRING ANGULAR");
		covid.setDtDebut(new Date());
		covid.setDuree(57);
		covid.setDispositif(Dispositif.POEI);
		covid.setReferent(eric);

//		Fonctionne mais un peu lourd � coder
//		ArrayList<Stagiaire> list = new ArrayList<Stagiaire>();
//		list.add(kevin);
//		list.add((Stagiaire) manon);
//		
//		covid.setStagiaires(list);

//		un peu mieux
//		covid.getStagiaires().add(kevin);
//		covid.getStagiaires().add((Stagiaire) manon);

		covid.addStagiaire(kevin);
		covid.addStagiaire((Stagiaire) manon);

		kevin.setFiliere(covid);
		((Stagiaire) manon).setFiliere(covid);

		Matiere unix = new Matiere("UNIX", 1);

		unix.addFormateur(eric);
		eric.addCompetence(unix);

		UE ueUnix = new UE(4738, 1, 1);
		ueUnix.setFiliere(covid);
		ueUnix.setFormateur(eric);
		ueUnix.setMatiere(unix);
		ueUnix.setSalle(wim);

		covid.addUe(ueUnix);
		eric.addUe(ueUnix);
		unix.addUe(ueUnix);
		wim.addUe(ueUnix);

		Matiere algo = new Matiere("ALGO EN JAVA", 3);

		algo.addFormateur(eric);
		eric.addCompetence(algo);

		UE ueAlgo = new UE(3326, 3, 2);
		ueAlgo.setFiliere(covid);
		ueAlgo.setFormateur(eric);
		ueAlgo.setMatiere(algo);
		ueAlgo.setSalle(wim);

		covid.addUe(ueAlgo);
		eric.addUe(ueAlgo);
		algo.addUe(ueAlgo);
		wim.addUe(ueAlgo);

		Matiere uml = new Matiere("UML", 1);

		uml.addFormateur(eric);
		eric.addCompetence(uml);

		UE ueUml = new UE(2369, 1, 3);
		ueUml.setFiliere(covid);
		ueUml.setFormateur(eric);
		ueUml.setMatiere(uml);
		ueUml.setSalle(wim);

		covid.addUe(ueUml);
		eric.addUe(ueUml);
		uml.addUe(ueUml);
		wim.addUe(ueUml);

		Matiere javaObjet = new Matiere("JAVA OBJET", 3);

		javaObjet.addFormateur(eric);
		eric.addCompetence(javaObjet);

		UE ueJavaObjet = new UE(3542, 2, 4);
		ueJavaObjet.setFiliere(covid);
		ueJavaObjet.setFormateur(eric);
		ueJavaObjet.setMatiere(javaObjet);
		ueJavaObjet.setSalle(wim);

		covid.addUe(ueJavaObjet);
		eric.addUe(ueJavaObjet);
		javaObjet.addUe(ueJavaObjet);
		wim.addUe(ueJavaObjet);

		kevin.setEvaluation(new Evaluation(15, 13, "super délégué"));
		((Stagiaire) manon).setEvaluation(new Evaluation(16, 11, "difficultés passagères"));

		System.out.println(covid);
	}

}
