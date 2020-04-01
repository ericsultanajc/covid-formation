package sopra.formation.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity // obligatoire
@Table(name = "room") // optionnel
public class Salle {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name = "name")
	@NotEmpty
	private String nom;
	@Column(name = "capacity")
	private Integer capacite;
	@Column(name = "video_projector",length=5)
	private Boolean videoProjecteur;
	@Transient
	private Adresse adr;
	@Transient
	private ArrayList<UE> ues = new ArrayList<UE>();

	public Salle() {
		super();
	}

	public Salle(String nom) {
		super();
		this.nom = nom;
	}

	public Salle(Long id, String nom, Integer capacite, Boolean videoProjecteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.videoProjecteur = videoProjecteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Boolean getVideoProjecteur() {
		return videoProjecteur;
	}

	public void setVideoProjecteur(Boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}

	public Adresse getAdr() {
		return adr;
	}

	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	public ArrayList<UE> getUes() {
		return ues;
	}

	public void setUes(ArrayList<UE> ues) {
		this.ues = ues;
	}

	public void addUe(UE ue) {
		this.ues.add(ue);
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", videoProjecteur=" + videoProjecteur
				+ ", adr=" + adr + "]";
	}

}
