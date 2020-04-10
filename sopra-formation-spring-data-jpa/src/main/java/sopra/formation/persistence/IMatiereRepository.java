package sopra.formation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Matiere;

public interface IMatiereRepository extends JpaRepository<Matiere, Long>{

}
