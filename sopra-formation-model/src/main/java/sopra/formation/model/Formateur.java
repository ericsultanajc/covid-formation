package sopra.formation.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // obligatoire
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	@Column(name="referent_person", nullable = false)
	private Boolean referent;
	@Column(name="background")
	private Integer experience;
	@Transient
	private ArrayList<UE> ues = new ArrayList<UE>();
	@Transient
	private ArrayList<Matiere> competences = new ArrayList<Matiere>();

	public Formateur() {
		super();
	}

	public Formateur(String email) {
		super(email);
	}
	
	public Formateur(Long id, Civilite civilite, String nom, String prenom, String email, String telephone, Boolean referent, Integer experience) {
		super(id, civilite, nom, prenom, email, telephone);
		this.referent = referent;
		this.experience = experience;
	}

	public Boolean getReferent() {
		return referent;
	}

	public void setReferent(Boolean referent) {
		this.referent = referent;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
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

	public ArrayList<Matiere> getCompetences() {
		return competences;
	}

	public void setCompetences(ArrayList<Matiere> competences) {
		this.competences = competences;
	}

	public void addCompetence(Matiere matiere) {
		this.competences.add(matiere);
	}

	@Override
	public String toString() {
		return "Formateur [referent=" + referent + ", experience=" + experience + ", competences=" + competences + "]";
	}

}
