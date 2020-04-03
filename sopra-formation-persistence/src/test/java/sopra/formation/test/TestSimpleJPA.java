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
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.IMatiereDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;

public class TestSimpleJPA {

	public static void main(String[] args) {

		IEvaluationDao evaluationDao = Application.getInstance().getEvaluationDao();
		IFiliereDao filiereDao = Application.getInstance().getFiliereDao();
		IFormateurDao formateurDao = Application.getInstance().getFormateurDao();
		IMatiereDao matiereDao = Application.getInstance().getMatiereDao();
		ISalleDao salleDao = Application.getInstance().getSalleDao();
		IStagiaireDao stagiaireDao = Application.getInstance().getStagiaireDao();
		IUEDao ueDao = Application.getInstance().getUeDao();

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

		Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
		manon.setCivilite(Civilite.MME);
		manon.setNom("CHARLES");
		manon.setPrenom("Manon");
		manon.setTelephone("0635244332");
		manon.setDtNaissance(new Date());
		manon.setNiveauEtude(NiveauEtude.BAC_5);

		manon.setAdresse(new Adresse("21 avenue du Colonel Pierre Bourgoin", "", "33127", "MARTIGNAS SUR JALLE"));

		Evaluation evalKevin = new Evaluation(5, 8, "Mauvais délégué");
		Evaluation evalManon = new Evaluation(2, 3, "En nette progression");

		evalKevin = evaluationDao.save(evalKevin);
		evalManon = evaluationDao.save(evalManon);

		kevin.setEvaluation(evalKevin);
		manon.setEvaluation(evalManon);

		kevin = stagiaireDao.save(kevin);
		manon = stagiaireDao.save(manon);

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setCivilite(Civilite.M);
		eric.setNom("SULTAN");
		eric.setPrenom("Eric");
		eric.setTelephone("0645104506");
		eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
		eric.setReferent(true);
		eric.setExperience(20);

		eric = formateurDao.save(eric);

		Filiere covid = new Filiere("COVID");
		covid.setIntitule("JAVA SPRING ANGULAR");
		covid.setDtDebut(new Date());
		covid.setDuree(57);
		covid.setDispositif(Dispositif.POEI);
		covid.setReferent(eric);
		
		covid = filiereDao.save(covid);
		
		kevin.setFiliere(covid);
		manon.setFiliere(covid);
		
		kevin = stagiaireDao.save(kevin);
		manon = stagiaireDao.save(manon);

		Matiere angular = new Matiere("ANGULAR", 7);
		Matiere java = new Matiere("JAVA", 5);

		angular = matiereDao.save(angular);
		java = matiereDao.save(java);

		eric.addCompetence(angular);
		eric.addCompetence(java);

		eric = formateurDao.save(eric);

		Salle wim = new Salle("San Fransisco");
		wim.setCapacite(14);
		wim.setVideoProjecteur(true);
		wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));

		wim = salleDao.save(wim);

		UE ueUnix = new UE(4738, 1, 1);

		ueUnix = ueDao.save(ueUnix);

		ueUnix.setFiliere(covid);
		ueUnix.setFormateur(eric);
		ueUnix.setMatiere(java);
		ueUnix.setSalle(wim);

		ueUnix = ueDao.save(ueUnix);
					

	}

	
}
