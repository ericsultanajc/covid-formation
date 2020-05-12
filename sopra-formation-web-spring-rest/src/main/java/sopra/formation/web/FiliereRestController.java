package sopra.formation.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import sopra.formation.model.Dispositif;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Views;
import sopra.formation.persistence.IFiliereRepository;
import sopra.formation.persistence.IFormateurRepository;
import sopra.formation.persistence.IStagiaireRepository;
import sopra.formation.web.dto.FiliereForm;

// 4 request et 1 lien avec le référent de la filière dans le filiere.java
@RestController
@RequestMapping("/filiere")
public class FiliereRestController {

	@Autowired
	private IFiliereRepository filiereRepo;

	@Autowired
	private IStagiaireRepository stagiaireRepo;

	@Autowired
	private IFormateurRepository formateurRepo;
	
	// GET --> findAll
	@GetMapping("")
	@JsonView(Views.ViewFiliere.class)
	public List<Filiere> findAll() {
		return filiereRepo.findAll();
	}

	// Request 1 : find by promotion dans IFiliere
	@GetMapping("/by-promotion/{promotion}")
	@JsonView(Views.ViewFiliere.class)
	public Filiere find(@PathVariable String promotion) {

		Filiere filiere = filiereRepo.findByPromotion(promotion);

		if (filiere != null) {
			return filiere;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// Request 2 : find by referent de la filière --> dans IFiliere
	@GetMapping("/{id}")
	@JsonView(Views.ViewFiliereWithReferent.class)
	public Filiere find(@PathVariable Long id) {

		Optional<Filiere> optFiliere = filiereRepo.findWithReferent(id);

		if (optFiliere.isPresent()) {
			return optFiliere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	// Request 3 : find by ville pour l'adresse de la salle --> dans IFiliere
	@GetMapping("/by-ville/{ville}")
	@JsonView(Views.ViewFiliere.class)
	public List<Filiere> findAllByVille(@PathVariable String ville) {

		return filiereRepo.findAllByVille(ville);
	}

	// Request 4 : find by filières tous les stagiaires --> dans Stagiaire
	@GetMapping("/{id}/stagiaires")
	@JsonView(Views.ViewFiliereWithReferent.class)
	public List<Stagiaire> findAllByFiliere(@PathVariable Long id) {

		List<Stagiaire> stagiaires = stagiaireRepo.findAllByFiliere(id);

		return stagiaires;
	}

	// POST --> create
	@PostMapping("")
	public Filiere create(@RequestBody FiliereForm filiereForm) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Filiere filiere = new Filiere();
		filiere.setIntitule(filiereForm.getIntitule());
		filiere.setPromotion(filiereForm.getPromotion());
		filiere.setDtDebut(sdf.parse(filiereForm.getDtDebut()));
		filiere.setDuree(filiereForm.getDuree());
		filiere.setDispositif(Dispositif.valueOf(filiereForm.getDispositif()));
		
		Optional<Formateur> optFormateur = formateurRepo.findById(filiereForm.getIdFormateur());
		
		if(optFormateur.isPresent()) {
			filiere.setReferent(optFormateur.get());
		}
		
		filiere = filiereRepo.save(filiere);

		return filiere;
	}

	// PUT --> update
	@PutMapping("/{id}")
	@JsonView(Views.ViewFiliere.class)
	public Filiere update(@RequestBody Filiere filiere, @PathVariable Long id) {
		if (!filiereRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		filiere = filiereRepo.save(filiere);

		return filiere;
	}

	// DELETE --> delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		filiereRepo.deleteById(id);
	}
}
