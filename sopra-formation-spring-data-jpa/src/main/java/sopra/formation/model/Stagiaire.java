package sopra.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("Stagiaire")
@NamedQueries({
		@NamedQuery(name = "Stagiaire.findAllByFormateur", query = "select s from Stagiaire s join s.filiere f join f.referent ref where ref.nom = :nom"),
		@NamedQuery(name = "Stagiaire.findAllByVille", query = "select s from Stagiaire s where s.adresse.ville = :ville") })
public class Stagiaire extends Personne {
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date dtNaissance;
	@Column(name = "study_level", length = 15)
	@Enumerated(EnumType.STRING)
	private NiveauEtude niveauEtude;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Filiere filiere;
	@ManyToOne
	@JoinColumn(name = "rating_id")
	private Evaluation evaluation;

	public Stagiaire() {
		super();
	}


	public Stagiaire(String email) {
		super(email);
	}

	public Stagiaire(Civilite civilite, String nom, String prenom, String email, String telephone, Date dtNaissance,
			NiveauEtude niveauEtude) {
		super(civilite, nom, prenom, email, telephone);
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
