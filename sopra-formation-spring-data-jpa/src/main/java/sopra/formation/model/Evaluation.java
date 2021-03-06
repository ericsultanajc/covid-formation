package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity // obligatoire
@Table(name = "rating") // optionnel
public class Evaluation {
	@Id // obligatoire
	@GeneratedValue // optionnelle
	private Long id;
	@Version
	private int version;
	@NotNull(message="{evaluation.comportemental.notNull}")
	@Min(value = 0, message="{evaluation.comportemental.min}")
	@Max(value = 20, message="{evaluation.comportemental.max}")
	@Column(name = "behaviour", nullable = false)
	private Integer comportemental;
	@Column(name = "technical")
	@NotNull(message="{evaluation.technique.notNull}")
	private Integer technique;
	@Column(name = "comments", length = 4000)
	@NotEmpty(message="{evaluation.commentaires.notEmpty}")
	private String commentaires;

	public Evaluation() {
		super();
	}

	public Evaluation(Integer comportemental, Integer technique, String commentaires) {
		super();
		this.comportemental = comportemental;
		this.technique = technique;
		this.commentaires = commentaires;
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

	public Integer getComportemental() {
		return comportemental;
	}

	public void setComportemental(Integer comportemental) {
		this.comportemental = comportemental;
	}

	public Integer getTechnique() {
		return technique;
	}

	public void setTechnique(Integer technique) {
		this.technique = technique;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", comportemental=" + comportemental + ", technique=" + technique
				+ ", commentaires=" + commentaires + "]";
	}

}
