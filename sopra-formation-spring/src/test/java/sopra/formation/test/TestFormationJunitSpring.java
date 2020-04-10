package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IStagiaireDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
//@ContextConfiguration(classes = ApplicationConfig.class)
public class TestFormationJunitSpring {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private IEvaluationDao evaluationDao;
	
	@Autowired
	private IStagiaireDao stagiaireDao;
	
	@Autowired
	private IFiliereDao filiereDao;
	
	@Test
	public void evaluation() {
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
	public void stagiaire() throws ParseException {
		int startSize = stagiaireDao.findAll().size();

		Stagiaire stagiaire = new Stagiaire(Civilite.M, "DUCHMON", "Jean-Louis", "jl.duchmon@gmail.com", "0666333111", sdf.parse("12/08/1972"), NiveauEtude.BAC_8);
		
		stagiaire = stagiaireDao.save(stagiaire);
		
		Stagiaire stagiaireFind = stagiaireDao.find(stagiaire.getId());
		
		Assert.assertEquals(Civilite.M, stagiaireFind.getCivilite());
		Assert.assertEquals("DUCHMON", stagiaireFind.getNom());
		Assert.assertEquals("Jean-Louis", stagiaireFind.getPrenom());
		Assert.assertEquals( "jl.duchmon@gmail.com", stagiaireFind.getEmail());
		Assert.assertEquals( "0666333111", stagiaireFind.getTelephone());
		Assert.assertEquals( sdf.parse("12/08/1972"), stagiaireFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_8, stagiaireFind.getNiveauEtude());
		
				
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation = evaluationDao.save(evaluation);
		
		Filiere filiere = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);

		filiere = filiereDao.save(filiere);
		
		stagiaire.setFiliere(filiere);
		stagiaire.setEvaluation(evaluation);
		stagiaire.setAdresse("9 route de Pessac"," " , "33700", "Merignac");
		
		stagiaireFind = stagiaireDao.find(stagiaire.getId());
		
		Assert.assertEquals( filiere.getId(), stagiaireFind.getFiliere());
		Assert.assertEquals( evaluation.getId(), stagiaireFind.getEvaluation());
//		Assert.assertEquals("9 route de Pessac"," ", "33700", "Merignac", stagiaireFind.getAdresse());
		
		int testSize = stagiaireDao.findAll().size();
		
		Assert.assertEquals(1, (testSize - startSize));
		
		stagiaireDao.delete(stagiaire);

		stagiaireFind = stagiaireDao.find(stagiaire.getId());

		Assert.assertNull(stagiaireFind);
		
	}

}
