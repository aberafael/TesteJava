package br.com.sabium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sabium.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
