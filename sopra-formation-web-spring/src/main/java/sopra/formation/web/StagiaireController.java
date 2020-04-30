package sopra.formation.web;

<<<<<<< HEAD
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
=======
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> master
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< HEAD
=======
import sopra.formation.model.Adresse;
>>>>>>> master
import sopra.formation.model.Civilite;
import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
<<<<<<< HEAD
=======
import sopra.formation.persistence.IEvaluationRepository;
>>>>>>> master
import sopra.formation.persistence.IStagiaireRepository;

@Controller
@RequestMapping("/stagiaire")
<<<<<<< HEAD
public class StagiaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

=======
public class StagiaireController {
	@Autowired
	private IEvaluationRepository evaluationRepo;
>>>>>>> master
	@Autowired
	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}
<<<<<<< HEAD
	
	@GetMapping({"", "/"})
	public String defaut() {
		return "forward:/stagiaire/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		// ETAPE 2 : Récuperation des données
		List<Stagiaire> stagiaires = stagiaireRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("mesStagiaires", stagiaires);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
=======

	@GetMapping({ "", "/", "/list" })
	public String list(Model model) {
		model.addAttribute("stagiaires", stagiaireRepo.findAll());

>>>>>>> master
		return "stagiaire/list";
	}

	@GetMapping("/add")
<<<<<<< HEAD
	private String add() {
		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "stagiaire/form";
	}


	@GetMapping("/edit")
	private String edit(@RequestParam Long id, Model model) {
		// ETAPE 2
		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		// ETAPE 3
		model.addAttribute("monStagiaire", optStagiaire.get());

		// ETAPE 4
		return "stagiaire/form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @RequestParam(required = false, defaultValue = "0") Integer version,@RequestParam(required = false) Civilite civilite,
			@RequestParam(required = false) String nom, @RequestParam(required = false) String prenom, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtNaissance, @RequestParam(required = false) NiveauEtude niveauEtude,
			@RequestParam(required = false) String email, @RequestParam(required = false) String telephone, @RequestParam(required = false) String rue, @RequestParam(required = false) String complement,
			@RequestParam(required = false) String codePostal, @RequestParam(required = false) String ville) {
		
		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(rue, complement, codePostal, ville);
		stagiaire.setId(id);
		stagiaire.setVersion(version);
		
		stagiaireRepo.save(stagiaire);
		
		return "redirect:list";	
	}

	@GetMapping("/delete")
	private String delete(@RequestParam Long id) {
		stagiaireRepo.deleteById(id);

		return "redirect:list";
	}
	
	@GetMapping("/cancel")
	private String cancel() {
		return "redirect:list";
=======
	public String add(Model model) {
		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("niveauEtudes", NiveauEtude.values());
		model.addAttribute("evaluations", evaluationRepo.findAllOrphan());

		return "stagiaire/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {

		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("niveauEtudes", NiveauEtude.values());
		model.addAttribute("evaluations", evaluationRepo.findAllOrphanAndCurrentStagiaire(id));
		model.addAttribute("stagiaire", stagiaireRepo.findById(id).get());

		return "stagiaire/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version,
			@RequestParam(required = false) Civilite civilite, @RequestParam String nom,
			@RequestParam(required = false) String prenom, @RequestParam String email,
			@RequestParam(required = false) String telephone,
			@RequestParam(required = false) Date dtNaissance,
			@RequestParam(required = false) NiveauEtude niveauEtude, @RequestParam(required = false) String rue,
			@RequestParam(required = false) String complement, @RequestParam(required = false) String codePostal,
			@RequestParam(required = false) String ville,
			@RequestParam(value = "evaluation", required = false) Long evaluationId) {

		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(new Adresse(rue, complement, codePostal, ville));
		stagiaire.setId(id);
		stagiaire.setVersion(version);

		if (evaluationId != null) {
			Evaluation evaluation = new Evaluation();
			evaluation.setId(evaluationId);
			stagiaire.setEvaluation(evaluation);
		}

		stagiaireRepo.save(stagiaire);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		stagiaireRepo.deleteById(id);

		return "forward:list";
>>>>>>> master
	}
}
