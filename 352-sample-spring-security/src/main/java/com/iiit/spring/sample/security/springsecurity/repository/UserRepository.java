package com.iiit.spring.sample.security.springsecurity.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiit.spring.sample.security.springsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.username = ?1") 
	Optional<User> findByUserName(String userName);
}
