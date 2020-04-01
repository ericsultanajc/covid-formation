package sopra.formation.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

		
		
		
		
		
		emf.close();
	}

}
