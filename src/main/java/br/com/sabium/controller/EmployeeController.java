package br.com.sabium.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabium.entity.Employee;
import br.com.sabium.entity.Project;
import br.com.sabium.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = employeeService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		try {
			Employee employee = employeeService.findById(id).get();
			
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		try {
			employee = employeeService.save(employee);

			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value="/employees/{id}/projects", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Employee> addProject(@PathVariable Long id, @RequestBody Project project) {
		try {
			Employee employee = employeeService.findById(id).get();
			employee.getProjects().add(project);
			employee = employeeService.save(employee);
			
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@RequestMapping(value="/multiprojectemployees", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Employee>> getMultiProjectEmployees() {
		List<Employee> employees = employeeService.findMultiProjectEmployees();
		
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
}
