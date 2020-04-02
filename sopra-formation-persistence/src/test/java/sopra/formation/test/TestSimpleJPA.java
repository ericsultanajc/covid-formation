package sopra.formation.test;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			em = Application.getInstance().getEmf().createEntityManager(); 
			tx = em.getTransaction();
			tx.begin();

			//EVALUTAION
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
			
			//STAGIAIRES
			
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
			kevin.setEvaluation(eval1);
			
			em.persist(kevin);
		
			
			Stagiaire manon = new Stagiaire("charles.manon@yahoo.com");
			 manon.setCivilite(Civilite.MME);
			 manon.setNom("CHARLES");
			 manon.setPrenom("Manon");
			 manon.setTelephone("0635244332");
			 manon.setDtNaissance(sdf.parse("06/08/1992"));
			 manon.setNiveauEtude(NiveauEtude.BAC_5);
			 manon.setAdresse("21 Av Colonel Pierre Bourgoin", "", "33127", "Martignas-sur-Jalle");
			 
			manon.setEvaluation(eval2);
			 
			em.persist(manon);
			
			//FORMATEURS
			 
			Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
			eric.setCivilite(Civilite.M);
			eric.setNom("SULTAN");
			eric.setPrenom("Eric");
			eric.setTelephone("0645104506");
			eric.setAdresse("4 rue de Corono", "", "33160", "Saint-Médard-en-Jalles");
			eric.setReferent(true);
			eric.setExperience(20);
			
			em.persist(eric);
			
			Formateur armand = new Formateur("a.khaida@ajc-ingenierie.fr");
			armand.setCivilite(Civilite.M);
			armand.setNom("KHAIDA");
			armand.setPrenom("Jonathan");
			armand.setTelephone("0011223344");
			armand.setAdresse("rue", "complement", "33000", "commune");
			armand.setReferent(false);
			armand.setExperience(10);
			
			em.persist(armand);
			
			//MATIERES
			
			Matiere angular = new Matiere("ANGULAR", 6);
			em.persist(angular);
			
			Matiere springboot = new Matiere("Spring boot", 3);
			em.persist(springboot);
			
			Matiere java = new Matiere("JAVA", 3);
			em.persist(java);
			
			Matiere comportemental = new Matiere("Comportemental", 4);
			em.persist(comportemental);
			
			//LIEN MATIERES FORMATEURS
			
			eric.getCompetences().add(angular);
			eric.getCompetences().add(springboot);
			eric.getCompetences().add(java);
			
			armand.getCompetences().add(comportemental);
			
			//FILIERE
			
			Filiere sopraJava = new Filiere();
			 sopraJava.setIntitule("JAVA SPRING ANGULAR");
			 sopraJava.setPromotion("COVID");
			 sopraJava.setDtDebut(sdf.parse("09/03/2020"));
			 sopraJava.setDuree(57);
			 sopraJava.setDispositif(Dispositif.POEI);
			em.persist(sopraJava);
			
			//LIEN FILIERE / FORMATEUR
			
			sopraJava.setReferent(eric);
			
			//LIEN STAGIAIRE FILIERE
			
			kevin.setFiliere(sopraJava);
			manon.setFiliere(sopraJava);
			
			//SALLE
			
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
			 
			 //UE
			 
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
