package sopra.formation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Formateur;

public interface IFormateurRepository extends JpaRepository<Formateur, Long> {
//	Formateur findByFiliere(Filiere filiere);
//	Formateur findByEmail(String email);
}
