package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;

public class TestFormation {

	public static void main(String[] args) throws Exception {
		IEvaluationDao evaluationDao = Application.getInstance().getEvaluationDao();
		IStagiaireDao stagiaireDao = Application.getInstance().getStagiaireDao();
		IFiliereDao filiereDao = Application.getInstance().getFiliereDao();
		IFormateurDao formateurDao = Application.getInstance().getFormateurDao();
		ISalleDao salleDao = Application.getInstance().getSalleDao();
		IUEDao ueDao = Application.getInstance().getUeDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new
//		
//		
//		evaluation = Application.getInstance().getEvaluationDao().save(evaluation); // managed
//		
//		System.out.println(evaluation);
//		
//		evaluation.setComportemental(14); // detached
//		
//		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
//		
//		Application.getInstance().getEvaluationDao().delete(evaluation);
//	}
		// FILIERE
		System.out.println("FILIERE");

		Filiere sopraJava = new Filiere();
		sopraJava.setIntitule("SOPRA Java");
		sopraJava.setPromotion("COVID");
		sopraJava.setDtDebut(sdf.parse("09/03/2020"));
		sopraJava.setDuree(57);
		sopraJava.setDispositif(Dispositif.POEI);

		sopraJava = filiereDao.save(sopraJava);
		filiereDao.delete(sopraJava);

		Filiere javaSpringAngular = new Filiere();
		javaSpringAngular.setIntitule("JAVA SPRING ANGULAR");
		javaSpringAngular.setPromotion("COVID");
		javaSpringAngular.setDtDebut(sdf.parse("09/03/2020"));
		javaSpringAngular.setDuree(57);
		javaSpringAngular.setDispositif(Dispositif.POEI);

		javaSpringAngular = filiereDao.save(javaSpringAngular);

		System.out.println("###############################################################");

		// MATIERE
		System.out.println("MATIERE");

		Matiere angular = new Matiere("ANGULAR", 6);
		angular = Application.getInstance().getMatiereDao().save(angular);

		Matiere springboot = new Matiere("Spring boot", 3);
		springboot = Application.getInstance().getMatiereDao().save(springboot);

		Matiere java = new Matiere("JAVA", 3);
		java = Application.getInstance().getMatiereDao().save(java);

		Matiere comportemental = new Matiere("Comportemental", 4);
		comportemental = Application.getInstance().getMatiereDao().save(comportemental);

		Matiere bidon = new Matiere("Bidon", 10);
		bidon = Application.getInstance().getMatiereDao().save(bidon);
		bidon.setDuree(5);
		bidon = Application.getInstance().getMatiereDao().save(bidon);
		Application.getInstance().getMatiereDao().delete(bidon);

		Matiere methodes = new Matiere("Méthodes et outils", 4);
		methodes = Application.getInstance().getMatiereDao().save(methodes);

		System.out.println("###############################################################");

		// FORMATEUR VIA PERSONNE
		System.out.println("FORMATEUR");

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);
		eric.getCompetences().add(angular);
		eric.getCompetences().add(springboot);
		eric.getCompetences().add(java);
		Matiere servletJsp = new Matiere("Servlet/JSP", 2);
		servletJsp = Application.getInstance().getMatiereDao().save(servletJsp);
		eric.getCompetences().add(servletJsp);
		eric = Application.getInstance().getFormateurDao().save(eric);

		System.out.println("FORMATEUR");

		Formateur armand = new Formateur("a.khaida@ajc-ingenierie.fr");
		armand.setCivilite(Civilite.M);
		armand.setNom("KHAIDA");
		armand.setPrenom("Armand");
		armand.setTelephone("0011223344");
		armand.setAdresse("rue", "complement", "33000", "commune");
		armand.setReferent(false);
		armand.setExperience(10);
		armand.getCompetences().add(comportemental);
		armand = Application.getInstance().getFormateurDao().save(armand);

		Formateur jonathan = new Formateur("j.turpin@ajc-ingenierie.fr");
		jonathan.setCivilite(Civilite.M);
		jonathan.setNom("TURPIN");
		jonathan.setPrenom("Jonathan");
		jonathan.setTelephone("5566778899");
		jonathan.setAdresse("rue", "", "33700", "Mérignac");
		jonathan.setReferent(false);
		jonathan.setExperience(15);
		jonathan.getCompetences().add(methodes);
		jonathan = Application.getInstance().getFormateurDao().save(jonathan);

		System.out.println("###############################################################");

		// COMPETENCE ET LIEN ENTRE COMPETENCE/MATIERE/FORMATEUR
		System.out.println("COMPETENCE ET LIEN ENTRE COMPETENCE/MATIERE/FORMATEUR");

		System.out.println("###############################################################");

		// EVALUATION
		System.out.println("EVALUATION");

		Evaluation eval1 = new Evaluation();
		eval1.setComportemental(15);
		eval1.setTechnique(10);
		eval1.setCommentaires("Attente d'une progression au niveau technique");
		eval1 = Application.getInstance().getEvaluationDao().save(eval1);

		Application.getInstance().getEvaluationDao().findAll();
		Evaluation eval2 = new Evaluation();
		eval2.setComportemental(16);
		eval2.setTechnique(15);
		eval2.setCommentaires("Super délégué!");
		eval2 = Application.getInstance().getEvaluationDao().save(eval2);

		eval1.setTechnique(13);
		eval1.setCommentaires("Nette progresion!");

		Application.getInstance().getEvaluationDao().save(eval1);

