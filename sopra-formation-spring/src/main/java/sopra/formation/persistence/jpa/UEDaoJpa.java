package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.UE;
import sopra.formation.persistence.IUEDao;

@Repository
@Transactional
public class UEDaoJpa implements IUEDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<UE> findAll() {

		TypedQuery<UE> query = em.createQuery("from UE", UE.class);

		List<UE> ues = query.getResultList();

		return ues;
	}

	@Override
	@Transactional(readOnly = true)
	public UE find(Long id) {

		UE ue = em.find(UE.class, id);

		return ue;
	}

	@Override
	public UE save(UE obj) {

		UE ue = em.merge(obj);

		return ue;
	}

	@Override
	public void delete(UE obj) {

		em.remove(em.merge(obj));
	}
}
