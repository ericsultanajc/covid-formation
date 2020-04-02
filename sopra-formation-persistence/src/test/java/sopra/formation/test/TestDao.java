package sopra.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

public class TestDao {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new

		evaluation = Application.getInstance().getEvaluationDao().save(evaluation); // managed

		System.out.println(evaluation);

		evaluation.setComportemental(14); // detached

		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);

		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI); // new

		covid = Application.getInstance().getFiliereDao().save(covid); // managed

		System.out.println(covid);

		covid.setDuree(60); // detached

		covid = Application.getInstance().getFiliereDao().save(covid);

		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "e.sultan@ajc-ingenierie.fr", "0645104506", true,
				20); // new

		eric = Application.getInstance().getFormateurDao().save(eric); // managed

		System.out.println(eric);

		eric.setExperience(21); // detached

		eric = Application.getInstance().getFormateurDao().save(eric);
		
		Stagiaire kevin = new Stagiaire(Civilite.M, "BOUGIS", "Kévin", "kevin.bougis@gmail.com", "0625570704", sdf.parse("02/07/1990"), NiveauEtude.BAC_8);
		
		Adresse adrKevin = new Adresse("5bis avenue villemejan","Résidence Diderot - Appt 8","33600","PESSAC");

		kevin.setAdresse(adrKevin);
		
		kevin = Application.getInstance().getStagiaireDao().save(kevin);
		
		Matiere jpaHibernate = new Matiere("JPA HIBERNATE", 5);
		
		jpaHibernate = Application.getInstance().getMatiereDao().save(jpaHibernate);
		
		UE jpa = new UE(9371, 5, 7);
		
		jpa = Application.getInstance().getUeDao().save(jpa);

		eric.setAdresse("4 rue de Corono", null, "33160", "Saint-Médard-en-Jalles");
		
		eric = Application.getInstance().getFormateurDao().save(eric);
		
		Salle wim = new Salle("San Fransisco",14 ,true);

		Adresse adrWim = new Adresse("86 Avenue John Fitzgerald Kennedy", null, "33700", "Mérignac");
		wim.setAdr(adrWim);
		
		wim = Application.getInstance().getSalleDao().save(wim);
		
		Evaluation evalkevin = new Evaluation(15,12,"OK");
		
		evalkevin = Application.getInstance().getEvaluationDao().save(evalkevin); 
		
		kevin.setEvaluation(evalkevin);
		
		jpa.setFiliere(covid);
		jpa.setFormateur(eric);
		jpa.setMatiere(jpaHibernate);
		jpa.setSalle(wim);
		
		kevin.setFiliere(covid);
		covid.setReferent(eric);
		eric.addCompetence(jpaHibernate);
		
		kevin = Application.getInstance().getStagiaireDao().save(kevin);
		eric = Application.getInstance().getFormateurDao().save(eric);
		covid = Application.getInstance().getFiliereDao().save(covid);
		jpa = Application.getInstance().getUeDao().save(jpa);




	}
}
