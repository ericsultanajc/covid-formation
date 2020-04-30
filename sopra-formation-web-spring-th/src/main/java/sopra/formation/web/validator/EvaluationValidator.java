package sopra.formation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Evaluation;

public class EvaluationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Evaluation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Evaluation evaluation = (Evaluation) target;

		if (evaluation.getTechnique() != null) {
			if (evaluation.getTechnique() < 0 || evaluation.getTechnique() > 20) {
				errors.rejectValue("technique", "borne",
						"La note technique doit être comprise entre 0 et 20");
			}
		}
	}

}
