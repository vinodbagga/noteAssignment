package com.assignment.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.demo.dto.Note;

/*
 * This is the JPA repository, helps to perform all DB related or CRUD operations 
 * 
 * */

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	
	@Query(value ="Select * from note_tbl n where n.userid=:userid",nativeQuery=true)
	List<Note> getNoteByUserId(@Param("userid") int userid);
	
	
	
	
	
	
}
