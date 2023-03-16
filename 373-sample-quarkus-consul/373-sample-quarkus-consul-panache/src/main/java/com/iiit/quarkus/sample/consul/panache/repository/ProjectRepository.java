package com.iiit.quarkus.sample.consul.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import com.iiit.quarkus.sample.consul.panache.domain.Project;

@ApplicationScoped
public class ProjectRepository implements PanacheRepository<Project> {

  public List<Project> findByCountry(String name) {
    return list("SELECT m FROM iiit_projects m WHERE m.name = ?1 ORDER BY id DESC", name);
  }
}
