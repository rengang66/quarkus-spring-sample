package com.iiit.quarkus.sample.security.jwt.security;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_entity")
public class User extends PanacheEntity {
    public String username;
    public String email;
    public String password;
    
    /**
     * Adds a new user in the database
     * @param username the user name
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     */
    public static void add(String username, String email, String password) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.email = email;
        user.persist();
    } 
    
}
