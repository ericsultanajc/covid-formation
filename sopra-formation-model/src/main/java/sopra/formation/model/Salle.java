package sopra.formation.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity // obligatoire
@Table(name = "room") // optionnel 
public class Salle {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name="name", columnDefinition="VARCHAR(100)")
	@Size(max=100)
	private String nom;
	@Column(name="capability", columnDefinition="VARCHAR(3)")
	@Size(max=100)
	private Integer capacite;
	@Column(name="video", columnDefinition="VARCHAR(1)")
	@Size(max=1)
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
