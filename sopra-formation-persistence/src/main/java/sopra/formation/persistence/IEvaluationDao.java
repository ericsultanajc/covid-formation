package sopra.formation.persistence;

import java.util.List;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;

public interface IEvaluationDao extends IDao<Evaluation, Long>{
	List<Evaluation> findAllByComportementalAndTechnique(Integer comportemental, Integer technique);
	List<Evaluation> findAllByStagiaireNiveau(NiveauEtude niveauEtude);

}
