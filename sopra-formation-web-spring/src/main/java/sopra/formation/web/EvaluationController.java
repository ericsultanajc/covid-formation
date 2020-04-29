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

	@RequestMapping("/list")
	public String list(Model model) {
		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("mesEvaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "evaluation/list";
	}

	@RequestMapping("/add")
	private String add() {
		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "evaluation/form";
	}

	@RequestMapping("/edit")
	private String edit(@RequestParam Long id, Model model) {
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monEvaluation", optEvaluation.get());

		// ETAPE 4
		return "evaluation/form";
	}
//
//	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Long id = (request.getParameter("id") != null && request.getParameter("id").length() > 0)
//				? Long.valueOf(request.getParameter("id"))
//				: null;
//		Integer version = (request.getParameter("version") != null && request.getParameter("version").length() > 0)
//				? Integer.valueOf(request.getParameter("version"))
//				: 0;
//		Integer comportemental = Integer.valueOf(request.getParameter("comportemental"));
//		Integer technique = Integer.valueOf(request.getParameter("technique"));
//		String commentaires = request.getParameter("commentaires");
//
//		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
//		evaluation.setId(id);
//		evaluation.setVersion(version);
//
//		evaluationRepo.save(evaluation);
//
//		response.sendRedirect("evaluation");
//	}
//
	@RequestMapping("/delete")
	private String delete(@RequestParam Long id) {
		evaluationRepo.deleteById(id);

		return "redirect:/evaluation/list";
	}
//
	@RequestMapping("/cancel")
	private String cancel(Model model) {
		return "redirect:/evaluation/list";
	}
}
