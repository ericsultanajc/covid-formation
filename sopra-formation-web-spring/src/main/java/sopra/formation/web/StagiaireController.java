package sopra.formation.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IStagiaireRepository;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}

	// ETAPE 1 : Réception de la Request
	// Tableau avec {} pour entrer dans la méthode sans préciser le suffix:
	@GetMapping({ "", "/list" })
	public String list(Model model) {

		// ETAPE 2 : Récupération des données
		List<Stagiaire> stagiaires = stagiaireRepo.findAll();

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("stagiaires", stagiaires);

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "stagiaire/list";
	}

	@GetMapping("/add")
	public String add() {

		// ETAPE 2 et 3 : non nécessaire ici

		// ETAPE 4
		return "stagiaire/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {

		// ETAPE 2 : Récupération des données
		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		// ETAPE 3 : Remplissage du Model avec les données
		model.addAttribute("stagiaire", optStagiaire.get());

		// ETAPE 4 : Appel de la vue avec les données renseignées à l'étape 3
		return "stagiaire/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @RequestParam(defaultValue = "0") Integer version,
			@RequestParam Civilite civilite, @RequestParam String nom, @RequestParam(required = false) String prenom,
			@RequestParam(required = false) String email, @RequestParam(required = false) String telephone,
			@RequestParam(required = false) Date dtNaissance, @RequestParam NiveauEtude niveauEtude,
			@RequestParam(required = false) String rue, @RequestParam(required = false) String complement,
			@RequestParam(required = false) String codePostal, @RequestParam(required = false) String ville) {

		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(new Adresse(rue, complement, codePostal, ville));
		stagiaire.setId(id);
		stagiaire.setVersion(version);

		stagiaireRepo.save(stagiaire);

		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id, Model model) {

		stagiaireRepo.deleteById(id);

		return "forward:list";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "redirect:list";
	}
}
