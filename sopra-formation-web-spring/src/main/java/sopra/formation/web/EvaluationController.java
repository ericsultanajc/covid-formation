package sopra.formation.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping({"", "/"})
	public String defaut() {
		return "forward:/evaluation/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("mesEvaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "evaluation/list";
	}

	@GetMapping("/add")
	private String add() {
		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "evaluation/form";
	}

	@GetMapping("/edit")
	private String edit(@RequestParam Long id, Model model) {
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version, @RequestParam Integer comportemental,
			@RequestParam(required = false) Integer technique, @RequestParam(required = false) String commentaires) {

		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
		evaluation.setId(id);
		evaluation.setVersion(version);

		evaluationRepo.save(evaluation);

		return "redirect:list";
	}

	@GetMapping("/delete")
	private String delete(@RequestParam Long id) {
		evaluationRepo.deleteById(id);

		return "redirect:list";
	}
//
	@GetMapping("/cancel")
	private String cancel() {
		return "redirect:/evaluation/list";
	}
}
