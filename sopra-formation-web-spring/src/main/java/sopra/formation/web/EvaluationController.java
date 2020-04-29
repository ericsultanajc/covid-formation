package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEvaluationRepository evaluationRepo;

	public EvaluationController() {
		super();
	}

	// Méthode pour avoir la liste par défaut 
//	@GetMapping({ "", "/" })
//	public String defaullt() {
//		return "forward:/evaluation/list";
//	}

	// ETAPE 1 : Réception de la Request
	// Tableau avec {} pour entrer dans la méthode sans préciser le suffix:
	@GetMapping({ "", "/list" })
	public String list(Model model) {

		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("mesEvaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "evaluation/list";
	}

	@GetMapping("/add")
	public String add() {

		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "evaluation/form";
	}

	// Méthode 1 : paramètres:
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {

		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	// Méthode 2 : pathVariable : directement inclus dans l'url:
	@GetMapping("/editWithPathVariable/{idEval}")
	public String editWithPathVariable(@PathVariable("ideval") Long id, Model model) {

		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @RequestParam(required = false) Integer version,
			@RequestParam Integer comportemental, @RequestParam(required = false) Integer technique,
			@RequestParam(required = false) String commentaires) {

		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
		evaluation.setId(id);
		evaluation.setVersion(version != null ? version : 0);

		evaluationRepo.save(evaluation);

		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id, Model model) {

		evaluationRepo.deleteById(id);

		return "redirect:list";
		// ou "forward:list"
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}
}
