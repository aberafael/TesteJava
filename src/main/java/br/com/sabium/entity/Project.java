package br.com.sabium.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="project")
public class Project implements Serializable {

	private static final long serialVersionUID = -735666138370335763L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_project")
	@SequenceGenerator(name="seq_project", sequenceName = "seq_project", initialValue=1, allocationSize=1)
    private Long id;
	
	@Column(name = "name", nullable = false, unique = true)
	@Size(min = 2, max = 300)
	private String name;

	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
    private Set<Employee> employees = new HashSet<>(); //Set garante que não vai ter duplicações
	
	public Project() {
		
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return Collections.unmodifiableSet(this.employees);
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
