package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;

@Controller
@RequestMapping("evaluation")
public class EvaluationController {
	@Autowired
	private IEvaluationRepository evaluationRepo;

	public EvaluationController() {
		super();
	}

	@RequestMapping({"","/list"})
	public String list(Model model) {
		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("evaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "evaluation/list";
	}

	@RequestMapping("/add")
	public String add() {
		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "evaluation/form";
	}

	@RequestMapping("/edit")
	public String edit(Model model, @RequestParam(value = "id") Long id) {
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	@RequestMapping("/save")
	public String save(@RequestParam(value = "id") Long id, @RequestParam(value = "version") Integer version,
			@RequestParam(value = "comportemental") Integer comportemental, @RequestParam(value = "technique") Integer technique,
			@RequestParam(value = "commentaires") String commentaires, Model model) {
		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
		evaluation.setId(id);
		if (version != null) {
			evaluation.setVersion(version);
		} else {
			evaluation.setVersion(0);
		}

		evaluationRepo.save(evaluation);

		return "forward:list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id") Long id, Model model) {
		evaluationRepo.deleteById(id);
		return "forward:list";
	}

	@RequestMapping("/cancel")
	public String cancel(Model model) {
		return "forward:list";
	}
}
