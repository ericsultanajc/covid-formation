package sopra.formation.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;

public class TestSpringXML {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		DataSource datasource = context.getBean(DataSource.class);

		System.out.println(datasource);

		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

		System.out.println(emf);
		
		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluationDao.save(evaluation);
		
		context.close();
	}

}
