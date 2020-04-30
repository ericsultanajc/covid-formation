package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;
import sopra.formation.web.validator.EvaluationValidator;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired
	private IEvaluationRepository evaluationRepo;

	public EvaluationController() {
		super();
	}

	@GetMapping({ "", "/" })
	public String defaut() {
		return "forward:/evaluation/list";
	}

	// ETAPE 1 : Réception de la Request
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("page", "evaluation");
		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("mesEvaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "evaluation/list";
	}

	// ETAPE 1 : Réception de la Request
	@GetMapping("/add")
	public String add(Model model) {
		// ETAPE 2 et 3 : deux méthodes : on crée un stagiaire vide ou on place des "?"
		// sur les th:value
		model.addAttribute("page", "evaluation");
		// ETAPE 2 et 3 : 
		model.addAttribute("monEvaluation", new Evaluation());
		// ETAPE 4
		return "evaluation/form";
	}

	// ETAPE 1 : Réception de la Request
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {
		model.addAttribute("page", "evaluation");
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	@GetMapping("/editWithPathVariable/{idEval}")
	public String editWithPathVariable(@PathVariable("idEval") Long id, Model model) {
		model.addAttribute("page", "evaluation");
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	// Version 1 : anciennement ("/save") sans le binding --> modif du nom pour le binding
	@PostMapping("/saveFirst")
	public String saveFirst(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version, @RequestParam Integer comportemental,
			@RequestParam(required = false) Integer technique, @RequestParam(required = false) String commentaires) {

		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
		evaluation.setId(id);
		evaluation.setVersion(version);

		evaluationRepo.save(evaluation);

		return "redirect:list";
		// renvoie au navigateur l'ordre de renvoyer la liste --> redirection : deux
		// allers-retours
		// l'URL correspond réellement à l'appel qui est fais
		// on ne peut pas faire le forward car le save est en Post et le list est en
		// Get, deux solutions : utiliser le redirect ou bien utiliser la méthode
		// RequestMapping (value="",method"").
	}
	
	// Version 2 : avec le binding
	@PostMapping("/save")
	public String save(@ModelAttribute("monEvaluation") @Valid Evaluation evaluation, BindingResult result) {
		
		new EvaluationValidator().validate(evaluation, result);
		
		if(result.hasErrors()) {
			return "evaluation/form";
		}
		
		evaluationRepo.save(evaluation);
		
		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		evaluationRepo.deleteById(id);

		return "forward:list";
		// on reste côté serveur et on retourne dans le dispatcherServlet -->
		// GetMapping("/list")
		// un peu moins de transit --> plus performant car moins d'allers-retours
		// ne fonctionne que si les méthodes sont les mêmes! 
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:list";
	}
}
