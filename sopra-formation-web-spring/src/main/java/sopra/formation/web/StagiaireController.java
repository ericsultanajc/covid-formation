package sopra.formation.web;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("stagiaire")
public class StagiaireController {

	@Autowired
	private IStagiaireRepository stagiaireRepo;
	@Autowired
	private IEvaluationRepository evaluationRepo;

	public StagiaireController() {
		super();
	}

	@RequestMapping({"","/list"})
	public String list(Model model) {
		List<Stagiaire> stagiaires = stagiaireRepo.findAll();
		model.addAttribute("mesStagiaires", stagiaires);
		return "stagiaire/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		List<Evaluation> evaluations = evaluationRepo.findAll();
		model.addAttribute("mesEvaluations", evaluations);
		return "stagiaire/form";
	}

	@RequestMapping("/edit")
	public String edit(Model model, @RequestParam("id") Long id) {
		List<Evaluation> evaluations = evaluationRepo.findAll();
		model.addAttribute("mesEvaluations", evaluations);
		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);
		model.addAttribute("monStagiaire", optStagiaire.get());
		return "stagiaire/form";
	}

	@PostMapping("/save")
	public String save(Model model, @RequestParam("id") Long id, @RequestParam("version") Integer version,
			@RequestParam("civilite") Civilite civilite, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
			@RequestParam("dtNaissance") Date dtNaissance, @RequestParam("email") String email, @RequestParam("telephone") String telephone,
			@RequestParam("rue") String rue, @RequestParam("complement") String complement, @RequestParam("codePostal") String codePostal,
			@RequestParam("ville") String ville, @RequestParam("niveauEtude") NiveauEtude niveauEtude,
			@RequestParam("evaluation") Long eval) {
		
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(eval);
		Evaluation evaluation = optEvaluation.get();

		Adresse adrStagiaire = new Adresse(rue, complement, codePostal, ville);
		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, email, telephone, dtNaissance, niveauEtude);
		stagiaire.setAdresse(adrStagiaire);
		stagiaire.setId(id);
		if (version != null) {
			stagiaire.setVersion(version);
		} else {
			stagiaire.setVersion(0);
		}
		if (evaluation.getId() != null) {
			stagiaire.setEvaluation(evaluation);
		}

		stagiaireRepo.save(stagiaire);

		return "redirect:list";
		}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id") Long id, Model model){
		stagiaireRepo.deleteById(id);
		return "forward:list";
	}

	@RequestMapping("/cancel")
	public String cancel(Model model) {
		return "forward:list";
	}
}
