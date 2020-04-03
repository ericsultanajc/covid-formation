package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Stagiaire;

public interface IStagiaireDao extends IDao<Stagiaire, Long> {
	List<Stagiaire> findAllByFormateur(String nom);
	List<Stagiaire> findAllByVille(String ville);
}
