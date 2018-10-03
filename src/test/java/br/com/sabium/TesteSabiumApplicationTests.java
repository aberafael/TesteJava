package br.com.sabium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sabium.entity.Employee;
import br.com.sabium.service.EmployeeService;
import br.com.sabium.entity.Project;
import br.com.sabium.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteSabiumApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;

	@Before
	public void setUp() {
		employeeService.deleteAll();
		projectService.deleteAll();
	}

	
	@Test
	public void saveEmployee() {
		Employee employee = new Employee("José", 5000.00);
		employee = employeeService.save(employee);
		
		assertNotNull(employee.getId());
	}

	@Test
	public void saveProject() {
		Project project = new Project("New Project");
		project = projectService.save(project);

		assertNotNull(project.getId());
	}

	@Test
	public void oneProject() {
		Employee employee = new Employee("José", 5000.00);
		Project project1 = new Project("New Project 1");
		employee.addProject(project1);
		assertThat(1).isEqualTo(employee.getProjects().size());
	}

	@Test
	public void twoProjects() {
		Employee employee = new Employee("José", 5000.00);
		Project project1 = new Project("New Project 1");
		Project project2 = new Project("New Project 2");
		employee.addProject(project1);
		employee.addProject(project2);
		assertThat(2).isEqualTo(employee.getProjects().size());
	}

	@Test
	public void threeProjects() {
		Employee employee = new Employee("José", 5000.00);
		Project project1 = new Project("The Project 1");
		Project project2 = new Project("The Project 2");
		Project project3 = new Project("The Project 3");
		employee.addProject(project1);
		employee.addProject(project2);
		employee.addProject(project3);
		assertThat(2).isEqualTo(employee.getProjects().size());
	}
	
	@Test
	public void multiProjectEmployees() {
		Employee employee = new Employee("José", 5000.00);
		Project project1 = new Project("Project 1");
		Project project2 = new Project("Project 2");
		employee.addProject(project1);
		employee.addProject(project2);
		employeeService.save(employee);
		
		Employee employee2 = new Employee("Joaquim", 3000.00);
		Project project3 = new Project("Application Deploy");
		employee2.addProject(project3);
		employeeService.save(employee2);
		
		assertThat(1).isEqualTo(employeeService.findMultiProjectEmployees().size());
	}
}
