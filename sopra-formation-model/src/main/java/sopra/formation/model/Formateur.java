package sopra.formation.model;

import java.util.ArrayList;

public class Formateur extends Personne {
	private Boolean referent;
	private Integer experience;
	private ArrayList<UE> ues = new ArrayList<UE>();
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
