package sopra.formation.test;

import sopra.formation.Application;
import sopra.formation.model.Evaluation;

public class TestEvaluationDao {
	public static void main(String[] args) {
		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution"); // new
		
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation); // managed
		
		System.out.println(evaluation);
		
		evaluation.setComportemental(14); // detached
		
		evaluation = Application.getInstance().getEvaluationDao().save(evaluation);
		
//		Application.getInstance().getEvaluationDao().delete(evaluation);
	}
}
