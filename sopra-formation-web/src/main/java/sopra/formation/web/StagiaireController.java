package sopra.formation.web;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
	
	private IStagiaireRepository stagiaireRepo;
	private IEvaluationRepository evaluationRepo;
       
    public StagiaireController() {
        super();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) config.getServletContext()
				.getAttribute("spring");
		stagiaireRepo = context.getBean(IStagiaireRepository.class);
		evaluationRepo = context.getBean(IEvaluationRepository.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stagiaire> stagiaires = stagiaireRepo.findAll();
		request.setAttribute("mesStagiaires", stagiaires);
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/stagiaire/list.jsp").forward(request,
				response);
	
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Evaluation> evaluations = evaluationRepo.findAll();
		request.setAttribute("mesEvaluations", evaluations);
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/stagiaire/form.jsp").forward(request,
				response);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Evaluation> evaluations = evaluationRepo.findAll();
		request.setAttribute("mesEvaluations", evaluations);
		Long id = Long.valueOf(request.getParameter("id"));
		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);
		request.setAttribute("monStagiaire", optStagiaire.get());
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/stagiaire/form.jsp").forward(request,
				response);
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = (request.getParameter("id") != null && request.getParameter("id").length() > 0)
				? Long.valueOf(request.getParameter("id"))
				: null;
		Integer version = (request.getParameter("version") != null && request.getParameter("version").length() > 0)
				? Integer.valueOf(request.getParameter("version"))
				: 0;
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Date dtNaissance = Date.valueOf(request.getParameter("dtNaissance"));
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");	
		String complement = request.getParameter("complement");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		NiveauEtude niveauEtude = NiveauEtude.valueOf(request.getParameter("niveauEtude"));
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(Long.valueOf(request.getParameter("evaluation")));
		Evaluation evaluation = optEvaluation.get();
		
		Adresse adrStagiaire = new Adresse(rue,complement,codePostal,ville);
		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(adrStagiaire);
		stagiaire.setId(id);
		stagiaire.setVersion(version);
		stagiaire.setEvaluation(evaluation);
		
		stagiaireRepo.save(stagiaire);
		
		request.getServletContext().getRequestDispatcher("/stagiaire?mode=list").forward(request,
				response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		stagiaireRepo.deleteById(id);
		request.getServletContext().getRequestDispatcher("/stagiaire?mode=list").forward(request,
				response);
	}
	
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/stagiaire?mode=list").forward(request,
				response);
	}
}
