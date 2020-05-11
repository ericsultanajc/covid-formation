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

import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IStagiaireRepository;

@RestController
@RequestMapping("/stagiaire")
public class StagiaireRestController {

	@Autowired
	private IStagiaireRepository stagiaireRepo;

	@GetMapping("")
	public List<Stagiaire> findAll() {
		return stagiaireRepo.findAll();
	}

	@GetMapping("/by-formateur/{nom}")
	public List<Stagiaire> findAllByFormateur(@PathVariable String nom) {
		return stagiaireRepo.findAllByFormateur(nom);
	}

	@GetMapping("/by-ville/{ville}")
	public List<Stagiaire> findAllByVille(@PathVariable String ville) {
		return stagiaireRepo.findAllByVille(ville);
	}

	@GetMapping("/by-nom/{nom}")
	public List<Stagiaire> findAllByNom(@PathVariable String nom) {
		return stagiaireRepo.findAllByNom(nom + "%");
	}

	@GetMapping("/{id}")
	public Stagiaire find(@PathVariable Long id) {

		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		if (optStagiaire.isPresent()) {
			return optStagiaire.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@PostMapping("")
	public Stagiaire create(@RequestBody Stagiaire stagiaire) {
		stagiaire = stagiaireRepo.save(stagiaire);

		return stagiaire;
	}

	@PutMapping("/{id}")
	public Stagiaire update(@RequestBody Stagiaire stagiaire, @PathVariable Long id) {
		if (!stagiaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		stagiaire = stagiaireRepo.save(stagiaire);

		return stagiaire;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		stagiaireRepo.deleteById(id);
	}
}
