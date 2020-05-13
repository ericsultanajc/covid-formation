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

import sopra.formation.model.Formateur;
import sopra.formation.model.Views;
import sopra.formation.persistence.IFormateurRepository;

@RestController
@RequestMapping("/formateur")
public class FormateurRestController {

	@Autowired
	private IFormateurRepository formateurRepo;

	@GetMapping("")
	@JsonView(Views.ViewFormateur.class)
	public List<Formateur> findAll() {
		return formateurRepo.findAll();
	}
	
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
	
	@GetMapping("/by-email/{email}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur findByEmail(@PathVariable String email) {

		Optional<Formateur> optFormateur = formateurRepo.findByEmail(email);

		if (optFormateur.isPresent()) {
			return optFormateur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@PostMapping("")
	public Formateur create(@RequestBody Formateur formateur) {
		formateur = formateurRepo.save(formateur);

		return formateur;
	}

	@PutMapping("/{id}")
	public Formateur update(@RequestBody Formateur formateur, @PathVariable Long id) {
		if (!formateurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		formateur = formateurRepo.save(formateur);

		return formateur;
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		formateurRepo.deleteById(id);
	}
}
