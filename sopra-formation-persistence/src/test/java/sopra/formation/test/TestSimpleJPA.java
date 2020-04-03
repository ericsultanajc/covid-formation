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

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// MATIERE
			System.out.println("MATIERE");

			Matiere angular = new Matiere("ANGULAR", 6);
			em.persist(angular);

			Matiere springboot = new Matiere("Spring boot", 3);
			em.persist(springboot);

			Matiere java = new Matiere("JAVA", 3);
			em.persist(java);

			Matiere comportemental = new Matiere("Comportemental", 4);
			em.persist(comportemental);
			System.out.println("###############################################################");

			// FORMATEUR VIA PERSONNE
			System.out.println("FORMATEUR");

			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
			eric.setCivilite(Civilite.M);
			eric.setNom("SULTAN");
			eric.setPrenom("Eric");
			eric.setTelephone("0645104506");
			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
			eric.setReferent(true);
			eric.setExperience(20);

			em.persist(eric);

			System.out.println("FORMATEUR");

			Formateur armand = new Formateur("a.khaida@ajc-ingenierie.fr");
			armand.setCivilite(Civilite.M);
			armand.setNom("KHAIDA");
			armand.setPrenom("Jonathan");
			armand.setTelephone("0011223344");
			armand.setAdresse("rue", "complement", "33000", "commune");
			armand.setReferent(false);
			armand.setExperience(10);

			em.persist(armand);
			System.out.println("###############################################################");

			// COMPETENCE ET LIEN ENTRE COMPETENCE/MATIERE/FORMATEUR
			System.out.println("COMPETENCE ET LIEN ENTRE COMPETENCE/MATIERE/FORMATEUR");

			eric.getCompetences().add(angular);
			eric.getCompetences().add(springboot);
			eric.getCompetences().add(java);

			Matiere servletJsp = new Matiere("Servlet/JSP", 2);
			eric.getCompetences().add(servletJsp);
			em.persist(servletJsp);

			armand.getCompetences().add(comportemental);
			System.out.println("###############################################################");

			// STAGIAIRE VIA PERSONNE
			System.out.println("STAGIAIRE VIA PERSONNE");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
			manon.setCivilite(Civilite.MME);
			manon.setNom("CHARLES");
			manon.setPrenom("Manon");
			manon.setTelephone("0635244332");
			manon.setDtNaissance(sdf.parse("06/08/1992"));
			manon.setNiveauEtude(NiveauEtude.BAC_5);
			manon.setAdresse("21 Av Colonel Pierre Bourgoin", "", "33127", "Martignas-sur-Jalle");

			em.persist(manon);

			Stagiaire kevin = new Stagiaire("kevin.bougis@gmail.com");
			kevin.setCivilite(Civilite.M);
			kevin.setNom("BOUGIS");
			kevin.setPrenom("Kevin");
			kevin.setTelephone("0625570704");
			kevin.setDtNaissance(sdf.parse("02/07/1990"));
			kevin.setNiveauEtude(NiveauEtude.BAC_8);
			kevin.setAdresse("5 bis avenue villemejan", "Résidence Diderot - Appt 8", "33600", "PESSAC");

			em.persist(kevin);

			manon.setAdresse("30 rue de Diane", "", "33210", "Pujols-sur-Ciron");
			em.persist(manon);

			System.out.println("###############################################################");

