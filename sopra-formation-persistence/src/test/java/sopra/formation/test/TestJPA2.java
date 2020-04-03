package sopra.formation.test;

import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

public class TestJPA2 {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Evaluation evalDamien = new Evaluation();
			evalDamien.setComportemental(12);
			evalDamien.setTechnique(15);
			evalDamien.setCommentaires("Un peu chiant");
			em.persist(evalDamien);
			
			Evaluation evalCecile = new Evaluation();
			evalCecile.setComportemental(15);
			evalCecile.setTechnique(14);
			evalCecile.setCommentaires("Pense tous les jours à Quest pour tout le monde");
			em.persist(evalCecile);

			Stagiaire damien = new Stagiaire();
			damien.setCivilite(Civilite.M);
			damien.setDtNaissance(sdf.parse("03/09/1993"));
			damien.setEmail("dubreuil.damien@laposte.net");
			damien.setEvaluation(evalDamien);
			damien.setNiveauEtude(NiveauEtude.BAC_5);
			damien.setNom("DUBREUIL");
			damien.setPrenom("Damien");
			damien.setTelephone("06.45.87.20.52");
			
			Adresse adresseDamien = new Adresse("5 impasse granet",null,"33610","Canejan");
			damien.setAdresse(adresseDamien);
			em.persist(damien);
			
			Stagiaire cecile = new Stagiaire();
			cecile.setCivilite(Civilite.MLLE);
			cecile.setDtNaissance(sdf.parse("05/02/1994"));
			cecile.setEmail("larrouy.cecile@gmail.com");
			cecile.setEvaluation(evalCecile);
			cecile.setNiveauEtude(NiveauEtude.BAC_5);
			cecile.setNom("LARROUY");
			cecile.setPrenom("Cecile");
			cecile.setTelephone("06.88.41.22.31");
			
			Adresse adresseCecile = new Adresse("175 cours gambetta","App42","33400","Talence");
			cecile.setAdresse(adresseCecile);
			em.persist(cecile);
						
			Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "eric.sultan@ajc-ingenierie.com", "06.45.10.45.06", true, 20);
			em.persist(eric);
			
			Filiere covid = new Filiere();
			covid.setDispositif(Dispositif.POEI);
			covid.setDtDebut(sdf.parse("09/03/2020"));
			covid.setDuree(57);
			covid.setIntitule("JAVA SPRING ANGULAR");
			covid.setPromotion("Sopra COVID");
			em.persist(covid);
			
			damien.setFiliere(covid);
			cecile.setFiliere(covid);
			covid.setReferent(eric);
			em.persist(covid);
			
			Matiere java = new Matiere("java", 15);
			em.persist(java);
			Matiere uml = new Matiere("uml",3);
			em.persist(uml);
			eric.addCompetence(java);
			eric.addCompetence(uml);
			em.persist(eric);
			
			Salle wim = new Salle("Work In Merignac", 20, true);
			Adresse adrWim = new Adresse("87 avenue JFK", null, "33700", "Merignac");
			wim.setAdr(adrWim);
			em.persist(wim);
			
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
			
			em.persist(java_avance);
			em.persist(java_algo);
			em.persist(sql);
			
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
}
