package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Stagiaire;
import sopra.formation.persistence.IStagiaireDao;

@Repository
@Transactional
public class StagiaireDaoJpa implements IStagiaireDao {

	@PersistenceContext
	private EntityManager em; // entityManagerFactory.createEntityManager()

	@Override
	@Transactional(readOnly = true)
	public List<Stagiaire> findAll() {
		TypedQuery<Stagiaire> query = em.createQuery("from Stagiaire", Stagiaire.class);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Stagiaire find(Long id) {
		return em.find(Stagiaire.class, id);
	}

	@Override
	public Stagiaire save(Stagiaire obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Stagiaire obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Stagiaire> findAllByFormateur(String nom) {
		TypedQuery<Stagiaire> query = em.createNamedQuery("Stagiaire.findAllByFormateur", Stagiaire.class);

		query.setParameter("nom", nom);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Stagiaire> findAllByVille(String ville) {
		TypedQuery<Stagiaire> query = em.createNamedQuery("Stagiaire.findAllByVille", Stagiaire.class);

		query.setParameter("ville", ville);

		return query.getResultList();
	}
}
