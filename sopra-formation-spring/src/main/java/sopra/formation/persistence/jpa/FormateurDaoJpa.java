package sopra.formation.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.persistence.IFormateurDao;

@Repository
@Transactional
public class FormateurDaoJpa implements IFormateurDao {

	@PersistenceContext
<<<<<<< Updated upstream
	private EntityManager em;
=======
	private EntityManager em; // entityManagerFactory.createEntityManager()
>>>>>>> Stashed changes

	@Override
	@Transactional(readOnly = true)
	public List<Formateur> findAll() {
		TypedQuery<Formateur> query = em.createQuery("from Formateur", Formateur.class);

		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur find(Long id) {
		return em.find(Formateur.class, id);
	}

	@Override
	public Formateur save(Formateur obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Formateur obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur findByFiliere(Filiere filiere) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
		TypedQuery<Formateur> query = em
				.createQuery("select form from Filiere f join f.referent form where f = :filiere", Formateur.class);

		query.setParameter("filiere", filiere);

		return query.getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur findByEmail(String email) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
		TypedQuery<Formateur> query = em.createQuery("select f from Formateur f where f.email = :email",
				Formateur.class);

		query.setParameter("email", email);

		return query.getSingleResult();
	}
}
