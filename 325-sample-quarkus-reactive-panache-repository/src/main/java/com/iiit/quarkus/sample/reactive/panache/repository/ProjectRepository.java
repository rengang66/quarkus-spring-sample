package com.iiit.quarkus.sample.reactive.panache.repository;



import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import com.iiit.quarkus.sample.reactive.panache.domain.Project;;

@ApplicationScoped
public class ProjectRepository implements PanacheRepositoryBase<Project, Long> {

  public Uni<Project> findByName(String name) {
	  return find("name", name).firstResult();
  }
}
