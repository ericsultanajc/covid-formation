package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//		int startSize = evaluationDao.findAll().size();

		Evaluation evalKevin = new Evaluation(12, 15, "Bonne évolution");
		evalKevin = evaluationDao.save(evalKevin);

		Adresse adrKevin = new Adresse();
		adrKevin.setRue("5bis avenue villemejan");
		adrKevin.setComplement("Résidence Diderot - Appt 8");
		adrKevin.setCodePostal("33600");
		adrKevin.setVille("PESSAC");

		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		covid = filiereDao.save(covid);

		Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
		kevin.setNom("BOUGIS");
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals("kevin.bougis@gmail.com", stagiaireDao.find(kevin.getId()).getEmail());

		kevin.setCivilite(Civilite.M);
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(Civilite.M, stagiaireDao.find(kevin.getId()).getCivilite());

		kevin.setNom("BOUGIS");
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals("BOUGIS", stagiaireDao.find(kevin.getId()).getNom());

		kevin.setPrenom("Kévin");
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals("Kévin", stagiaireDao.find(kevin.getId()).getPrenom());

		kevin.setTelephone("0625570704");
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals("0625570704", stagiaireDao.find(kevin.getId()).getTelephone());

		kevin.setDtNaissance(sdf.parse("02/07/1990"));
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(sdf.parse("02/07/1990"), stagiaireDao.find(kevin.getId()).getDtNaissance());

		kevin.setNiveauEtude(NiveauEtude.BAC_8);
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(NiveauEtude.BAC_8, stagiaireDao.find(kevin.getId()).getNiveauEtude());

		kevin.setAdresse(adrKevin);
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(adrKevin, stagiaireDao.find(kevin.getId()).getAdresse());

		kevin.setFiliere(covid);
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(covid.getId(), stagiaireDao.find(kevin.getId()).getFiliere().getId());

		kevin.setEvaluation(evalKevin);
		kevin = stagiaireDao.save(kevin);
		Assert.assertEquals(evalKevin.getId(), stagiaireDao.find(kevin.getId()).getEvaluation().getId());
		
		kevin = stagiaireDao.save(kevin);
		
		Stagiaire cecile = new Stagiaire(Civilite.MME, "LARROUY", "Cécile", "cecile.larrouy@outlook.fr", "0645454545", sdf.parse("09/03/2020"), NiveauEtude.BAC_5);
		cecile = stagiaireDao.save(cecile);
		Assert.assertEquals(NiveauEtude.BAC_5, stagiaireDao.find(cecile.getId()).getNiveauEtude());
		Assert.assertEquals(sdf.parse("09/03/2020"), stagiaireDao.find(cecile.getId()).getDtNaissance());
		Assert.assertEquals("Cécile", stagiaireDao.find(cecile.getId()).getPrenom());
		Assert.assertEquals("LARROUY", stagiaireDao.find(cecile.getId()).getNom());
		Assert.assertEquals("0645454545", stagiaireDao.find(cecile.getId()).getTelephone());
		Assert.assertEquals(Civilite.MME, stagiaireDao.find(cecile.getId()).getCivilite());
		Assert.assertEquals("cecile.larrouy@outlook.fr", stagiaireDao.find(cecile.getId()).getEmail());
		
		Long idCecile=cecile.getId();
//		System.out.println(cecile);
		Assert.assertEquals("Stagiaire [niveauEtude=BAC_5, evaluation=null, Personne [id="+idCecile+", civilite=MME, nom=LARROUY, prenom=Cécile, email=cecile.larrouy@outlook.fr, telephone=0645454545, adresse=null]]", stagiaireDao.find(cecile.getId()).toString());
	}		

}
