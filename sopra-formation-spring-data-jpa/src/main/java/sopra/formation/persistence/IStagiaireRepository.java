package sopra.formation.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Stagiaire;

public interface IStagiaireRepository extends JpaRepository<Stagiaire, Long> {
	List<Stagiaire> findAllByFormateur(@Param("nom") String nom); // via @NamedQuery
	List<Stagiaire> findAllByVille(String ville);
}
