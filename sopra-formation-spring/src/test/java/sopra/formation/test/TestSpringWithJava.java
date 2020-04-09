package sopra.formation.test;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;

public class TestSpringWithJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		DataSource datasource = context.getBean(DataSource.class);

		System.out.println(datasource);

		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

		System.out.println(emf);

		context.close();
	}

}
