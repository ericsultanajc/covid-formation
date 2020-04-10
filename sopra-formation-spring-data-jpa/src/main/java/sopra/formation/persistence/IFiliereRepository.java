package sopra.formation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Filiere;

public interface IFiliereRepository extends JpaRepository<Filiere, Long>{
//	Filiere findByPromotion(String promotion);
//	
//	Filiere findWithReferent(Long id);
//	
//	List<Filiere> findAllByVille(String ville);
}
