package sopra.formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Filiere {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "title", length = 100, nullable = false)
	@Size(max = 100)
	private String intitule;
	@Column(length = 255)
	private String promotion;
	@Temporal(TemporalType.DATE)
	@Column(name = "startdate")
	private Date dtDebut;
	@Column(name = "duration")
	private Integer duree;
	@Enumerated(EnumType.STRING)
	@Column(name = "plan", length = 10)
	private Dispositif dispositif;
	@OneToMany(mappedBy = "filiere")
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	@OneToMany(mappedBy = "filiere")
	private List<UE> ues = new ArrayList<UE>();
	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Formateur referent;

	public Filiere() {
		super();
	}

	public Filiere(String promotion) {
		super();
		this.promotion = promotion;
	}

	public Filiere(Long id, String intitule, String promotion, Date dtDebut, Integer duree, Dispositif dispositif) {
		super();
		this.id = id;
		this.intitule = intitule;
		this.promotion = promotion;
		this.dtDebut = dtDebut;
		this.duree = duree;
		this.dispositif = dispositif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Date getDtDebut() {
		return dtDebut;
	}

	public void setDtDebut(Date dtDebut) {
		this.dtDebut = dtDebut;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Dispositif getDispositif() {
		return dispositif;
	}

	public void setDispositif(Dispositif dispositif) {
		this.dispositif = dispositif;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public void addStagiaire(Stagiaire stagiaire) {
		this.stagiaires.add(stagiaire);
	}

	public List<UE> getUes() {
		return ues;
	}

	public void setUes(List<UE> ues) {
		this.ues = ues;
	}

	public void addUe(UE ue) {
		this.ues.add(ue);
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	@Override
	public String toString() {
		return "Filiere [id=" + id + ", intitule=" + intitule + ", promotion=" + promotion + ", dtDebut=" + dtDebut
				+ ", duree=" + duree + ", dispositif=" + dispositif + ", stagiaires=" + stagiaires + ", ues=" + ues
				+ ", referent=" + referent + "]";
	}

}
