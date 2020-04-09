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
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;

public class TestJPQL {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager(); // PersistenceContext
			tx = em.getTransaction();
			tx.begin();

			System.out.println("################## Query 1");

			TypedQuery<Evaluation> query1 = em.createQuery(
					"select e from Evaluation e where e.comportemental > :noteC and e.technique > :noteT",
					Evaluation.class);

			query1.setParameter("noteC", 12);
			query1.setParameter("noteT", 12);

			List<Evaluation> evaluations = query1.getResultList();

			for (Evaluation evaluation : evaluations) {
				System.out.println(evaluation);
			}

			System.out.println("################## Query 2");

			TypedQuery<Evaluation> query2 = em.createQuery(
					"select s.evaluation from Stagiaire s where s.niveauEtude = :niveau", Evaluation.class);

			query2.setParameter("niveau", NiveauEtude.BAC_8);

			for (Evaluation evaluation : query2.getResultList()) {
				System.out.println(evaluation);
			}

			System.out.println("################## Query 3");

			TypedQuery<Stagiaire> query3 = em
					.createQuery("select s from Stagiaire s where s.evaluation.technique >:noteT", Stagiaire.class);

			query3.setParameter("noteT", 15);

			for (Stagiaire stagiaire : query3.getResultList()) {
				System.out.println(stagiaire);
			}

			System.out.println("################## Query 4");

			TypedQuery<Stagiaire> query4 = em.createQuery(
					"select s from Stagiaire s join s.evaluation e where e.technique >:noteT", Stagiaire.class);

			query4.setParameter("noteT", 15);

			for (Stagiaire stagiaire : query4.getResultList()) {
				System.out.println(stagiaire);
			}

			System.out.println("################## Query 5");

			TypedQuery<Filiere> query5 = em.createQuery(
					"select f from Filiere f join f.stagiaires s where s.niveauEtude = :niveau", Filiere.class);

			query5.setParameter("niveau", NiveauEtude.BAC_8);

			for (Filiere filiere : query5.getResultList()) {
				System.out.println(filiere);
			}

			System.out.println("################## Query 6");

			TypedQuery<Filiere> query6 = em
					.createQuery("select s.filiere from Stagiaire s where s.niveauEtude = :niveau", Filiere.class);

			query6.setParameter("niveau", NiveauEtude.BAC_8);

			for (Filiere filiere : query6.getResultList()) {
				System.out.println(filiere);
			}

			System.out.println("################## Query 7");

			TypedQuery<Stagiaire> query7 = em.createQuery(
					"select s from Stagiaire s join s.filiere f join f.referent ref where ref.nom = :nom",
					Stagiaire.class);

			query7.setParameter("nom", "SULTAN");

			for (Stagiaire stagiaire : query7.getResultList()) {
				System.out.println(stagiaire);
			}

			System.out.println("################## Query 8");

			TypedQuery<Stagiaire> query8 = em
					.createQuery("select s from Stagiaire s where s.filiere.referent.nom = :nom", Stagiaire.class);

			query8.setParameter("nom", "SULTAN");

			for (Stagiaire stagiaire : query8.getResultList()) {
				System.out.println(stagiaire);
			}

			System.out.println("################## Query 9");

			TypedQuery<String> query9 = em
					.createQuery("select s.nom from Stagiaire s where s.filiere.referent.nom = :nom", String.class);

			query9.setParameter("nom", "SULTAN");

			for (String nom : query9.getResultList()) {
				System.out.println(nom);
			}

			System.out.println("################## Query 10");

			TypedQuery<Object[]> query10 = em.createQuery(
					"select s.nom, s.filiere.intitule, s.filiere.duree, s.filiere.referent.prenom from Stagiaire s where s.filiere.referent.nom = :nom",
					Object[].class);

			query10.setParameter("nom", "SULTAN");

			for (Object[] ligne : query10.getResultList()) {
				System.out.println(ligne[0]);
				System.out.println(ligne[1]);
				System.out.println(ligne[2]);
				System.out.println(ligne[3]);
			}

			System.out.println("################## Query 11");

			TypedQuery<Personne> query11 = em.createQuery("select p from Personne p", Personne.class);

			for (Personne personne : query11.getResultList()) {
				System.out.println(personne);
			}

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

		Application.getInstance().getEmf().close();
	}

}
