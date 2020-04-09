package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
import sopra.formation.persistence.IMatiereDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;

public class TestFormationDao {

	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);
		IFiliereDao filiereDao = context.getBean(IFiliereDao.class);
		IFormateurDao formateurDao = context.getBean(IFormateurDao.class);
		IMatiereDao matiereDao = context.getBean(IMatiereDao.class);
		ISalleDao salleDao = context.getBean(ISalleDao.class);
		IStagiaireDao stagiaireDao = context.getBean(IStagiaireDao.class);
		IUEDao ueDao = context.getBean(IUEDao.class);

		Evaluation evalCecile = new Evaluation(14, 17, "RAS");
		evalCecile = evaluationDao.save(evalCecile);

		Stagiaire cecile = new Stagiaire("cecile.larrouy@outlook.fr");
		cecile.setCivilite(Civilite.M);
		cecile.setNom("LARROUY");
		cecile.setPrenom("Cécile");
		cecile.setTelephone("0608050400");
		cecile.setDtNaissance(sdf.parse("23/04/1994"));
		cecile.setNiveauEtude(NiveauEtude.BAC_5);
		cecile.setAdresse(new Adresse("93 Boulevard Georges V", "Résidence Zola", "33400", "Talence"));
		cecile.setEvaluation(evalCecile);

		cecile = stagiaireDao.save(cecile);

		Evaluation evalKevin = new Evaluation(12, 15, "Bonne évolution");
		evalKevin = evaluationDao.save(evalKevin);

		Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
		kevin.setCivilite(Civilite.M);
		kevin.setNom("BOUGIS");
		kevin.setPrenom("Kévin");
		kevin.setTelephone("0625570704");
		kevin.setDtNaissance(sdf.parse("02/07/1990"));
		kevin.setNiveauEtude(NiveauEtude.BAC_8);
		kevin.setEvaluation(evalKevin);

		Adresse adrKevin = new Adresse();

		adrKevin.setRue("5bis avenue villemejan");
		adrKevin.setComplement("Résidence Diderot - Appt 8");
		adrKevin.setCodePostal("33600");
		adrKevin.setVille("PESSAC");

		kevin.setAdresse(adrKevin);

		kevin = stagiaireDao.save(kevin);

		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);

		covid = filiereDao.save(covid);

		kevin.setFiliere(covid);
		kevin = stagiaireDao.save(kevin);

		cecile.setFiliere(covid);
		cecile = stagiaireDao.save(cecile);

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);

		eric = formateurDao.save(eric);

		covid.setReferent(eric);

		covid = filiereDao.save(covid);

		Matiere angular = new Matiere("ANGULAR", 6);

		angular = matiereDao.save(angular);

		Matiere springboot = new Matiere("Spring boot", 3);

		springboot = matiereDao.save(springboot);

		Matiere servletJsp = new Matiere("Servlet/JSP", 2);
		servletJsp = matiereDao.save(servletJsp);

		eric.getCompetences().add(angular);
		eric.getCompetences().add(springboot);
		eric.getCompetences().add(servletJsp);

		eric = formateurDao.save(eric);

		Salle wim = new Salle("WIM", 15, true);
		wim.setAdr(new Adresse("86 avenue JFK", "1er étage", "33700", "Mérignac"));

		wim = salleDao.save(wim);

		UE covidAngular = new UE(3352, 6, 3);
		covidAngular.setFiliere(covid);
		covidAngular.setFormateur(eric);
		covidAngular.setMatiere(angular);
		covidAngular.setSalle(wim);
		covidAngular = ueDao.save(covidAngular);

		UE covidSpringBoot = new UE(1245, 3, 2);
		covidSpringBoot.setFiliere(covid);
		covidSpringBoot.setFormateur(eric);
		covidSpringBoot.setMatiere(springboot);
		covidSpringBoot.setSalle(wim);
		covidSpringBoot = ueDao.save(covidSpringBoot);

		UE covidServletJsp = new UE(0034, 2, 1);
		covidServletJsp.setFiliere(covid);
		covidServletJsp.setFormateur(eric);
		covidServletJsp.setMatiere(servletJsp);
		covidServletJsp.setSalle(wim);
		covidServletJsp = ueDao.save(covidServletJsp);
		
		context.close();

		context.close();
	}

}
