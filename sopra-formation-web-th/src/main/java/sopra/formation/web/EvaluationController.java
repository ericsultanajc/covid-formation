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

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationRepository;

@WebServlet("/evaluation")
public class EvaluationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IEvaluationRepository evaluationRepo;

	public EvaluationController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) config.getServletContext()
				.getAttribute("spring");
		evaluationRepo = context.getBean(IEvaluationRepository.class);
	}

	// ETAPE 1 : Réception de la Request
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mode = request.getParameter("mode") != null ? request.getParameter("mode") : "list";

		if (mode.contentEquals("list")) {
			list(request, response);
		} else if (mode.contentEquals("add")) {
			add(request, response);
		} else if (mode.contentEquals("edit")) {
			edit(request, response);
		} else if (mode.contentEquals("save")) {
			save(request, response);
		} else if (mode.contentEquals("delete")) {
			delete(request, response);
		} else if (mode.contentEquals("cancel")) {
			cancel(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ETAPE 2 : Récuperation des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		request.setAttribute("mesEvaluations", evaluations);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/evaluation/list.jsp").forward(request,
				response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ETAPE 2 et 3 : non nécessaire ici
		
		// ETAPE 4
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/evaluation/form.jsp").forward(request,
				response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		
		// ETAPE 2
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);
		
		// ETAPE 3
		request.setAttribute("monEvaluation", optEvaluation.get());
		
		// ETAPE 4
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/evaluation/form.jsp").forward(request,
				response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = (request.getParameter("id") != null && request.getParameter("id").length() > 0)
				? Long.valueOf(request.getParameter("id"))
				: null;
		Integer version = (request.getParameter("version") != null && request.getParameter("version").length() > 0)
				? Integer.valueOf(request.getParameter("version"))
				: 0;
		Integer comportemental = Integer.valueOf(request.getParameter("comportemental"));
		Integer technique = Integer.valueOf(request.getParameter("technique"));
		String commentaires = request.getParameter("commentaires");
		
		Evaluation evaluation = new Evaluation(comportemental, technique, commentaires);
		evaluation.setId(id);
		evaluation.setVersion(version);
		
		evaluationRepo.save(evaluation);
		
		response.sendRedirect("evaluation");		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		
		evaluationRepo.deleteById(id);
		
		request.getServletContext().getRequestDispatcher("/evaluation?mode=list").forward(request,
				response);
	}
	
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list(request, response);
	}
}
