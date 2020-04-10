package sopra.formation.test;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;

public class TestSpringXML {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IEvaluationRepository evaluationDao = context.getBean(IEvaluationRepository.class);

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation= evaluationDao.save(evaluation);
		
		Optional<Evaluation> evalutionFind = evaluationDao.findById(evaluation.getId());
		
		System.out.println(evalutionFind.get());
		
		context.close();
	}

}
