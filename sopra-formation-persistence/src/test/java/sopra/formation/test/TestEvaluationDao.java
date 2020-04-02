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

public class TestEvaluationDao {
	public static void main(String[] args) {
		
		Evaluation evaluation = new Evaluation(12, 15, "Bonne Evolution"); // new
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation); // managed
		
		evaluation.setComportemental(14); // detached
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
		
		Application.getInstance().getEvaluationDao().delete(evaluation);
		
		Evaluation eva2 = new Evaluation(8, 11, "sa va");
		
		eva2 = Application.getInstance().getEvaluationDao().save(eva2);
		
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

		kevin.setAdresse(adrKevin); // new
		
		kevin = Application.getInstance().getStagiaireDao().save(kevin); // managed
		
		kevin.setEvaluation(eva2);
		
		eva2 = Application.getInstance().getEvaluationDao().save(eva2);
		
		Filiere covid = new Filiere("covid", "2020", new Date(), 57, Dispositif.POEI);	
		
		covid = Application.getInstance().getFiliereDao().save(covid);
		
		kevin.setFiliere(covid);
		
		kevin = Application.getInstance().getStagiaireDao().save(kevin);		
		
		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);
		
		eric = Application.getInstance().getFormateurDao().save(eric);
		
		covid.setReferent(eric);
		
		covid = Application.getInstance().getFiliereDao().save(covid);
		
		Matiere angular = new Matiere("Angular", 6);		
		angular = Application.getInstance().getMatiereDao().save(angular);
		
		Matiere boot = new Matiere("Spring Boot", 2);
		boot = Application.getInstance().getMatiereDao().save(boot);
		
		eric.addCompetence(angular);
		
		eric.addCompetence(boot);
		eric = Application.getInstance().getFormateurDao().save(eric);
		
		Salle wim = new Salle("wim", 9, true);	
				
		Adresse adrWim = new Adresse();
		
		adrWim.setRue("582qzefsgrdth");
		adrWim.setComplement("");
		adrWim.setCodePostal("33600");
		adrWim.setVille("PESSAC");

		wim.setAdr(adrWim);
		
		wim = Application.getInstance().getSalleDao().save(wim);
		
		UE java = new UE(3452, 12, 98);
		
		java = Application.getInstance().getUeDao().save(java);
		
		java.setFiliere(covid);
		java.setFormateur(eric);
		java.setMatiere(boot);
		java.setMatiere(angular);
		java.setSalle(wim);
		
		java = Application.getInstance().getUeDao().save(java);
		
		
		
		
	}
}
