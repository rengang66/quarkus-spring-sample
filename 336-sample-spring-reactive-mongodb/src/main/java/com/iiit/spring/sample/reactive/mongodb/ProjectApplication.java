package com.iiit.spring.sample.reactive.mongodb;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}


@Component
@Slf4j
class ProjectDataInitializer implements CommandLineRunner {

    private final ProjectRepository projects;

    public ProjectDataInitializer(ProjectRepository projects) {
        this.projects = projects;
    }

    @Override
    public void run(String[] args) {
        log.info("start Project data initialization ...");
        this.projects
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> this.projects.save(Project.builder().title(title).content("content of " + title).build())
                                )
                )
                .thenMany(
                        this.projects.findAll()
                )
                .subscribe(
                        data -> log.info("found posts: {}", projects),
                        error -> log.error("" + error),
                        () -> log.info("done initialization...")
                );

    }

}

@RestController()
@RequestMapping(value = "/projects")
class ProjectController {

    private final ProjectRepository projects;

    public ProjectController(ProjectRepository projects) {
        this.projects = projects;
    }

    @GetMapping("")
    public Flux<Project> all() {
        return this.projects.findAll();
    }

    @PostMapping("")
    public Mono<Project> create(@RequestBody Project project) {
        return this.projects.save(project);
    }

    @GetMapping("/{id}")
    public Mono<Project> get(@PathVariable("id") String id) {
        return this.projects.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Project> update(@PathVariable("id") String id, @RequestBody Project project) {
        return this.projects.findById(id)
                .map(p -> {
                    p.setTitle(project.getTitle());
                    p.setContent(project.getContent());

                    return p;
                })
                .flatMap(this.projects::save);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return this.projects.deleteById(id);
    }

}

interface ProjectRepository extends ReactiveMongoRepository<Project, String> {

    @Query(
            value = """
                    {
                         "title" : {
                             "$regularExpression" : { "pattern" : ?0, "options" : ""}
                         }
                    }
                    """,
            sort = """
                    { 
                        "title" : 1 , 
                        "createdDate" : -1
                    } 
                    """
    )
    Flux<Project> findByKeyword(String q);

    Flux<ProjectSummary> findByTitleContains(String title);

    Flux<ProjectSummary> findByTitleContains(String title, Pageable page);
}

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Project {
	
    @Id
    private String id;
    private String title;
    private String content;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();
}

@Data
class ProjectSummary {
    private String title;
}

