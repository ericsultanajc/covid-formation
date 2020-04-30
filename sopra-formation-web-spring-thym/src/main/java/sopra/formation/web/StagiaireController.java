package sopra.formation.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Civilite;
import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationRepository;
import sopra.formation.persistence.IStagiaireRepository;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private IEvaluationRepository evaluationRepo;
	@Autowired
	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}

	@GetMapping({ "", "/" })
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
		return "stagiaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("page", "stagiaire");
		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("niveauEtudes", NiveauEtude.values());
		model.addAttribute("evaluations", evaluationRepo.findAllOrphan());
		model.addAttribute("monStagiaire", new Stagiaire());

		return "stagiaire/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		model.addAttribute("page", "stagiaire");
		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("niveauEtudes", NiveauEtude.values());
		model.addAttribute("evaluations", evaluationRepo.findAllOrphanAndCurrentStagiaire(id));
		model.addAttribute("monStagiaire", stagiaireRepo.findById(id).get());

		return "stagiaire/form";
	}

	@PostMapping("/saveFirst")
	public String saveFirst(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version,
			@RequestParam(required = false) Civilite civilite, @RequestParam(required = false) String nom,
			@RequestParam(required = false) String prenom,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtNaissance,
			@RequestParam(required = false) NiveauEtude niveauEtude, @RequestParam(required = false) String email,
			@RequestParam(required = false) String telephone, @RequestParam(required = false) String rue,
			@RequestParam(required = false) String complement, @RequestParam(required = false) String codePostal,
			@RequestParam(required = false) String ville,
			@RequestParam(value = "evaluation", required = false) Long evaluationId) {

		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(rue, complement, codePostal, ville);
		stagiaire.setId(id);
		stagiaire.setVersion(version);

		stagiaireRepo.save(stagiaire);

		if (evaluationId != null) {
			Evaluation evaluation = new Evaluation();
			evaluation.setId(evaluationId);
			stagiaire.setEvaluation(evaluation);
		}

		return "redirect:list";
	}

	public String save(@ModelAttribute ("monStagiaire") Stagiaire stagiaire, Long evaluationId) {

		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		stagiaireRepo.deleteById(id);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:list";
	}
}
