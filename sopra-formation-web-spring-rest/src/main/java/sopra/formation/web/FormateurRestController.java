package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Views;
import sopra.formation.persistence.IFiliereRepository;
import sopra.formation.persistence.IFormateurRepository;
import sopra.formation.persistence.IStagiaireRepository;

// 3 request et le lien avec matière via les compétences est fais dans le formateur.java
@RestController
@RequestMapping("/formateur")
public class FormateurRestController {

	@Autowired
	private IFiliereRepository filiereRepo;

	@Autowired
	private IFormateurRepository formateurRepo;

	@Autowired
	private IStagiaireRepository stagiaireRepo;

	// GET --> findAll
	@GetMapping("")
	@JsonView(Views.ViewFormateur.class)
	public List<Formateur> findAll() {
		return formateurRepo.findAll();
	}

	// Request 1 : find by filiere dans Iformateur
	@GetMapping("/by-filiere/{filiere}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur findByFiliere(@PathVariable Long filiere) {
		Optional<Filiere> optFiliere = filiereRepo.findById(filiere);

		return formateurRepo.findByFiliere(optFiliere.get());
	}

	// Request 2 : find by email --> dans IFormateur
	@GetMapping("/by-email/{email}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur findByEmail(@PathVariable String email) {
		return formateurRepo.findByEmail(email);
	}

	// Request 3 : find by nom --> dans stagiaire
	@GetMapping("/by-nom/{nom}/stagiaires")
	@JsonView(Views.ViewStagiaire.class)
	public List<Stagiaire> findAllStagiaireByNom(@PathVariable String nom) {
		return stagiaireRepo.findAllByFormateur(nom);
	}

	// GET --> find
	@GetMapping("/{id}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur find(@PathVariable Long id) {

		Optional<Formateur> optFormateur = formateurRepo.findById(id);

		if (optFormateur.isPresent()) {
			return optFormateur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// POST --> create // @JsonView recommandé si on veut seulement les paramètres
	// du formateur sans les liens
	@PostMapping("")
	@JsonView(Views.ViewFormateur.class)
	public Formateur create(@RequestBody Formateur formateur) {
		formateur = formateurRepo.save(formateur);

		return formateur;
	}

	// PUT --> update // @JsonView obligatoire pour le fonctionnement de la méthode
	@PutMapping("/{id}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur update(@RequestBody Formateur formateur, @PathVariable Long id) {
		if (!formateurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		formateur = formateurRepo.save(formateur);

		return formateur;
	}

	// DELETE --> delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		formateurRepo.deleteById(id);
	}
}
