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
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Formateur> findAll() {

		TypedQuery<Formateur> query = em.createQuery("from Formateur", Formateur.class);

		List<Formateur> formateurs = query.getResultList();

		return formateurs;
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur find(Long id) {

		Formateur formateur = em.find(Formateur.class, id);

		return formateur;
	}

	@Override
	public Formateur save(Formateur obj) {

		Formateur formateur = em.merge(obj);

		return formateur;
	}

	@Override
	public void delete(Formateur obj) {

		em.remove(em.merge(obj));
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur findByFiliere(Filiere filiere) {
		TypedQuery<Formateur> query = em
				.createQuery("select form from Filiere f join f.referent form where f = :filiere", Formateur.class);

		query.setParameter("filiere", filiere);

		Formateur formateur = query.getSingleResult();

		return formateur;
	}

	@Override
	@Transactional(readOnly = true)
	public Formateur findByEmail(String email) {

		TypedQuery<Formateur> query = em.createQuery("select f from Formateur f where f.email = :email",
				Formateur.class);

		query.setParameter("email", email);

		Formateur formateur = query.getSingleResult();

		return formateur;
	}
}
