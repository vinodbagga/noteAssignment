package com.assignment.demo.services;

import java.util.List;
import java.util.Optional;

import com.assignment.demo.dto.Note;

/*
 * interface:: declared the required methods
 * will be implemented or defined in their implementation classes.
 * 
 * */

public interface INote {
	
	Note addNote(Note note);
	Optional<Note> getNoteById(int noteid);
	Note updateNoteById(Note note);
	void deleteNoteById(int noteid);
	List<Note> getNoteByUserId(int userid);
	

}
