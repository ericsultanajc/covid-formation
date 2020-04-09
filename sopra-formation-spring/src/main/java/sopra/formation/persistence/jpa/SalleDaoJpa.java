package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Salle;
import sopra.formation.persistence.ISalleDao;

@Repository
@Transactional
public class SalleDaoJpa implements ISalleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Salle> findAll() {

		TypedQuery<Salle> query = em.createQuery("from Salle", Salle.class);

		return query.getResultList();

	}

	@Override
	@Transactional(readOnly = true)
	public Salle find(Long id) {

		return em.find(Salle.class, id);

	}

	@Override
	public Salle save(Salle obj) {

		return em.merge(obj);

	}

	@Override
	public void delete(Salle obj) {

		em.remove(em.merge(obj));

	}

	@Override
	@Transactional(readOnly = true)
	public List<Salle> findAllByFiliere(Long id) {

		TypedQuery<Salle> query = em.createQuery("select distinct ue.salle from UE ue where ue.filiere.id = :id",
				Salle.class);

		query.setParameter("id", id);

		return query.getResultList();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Salle> findAllByVille(String ville) {

		TypedQuery<Salle> query = em.createQuery("select s from Salle s where s.adr.ville = :ville", Salle.class);

		query.setParameter("ville", ville);

		return query.getResultList();

	}
}
