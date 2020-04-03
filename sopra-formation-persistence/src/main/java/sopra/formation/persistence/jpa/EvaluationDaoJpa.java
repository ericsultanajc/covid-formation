package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.formation.Application;
import sopra.formation.model.Evaluation;
<<<<<<< HEAD
=======
import sopra.formation.model.NiveauEtude;
>>>>>>> master
import sopra.formation.persistence.IEvaluationDao;

public class EvaluationDaoJpa implements IEvaluationDao {

	@Override
	public List<Evaluation> findAll() {
		List<Evaluation> evaluations = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Evaluation> query = em.createQuery("from Evaluation", Evaluation.class);

			evaluations = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return evaluations;
	}

	@Override
	public Evaluation find(Long id) {
		Evaluation evaluation = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			evaluation = em.find(Evaluation.class, id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return evaluation;
	}

	@Override
	public Evaluation save(Evaluation obj) {
		Evaluation evaluation = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			evaluation = em.merge(obj);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return evaluation;
	}

	@Override
	public void delete(Evaluation obj) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(obj));

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
<<<<<<< HEAD
}
=======

	@Override
	public List<Evaluation> findAllByComportementalAndTechnique(Integer comportemental, Integer technique) {
		List<Evaluation> evaluations = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Evaluation> query1 = em.createQuery(
					"select e from Evaluation e where e.comportemental > :noteC and e.technique > :noteT",
					Evaluation.class);

			query1.setParameter("noteC", comportemental);
			query1.setParameter("noteT", technique);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return evaluations;
	}

	@Override
	public List<Evaluation> findAllByStagiaireNiveau(NiveauEtude niveauEtude) {
		List<Evaluation> evaluations = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Evaluation> query2 = em.createQuery(
					"select s.evaluation from Stagiaire s where s.niveauEtude = :niveau", Evaluation.class);

			query2.setParameter("niveau", niveauEtude);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return evaluations;
	}
}
>>>>>>> master
