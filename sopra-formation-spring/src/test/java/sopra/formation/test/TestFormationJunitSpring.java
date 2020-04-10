package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
//@ContextConfiguration(classes = ApplicationConfig.class)
public class TestFormationJunitSpring {

	@Autowired
	private IEvaluationDao evaluationDao;

	@Autowired
	private IFiliereDao filiereDao;

	@Autowired
	private IFormateurDao formateurDao;

	@Autowired
	private IStagiaireDao stagiaireDao;

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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setNom("SULTAN");

		eric = formateurDao.save(eric);

		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		covid.setReferent(eric);
		covid = filiereDao.save(covid);
		Filiere elysee = new Filiere("JAVA SPRING ANGULAR", "Elysée", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		elysee.setReferent(eric);
		elysee = filiereDao.save(elysee);

		Evaluation evalCecile = new Evaluation(14, 17, "RAS");
		evalCecile = evaluationDao.save(evalCecile);
		Evaluation evalCecilia = new Evaluation(18, 19, "Au top");
		evalCecilia = evaluationDao.save(evalCecilia);

		int startSize = stagiaireDao.findAll().size();
		int startSizeByVille = stagiaireDao.findAllByVille("Paris").size();
		int startSizeByFormateur = stagiaireDao.findAllByFormateur("SULTAN").size();
		
		Stagiaire cecile = new Stagiaire();
		cecile.setEmail("cecile.larrouy@outlook.fr");
		cecile.setCivilite(Civilite.MLLE);
		cecile.setNom("LARROUY");
		cecile.setPrenom("Cécile");
		cecile.setTelephone("0608050400");
		cecile.setDtNaissance(sdf.parse("23/04/1994"));
		cecile.setNiveauEtude(NiveauEtude.BAC_5);
		cecile.setAdresse(new Adresse("93 Boulevard Georges V", "Résidence Zola", "33400", "Talence"));
		cecile.setEvaluation(evalCecile);
		cecile.setFiliere(covid);

		cecile = stagiaireDao.save(cecile);

		Stagiaire cecileFind = stagiaireDao.find(cecile.getId());

		Assert.assertEquals(Civilite.MLLE, cecileFind.getCivilite());
		Assert.assertEquals("LARROUY", cecileFind.getNom());
		Assert.assertEquals("Cécile", cecileFind.getPrenom());
		Assert.assertEquals("cecile.larrouy@outlook.fr", cecileFind.getEmail());
		Assert.assertEquals("0608050400", cecileFind.getTelephone());
		Assert.assertEquals(sdf.parse("23/04/1994"), cecileFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_5, cecileFind.getNiveauEtude());
		Assert.assertEquals(new Adresse("93 Boulevard Georges V", "Résidence Zola", "33400", "Talence"),
				cecileFind.getAdresse());
		Assert.assertNotNull(cecileFind.getEvaluation());
		Assert.assertEquals(evalCecile.getId(), cecileFind.getEvaluation().getId());
		Assert.assertNotNull(cecileFind.getFiliere());
		Assert.assertEquals(covid.getId(), cecileFind.getFiliere().getId());

		cecile.setCivilite(Civilite.MME);
		cecile.setNom("SARKOZY");
		cecile.setPrenom("Cécilia");
		cecile.setEmail("cecilia.sarkory@outlook.fr");
		cecile.setTelephone("0606060606");
		cecile.setDtNaissance(sdf.parse("23/04/1954"));
		cecile.setNiveauEtude(NiveauEtude.BAC_8);
		cecile.setAdresse(new Adresse("55 Rue du Faubourg Saint-Honoré", "", "75008", "Paris"));
		cecile.setEvaluation(evalCecilia);
		cecile.setFiliere(elysee);

		cecile = stagiaireDao.save(cecile);

		cecileFind = stagiaireDao.find(cecile.getId());

		Assert.assertEquals(Civilite.MME, cecileFind.getCivilite());
		Assert.assertEquals("SARKOZY", cecileFind.getNom());
		Assert.assertEquals("Cécilia", cecileFind.getPrenom());
		Assert.assertEquals("cecilia.sarkory@outlook.fr", cecileFind.getEmail());
		Assert.assertEquals("0606060606", cecileFind.getTelephone());
		Assert.assertEquals(sdf.parse("23/04/1954"), cecileFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_8, cecileFind.getNiveauEtude());
		Assert.assertNotNull(cecileFind.getAdresse());
		Assert.assertEquals("55 Rue du Faubourg Saint-Honoré", cecileFind.getAdresse().getRue());
		Assert.assertEquals("", cecileFind.getAdresse().getComplement());
		Assert.assertEquals("75008", cecileFind.getAdresse().getCodePostal());
		Assert.assertEquals("Paris", cecileFind.getAdresse().getVille());
		Assert.assertNotNull(cecileFind.getEvaluation());
		Assert.assertEquals(evalCecilia.getId(), cecileFind.getEvaluation().getId());
		Assert.assertNotNull(cecileFind.getFiliere());
		Assert.assertEquals(elysee.getId(), cecileFind.getFiliere().getId());

		int testSize = stagiaireDao.findAll().size();

		if ((testSize - startSize) != 1) {
			Assert.fail("FindAll size en erreur");
		}

		int testSizeByVille = stagiaireDao.findAllByVille("Paris").size();

		Assert.assertEquals(1, testSizeByVille - startSizeByVille);
		
		int testSizeByFormateur = stagiaireDao.findAllByFormateur("SULTAN").size();

		Assert.assertEquals("Erreur dans la requête findAllByFormateur", 1, testSizeByFormateur - startSizeByFormateur);
		
		stagiaireDao.delete(cecile);

		cecileFind = stagiaireDao.find(cecile.getId());

		Assert.assertNull(cecileFind);

		evaluationDao.delete(evalCecilia);
		evaluationDao.delete(evalCecile);

		filiereDao.delete(elysee);
		filiereDao.delete(covid);
	}

}
