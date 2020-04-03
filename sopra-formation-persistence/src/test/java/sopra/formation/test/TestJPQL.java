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

public class TestJPQL {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager(); // PersistenceContext
			tx = em.getTransaction();
			tx.begin();
			
//			System.out.println("################## Query 1");
//
//			TypedQuery<Object[]> query1 = em.createQuery("select s.nom, s.filiere.intitule, s.filiere.duree, s.filiere.referent.prenom from Stagiaire s where s.filiere.referent.nom = :nom",
//					Object[].class);
//
//			query1.setParameter("intitule", "JAVA SPRING ANGULAR");
//
//			for (Object[] ligne : query1.getResultList()) {
//				System.out.println(ligne[0]);
//				System.out.println(ligne[1]);
//				System.out.println(ligne[2]);
//				System.out.println(ligne[3]);
//			}
			
			System.out.println("################## Query 2");

			TypedQuery<String> query2 = em.createQuery("select s.nom from Salle s where s.adr.ville = :ville",
					String.class);

			query2.setParameter("ville", "Mérignac");

			for (String nom : query2.getResultList()) {
				System.out.println(nom);
			}
			
			System.out.println("################## Query 3");

			TypedQuery<String> query3 = em.createQuery("select f.nom from Formateur f where f.email = :email",
					String.class);

			query3.setParameter("email", "e.sultan@ajc-ingenierie.fr");

			for (String nom : query3.getResultList()) {
				System.out.println(nom);
			}
			
			System.out.println("################## Query 4");
			
			TypedQuery<Object[]> query4 = em.createQuery(
					"select f.intitule from Filiere f where f.ue.salle.adr.ville = :ville",
					Object[].class);

			query4.setParameter("ville", "Mérignac");
			
			for (Object[] ligne : query4.getResultList()) {
				System.out.println(ligne[0]);
				System.out.println(ligne[1]);
				System.out.println(ligne[2]);
				System.out.println(ligne[3]);
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
