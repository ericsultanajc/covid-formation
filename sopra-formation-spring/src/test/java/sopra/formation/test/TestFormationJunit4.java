package sopra.formation.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IStagiaireDao;

public class TestFormationJunit4 {

	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void start() throws Exception {
		context = new ClassPathXmlApplicationContext("application-context.xml");
	}

	@AfterClass
	public static void end() throws Exception {
		context.close();
	}

	@Before
	public void beforeTest() {
		System.out.println("Before");
	}

	@After
	public void afterTest() {
		System.out.println("After");
	}

	@Test
	public void evaluation() {
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

	@Test
	public void stagiaire() {
		IEvaluationDao evaluationDao = context.getBean(IEvaluationDao.class);
		IStagiaireDao stagiaireDao = context.getBean(IStagiaireDao.class);

		Assert.fail("Not yet implemented");
	}

}
