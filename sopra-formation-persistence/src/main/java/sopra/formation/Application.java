package sopra.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IFiliereDao;
import sopra.formation.persistence.IFormateurDao;
import sopra.formation.persistence.IMatiereDao;
import sopra.formation.persistence.ISalleDao;
import sopra.formation.persistence.IStagiaireDao;
import sopra.formation.persistence.IUEDao;

public class Application {
	private static Application instance = null;

	private final IEvaluationDao evaluationDao = null;
	private final IFiliereDao filiereDao = null;
	private final IFormateurDao formateurDao = null;
	private final IMatiereDao matiereDao = null;
	private final ISalleDao salleDao = null;
	private final IStagiaireDao stagiaireDao = null;
	private final IUEDao ueDao = null;

	private Application() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost/training", "postgres", "manager");
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
