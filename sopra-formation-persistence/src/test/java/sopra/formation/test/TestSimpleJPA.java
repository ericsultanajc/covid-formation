package sopra.formation.test;

import java.util.Date;

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

public class TestSimpleJPA {
	
	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		
		
		try {
			em = Application.getInstance().getEmf().createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();

			
			Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
			kevin.setCivilite(Civilite.M);
			kevin.setNom("BOUGIS");
			kevin.setPrenom("Kévin");
			kevin.setTelephone("0625570704");
			kevin.setDtNaissance(new Date());
			kevin.setNiveauEtude(NiveauEtude.BAC_8);
	
			Adresse adrKevin = new Adresse();
	
			adrKevin.setRue("5bis avenue villemejan");
			adrKevin.setComplement("Résidence Diderot - Appt 8");
			adrKevin.setCodePostal("33600");
			adrKevin.setVille("PESSAC");
	
			kevin.setAdresse(adrKevin);
			
			em.persist(kevin);
			
			Evaluation evaluation = new Evaluation(13,15,"sa va");
			
			em.persist(evaluation);
			
			kevin.setEvaluation(evaluation);
			em.persist(kevin);

			Filiere covid = new Filiere("covid", "2020", new Date(), 57, Dispositif.POEI);	
			
			em.persist(covid);
			
			kevin.setFiliere(covid);
			em.persist(kevin);			
			
			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
			eric.setCivilite(Civilite.M);
			eric.setNom("SULTAN");
			eric.setPrenom("Eric");
			eric.setTelephone("0645104506");
			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
			eric.setReferent(true);
			eric.setExperience(20);
			
			em.persist(eric);
			
			covid.setReferent(eric);
			
			em.persist(covid);
			
			Matiere angular = new Matiere("Angular", 6);		
			em.persist(angular);
			
			Matiere boot = new Matiere("Spring Boot", 2);
			em.persist(boot);
			
			eric.addCompetence(angular);
			
			eric.addCompetence(boot);
			em.persist(eric);
			
			Salle wim = new Salle("wim", 9, true);	
					
			Adresse adrWim = new Adresse();
			
			adrWim.setRue("582qzefsgrdth");
			adrWim.setComplement("");
			adrWim.setCodePostal("33600");
			adrWim.setVille("PESSAC");
	
			wim.setAdr(adrWim);
			
			em.persist(wim);
			
			UE java = new UE(3452, 12, 98);
			
			em.persist(java);
			
			java.setFiliere(covid);
			java.setFormateur(eric);
			java.setMatiere(boot);
			java.setMatiere(angular);
			java.setSalle(wim);
			
			em.persist(java);
			
			
			
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