package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity // obligatoire
@Table(name = "Person") // optionnel
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="disc")
public abstract class Personne {
	@Id // obligatoire
	@GeneratedValue // optionnel
	private Long id;
	@Column(name = "civility",length=4)
	@NotEmpty
	private Civilite civilite;
	@Column(name = "name")
	@NotEmpty
	private String nom;
	@Column(name = "first_name")
	private String prenom;
	@Column(name = "mail")
	@NotEmpty
	private String email;
	@Column(name = "phone_number")
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
