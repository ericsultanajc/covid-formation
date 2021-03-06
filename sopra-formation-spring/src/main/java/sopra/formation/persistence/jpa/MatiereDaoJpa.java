package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Matiere;
import sopra.formation.persistence.IMatiereDao;

@Repository
@Transactional
public class MatiereDaoJpa implements IMatiereDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Matiere> findAll() {
		TypedQuery<Matiere> query = em.createQuery("from Matiere", Matiere.class);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Matiere find(Long id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public Matiere save(Matiere obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Matiere obj) {
		em.remove(em.merge(obj));
	}
}
