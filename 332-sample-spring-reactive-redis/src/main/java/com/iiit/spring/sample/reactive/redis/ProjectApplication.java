package com.iiit.spring.sample.reactive.redis;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ProjectApplication {

	
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
    

    @Bean
    public ReactiveRedisTemplate<String, Project> reactiveJsonProjectRedisTemplate(
        ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<String, Project> serializationContext = RedisSerializationContext
            .<String, Project>newSerializationContext(new StringRedisSerializer())
            .hashKey(new StringRedisSerializer())
            .hashValue(new Jackson2JsonRedisSerializer<>(Project.class))
            .build();


        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean
    public RouterFunction<ServerResponse> projetRoutes(ProjectHandler projectController) {
        return route(GET("/projects"), projectController::all)
            .andRoute(POST("/projects"), projectController::create)
            .andRoute(GET("/projects/{id}"), projectController::get)
            .andRoute(PUT("/projects/{id}"), projectController::update)
            .andRoute(DELETE("/projects/{id}"), projectController::delete);
    }

}

@Component
@Slf4j
class ProjectInitializer implements CommandLineRunner {

    private final ProjectRepository projects;

    public ProjectInitializer(ProjectRepository projects) {
        this.projects = projects;
    }

    @Override
    public void run(String[] args) {
        log.info("start Project data initialization  ...");
        this.projects
            .deleteAll()
            .thenMany(
                Flux
                    .just("Project one", "Project two")
                    .flatMap(
                        title -> {
                            String id = UUID.randomUUID().toString();
                            return this.projects.save(Project.builder().id(id).title(title).content("content of " + title).build());
                        }
                    )
            )
            .log()
            .subscribe(
                null,
                null,
                () -> log.info("done initialization...")
            );

    }

}

@Component
class ProjectHandler {

    private final ProjectRepository projects;

    public ProjectHandler(ProjectRepository projects) {
        this.projects = projects;
    }

    public Mono<ServerResponse> all(ServerRequest req) {
        return ServerResponse.ok().body(this.projects.findAll(), Project.class);
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(Project.class)
            .flatMap(project -> this.projects.save(project))
            .flatMap(p -> ServerResponse.created(URI.create("/projects/" + p.getId())).build());
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        return this.projects.findById(req.pathVariable("id"))
            .flatMap(project -> ServerResponse.ok().body(Mono.just(project), Project.class))
            .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest req) {
    	/*
    	this.projects.deleteById(req.pathVariable("id"));
    	
    	return req.bodyToMono(Project.class)
                .flatMap(project -> this.projects.save(project))
                .flatMap(p -> ServerResponse.created(URI.create("/projects/" + p.getId())).build());
    	*/
    	
        return Mono
            .zip(
                (data) -> {
                	Project p = (Project) data[0];
                	Project p2 = (Project) data[1];
                    p.setTitle(p2.getTitle());
                    p.setContent(p2.getContent());
                    return p;
                },
                this.projects.findById(req.pathVariable("id")),
                //this.projects.deleteById(req.pathVariable("id")),
                req.bodyToMono(Project.class)
            )
            .cast(Project.class)
            .flatMap(project -> this.projects.save(project))
            .flatMap(project -> ServerResponse.noContent().build());
         
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        return ServerResponse.noContent().build(this.projects.deleteById(req.pathVariable("id")));
    }

}

@Component
class ProjectRepository {

    ReactiveRedisOperations<String, Project> template;

    public ProjectRepository(ReactiveRedisOperations<String, Project> template) {
        this.template = template;
    }

    Flux<Project> findAll() {
        return template.<String, Project>opsForHash().values("projects");
    }

    Mono<Project> findById(String id) {
        return template.<String, Project>opsForHash().get("projects", id);
    }

    Mono<Project> save(Project project) {
        if (project.getId() != null) {
            String id = UUID.randomUUID().toString();
            project.setId(id);
        }
        return template.<String, Project>opsForHash().put("projects", project.getId(), project)
            .log()
            .map(p -> project);

    }

    Mono<Void> deleteById(String id) {
        return template.<String, Project>opsForHash().remove("projects", id)
            .flatMap(p -> Mono.<Void>empty());
    }

    Mono<Boolean> deleteAll() {
        return template.<String, Project>opsForHash().delete("projects");
    }

}

//@RedisHash("projects")
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
    private LocalDateTime createdDate;
}
