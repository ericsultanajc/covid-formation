package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.formation.Application;
import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Salle;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.UE;

public class TestJPADao {

	public static void main(String[] args) {
		testRequete();

	}
	
	public static void testRequete() {
		List<Salle> salles = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Salle> query = em.createQuery("select s from Salle s join s.ues u join u.filiere f where f.intitule = :intitule", Salle.class);
			query.setParameter("intitule", "Sopra COVID");
			
			salles = query.getResultList();
			System.out.println(salles);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		}
	
	public static void testComplet() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Evaluation evalDamien = new Evaluation();
		evalDamien.setComportemental(12);
		evalDamien.setTechnique(15);
		evalDamien.setCommentaires("Un peu chiant");
		evalDamien = Application.getInstance().getEvaluationDao().save(evalDamien);
		
		Evaluation evalCecile = new Evaluation();
		evalCecile.setComportemental(15);
		evalCecile.setTechnique(14);
		evalCecile.setCommentaires("Pense tous les jours à Quest pour tout le monde");
		evalCecile = Application.getInstance().getEvaluationDao().save(evalCecile);
		
		Stagiaire damien = new Stagiaire();
		damien.setCivilite(Civilite.M);
		try {
			damien.setDtNaissance(sdf.parse("03/09/1993"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		damien.setEmail("dubreuil.damien@laposte.net");
		damien.setEvaluation(evalDamien);
		damien.setNiveauEtude(NiveauEtude.BAC_5);
		damien.setNom("DUBREUIL");
		damien.setPrenom("Damien");
		damien.setTelephone("06.45.87.20.52");
		
		Adresse adresseDamien = new Adresse("5 impasse granet",null,"33610","Canejan");
		damien.setAdresse(adresseDamien);
		
		damien = Application.getInstance().getStagiaireDao().save(damien);

		Stagiaire cecile = new Stagiaire();
		cecile.setCivilite(Civilite.MLLE);
		try {
			cecile.setDtNaissance(sdf.parse("05/02/1994"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cecile.setEmail("larrouy.cecile@gmail.com");
		cecile.setEvaluation(evalCecile);
		cecile.setNiveauEtude(NiveauEtude.BAC_5);
		cecile.setNom("LARROUY");
		cecile.setPrenom("Cecile");
		cecile.setTelephone("06.88.41.22.31");
		
		Adresse adresseCecile = new Adresse("175 cours gambetta","App42","33400","Talence");
		cecile.setAdresse(adresseCecile);
		cecile = Application.getInstance().getStagiaireDao().save(cecile);

		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "eric.sultan@ajc-ingenierie.com", "06.45.10.45.06", true, 20);
		eric = Application.getInstance().getFormateurDao().save(eric);
		
		Filiere covid = new Filiere();
		covid.setDispositif(Dispositif.POEI);
		try {
			covid.setDtDebut(sdf.parse("09/03/2020"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		covid.setDuree(57);
		covid.setIntitule("JAVA SPRING ANGULAR");
		covid.setPromotion("Sopra COVID");
		covid.setReferent(eric);
		covid = Application.getInstance().getFiliereDao().save(covid);
		damien.setFiliere(covid);
		cecile.setFiliere(covid);

		damien = Application.getInstance().getStagiaireDao().save(damien);
		cecile = Application.getInstance().getStagiaireDao().save(cecile);
		
		Matiere java = new Matiere("java", 15);
		Matiere uml = new Matiere("uml",3);
		java = Application.getInstance().getMatiereDao().save(java);
		uml = Application.getInstance().getMatiereDao().save(uml);
		eric.addCompetence(java);
		eric.addCompetence(uml);
		eric = Application.getInstance().getFormateurDao().save(eric);

		Salle wim = new Salle("Work In Merignac", 20, true);
		Adresse adrWim = new Adresse("87 avenue JFK", null, "33700", "Merignac");
		wim.setAdr(adrWim);
		wim = Application.getInstance().getSalleDao().save(wim);

		UE java_algo = new UE(3370,3,1);
		UE java_avance = new UE (8870,3,2);
		UE sql = new UE(5597,3,3);
		java_algo.setFormateur(eric);
		java_algo.setFiliere(covid);
		java_algo.setMatiere(java);
		java_algo.setSalle(wim);
		java_avance.setFormateur(eric);
		java_avance.setFiliere(covid);
		java_avance.setMatiere(java);
		java_avance.setSalle(wim);
		sql.setFormateur(eric);
		sql.setFiliere(covid);
		sql.setMatiere(uml);
		sql.setSalle(wim);
		java_algo = Application.getInstance().getUeDao().save(java_algo);
		java_avance = Application.getInstance().getUeDao().save(java_avance);
		sql = Application.getInstance().getUeDao().save(sql);

	}

}
