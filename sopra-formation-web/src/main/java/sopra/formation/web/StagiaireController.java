package sopra.formation.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IStagiaireRepository;

@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) config.getServletContext()
				.getAttribute("spring");
		stagiaireRepo = context.getBean(IStagiaireRepository.class);
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
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ETAPE 2 : Récuperation des données
		List<Stagiaire> stagiaires = stagiaireRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		request.setAttribute("mesStagiaires", stagiaires);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/stagiaire/list.jsp").forward(request,
				response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/stagiaire/form.jsp").forward(request,
				response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		// ETAPE 2
		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		// ETAPE 3
		request.setAttribute("monStagiaire", optStagiaire.get());

		// ETAPE 4
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
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String complement = request.getParameter("complement");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String dtNaissance = request.getParameter("dtNaissance");
		String niveauEtude = request.getParameter("niveauEtude");

		Date dateDeNaissance =new Date();
		try {
			dateDeNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(dtNaissance);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		Adresse adresse = new Adresse(rue, complement, codePostal, ville);

		Stagiaire stagiaire = new Stagiaire();
		stagiaire.setId(id);
		stagiaire.setVersion(version);
		stagiaire.setAdresse(adresse);
		stagiaire.setNom(nom);
		stagiaire.setPrenom(prenom);
		stagiaire.setEmail(email);
		stagiaire.setTelephone(telephone);
		stagiaire.setCivilite(Civilite.valueOf(civilite));
		stagiaire.setDtNaissance(dateDeNaissance);
		stagiaire.setNiveauEtude(NiveauEtude.valueOf(niveauEtude));
		
		stagiaireRepo.save(stagiaire);

		response.sendRedirect("stagiaire");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		stagiaireRepo.deleteById(id);

		request.getServletContext().getRequestDispatcher("/stagiaire?mode=list").forward(request, response);
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list(request, response);
	}
}
