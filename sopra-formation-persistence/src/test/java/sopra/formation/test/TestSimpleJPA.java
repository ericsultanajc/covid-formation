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
			
			Evaluation evalKevin = new Evaluation(12, 15, "Bonne évolution");
			
			em.persist(evalKevin);
			
			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
			eric.setCivilite(Civilite.M);
			eric.setNom("SULTAN");
			eric.setPrenom("Eric");
			eric.setTelephone("0645104506");
			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
			eric.setReferent(true);
			eric.setExperience(20);
			
			em.persist(eric);
			
			Filiere covid = new Filiere("COVID");
			covid.setIntitule("JAVA SPRING ANGULAR");
			covid.setDtDebut(new Date());
			covid.setDuree(57);
			covid.setDispositif(Dispositif.POEI);
			covid.setReferent(eric);
			
			em.persist(covid);
			
			kevin.setFiliere(covid);
			
			kevin.setEvaluation(evalKevin);

			em.persist(kevin);
			
			
			
			Matiere javaObjet = new Matiere("JAVA OBJET", 3);
			Matiere uml = new Matiere("UML", 1);
			
			em.persist(javaObjet);
			em.persist(uml);
			
			eric.addCompetence(javaObjet);
			eric.addCompetence(uml);
			
			em.persist(eric);
			
			Salle wim = new Salle("WIM");
			wim.setCapacite(14);
			wim.setVideoProjecteur(true);
			wim.setAdr(new Adresse("86 avenue JFK", "1ère étage", "33700", "Mérignac"));
			
			em.persist(wim);
			
			UE ueUnix = new UE(4738, 1, 1);
			em.persist(ueUnix);
			ueUnix.setFiliere(covid);
			ueUnix.setFormateur(eric);
			ueUnix.setMatiere(javaObjet);
			ueUnix.setSalle(wim);
			
			em.persist(ueUnix);
			
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