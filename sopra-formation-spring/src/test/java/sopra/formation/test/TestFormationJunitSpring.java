package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.jpa.StagiaireDaoJpa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
//@ContextConfiguration(classes = ApplicationConfig.class)
public class TestFormationJunitSpring {

	@Autowired
	private IEvaluationDao evaluationDao;
	
	@Autowired
	private IStagiaireDao stagiaireDao;
	
	@Autowired
	private IFiliereDao filiereDao;
	
	@Autowired
	private IFormateurDao formateurDao;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
//	@Test
//	public void evaluation() {
//		int startSize = evaluationDao.findAll().size();
//
//		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");
//
//		evaluation = evaluationDao.save(evaluation);
//
//		Evaluation evalutionFind = evaluationDao.find(evaluation.getId());
//
//		Assert.assertEquals((Integer) 12, evalutionFind.getComportemental());
//		Assert.assertEquals((Integer) 15, evalutionFind.getTechnique());
//		Assert.assertEquals("Bonne évolution", evalutionFind.getCommentaires());
//
//		evaluation.setComportemental(16);
//		evaluation.setTechnique(13);
//		evaluation.setCommentaires("Baisse de régime");
//
//		evaluation = evaluationDao.save(evaluation);
//
//		evalutionFind = evaluationDao.find(evaluation.getId());
//
//		Assert.assertEquals((Integer) 16, evalutionFind.getComportemental());
//		Assert.assertEquals((Integer) 13, evalutionFind.getTechnique());
//		Assert.assertEquals("Baisse de régime", evalutionFind.getCommentaires());
//
//		int testSize = evaluationDao.findAll().size();
//
//		if ((testSize - startSize) != 1) {
//			Assert.fail("FindAll size en erreur");
//		}
//
//		evaluationDao.delete(evaluation);
//
//		evalutionFind = evaluationDao.find(evaluation.getId());
//
//		Assert.assertNull(evalutionFind);
//
//		int endSize = evaluationDao.findAll().size();
//
//		Assert.assertEquals(0, (endSize - startSize));
//	}

		
	@Test
	public void stagiaire() throws ParseException {
		int startSize = stagiaireDao.findAll().size();

		Stagiaire cecile = new Stagiaire(Civilite.MLLE, "LARROUY", "Cécile", "cecile.larrouy@outlook.fr", "0608050400", sdf.parse("23/04/1994"), NiveauEtude.BAC_5);
		cecile = stagiaireDao.save(cecile);
		
		cecile.setAdresse(new Adresse("93 Boulevard George V", "Résidence Zola", "33400", "Talence"));
		cecile = stagiaireDao.save(cecile);
		
		Evaluation evalCecile = new Evaluation(14, 17, "RAS");
		evalCecile = evaluationDao.save(evalCecile);
		cecile.setEvaluation(evalCecile);
		cecile = stagiaireDao.save(cecile);
		
		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		covid = filiereDao.save(covid);
		cecile.setFiliere(covid);
		cecile = stagiaireDao.save(cecile);
		
		Stagiaire stagiaireFind = stagiaireDao.find(cecile.getId());

		Assert.assertEquals("cecile.larrouy@outlook.fr", stagiaireFind.getEmail());
		Assert.assertEquals(Civilite.MLLE, stagiaireFind.getCivilite());
		Assert.assertEquals("LARROUY", stagiaireFind.getNom());
		Assert.assertEquals("Cécile", stagiaireFind.getPrenom());
		Assert.assertEquals("0608050400", stagiaireFind.getTelephone());
		Assert.assertEquals(sdf.parse("23/04/1994"), stagiaireFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_5, stagiaireFind.getNiveauEtude());
//		Assert.assertEquals(new Adresse("93 Boulevard George V", "Résidence Zola", "33400", "Talence"), stagiaireFind.getAdresse());
//		Assert.assertNotNull(stagiaireFind.getEvaluation()); 
		Assert.assertEquals("covid", stagiaireFind.getFiliere());
//		Assert.assertEquals("(Integer) 14, (Integer) 17, \"RAS\"", stagiaireFind.getEvaluation());
//		
//		
		cecile.setEmail("cecile.larrouy@live.fr");
		cecile = stagiaireDao.save(cecile);
		
		stagiaireFind = stagiaireDao.find(cecile.getId());
		
		Assert.assertEquals("cecile.larrouy@live.fr", stagiaireFind.getEmail());
		
		int testSize = stagiaireDao.findAll().size(); 
		
		if ((testSize - startSize) != 1) {
			Assert.fail("FindAll size en erreur");
		}
		
		stagiaireDao.delete(cecile);
		
		stagiaireFind = stagiaireDao.find(cecile.getId());

		Assert.assertNull(stagiaireFind);

		int endSize = stagiaireDao.findAll().size();

		Assert.assertEquals(0, (endSize - startSize));
		
		List<Stagiaire> stagiaireFindByVille = stagiaireDao.findAllByVille("Talence"); 
		System.out.println(stagiaireFindByVille);
		
		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "e.sultan@ajc-ingenierie.fr", "0645104506", true, 20);
		eric = formateurDao.save(eric);
		
		covid.setReferent(eric);
		filiereDao.save(covid); 
		
		List<Stagiaire> stagiaireFindByFormateur = stagiaireDao.findAllByFormateur("eric"); 
		System.out.println(stagiaireFindByFormateur);
	}

}
