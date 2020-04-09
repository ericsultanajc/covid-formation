package sopra.formation.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;

public class TestSpringXML {

	public static void main(String[] args) {
		
//		try {
//			TestFormationDao.main(args);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation= evaluationDao.save(evaluation);
		
		Evaluation evalutionFind = evaluationDao.find(evaluation.getId());
		
		System.out.println(evalutionFind);
		
		context.close();
	}

}
