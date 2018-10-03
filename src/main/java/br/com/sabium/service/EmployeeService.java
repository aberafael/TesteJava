package br.com.sabium.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sabium.entity.Employee;
import br.com.sabium.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

@Service
@Transactional
public class EmployeeService {

	static Logger log = Logger.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Optional<Employee> findById(Long id) {
		log.info("Find Employee by Id: " + id);
		return employeeRepository.findById(id);
	}

	public List<Employee> findAll() {
		log.info("Find All Employees");
		return employeeRepository.findAll();
	}

	public List<Employee> findMultiProjectEmployees() {
		log.info("Find Multiproject Employees");
		return employeeRepository.findMultiProjectEmployees();
	}

	public Employee save(Employee employee) {
		log.info("Save Employee: " + employee.getName());
		return employeeRepository.save(employee);
	}

	public void deleteAll() {
		log.info("Delete All Employees");
		employeeRepository.deleteAll();
	}

	public long count() {
		log.info("Count Employees");
		return employeeRepository.count();
	}	
}