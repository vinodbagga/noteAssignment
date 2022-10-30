package com.assignment.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.demo.dto.Note;
import com.assignment.demo.repositories.NoteRepository;
import com.assignment.demo.services.INote;


/*
 * 
 * Service class implementing the required interface
 * business logic implementation 
 * 
 * */
@Service
public class NoteImpl implements INote {

	@Autowired
	NoteRepository noteRepositiory;

	@Override
	public Note addNote(Note note) {
		return noteRepositiory.save(note);
	}

	@Override
	public Optional<Note> getNoteById(int noteid) {
		return noteRepositiory.findById(noteid);
	}

	@Override
	public Note updateNoteById(Note note) {

		return noteRepositiory.save(note);
	}

	@Override
	public void deleteNoteById(int noteid) {
		noteRepositiory.deleteById(noteid);
	}

	@Override
	public List<Note> getNoteByUserId(int userid) {
		return noteRepositiory.getNoteByUserId(userid);
	}

}
