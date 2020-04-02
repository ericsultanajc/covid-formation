package sopra.formation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.IMatiereDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;
import sopra.formation.persistence.jpa.EvaluationDaoJpa;
import sopra.formation.persistence.jpa.FiliereDaoJpa;
import sopra.formation.persistence.jpa.FormateurDaoJpa;
import sopra.formation.persistence.jpa.MatiereDaoJpa;
import sopra.formation.persistence.jpa.SalleDaoJpa;
import sopra.formation.persistence.jpa.StagiaireDaoJpa;
import sopra.formation.persistence.jpa.UEDaoJpa;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

	private final IEvaluationDao evaluationDao = new EvaluationDaoJpa();
	private final IFiliereDao filiereDao = new FiliereDaoJpa();
	private final IFormateurDao formateurDao = new FormateurDaoJpa();
	private final IMatiereDao matiereDao = new MatiereDaoJpa();
	private final ISalleDao salleDao = new SalleDaoJpa();
	private final IStagiaireDao stagiaireDao = new StagiaireDaoJpa();
	private final IUEDao ueDao = new UEDaoJpa();

	private Application() {
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IEvaluationDao getEvaluationDao() {
		return evaluationDao;
	}

	public IFiliereDao getFiliereDao() {
		return filiereDao;
	}

	public IFormateurDao getFormateurDao() {
		return formateurDao;
	}

	public IMatiereDao getMatiereDao() {
		return matiereDao;
	}

	public ISalleDao getSalleDao() {
		return salleDao;
	}

	public IStagiaireDao getStagiaireDao() {
		return stagiaireDao;
	}

	public IUEDao getUeDao() {
		return ueDao;
	}

}