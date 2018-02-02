package br.com.university;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professors")
public class Professor {

	@Id
	@Column(name = "ProfId")
	private int profId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Admission")
	private LocalDate admission;
	
	@Column(name = "ResearchArea")
	private String researchArea;
	
	@OneToMany(mappedBy = "professor")
	private Set<UniClass> uniClasses = new HashSet<UniClass>();

	public Professor() {}
	
	public Professor(int profId, String name, LocalDate admission, String researchArea) {
		this.profId = profId;
		this.name = name;
		this.admission = admission;
		this.researchArea = researchArea;
	}

	public int getProfId() {
		return profId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAdmission() {
		return admission;
	}

	public void setAdmission(LocalDate admission) {
		this.admission = admission;
	}

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public Set<UniClass> getUniClasses() {
		return uniClasses;
	}

	public void setUniClasses(Set<UniClass> uniClasses) {
		this.uniClasses = uniClasses;
	}

}