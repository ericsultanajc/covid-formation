package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // obligatoire
@Table(name = "individual") // optionnel 
@DiscriminatorColumn(name="disc") 
public abstract class Personne {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name="civility")
	private Civilite civilite;
	@Column(name="name")
	@NotEmpty 
	private String nom;
	@Column(name="firstname")
	private String prenom;
	@Column(name="mail", length=50)
	@Size(max=50)
	@NotEmpty 
	private String email;
	@Column(name="phone", length=15)
	@Size(max=15)
	private String telephone;
	@Transient
	private Adresse adresse;

	public Personne() {
		super();
	}

	public Personne(String email) {
		super();
		this.email = email;
	}

	public Personne(Long id, Civilite civilite, String nom, String prenom, String email, String telephone) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setAdresse(String rue, String complement, String codePostal, String ville) {
		this.adresse = new Adresse(rue, complement, codePostal, ville);
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", adresse=" + adresse + "]";
	}

}
