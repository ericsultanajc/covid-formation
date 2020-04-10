package sopra.formation.test;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;

public class TestSpringWithJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		DataSource datasource = context.getBean(DataSource.class);

		System.out.println(datasource);

		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

		System.out.println(emf);

		IEvaluationRepository evaluationDao = context.getBean(IEvaluationRepository.class);

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluationDao.save(evaluation);

		context.close();
	}

}
