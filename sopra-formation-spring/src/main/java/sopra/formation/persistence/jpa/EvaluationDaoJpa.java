package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.persistence.IEvaluationDao;

@Repository
@Transactional
public class EvaluationDaoJpa implements IEvaluationDao {

	@PersistenceContext
	private EntityManager em; // entityManagerFactory.createEntityManager()

	@Override
	@Transactional(readOnly = true)
	public List<Evaluation> findAll() {
		TypedQuery<Evaluation> query = em.createQuery("from Evaluation", Evaluation.class);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Evaluation find(Long id) {
		return em.find(Evaluation.class, id);
	}

	@Override
	public Evaluation save(Evaluation obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Evaluation obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evaluation> findAllByComportementalAndTechnique(Integer comportemental, Integer technique) {
		TypedQuery<Evaluation> query = em.createQuery(
				"select e from Evaluation e where e.comportemental > :noteC and e.technique > :noteT",
				Evaluation.class);

		query.setParameter("noteC", comportemental);
		query.setParameter("noteT", technique);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evaluation> findAllByStagiaireNiveau(NiveauEtude niveauEtude) {
		TypedQuery<Evaluation> query = em
				.createQuery("select s.evaluation from Stagiaire s where s.niveauEtude = :niveau", Evaluation.class);

		query.setParameter("niveau", niveauEtude);

		return query.getResultList();
	}
}
