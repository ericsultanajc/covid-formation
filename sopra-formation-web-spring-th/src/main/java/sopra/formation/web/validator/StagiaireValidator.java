package sopra.formation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Stagiaire;

public class StagiaireValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Stagiaire.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stagiaire stagiaire = (Stagiaire) target;

		if (stagiaire.getAdresse().getRue() != "") {
			if (stagiaire.getAdresse().getCodePostal() == "" || stagiaire.getAdresse().getVille() == "") {
				errors.rejectValue("adresse.rue", "la rue", "renseigner cp et ville");
				System.out.println("On est dans l erreur ");
			}
		}
	}
}
