package sopra.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("student")
public class Stagiaire extends Personne {
	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	private Date dtNaissance;
	@Column(name="studies_level")
	@Enumerated(EnumType.STRING)
	private NiveauEtude niveauEtude;
	@Transient
	private Filiere filiere;
	@Transient
	private Evaluation evaluation;

	public Stagiaire() {
		super();
	}

	public Stagiaire(String email) {
		super(email);
	}

	public Stagiaire(Long id, Civilite civilite, String nom, String prenom, String email, String telephone,
			Date dtNaissance, NiveauEtude niveauEtude) {
		super(id, civilite, nom, prenom, email, telephone);
		this.dtNaissance = dtNaissance;
		this.niveauEtude = niveauEtude;
	}

	public Date getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(Date dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public NiveauEtude getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(NiveauEtude niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Stagiaire [dtNaissance=" + dtNaissance + ", niveauEtude=" + niveauEtude + ", evaluation=" + evaluation
				+ ", " + super.toString() + "]";
	}

}
