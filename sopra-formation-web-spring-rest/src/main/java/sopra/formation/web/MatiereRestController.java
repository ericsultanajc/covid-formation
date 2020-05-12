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

import sopra.formation.model.Matiere;
import sopra.formation.model.Views;
import sopra.formation.persistence.IMatiereRepository;

//lien pas fais avec postman
// pas de request et 1 lien avec le formateur dans le matiere.java
@RestController
@RequestMapping("/matiere")
public class MatiereRestController {

	@Autowired
	private IMatiereRepository matiereRepo;

	// GET --> findAll
	@GetMapping("")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> findAll() {
		return matiereRepo.findAll();
	}

	// GET --> find
	@GetMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere find(@PathVariable Long id) {

		Optional<Matiere> optMatiere = matiereRepo.findById(id);

		if (optMatiere.isPresent()) {
			return optMatiere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// POST --> create
	@PostMapping("")
	@JsonView(Views.ViewMatiere.class)
	public Matiere create(@RequestBody Matiere matiere) {
		matiere = matiereRepo.save(matiere);

		return matiere;
	}

	// PUT --> update
	@PutMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere update(@RequestBody Matiere matiere, @PathVariable Long id) {
		if (!matiereRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		matiere = matiereRepo.save(matiere);

		return matiere;
	}

	// DELETE --> delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		matiereRepo.deleteById(id);
	}
}
