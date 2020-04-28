package sopra.formation.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationRepository;
import sopra.formation.persistence.IStagiaireRepository;

@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IEvaluationRepository evaluationRepo;
	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) getServletContext()
				.getAttribute("spring");
		evaluationRepo = context.getBean(IEvaluationRepository.class);
		stagiaireRepo = context.getBean(IStagiaireRepository.class);
	}

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
		request.setCharacterEncoding("UTF-8"); // régler le problème des données du formulaire mal traités par Tomcat
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("stagiaires", stagiaireRepo.findAll());

		request.getRequestDispatcher("/WEB-INF/views/stagiaire/list.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("civilites", Civilite.values());
		request.setAttribute("niveauEtudes", NiveauEtude.values());
		request.setAttribute("evaluations", evaluationRepo.findAllOrphan());

		request.getRequestDispatcher("/WEB-INF/views/stagiaire/form.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		request.setAttribute("civilites", Civilite.values());
		request.setAttribute("niveauEtudes", NiveauEtude.values());
		request.setAttribute("evaluations", evaluationRepo.findAllOrphanAndCurrentStagiaire(id));
		request.setAttribute("stagiaire", stagiaireRepo.findById(id).get());

		request.getRequestDispatcher("/WEB-INF/views/stagiaire/form.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Long id = (request.getParameter("id") != null && request.getParameter("id").length() > 0)
				? Long.valueOf(request.getParameter("id"))
				: null;
		Integer version = (request.getParameter("version") != null && request.getParameter("version").length() > 0)
				? Integer.valueOf(request.getParameter("version"))
				: 0;
		Civilite civilite = (request.getParameter("civilite") != null && !request.getParameter("civilite").isEmpty()
				? Civilite.valueOf(request.getParameter("civilite"))
				: null);
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");

		String telephone = request.getParameter("telephone");
		Date dtNaissance = null;
		try {
			dtNaissance = request.getParameter("dtNaissance") != null
					&& request.getParameter("dtNaissance").length() == 10
							? sdf.parse(request.getParameter("dtNaissance"))
							: null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		NiveauEtude niveauEtude = (request.getParameter("niveauEtude") != null
				&& !request.getParameter("niveauEtude").isEmpty())
						? NiveauEtude.valueOf(request.getParameter("niveauEtude"))
						: null;

		String rue = request.getParameter("rue");
		String complement = request.getParameter("complement");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");

		Long evaluationId = (request.getParameter("evaluation") != null
				&& request.getParameter("evaluation").length() > 0) ? Long.valueOf(request.getParameter("evaluation"))
						: null;

		Evaluation evaluation = new Evaluation();
		evaluation.setId(evaluationId);

		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(new Adresse(rue, complement, codePostal, ville));
		stagiaire.setId(id);
		stagiaire.setVersion(version);

		stagiaire.setEvaluation(evaluation);

		stagiaireRepo.save(stagiaire);

		request.getRequestDispatcher("/stagiaire?mode=list").forward(request, response);
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("stagiaire?mode=list");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		stagiaireRepo.deleteById(id);

		request.getRequestDispatcher("/stagiaire?mode=list").forward(request, response);
	}
}
