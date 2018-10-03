package br.com.sabium.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sabium.entity.Project;
import br.com.sabium.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

@Service
@Transactional
public class ProjectService {

	static Logger log = Logger.getLogger(ProjectService.class);
	
	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> findAll() {
		log.info("Find All Projects");
		return projectRepository.findAll();
	}
	
	public Optional<Project> findById(Long id) {
		log.info("Find Project by Id: " + id);
		return projectRepository.findById(id);
	}
	
	public Project save(Project project) {
		log.info("Save Project: " + project.getName());
		return projectRepository.save(project);
	}
	
	public void deleteAll() {
		log.info("Delete All Projects");
		projectRepository.deleteAll();
	}
	
	public long count() {
		log.info("Count Projects");
		return projectRepository.count();
	}
}
