package sopra.formation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Salle;

public interface ISalleRepository extends JpaRepository<Salle, Long>{
//	List<Salle> findAllByFiliere(Long id);
//	List<Salle> findAllByVille(String ville);
}
