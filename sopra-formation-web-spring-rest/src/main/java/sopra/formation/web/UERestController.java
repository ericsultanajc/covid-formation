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

import sopra.formation.model.UE;
import sopra.formation.model.Views;
import sopra.formation.persistence.IUERepository;

// pas de @query ici, tous les liens sont fais directement avec le @json dans le UE.java
@RestController
@RequestMapping("/ue")
public class UERestController {

	@Autowired
	private IUERepository ueRepo;

	// GET --> findAll
	@GetMapping("")
	@JsonView(Views.ViewUE.class)
	public List<UE> findAll() {
		return ueRepo.findAll();
	}

	// GET --> find
	@GetMapping("/{id}")
	@JsonView(Views.ViewUE.class)
	public UE find(@PathVariable Long id) {

		Optional<UE> optUE = ueRepo.findById(id);

		if (optUE.isPresent()) {
			return optUE.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// POST --> create
	@PostMapping("")
	public UE create(@RequestBody UE ue) {
		ue = ueRepo.save(ue);

		return ue;
	}

	// PUT --> update
	@PutMapping("/{id}")
	@JsonView(Views.ViewUE.class)
	public UE update(@RequestBody UE ue, @PathVariable Long id) {
		if (!ueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		ue = ueRepo.save(ue);

		return ue;
	}

	// DELETE --> delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		ueRepo.deleteById(id);
	}
}
