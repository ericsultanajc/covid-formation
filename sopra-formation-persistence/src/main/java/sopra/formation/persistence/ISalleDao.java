package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Filiere;
import sopra.formation.model.Salle;

public interface ISalleDao extends IDao<Salle, Long>{
	List<Salle> findAllByFiliere(String nom);

}
