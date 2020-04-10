package sopra.formation.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sopra.formation.model.Filiere;

public interface IFiliereRepository extends JpaRepository<Filiere, Long>{
	Filiere findByPromotion(String promotion);
	
	@Query("from Filiere f left join fetch f.referent r where f.id = :id")
	Filiere findWithReferent(Long id);
	
	@Query("select f from Filiere f join f.ues ue join ue.salle s where s.adr.ville = :ville")
	List<Filiere> findAllByVille(String ville);
}
