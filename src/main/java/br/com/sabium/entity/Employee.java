package br.com.sabium.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 2982775622518754044L;

	private static final int PROJECTS_ALLOWED = 2;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_employee")
	@SequenceGenerator(name="seq_employee", sequenceName = "seq_employee", initialValue=1, allocationSize=1)
    private Long id;
	
	@Column(name = "name", nullable = false)
	@Size(min = 2, max = 300)
	private String name;
	
	@Column(name = "salary")
	private double salary;

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "employee_project", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<Project> projects = new HashSet<>(); //Set garante que não vai ter duplicações
	
	public Employee() {
		
	}
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;		
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Retorna uma lista imutável, para evitar que utilizem dando get na lista e dando add sem validar a qtde permitida de projetos
	 * @return
	 */
	public Set<Project> getProjects() {
		return Collections.unmodifiableSet(this.projects);
	}

	/**
	 * A inserção é feita pelo Employee, então aqui valida a quantidade permitida de projetos
	 * @param project
	 */
	public void addProject(Project project) {
		if (this.projects.size() < PROJECTS_ALLOWED)
			this.projects.add(project);
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
		Employee other = (Employee) obj;
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
