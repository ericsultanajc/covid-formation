package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Salle;

public interface ISalleDao extends IDao<Salle, Long> {
	List<Salle> findAllByFiliere(Long id);
	List<Salle> findAllByVille(String ville);
}
