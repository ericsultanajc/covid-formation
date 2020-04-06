package sopra.formation.persistence;

import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;

public interface IFormateurDao extends IDao<Formateur, Long> {

	Formateur findByFiliere(Filiere filiere);
	

	
}
