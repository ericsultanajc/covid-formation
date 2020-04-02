package sopra.formation.test;

import sopra.formation.Application;
import sopra.formation.model.Evaluation;
import sopra.formation.persistence.IEvaluationDao;
import sopra.formation.persistence.IStagiaireDao;

public class TestEvaluationDao {
	public static void main(String[] args) {
		
		IEvaluationDao evaluationDao = Application.getInstance().getEvaluationDao();
		IStagiaireDao stagiaireDao = Application.getInstance().getStagiaireDao();
		
		
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new
		
		evaluation = evaluationDao.save(evaluation); // managed
		
		System.out.println(evaluation);
		
		evaluation.setComportemental(14); // detached
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
		
		Application.getInstance().getEvaluationDao().delete(evaluation);
	}
}