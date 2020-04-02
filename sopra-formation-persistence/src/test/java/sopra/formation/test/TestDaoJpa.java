package sopra.formation.test;

import java.util.Date;

import sopra.formation.Application;
import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Salle;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.UE;

public class TestDaoJpa {

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
		
		
		kevin = Application.getInstance().getStagiaireDao().save(kevin); // managed	
		
		
		
		Salle wim = new Salle("San Fransisco");
		wim.setCapacite(14);
		wim.setVideoProjecteur(true);

		wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));
		
		wim = Application.getInstance().getSalleDao().save(wim);
		
		
		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);
		
		eric = Application.getInstance().getFormateurDao().save(eric);

		Filiere covid = new Filiere("COVID");
		covid.setIntitule("JAVA SPRING ANGULAR");
		covid.setDtDebut(new Date());
		covid.setDuree(57);
		covid.setDispositif(Dispositif.POEI);
		covid.setReferent(eric);		
		
		covid = Application.getInstance().getFiliereDao().save(covid);
		
		
		
		Matiere unix = new Matiere("UNIX", 1);
		
		unix = Application.getInstance().getMatiereDao().save(unix);	

		eric.addCompetence(unix);
		
		eric = Application.getInstance().getFormateurDao().save(eric);
		
		
		UE ueUnix = new UE(4738, 1, 1);
		ueUnix.setFiliere(covid);
		ueUnix.setFormateur(eric);
		ueUnix.setMatiere(unix);
		ueUnix.setSalle(wim);
		
		ueUnix = Application.getInstance().getUeDao().save(ueUnix);

		covid.addUe(ueUnix);
		
		covid = Application.getInstance().getFiliereDao().save(covid);
		
//		eric.addUe(ueUnix);
		
//		eric = Application.getInstance().getFormateurDao().save(eric);
//		
		unix.addUe(ueUnix);
		
		unix = Application.getInstance().getMatiereDao().save(unix);	
		
		wim.addUe(ueUnix);
		
		wim = Application.getInstance().getSalleDao().save(wim);
		
		Evaluation eval = new Evaluation(19, 19, "ok");
		
		eval = Application.getInstance().getEvaluationDao().save(eval);
		
		kevin.setEvaluation(eval);
////		
		kevin = Application.getInstance().getStagiaireDao().save(kevin); // managed	
//		
//		
	
		
		
					
     		
		
		
//
//		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new
//
//		
//
//		evaluation = Application.getInstance().getEvaluationDao().save(evaluation); // managed
//
//		
//
//		System.out.println(evaluation);
//
//		
//
//		evaluation.setComportemental(14); // detached
//
//		
//
//		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
//
//		
//
//		Application.getInstance().getEvaluationDao().delete(evaluation);

	}

}