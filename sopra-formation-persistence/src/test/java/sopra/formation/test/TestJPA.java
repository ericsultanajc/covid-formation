package sopra.formation.test;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.formation.Application;
import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;

public class TestJPA {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		Evaluation evaluation = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager(); // PersistenceContext
			tx = em.getTransaction();
			tx.begin();

			evaluation = new Evaluation(12, 15, "Bonne évolution"); // etat : new

			em.persist(evaluation); // etat : managed

			evaluation.setCommentaires("Très bonne évolution");

//			Evaluation evalFind = em.find(Evaluation.class, evaluation.getId()); // etat : managed

			tx.commit(); // em.flush();
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

		evaluation.setComportemental(18); // etat : detached

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Evaluation evaluationBis = em.merge(evaluation); // evaluationBis : managed - evaluation : detached

			evaluationBis.setTechnique(19);

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

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Evaluation evalFind = em.find(Evaluation.class, evaluation.getId()); // etat : managed

			em.remove(evalFind); // etat : removed

			em.flush();

			em.merge(evalFind);

			tx.commit(); // em.flush();
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

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e where e.comportemental = :noteC",
					Evaluation.class);

			query.setParameter("noteC", 12);

			List<Evaluation> evaluations = query.getResultList();

			for (Evaluation e : evaluations) {
				System.out.println(e);
			}

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

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Evaluation evalKevin = new Evaluation(13, 14, "En bonne voie");

			em.persist(evalKevin);

			Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
			kevin.setCivilite(Civilite.M);
			kevin.setNom("BOUGIS");
			kevin.setPrenom("Kévin");
			kevin.setTelephone("0625570704");
			kevin.setDtNaissance(sdf.parse("02/07/1990"));
			kevin.setNiveauEtude(NiveauEtude.BAC_8);

			Adresse adrKevin = new Adresse();

			adrKevin.setRue("5bis avenue villemejan");
			adrKevin.setComplement("Résidence Diderot - Appt 8");
			adrKevin.setCodePostal("33600");
			adrKevin.setVille("PESSAC");

			kevin.setAdresse(adrKevin);

			kevin.setEvaluation(evalKevin);

			em.persist(kevin); // managed

			Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);

			em.persist(covid);

			kevin.setFiliere(covid);

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

		List<Stagiaire> stagiaires = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s", Stagiaire.class);

			stagiaires = query.getResultList();

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

		for (Stagiaire stagiaire : stagiaires) {
			System.out.println(stagiaire.getEvaluation().toString());
		}

		Application.getInstance().getEmf().close();
	}

}
