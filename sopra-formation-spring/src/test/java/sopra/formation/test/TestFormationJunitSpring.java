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
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
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
		int startSize = stagiaireDao.findAll().size();

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");
		evaluation = evaluationDao.save(evaluation);
		
		Filiere filiere = new Filiere("Java Spring Angular", "Covid", sdf.parse("09/03/2020"), 5, Dispositif.POEI);
		filiere = filiereDao.save(filiere);
		
		Adresse adrDamien = new Adresse("5 impasse Granet", "aucun", "33610", "Canejan");
		
		Stagiaire damien = new Stagiaire(Civilite.M, "Dubreuil", "Damien", "dubreuil.damien@laposte.net", "0645872052",sdf.parse("03/09/1993"),NiveauEtude.BAC_5);
		damien.setAdresse(adrDamien);
		damien.setEvaluation(evaluation);
		damien.setFiliere(filiere);
		damien = stagiaireDao.save(damien);
		Stagiaire stagiaireFind = stagiaireDao.find(damien.getId());
		
		Assert.assertEquals(Civilite.M, stagiaireFind.getCivilite());
		Assert.assertEquals("Dubreuil", stagiaireFind.getNom());
		Assert.assertEquals("Damien", stagiaireFind.getPrenom());
		Assert.assertEquals("dubreuil.damien@laposte.net", stagiaireFind.getEmail());
		Assert.assertEquals("0645872052", stagiaireFind.getTelephone());
		Assert.assertEquals(sdf.parseObject("03/09/1993"), stagiaireFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_5, stagiaireFind.getNiveauEtude());
		Assert.assertEquals(adrDamien, stagiaireFind.getAdresse());
		Assert.assertNotNull(stagiaireFind.getEvaluation());
		Assert.assertEquals(evaluation.getId(), stagiaireFind.getEvaluation().getId());
		Assert.assertNotNull(stagiaireFind.getFiliere());
		Assert.assertEquals(filiere.getId(), stagiaireFind.getFiliere().getId());
		
		damien.setCivilite(Civilite.MLLE);
		damien.setNom("Hector");
		damien.setPrenom("Remi");
		damien.setEmail("niarf33@hotmail.fr");
		damien.setTelephone("0557835614");
		damien.setDtNaissance(sdf.parse("26/09/1993"));
		damien.setNiveauEtude(NiveauEtude.BAC_8);
		
		damien = stagiaireDao.save(damien);
		stagiaireFind = stagiaireDao.find(damien.getId());
		
		Assert.assertEquals(Civilite.MLLE, stagiaireFind.getCivilite());
		Assert.assertEquals("Hector", stagiaireFind.getNom());
		Assert.assertEquals("Remi", stagiaireFind.getPrenom());
		Assert.assertEquals("niarf33@hotmail.fr", stagiaireFind.getEmail());
		Assert.assertEquals("0557835614", stagiaireFind.getTelephone());
		Assert.assertEquals(sdf.parseObject("26/09/1993"), stagiaireFind.getDtNaissance());
		Assert.assertEquals(NiveauEtude.BAC_8, stagiaireFind.getNiveauEtude());
		Assert.assertEquals(adrDamien, stagiaireFind.getAdresse());
		Assert.assertEquals(evaluation.getId(), stagiaireFind.getEvaluation().getId());
		Assert.assertEquals(filiere.getId(), stagiaireFind.getFiliere().getId());

		int testSize = stagiaireDao.findAll().size();

		if ((testSize - startSize) != 1) {
			Assert.fail("FindAll size en erreur");
		}
		stagiaireDao.delete(damien);

		stagiaireFind = stagiaireDao.find(damien.getId());

		Assert.assertNull(stagiaireFind);

		int endSize = stagiaireDao.findAll().size();

		Assert.assertEquals(0, (endSize - startSize));
		
		List<Stagiaire> stagiairesFind = stagiaireDao.findAllByFormateur("SULTAN");
		int formateurSize = stagiairesFind.size();
		
		Assert.assertEquals(2, (formateurSize));

		stagiairesFind = stagiaireDao.findAllByVille("Canejan");
		int villeSize = stagiairesFind.size();
		Assert.assertEquals(4, (villeSize));
		
		evaluationDao.delete(evaluation);
		filiereDao.delete(filiere);


	}

}
