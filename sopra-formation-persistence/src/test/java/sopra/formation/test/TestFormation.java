package sopra.formation.test;

import java.util.Date;

import sopra.formation.Application;
import sopra.formation.model.Civilite;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;

public class TestFormation {

	public static void main(String[] args) {
//		IEvaluationDao evaluationDao = Application.getInstance().getEvaluationDao();
//		IStagiaireDao stagiaireDao = Application.getInstance().getStagiaireDao();
//		
//		List<Stagiaire> stagiaires = stagiaireDao.findAll();
//
//		for(Stagiaire stagiaire : stagiaires) {
//			System.out.println(stagiaire.toString());
//		}
//		
//		System.out.println("######################################");
//		
//		Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
//		kevin.setCivilite(Civilite.M);
//		kevin.setNom("BOUGIS");
//		kevin.setPrenom("Kévin");
//		kevin.setTelephone("0625570704");
//		kevin.setDtNaissance(new Date());
//		kevin.setNiveauEtude(NiveauEtude.BAC_8);
//
//		Adresse adrKevin = new Adresse();
//
//		adrKevin.setRue("5bis avenue villemejan");
//		adrKevin.setComplement("Résidence Diderot - Appt 8");
//		adrKevin.setCodePostal("33600");
//		adrKevin.setVille("PESSAC");
//
//		kevin.setAdresse(adrKevin);
//		
//		kevin = stagiaireDao.create(kevin);
//
//		stagiaires = stagiaireDao.findAll();
//
//		for(Stagiaire stagiaire : stagiaires) {
//			System.out.println(stagiaire.toString());
//		}
//		
//		System.out.println("####################");
//		
//		Stagiaire stagiaire5 = stagiaireDao.find(5L);
//		
//		System.out.println(stagiaire5);
//		
//		stagiaire5.setCivilite(Civilite.MME);
//		stagiaire5.setNiveauEtude(NiveauEtude.BAC_8);
//		
//		stagiaireDao.update(stagiaire5);
//		
//		System.out.println("####################");
//		
//		Stagiaire stagiaire5Copy = stagiaireDao.find(5L);
//		
//		System.out.println(stagiaire5Copy);
//		
//		stagiaireDao.delete(kevin.getId());
//		
//		System.out.println("####################");
//		
//		stagiaires = stagiaireDao.findAll();
//
//		for(Stagiaire stagiaire : stagiaires) {
//			System.out.println(stagiaire.toString());
//		}
//		
//		for(Evaluation eval : evaluationDao.findAll()) {
//			System.out.println(eval);
//		}

//		Matiere angular = new Matiere("ANGULAR", 6);
//
//		angular = Application.getInstance().getMatiereDao().create(angular);
//
//		Matiere springboot = new Matiere("Spring boot", 3);
//
//		springboot = Application.getInstance().getMatiereDao().create(springboot);
//
//		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
//		eric.setCivilite(Civilite.M);
//		eric.setNom("SULTAN");
//		eric.setPrenom("Eric");
//		eric.setTelephone("0645104506");
//		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
//		eric.setReferent(true);
//		eric.setExperience(20);
//		
//		Date dtNaissance;
//
//		eric.getCompetences().add(angular);
//		eric.getCompetences().add(springboot);
//
//		Application.getInstance().getFormateurDao().create(eric);
//		
//		Matiere servletJsp = new Matiere("Servlet/JSP", 2);
//		servletJsp = Application.getInstance().getMatiereDao().create(servletJsp);
//		
//		eric.getCompetences().add(servletJsp);
//		
//		Application.getInstance().getFormateurDao().update(eric);
		
	}

}
