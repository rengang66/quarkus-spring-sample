package com.iiit.quarkus.sample.security.jwt.resource;

import java.util.List;

import com.iiit.quarkus.sample.security.jwt.security.User;
import com.iiit.quarkus.sample.security.jwt.security.TokenService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    TokenService service;

    @POST
    @Path("/register")
    @Transactional
    public User register(User user) {
        user.persist(); //super simplified registration, no checks of uniqueness
        return user;
    }

    @GET
    @Path("/login")
    public String login(@QueryParam("username")String login, @QueryParam("password") String password) {
        User existingUser = User.find("username", login).firstResult();
        if(existingUser == null || !existingUser.password.equals(password)) {
            throw new WebApplicationException(Response.status(404).entity("No user found or password is incorrect").build());
        }
        return service.generateUserToken(existingUser.email, password);
    }   
    
    @GET
    @PermitAll
    public List<User> get() {
        return User.findAll().list();
    }
    
}
