package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // obligatoire
@Table(name = "teaching_unit") // optionnel
public class UE {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name = "version")
	private int Version;
	@Column(name = "code", nullable = false)
	private Integer code;
	@Column(name = "time", nullable = false)
	private Integer duree;
	@Column(name = "ord", nullable = false)
	private int ordre;
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Filiere filiere;
	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Formateur formateur;
	@ManyToOne
	@JoinColumn(name = "discipline_id")
	private Matiere matiere;
	@ManyToOne
	@JoinColumn(name = "room_id")
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

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
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
