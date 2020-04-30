package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING, length = 15)
public abstract class Personne {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	@Enumerated(EnumType.STRING)
	@Column(name = "civility", length = 5)
	@NotNull(message="La civilité est obligatoire")
	private Civilite civilite;
	@Column(name = "last_name", length = 100, nullable = false)
	@Size(min = 2, max = 100, message="Le nom doit comporter au moins 2 caractères et 100 au maximum")
	private String nom;
	@NotEmpty(message="Le prénom est obligatoire")
	@Column(name = "first_name", length = 100)
	@Size(min = 1, message = "Le prénom est obligatoire")
	private String prenom;
	@Column(length = 255, nullable = false)
	@NotEmpty(message = "Le courriel est obligatoire")
	@Email(message = "Le courriel est invalide")
	private String email;
	@Column(name = "phonenumber", length = 15)
//	@Pattern(regexp = "(\\+33|0)[0-9]{9}", message="Numéro pas bon")
	@Pattern(regexp = "^\\+?[0-9\\s]*", message = "Seul les chiffres, espaces ou + sont autorisés")
	private String telephone;
	@Embedded
	private Adresse adresse;

	public Personne() {
		super();
	}

	public Personne(String email) {
		super();
		this.email = email;
	}

	public Personne(Civilite civilite, String nom, String prenom, String email, String telephone) {
		super();
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
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
