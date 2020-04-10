package sopra.formation.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long>{
	List<Evaluation> findByTechnique(Integer technique);
	
	List<Evaluation> findByComportementalGreaterThanAndTechniqueGreaterThan(Integer comportemental, Integer technique);

	@Query("select s.evaluation from Stagiaire s where s.niveauEtude = :niveau")
	List<Evaluation> findAllByStagiaireNiveau(@Param("niveau") NiveauEtude niveauEtude);
}
