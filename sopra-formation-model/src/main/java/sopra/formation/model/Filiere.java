package sopra.formation.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity // obligatoire
@Table(name = "studypath")
public class Filiere {
	@Id // obligatoire
	@Column(name="id", columnDefinition="VARCHAR(10)", nullable=false)
	@Size(max=10)
	private Long id;
	@Column(name="intitule", columnDefinition="VARCHAR(100)", nullable=false)
	@Size(max=100)
	private String intitule;
	@Column(name="promotion", columnDefinition="VARCHAR(100)")
	@Size(max=100)
	private String promotion;
	@Column(name="dtDebut", columnDefinition="VARCHAR(100)")
	@Size(max=100)
	@Temporal(TemporalType.DATE)
	private Date dtDebut;
	@Column(name="duree", columnDefinition="DECIMAL(3)")
	@Size(max=100)
	private Integer duree;
	@Transient
	private Dispositif dispositif;
	@Transient
	private ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	@Transient
	private ArrayList<UE> ues = new ArrayList<UE>();
	@Transient
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

	public ArrayList<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(ArrayList<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public void addStagiaire(Stagiaire stagiaire) {
		this.stagiaires.add(stagiaire);
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
