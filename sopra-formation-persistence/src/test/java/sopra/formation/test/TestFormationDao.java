package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.IMatiereDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;

public class TestFormationDao {
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IEvaluationDao testEval = Application.getInstance().getEvaluationDao();
		IStagiaireDao testStag = Application.getInstance().getStagiaireDao();
		IFormateurDao testForm = Application.getInstance().getFormateurDao();
		IMatiereDao testMat = Application.getInstance().getMatiereDao();
		IFiliereDao testFil = Application.getInstance().getFiliereDao();
		ISalleDao testSalle = Application.getInstance().getSalleDao();
		IUEDao testUE = Application.getInstance().getUeDao();
		
		
		//Eval
		
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new
		
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
		
		evaluation = testEval.save(evaluation); // managed
		
		System.out.println(evaluation);
		
		evaluation.setComportemental(14); // detached
		
		evaluation = testEval.save(evaluation);
		
		Application.getInstance().getEvaluationDao().delete(evaluation);
		

		Evaluation eval2 = new Evaluation();
		eval2.setComportemental(16);
		eval2.setTechnique(15);
		eval2.setCommentaires("Super délégué!");
		
		eval2 = testEval.save(eval2); // managed
		
		System.out.println(eval2);
		
		eval2.setTechnique(8); // detached
		
		eval2 = testEval.save(eval2);
		
		//Stagiaire
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
		kevin.setEvaluation(eval2);
		
		testStag.save(kevin);
		
		//Formateur
		
		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);
		
	
		//Matiere
		
		Matiere angular = new Matiere("ANGULAR", 6);
		angular = testMat.save(angular);
		Matiere springboot = new Matiere("Spring boot", 3);
		springboot = testMat.save(springboot);
		
		eric.getCompetences().add(springboot);
		eric.getCompetences().add(angular);
		
		eric = testForm.save(eric);
		
		//Filiere
		
		Filiere sopraJava = new Filiere();
		 sopraJava.setIntitule("JAVA SPRING ANGULAR");
		 sopraJava.setPromotion("COVID");
		 sopraJava.setDtDebut(sdf.parse("09/03/2020"));
		 sopraJava.setDuree(57);
		 sopraJava.setDispositif(Dispositif.POEI);
		 
		 sopraJava = testFil.save(sopraJava);
		
		 //Salle
		 
		 Salle sanFransisco = new Salle();
		 sanFransisco.setNom("San Fransisco");
		 sanFransisco.setCapacite(15);
		 sanFransisco.setVideoProjecteur(true);
		
		 Adresse adrsanFransisco = new Adresse();
		 adrsanFransisco.setRue("86 Avenue JFK");
		 adrsanFransisco.setComplement("WIM");
		 adrsanFransisco.setCodePostal("33700");
		 adrsanFransisco.setVille("Mérignac");
		 sanFransisco.setAdr(adrsanFransisco);
		 
		 sanFransisco = testSalle.save(sanFransisco);
		 
		 //UE
		
		
	}
}