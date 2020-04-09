package sopra.formation.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.formation.Application;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Salle;
import sopra.formation.persistence.ISalleDao;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

		EntityManager em = emf.createEntityManager(); // il est lié au PersistenceContext-->cache mémoire

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // état = new

		em.persist(evaluation); // état : managed --> un id lui est associé donc il change d'état

		Evaluation evalFind = em.find(Evaluation.class, evaluation.getId()); // état = managed

		// lister les salles utilisées par filière
		List<Salle> salles1 = new ArrayList<Salle>();
		ISalleDao salleDao = Application.getInstance().getSalleDao();
		salles1 = salleDao.findAllByVille("Mérignac");
		System.out.println(salles1);

		// lister les salles par ville
		List<Salle> salles2 = new ArrayList<Salle>();
		salles2 = salleDao.findAllByVille("Mérignac");
		System.out.println(salles2);

		em.close();
		emf.close();
	}

}
