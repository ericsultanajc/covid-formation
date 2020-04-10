package sopra.formation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;

public interface IFormateurRepository extends JpaRepository<Formateur, Long> {
	@Query("select form from Filiere f join f.referent form where f = :filiere")
	Formateur findByFiliere(@Param("filière") Filiere filiere);

	@Query("select f from Formateur f where f.email = :email")
	Formateur findByEmail(@Param("email") String email);
}