		System.out.println("###############################################################");

		// LIEN ENTRE FILIERE ET FORMATEUR VIA REFERENT
		System.out.println("LIEN ENTRE FILIERE ET FORMATEUR VIA REFERENT");

		javaSpringAngular.setReferent(eric);
		eric = Application.getInstance().getFormateurDao().save(eric);

		System.out.println("###############################################################");

		// STAGIAIRE VIA PERSONNE
		System.out.println("STAGIAIRE VIA PERSONNE");

		Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
		manon.setCivilite(Civilite.MME);
		manon.setNom("CHARLES");
		manon.setPrenom("Manon");
		manon.setTelephone("0635244332");
		manon.setDtNaissance(sdf.parse("06/08/1992"));
		manon.setNiveauEtude(NiveauEtude.BAC_5);
		manon.setAdresse("21 Av Colonel Pierre Bourgoin", "", "33127", "Martignas-sur-Jalle");
		manon.setEvaluation(eval1);
		manon.setFiliere(javaSpringAngular);
		manon = Application.getInstance().getStagiaireDao().save(manon);

		Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
		kevin.setCivilite(Civilite.M);
		kevin.setNom("BOUGIS");
		kevin.setPrenom("Kevin");
		kevin.setTelephone("0625570704");
		kevin.setDtNaissance(sdf.parse("02/07/1990"));
		kevin.setNiveauEtude(NiveauEtude.BAC_8);
		kevin.setAdresse("5 bis avenue villemejan", "Résidence Diderot - Appt 8", "33600", "PESSAC");
		kevin.setEvaluation(eval2);
		kevin.setFiliere(javaSpringAngular);
		kevin = Application.getInstance().getStagiaireDao().save(kevin);

		manon.setAdresse("30 rue de Diane", "", "33210", "Pujols-sur-Ciron");
		Application.getInstance().getStagiaireDao().save(manon);

		System.out.println("###############################################################");

		// LIEN ENTRE STAGIAIRE ET EVALUATION
		System.out.println("LIEN ENTRE STAGIAIRE ET EVALUATION");

		System.out.println("###############################################################");

		// LIEN ENTRE STAGIAIRE ET FILIERE
		System.out.println("LIEN ENTRE STAGIAIRE ET FILIERE");

		System.out.println("###############################################################");

		// SALLE
		System.out.println("SALLE");

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

		sanFransisco = salleDao.save(sanFransisco);

		Salle losAngeles = new Salle();
		losAngeles.setNom("Los Angeles");
		losAngeles.setCapacite(10);
		losAngeles.setVideoProjecteur(false);

		Adresse adrlosAngeles = new Adresse();
		adrlosAngeles.setRue("86 Avenue JFK");
		adrlosAngeles.setComplement("");
		adrlosAngeles.setCodePostal("33700");
		adrlosAngeles.setVille("Mérignac");
		losAngeles.setAdr(adrlosAngeles);

		losAngeles = salleDao.save(losAngeles);

		adrlosAngeles.setComplement("WIM");
		losAngeles.setAdr(adrlosAngeles);
		losAngeles = salleDao.save(losAngeles);

		salleDao.delete(losAngeles);

		System.out.println("###############################################################");

		// UE
		System.out.println("UE");

		UE unix = new UE();
		unix.setCode(4738);
		unix.setDuree(1);
		unix.setOrdre(1);
		unix.setFiliere(javaSpringAngular);
		unix.setMatiere(null);
		unix.setFormateur(eric);
		unix.setSalle(sanFransisco);
		unix = ueDao.save(unix);

		UE algo = new UE();
		algo.setCode(3326);
		algo.setDuree(3);
		algo.setOrdre(2);
		algo.setFiliere(javaSpringAngular);
		algo.setMatiere(java);
		algo.setFormateur(eric);
		algo.setSalle(sanFransisco);
		algo = ueDao.save(algo);

		UE javaObjet = new UE();
		javaObjet.setCode(3542);
		javaObjet.setDuree(3);
		javaObjet.setOrdre(4);
		javaObjet.setFiliere(javaSpringAngular);
		javaObjet.setMatiere(java);
		javaObjet.setFormateur(eric);
		javaObjet.setSalle(sanFransisco);
		javaObjet = ueDao.save(javaObjet);

		UE javaAvance = new UE();
		javaAvance.setCode(2789);
		javaAvance.setDuree(4);
		javaAvance.setOrdre(6);
		javaAvance.setFiliere(javaSpringAngular);
		javaAvance.setMatiere(java);
		javaAvance.setFormateur(eric);
		javaAvance.setSalle(sanFransisco);
		javaAvance = ueDao.save(javaAvance);

		UE springMvc = new UE();
		springMvc.setCode(7151);
		springMvc.setDuree(4);
		springMvc.setOrdre(14);
		springMvc.setFiliere(javaSpringAngular);
		springMvc.setMatiere(springboot);
		springMvc.setFormateur(eric);
		springMvc.setSalle(sanFransisco);
		springMvc = ueDao.save(springMvc);

		UE travailEnEquipe = new UE();
		travailEnEquipe.setCode(6451);
		travailEnEquipe.setDuree(1);
		travailEnEquipe.setOrdre(18);
		travailEnEquipe.setFiliere(javaSpringAngular);
		travailEnEquipe.setMatiere(comportemental);
		travailEnEquipe.setFormateur(armand);
		travailEnEquipe.setSalle(sanFransisco);
		travailEnEquipe = ueDao.save(travailEnEquipe);

	}
}