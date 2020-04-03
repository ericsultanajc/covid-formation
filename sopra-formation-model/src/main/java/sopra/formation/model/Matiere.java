package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity // obligatoire
@Table(name = "discipline") // optionnel
public class Matiere {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name = "version")
	private int Version;
	@Column(name = "name", nullable = false, length = 100)
	private String nom;
	@Column(name = "time", nullable = false)
	private Integer duree;
	@OneToMany(mappedBy = "matiere")
	private List<UE> ues = new ArrayList<UE>();
	@ManyToMany(mappedBy = "competences")
	private List<Formateur> formateurs = new ArrayList<Formateur>();

	public Matiere() {
		super();
	}

	public Matiere(Long id, String nom, Integer duree) {
		super();
		this.id = id;
		this.nom = nom;
		this.duree = duree;
	}

	public Matiere(String nom, Integer duree) {
		super();
		this.nom = nom;
		this.duree = duree;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public List<UE> getUes() {
		return ues;
	}

	public void setUes(ArrayList<UE> ues) {
		this.ues = ues;
	}

	public void addUe(UE ue) {
		this.ues.add(ue);
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(ArrayList<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public void addFormateur(Formateur formateur) {
		this.formateurs.add(formateur);
	}

	@Override
	public String toString() {
		return "Matiere [id=" + id + ", nom=" + nom + ", duree=" + duree + "]";
	}

}
