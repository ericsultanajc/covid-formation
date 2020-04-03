package sopra.formation.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import sopra.formation.model.UE;
import sopra.formation.model.Matiere;
import sopra.formation.model.Salle;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;
import sopra.formation.Application;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
//			Salle wim = new Salle("San Francisco");
//			wim.setCapacite(14);
//			wim.setVideoProjecteur(true);
//			wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));
//			
//			em.persist(wim);
			
			
//			Matiere java = new Matiere("JAVA", 12);
//			em.persist(java);
//			
//			Matiere web = new Matiere("WEB", 8);
//			em.persist(web);
//			
//						
//			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
//			eric.setCivilite(Civilite.M);
//			eric.setNom("SULTAN");
//			eric.setPrenom("Eric");
//			eric.setTelephone("0645104506");
//			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
//			eric.setReferent(true);
//			eric.setExperience(20);
//			
//			java.addFormateur(eric);
//				
//			em.persist(eric);
//			
//			
//			Filiere covid = new Filiere("COVID");
//			covid.setIntitule("JAVA SPRING ANGULAR");
//			covid.setDtDebut(new Date());
//			covid.setDuree(57);
//			covid.setDispositif(Dispositif.POEI);
//			covid.setReferent(eric);
//			
//			em.persist(covid);
//			
//			UE ueAlgo = new UE(3326, 3, 2);
//			ueAlgo.setFiliere(covid);
//			ueAlgo.setFormateur(eric);
//			ueAlgo.setMatiere(java);
//			ueAlgo.setSalle(wim);
//			
//			em.persist(ueAlgo);
//			
//			Evaluation evalKevin = new Evaluation(15, 13, "super délégué");
////						
//			Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
//			kevin.setCivilite(Civilite.M);
//			kevin.setNom("BOUGIS");
//			kevin.setPrenom("Kévin");
//			kevin.setTelephone("0625570704");
//			kevin.setDtNaissance(sdf.parse("02/02/1990"));
//			kevin.setNiveauEtude(NiveauEtude.BAC_8);
//
//			Adresse adrKevin = new Adresse();
//
//			adrKevin.setRue("5bis avenue villemejan");
//			adrKevin.setComplement("Résidence Diderot - Appt 8");
//			adrKevin.setCodePostal("33600");
//			adrKevin.setVille("PESSAC");
//
//			kevin.setAdresse(adrKevin);
//			
//			kevin.setEvaluation(evalKevin);
//			
//			kevin.setFiliere(covid);
//
//			em.persist(kevin);
//			
//			
//			
//			Evaluation evalManon = new Evaluation(16, 11, "difficultés passagères");
//			
//			Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
//			manon.setCivilite(Civilite.MME);
//			manon.setNom("CHARLES");
//			manon.setPrenom("Manon");
//			manon.setTelephone("0635244332");
//			((Stagiaire) manon).setDtNaissance(new Date());
//			((Stagiaire) manon).setNiveauEtude(NiveauEtude.BAC_5);
//			
//			manon.setAdresse(new Adresse("21 avenue du Colonel Pierre Bourgoin", "", "33127", "MARTIGNAS SUR JALLE"));
//			
//			manon.setEvaluation(evalManon);
//			
//			manon.setFiliere(covid);
//			
//			em.persist(manon);
//			

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

		emf.close();

	}

}
