package com.iiit.spring.sample.orm.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiit.spring.sample.orm.hibernate.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	Optional<Project> findByName(String name);
}
