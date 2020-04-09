package sopra.formation.test;

import sopra.formation.Application;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;

public class TestJPQLLazy {

	public static void main(String[] args) {
		Filiere covid = Application.getInstance().getFiliereDao().findByPromotion("COVID");
		
		Formateur referent = Application.getInstance().getFormateurDao().findByFiliere(covid);
		
		System.out.println(referent.getNom()); 
		
		Filiere covidWithFetch = Application.getInstance().getFiliereDao().findWithReferent(covid.getId());
		
		System.out.println(covidWithFetch.getReferent().getNom());

	}

}
