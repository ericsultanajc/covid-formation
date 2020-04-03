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
import sopra.formation.persistence.jpa.EvaluationDao;
import sopra.formation.persistence.jpa.FiliereDao;
import sopra.formation.persistence.jpa.FormateurDao;
import sopra.formation.persistence.jpa.MatiereDao;
import sopra.formation.persistence.jpa.SalleDao;
import sopra.formation.persistence.jpa.StagiaireDao;
import sopra.formation.persistence.jpa.UeDao;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sopra-formation");

	private final IEvaluationDao evaluationDao = new EvaluationDao();
	private final IFiliereDao filiereDao = new FiliereDao();
	private final IFormateurDao formateurDao = new FormateurDao();
	private final IMatiereDao matiereDao = new MatiereDao();
	private final ISalleDao salleDao = new SalleDao();
	private final IStagiaireDao stagiaireDao = new StagiaireDao();
	private final IUEDao ueDao = new UeDao();

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
