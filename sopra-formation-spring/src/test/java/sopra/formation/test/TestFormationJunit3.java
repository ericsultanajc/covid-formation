package sopra.formation.test;

import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;

public class TestFormationJunit3 extends TestCase {
	private ClassPathXmlApplicationContext context;
	
	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("application-context.xml");
	}

	protected void tearDown() throws Exception {
		context.close();
	}

	public void testEvaluation() {
		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);

		int startSize = evaluationDao.findAll().size();

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation = evaluationDao.save(evaluation);

		Evaluation evalutionFind = evaluationDao.find(evaluation.getId());

		Assert.assertEquals((Integer) 12, evalutionFind.getComportemental());
		Assert.assertEquals((Integer) 15, evalutionFind.getTechnique());
		Assert.assertEquals("Bonne évolution", evalutionFind.getCommentaires());

		evaluation.setComportemental(16);
		evaluation.setTechnique(13);
		evaluation.setCommentaires("Baisse de régime");

		evaluation = evaluationDao.save(evaluation);

		evalutionFind = evaluationDao.find(evaluation.getId());

		Assert.assertEquals((Integer) 16, evalutionFind.getComportemental());
		Assert.assertEquals((Integer) 13, evalutionFind.getTechnique());
		Assert.assertEquals("Baisse de régime", evalutionFind.getCommentaires());

		int testSize = evaluationDao.findAll().size();

		if ((testSize - startSize) != 1) {
			Assert.fail("FindAll size en erreur");
		}

		evaluationDao.delete(evaluation);

		evalutionFind = evaluationDao.find(evaluation.getId());

		Assert.assertNull(evalutionFind);

		int endSize = evaluationDao.findAll().size();

		Assert.assertEquals(0, (endSize - startSize));
	}

	public void testStagiaire() {
		Assert.fail("Not yet implemented");
	}

}
