package sopra.formation.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;

public class TestFormationJunit3 extends TestCase {

	public void testEvaluation() {
		System.out.println("testEvaluation");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);

		int startSize = evaluationDao.findAll().size();
		
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation = evaluationDao.save(evaluation);

		Evaluation evaluationFind = evaluationDao.find(evaluation.getId());
		
		Assert.assertEquals((Integer)16, evaluationFind.getComportemental());
		Assert.assertEquals((Integer)12, evaluationFind.getTechnique());
		Assert.assertEquals("Bonne évolution", evaluationFind.getCommentaires());
		
		evaluation.setComportemental(16);
		evaluation.setTechnique(13);
		evaluation.setCommentaires("Baisse de régime");
		
		evaluation = evaluationDao.save(evaluation);
		
		evaluationFind = evaluationDao.find(evaluation.getId());
		
		Assert.assertEquals((Integer)16, evaluationFind.getComportemental());
		Assert.assertEquals((Integer)13, evaluationFind.getTechnique());
		Assert.assertEquals("Baisse de régime", evaluationFind.getCommentaires());
		
		context.close();

	}

	public void testStagiaire() {
		System.out.println("testStagaire");	
}