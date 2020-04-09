package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Filiere;
import sopra.formation.persistence.IFiliereDao;

@Repository
@Transactional
public class FiliereDaoJpa implements IFiliereDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Filiere> findAll() {
		TypedQuery<Filiere> query = em.createQuery("from Filiere", Filiere.class);

		List<Filiere> filieres = query.getResultList();

		return filieres;
	}

	@Override
	@Transactional(readOnly = true)
	public Filiere find(Long id) {

		Filiere filiere = em.find(Filiere.class, id);

		return filiere;
	}

	@Override
	public Filiere save(Filiere obj) {

		Filiere filiere = em.merge(obj);

		return filiere;
	}

	@Override
	public void delete(Filiere obj) {

		em.remove(em.merge(obj));
	}

	@Override
	@Transactional(readOnly = true)
	public Filiere findByPromotion(String promotion) {

		TypedQuery<Filiere> query = em.createQuery("from Filiere f where f.promotion = ?0", Filiere.class);

		query.setParameter(0, promotion);

		Filiere filiere = query.getSingleResult();

		return filiere;
	}

	@Override
	@Transactional(readOnly = true)
	public Filiere findWithReferent(Long id) {

		TypedQuery<Filiere> query = em.createQuery("from Filiere f left join fetch f.referent r where f.id = :id",
				Filiere.class);

		query.setParameter("id", id);

		Filiere filiere = query.getSingleResult();

		return filiere;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Filiere> findAllByVille(String ville) {

		TypedQuery<Filiere> query = em.createQuery(
				"select f from Filiere f join f.ues ue join ue.salle s where s.adr.ville = :ville", Filiere.class);

		query.setParameter("ville", ville);

		List<Filiere> filieres = query.getResultList();

		return filieres;
	}
}
