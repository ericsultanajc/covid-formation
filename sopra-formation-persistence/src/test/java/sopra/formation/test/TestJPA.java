package sopra.formation.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sopra.formation.Application;
import sopra.formation.model.Evaluation;

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
	}

}
