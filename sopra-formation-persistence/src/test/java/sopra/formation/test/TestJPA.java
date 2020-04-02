package sopra.formation.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.formation.model.Evaluation;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

		EntityManager em = emf.createEntityManager(); // il est lié au PersistenceContext-->cache mémoire
		
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // état = new
		
		em.persist(evaluation); // état : managed --> un id lui est associé donc il change d'état
		
		Evaluation evalFind = em.find(Evaluation.class, evaluation.getId()); // état = managed 
		
		
		
		
		
		em.close();
		emf.close();
	}

}
