package sopra.formation.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IEvaluationRepository;
import sopra.formation.persistence.IStagiaireRepository;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	@Autowired
	private IEvaluationRepository evaluationRepo;
	@Autowired
	private IStagiaireRepository stagiaireRepo;

	public StagiaireController() {
		super();
	}

	@GetMapping({ "", "/", "/list" })
	public String list(Model model) {
		model.addAttribute("stagiaires", stagiaireRepo.findAll());

		return "stagiaire/list";
	}

	@GetMapping("/add")
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
	}
}
