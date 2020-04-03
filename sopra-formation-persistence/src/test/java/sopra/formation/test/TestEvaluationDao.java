package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

public class TestEvaluationDao {

	public static void main(String[] args) throws ParseException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			IFormateurDao formateurDao = Application.getInstance().getFormateurDao();

			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
			eric.setCivilite(Civilite.M);
			eric.setNom("SULTAN");
			eric.setPrenom("Eric");
			eric.setTelephone("0645104506");
			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
			eric.setReferent(true);
			eric.setExperience(20);

			eric = formateurDao.save(eric);

			System.out.println("____");

			IFiliereDao filiereDao = Application.getInstance().getFiliereDao();

			Filiere covid = new Filiere("JAVA J2E", "Covid", sdf.parse("09/03/2020"), 57, Dispositif.POEI);

			covid.setReferent(eric);

			covid = filiereDao.save(covid);

			IMatiereDao matiereDao = Application.getInstance().getMatiereDao();

			Matiere java = new Matiere("JAVA", 12);

			java = matiereDao.save(java);

			Matiere web = new Matiere("WEB", 8);

			web = matiereDao.save(web);

			System.out.println("____");

			ISalleDao salleDao = Application.getInstance().getSalleDao();

			Salle wim = new Salle("San Francisco");
			wim.setCapacite(14);
			wim.setVideoProjecteur(true);
			wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));

			wim = salleDao.save(wim);

			System.out.println("____");

			IEvaluationDao evaluationDao = Application.getInstance().getEvaluationDao();

			Evaluation evalKevin = new Evaluation(14, 15, "Super délégué");

			evalKevin = evaluationDao.save(evalKevin);

			IStagiaireDao stagiaireDao = Application.getInstance().getStagiaireDao();

			Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
			kevin.setCivilite(Civilite.M);
			kevin.setNom("BOUGIS");
			kevin.setPrenom("Kévin");
			kevin.setTelephone("0625570704");
			kevin.setDtNaissance(sdf.parse("02/02/1990"));
			kevin.setNiveauEtude(NiveauEtude.BAC_8);

			Adresse adrKevin = new Adresse();

			adrKevin.setRue("5bis avenue villemejan");
			adrKevin.setComplement("Résidence Diderot - Appt 8");
			adrKevin.setCodePostal("33600");
			adrKevin.setVille("PESSAC");

			kevin.setAdresse(adrKevin);

			kevin.setEvaluation(evalKevin);

			kevin.setFiliere(covid);

			kevin = stagiaireDao.save(kevin);

			Evaluation evalManon = new Evaluation(12, 16, "Difficultés passagères");

			evalManon = evaluationDao.save(evalManon);

			List<Evaluation> evaluations = evaluationDao.findAll();

			for (Evaluation evaluation : evaluations) {
				System.out.println(evaluation.toString());
			}

			Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
			manon.setCivilite(Civilite.MME);
			manon.setNom("CHARLES");
			manon.setPrenom("Manon");
			manon.setTelephone("0635244332");
			manon.setDtNaissance(sdf.parse("06/08/1992"));
			manon.setNiveauEtude(NiveauEtude.BAC_5);

			manon.setAdresse(new Adresse("21 avenue du Colonel Pierre Bourgoin", "", "33127", "MARTIGNAS SUR JALLE"));

			manon.setEvaluation(evalManon);

			manon.setFiliere(covid);

			manon = stagiaireDao.save(manon);

			System.out.println("____");

			IUEDao ueDao = Application.getInstance().getUeDao();

			UE ueAlgo = new UE(3326, 3, 2);

			ueAlgo.setSalle(wim);
			ueAlgo.setFiliere(covid);
			ueAlgo.setFormateur(eric);
			ueAlgo.setMatiere(java);

			ueAlgo = ueDao.save(ueAlgo);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		emf.close();

	}

}
