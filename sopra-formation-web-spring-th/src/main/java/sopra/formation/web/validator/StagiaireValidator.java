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

		if (stagiaire.getAdresse() != null) {
			if (!stagiaire.getAdresse().getRue().isEmpty() || !stagiaire.getAdresse().getCodePostal().isEmpty() || !stagiaire.getAdresse().getVille().isEmpty()) {
				if (stagiaire.getAdresse().getCodePostal().isEmpty()) {
					errors.rejectValue("adresse.codePostal", "codePostal",
							"Il faut également renseigner le code postal en plus de la ville et de la rue");
				}
				if(stagiaire.getAdresse().getVille().isEmpty()) {
					errors.rejectValue("adresse.ville", "ville",
							"Il faut également renseigner la ville en plus du code postal et de la rue");
				}
				if(stagiaire.getAdresse().getRue().isEmpty()) {
					errors.rejectValue("adresse.rue", "rue",
							"Il faut également renseigner la rue en plus du code postal et de la ville");				}
			}
		}
	}

}
