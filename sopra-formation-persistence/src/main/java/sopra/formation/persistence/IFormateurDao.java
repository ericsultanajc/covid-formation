package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Formateur;

public interface IFormateurDao extends IDao<Formateur, Long> {
	List<Formateur> findAllByEmail(String email);

}
