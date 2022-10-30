package com.assignment.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.demo.dto.Note;
import com.assignment.demo.dto.User;
import com.assignment.demo.exceptions.ResourceNotFoundException;
import com.assignment.demo.services.INote;
import com.assignment.demo.services.IUser;

/*
 * Rest controller used to define the rest API
 * GetMapping, PostMapping... and many more...
 * In this controller we are using JSON for request body and out come will be JSON format while fetching the details of note.
 * 
 * */

@RestController
@RequestMapping("/note/api/")
public class NoteController {

	@Autowired
	INote iNoteService;

	@Autowired
	IUser userService;

	/*
	 * Used to save the note by passing the request body in JSON format and user id
	 * 
	 * 
	 */
	@PostMapping("/addnote/{userid}")
	public Note addNewNote(@RequestBody Note note, @PathVariable int userid) {
		System.out.println("User id = " + userid);
		Optional<User> noteObj = userService.getUserById(userid);
		if (noteObj.isPresent()) {
			note.setUserid(userid);
			return iNoteService.addNote(note);

		} else {
			return new Note();
		}

	}

	/*
	 * used to get detail of note only getNoteById : method is used to fetch the
	 * note details by note id.
	 * 
	 */

	@GetMapping("/note/{noteid}")
	public Optional<Note> getNoteById(@PathVariable int noteid) {
		return iNoteService.getNoteById(noteid);
	}

	/*
	 * Used to get list of notes getNoteByUser : method is used to fetch list of all
	 * note details using related user id. passing the user id, it will show list of
	 * all related users note.
	 * 
	 */
	@GetMapping("/note/user/{userId}")
	public List<Note> getNoteByUser(@PathVariable int userId) {
		return iNoteService.getNoteByUserId(userId);
	}

	/*
	 * Used to update the details of created note updateNoteById: method is used to
	 * update the note details using note id. user id is used to check the existence
	 * of user with related note.
	 * 
	 */
	@PutMapping("/updatenote/{noteid}/user/{userid}")
	public ResponseEntity<?> updateNoteById(@RequestBody Note note, @PathVariable int noteid, @PathVariable int userid)
			throws ResourceNotFoundException {

		Note updatedNote = null;

		Optional<User> userObj = userService.getUserById(userid);
		System.out.println("userObj !!!!" + userObj);
		if (userObj.isPresent()) {
			Note noteObj = iNoteService.getNoteById(noteid)
					.orElseThrow(() -> new ResourceNotFoundException("Resource is not available."));

			if (noteObj.getUserid() == userid) {
				noteObj.setTitle(note.getTitle());
				noteObj.setNote(note.getNote());
				updatedNote = iNoteService.addNote(noteObj);
			} else {
				updatedNote = new Note();

				return ResponseEntity.ok(updatedNote);
			}

		} else {
			User user = new User();
			user.setUserid(userid);
			user.setUsername("User does not exist.");
			return ResponseEntity.ok(user);
		}

		return ResponseEntity.ok(updatedNote);

	}

	/*
	 * Used to delete the already created note deleteNote: method is used to delete
	 * the existing note by passing note id and user id is used to check the
	 * existence of user with related note.
	 * 
	 * 
	 */
	@DeleteMapping("/deletenote/{noteid}/user/{userid}")
	public ResponseEntity<Map<String, Boolean>> deleteNote(@PathVariable int noteid, @PathVariable int userid)
			throws ResourceNotFoundException {

		Map<String, Boolean> responseMap = null;
		Optional<User> userObj = userService.getUserById(userid);
		if (userObj.isPresent()) {
			Note noteObj = iNoteService.getNoteById(noteid)
					.orElseThrow(() -> new ResourceNotFoundException("Note id is not available, Resource Not Found"));
			if (noteObj.getUserid() == userid) {
				iNoteService.deleteNoteById(noteObj.getNoteid());
				responseMap = new HashMap<String, Boolean>();
				responseMap.put("is note deleted", true);
			} else {
				responseMap = new HashMap<String, Boolean>();
				responseMap.put("User is not matched, Note can not be deleted", false);
			}
		} else {
			responseMap = new HashMap<String, Boolean>();
			responseMap.put("User is not available, Note can not be deleted", false);
		}
		return ResponseEntity.ok(responseMap);

	}

}
