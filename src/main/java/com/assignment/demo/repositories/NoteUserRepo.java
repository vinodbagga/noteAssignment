package com.assignment.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.demo.dto.User;

/*
 * This is the JPA repository, helps to perform all DB related or CRUD operations 
 * 
 * */

@Repository
public interface NoteUserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