//			// EVALUATION
			System.out.println("EVALUATION");

			Evaluation eval1 = new Evaluation();
			eval1.setComportemental(15);
			eval1.setTechnique(10);
			eval1.setCommentaires("Attente d'une progression au niveau technique");

			em.persist(eval1);

			Evaluation eval2 = new Evaluation();
			eval2.setComportemental(16);
			eval2.setTechnique(15);
			eval2.setCommentaires("Super délégué!");

			em.persist(eval2);

			eval1.setTechnique(13);
			eval1.setCommentaires("Nette progresion!");
			em.persist(eval1);

			System.out.println("###############################################################");

			// LIEN ENTRE STAGIAIRE ET EVALUATION
			System.out.println("LIEN ENTRE STAGIAIRE ET EVALUATION");

			manon.setEvaluation(eval1);

			kevin.setEvaluation(eval2);

			System.out.println("###############################################################");

			// FILIERE
			System.out.println("FILIERE");

			Filiere sopraJava = new Filiere();
			sopraJava.setIntitule("JAVA SPRING ANGULAR");
			sopraJava.setPromotion("COVID");
			sopraJava.setDtDebut(sdf.parse("09/03/2020"));
			sopraJava.setDuree(57);
			sopraJava.setDispositif(Dispositif.POEI);

			em.persist(sopraJava);
			System.out.println("###############################################################");

			// LIEN ENTRE FILIERE ET FORMATEUR VIA REFERENT
			System.out.println("LIEN ENTRE FILIERE ET FORMATEUR VIA REFERENT");

			sopraJava.setReferent(eric);
			System.out.println("###############################################################");

			// LIEN ENTRE STAGIAIRE ET FILIERE
			System.out.println("LIEN ENTRE STAGIAIRE ET FILIERE");

			manon.setFiliere(sopraJava);

			kevin.setFiliere(sopraJava);
			System.out.println("###############################################################");

			// SALLE
			System.out.println("SALLE");

			Salle sanFransisco = new Salle();
			sanFransisco.setNom("San Fransisco");
			sanFransisco.setCapacite(15);
			sanFransisco.setVideoProjecteur(true);

			Adresse adrsanFransisco = new Adresse();
			adrsanFransisco.setRue("86 Avenue JFK");
			adrsanFransisco.setComplement("WIM");
			adrsanFransisco.setCodePostal("33700");
			adrsanFransisco.setVille("Mérignac");
			sanFransisco.setAdr(adrsanFransisco);

			em.persist(sanFransisco);

			System.out.println("###############################################################");

			// UE
			System.out.println("UE");

			UE unix = new UE();
			unix.setCode(4738);
			unix.setDuree(1);
			unix.setOrdre(1);
			unix.setFiliere(sopraJava);
			unix.setMatiere(null);
			unix.setFormateur(eric);
			unix.setSalle(sanFransisco);
			em.persist(unix);

			UE algo = new UE();
			algo.setCode(3326);
			algo.setDuree(3);
			algo.setOrdre(2);
			algo.setFiliere(sopraJava);
			algo.setMatiere(java);
			algo.setFormateur(eric);
			algo.setSalle(sanFransisco);
			em.persist(algo);

			UE javaObjet = new UE();
			javaObjet.setCode(3542);
			javaObjet.setDuree(3);
			javaObjet.setOrdre(4);
			javaObjet.setFiliere(sopraJava);
			javaObjet.setMatiere(java);
			javaObjet.setFormateur(eric);
			javaObjet.setSalle(sanFransisco);
			em.persist(javaObjet);

			UE javaAvance = new UE();
			javaAvance.setCode(2789);
			javaAvance.setDuree(4);
			javaAvance.setOrdre(6);
			javaAvance.setFiliere(sopraJava);
			javaAvance.setMatiere(java);
			javaAvance.setFormateur(eric);
			javaAvance.setSalle(sanFransisco);
			em.persist(javaAvance);

			UE springMvc = new UE();
			springMvc.setCode(7151);
			springMvc.setDuree(4);
			springMvc.setOrdre(14);
			springMvc.setFiliere(sopraJava);
			springMvc.setMatiere(springboot);
			springMvc.setFormateur(eric);
			springMvc.setSalle(sanFransisco);
			em.persist(springMvc);

			UE travailEnEquipe = new UE();
			travailEnEquipe.setCode(6451);
			travailEnEquipe.setDuree(1);
			travailEnEquipe.setOrdre(18);
			travailEnEquipe.setFiliere(sopraJava);
			travailEnEquipe.setMatiere(comportemental);
			travailEnEquipe.setFormateur(armand);
			travailEnEquipe.setSalle(sanFransisco);
			em.persist(travailEnEquipe);

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
