package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Filiere;

public interface IFiliereDao extends IDao<Filiere, Long>{
	Filiere findByPromotion(String promotion);
	
	Filiere findWithReferent(Long id);
	
	List<Filiere> findAllByVille(String ville);
}
