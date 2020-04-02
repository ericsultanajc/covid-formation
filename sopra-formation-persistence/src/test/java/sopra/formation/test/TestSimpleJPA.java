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

public class TestSimpleJPA {
	
	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			em = Application.getInstance().getEmf().createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();
			
			Stagiaire kevin = new Stagiaire(Civilite.M, "BOUGIS", "Kévin", "kevin.bougis@gmail.com", "0625570704", sdf.parse("02/07/1990"), NiveauEtude.BAC_8);
	
			Adresse adrKevin = new Adresse("5bis avenue villemejan","Résidence Diderot - Appt 8","33600","PESSAC");
	
			kevin.setAdresse(adrKevin);
			
			Matiere jpaHibernate = new Matiere("JPA HIBERNATE", 5);
			
			UE jpa = new UE(9371, 5, 7);	

			Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "e.sultan@ajc-ingenierie.fr", "0645104506", true, 20);

			eric.setAdresse("4 rue de Corono", null, "33160", "Saint-Médard-en-Jalles");


			Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
			
			Salle wim = new Salle("San Fransisco",14 ,true);

			Adresse adrWim = new Adresse("86 Avenue John Fitzgerald Kennedy", null, "33700", "Mérignac");
			wim.setAdr(adrWim);
			
			Evaluation evalkevin = new Evaluation(15,12,"OK");
			kevin.setEvaluation(evalkevin);
			
			jpa.setFiliere(covid);
			jpa.setFormateur(eric);
			jpa.setMatiere(jpaHibernate);
			jpa.setSalle(wim);
			
			kevin.setFiliere(covid);
			covid.setReferent(eric);
			eric.addCompetence(jpaHibernate);
			
			em.persist(kevin);
			em.persist(eric);
			em.persist(jpaHibernate);
			em.persist(jpa);
			em.persist(wim);
			em.persist(covid);
			em.persist(evalkevin);
			

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
