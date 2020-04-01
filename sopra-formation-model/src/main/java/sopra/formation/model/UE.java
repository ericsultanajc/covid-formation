package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "course_unit")

public class UE {
	@Id
	@GeneratedValue

	private Long id;
	@Column(name = "code")
	private Integer code;
	@Column(name = "duration")
	private Integer duree;
	@Column(name = "position")
	private int ordre;

	@Transient
	private Filiere filiere;
	@Transient
	private Formateur formateur;
	@Transient
	private Matiere matiere;
	@Transient
	private Salle salle;

	public UE() {
		super();
	}

	public UE(Integer code, Integer duree, int ordre) {
		super();
		this.code = code;
		this.duree = duree;
		this.ordre = ordre;
	}

	public UE(Long id, Integer code, Integer duree, int ordre) {
		super();
		this.id = id;
		this.code = code;
		this.duree = duree;
		this.ordre = ordre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "UE [id=" + id + ", code=" + code + ", duree=" + duree + ", ordre=" + ordre + ", formateur=" + formateur
				+ ", matiere=" + matiere + ", salle=" + salle + "]";
	}

}
