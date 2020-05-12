package sopra.formation.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Views;
import sopra.formation.persistence.IEvaluationRepository;
import sopra.formation.web.exception.EvaluationValidationException;

// 5 request et lien avec stagiaire dans Stagiaire.java 
@RestController
public class EvaluationRestController {

	@Autowired
	private IEvaluationRepository evaluationRepo;

	// GET --> findAll
	@GetMapping("/evaluation")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAll() {
		return evaluationRepo.findAll();
	}

	// Request 1 : find by technique --> dans IEvaluation
	@GetMapping("/evaluation/by-technique/{technique}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllByTechnique(@PathVariable Integer technique) {
		return evaluationRepo.findByTechnique(technique);
	}

	// Request 2 : find by paramètres --> dans IEvaluation
	@GetMapping("/evaluation/by-gt-comp-and-technique/{comp}:{technique}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findByComportementalGreaterThanAndTechniqueGreaterThan(
			@PathVariable("comp") Integer comportemental, @PathVariable Integer technique) {
		return evaluationRepo.findByComportementalGreaterThanAndTechniqueGreaterThan(comportemental, technique);
	}

	// Request 3 : find by niveauEtude dans IEvaluation
	@GetMapping("/evaluation/by-niveau/{niveau}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllByStagiaireNiveau(@PathVariable NiveauEtude niveauEtude) {
		return evaluationRepo.findAllByStagiaireNiveau(niveauEtude);
	}

	// Request 4 : --> dans IEvaluation
	@GetMapping("/evaluation/orphans")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllOrphan() {
		return evaluationRepo.findAllOrphan();
	}

	// Request 5 : --> dans IEvaluation
	@GetMapping("/evaluation/orphans/{stagiaireId}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllOrphanAndCurrentStagiaire(@PathVariable Long stagiaireId) {
		return evaluationRepo.findAllOrphanAndCurrentStagiaire(stagiaireId);
	}

	// GET --> find
	@GetMapping("/evaluation/{id}")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation find(@PathVariable Long id) {

		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		if (optEvaluation.isPresent()) {
			return optEvaluation.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// POST --> create
	@PostMapping("/evaluation")
	public Evaluation create(@Valid @RequestBody Evaluation evaluation, BindingResult result) {
		if (result.hasErrors()) {
			throw new EvaluationValidationException();
		}

		evaluation = evaluationRepo.save(evaluation);

		return evaluation;
	}

	// PUT --> update
	@PutMapping("/evaluation/{id}")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation update(@RequestBody Evaluation evaluation, @PathVariable Long id) {
		if (!evaluationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		evaluation = evaluationRepo.save(evaluation);

		return evaluation;
	}

	// PATCH --> update partiel
	@PatchMapping("/evaluation/{id}")
	public Evaluation partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!evaluationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Evaluation evaluationFind = evaluationRepo.findById(id).get();

		if (updates.containsKey("comportemental")) {
			evaluationFind.setComportemental((Integer) updates.get("comportemental"));
		}
		if (updates.containsKey("technique")) {
			evaluationFind.setTechnique((Integer) updates.get("technique"));
		}
		if (updates.containsKey("commentaires")) {
			evaluationFind.setCommentaires((String) updates.get("commentaires"));
		}

		evaluationFind = evaluationRepo.save(evaluationFind);

		return evaluationFind;
	}

	// DELETE --> delete
	@DeleteMapping("/evaluation/{id}")
	public void delete(@PathVariable Long id) {
		evaluationRepo.deleteById(id);
	}
}
