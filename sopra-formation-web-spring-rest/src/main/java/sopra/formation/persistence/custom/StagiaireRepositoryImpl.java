package sopra.formation.persistence.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IStagiaireRepositoryCustom;

@Repository
public class StagiaireRepositoryImpl implements IStagiaireRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Query("select s from Stagiaire s where s.nom like :nom and year(s.dtNaissance) = :annee and s.niveauEtude = :niveau and s.adresse.ville = :ville")

	@Override
	public List<Stagiaire> search(String nom, Integer annee, NiveauEtude niveau, String ville) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Stagiaire> criteriaQuery = criteriaBuilder.createQuery(Stagiaire.class);

		Root<Stagiaire> stagiaireFrom = criteriaQuery.from(Stagiaire.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!nom.isBlank()) {
			predicates.add(criteriaBuilder.like(stagiaireFrom.get("nom"), nom + "%"));
		}

		if (annee != null) {
			predicates.add(criteriaBuilder
					.equal(criteriaBuilder.function("YEAR", Integer.class, stagiaireFrom.get("dtNaissance")), annee));
		}

		if (niveau != null) {
			predicates.add(criteriaBuilder.equal(stagiaireFrom.get("niveauEtude"), niveau));
		}

		if (!ville.isBlank()) {
			predicates.add(criteriaBuilder.equal(stagiaireFrom.get("adresse.ville"), ville));
		}

		if (predicates.size() > 0) {
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
		}

		return em.createQuery(criteriaQuery).getResultList();
	}

}
