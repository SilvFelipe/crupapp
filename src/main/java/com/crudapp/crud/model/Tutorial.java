package com.crudapp.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial")
public class Tutorial {
		
	@Id
	@SequenceGenerator(name = "tutorial_tutorial_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "tutorial_tutorial_id_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "tutorial_id")
	private Long idTutorial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "published")
	private Boolean published;

	
	public Tutorial() {
		super();
	}
	
	
	public Long getIdTutorial() {
		return idTutorial;
	}
	public void setIdTutorial(Long idTutorial) {
		this.idTutorial = idTutorial;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	@Override
	public String toString() {
		return "Tutorial [idTutorial=" + idTutorial + ", title=" + title + ", description=" + description
				+ ", published=" + published + "]";
	}
	
}
