package com.assignment.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.demo.dto.NoteCustomUserDetails;
import com.assignment.demo.dto.User;
import com.assignment.demo.repositories.NoteUserRepo;

/*
 * This class is used to retrieving a username, password and other things for authentication used by DaoAuthenticationProvider
 * 
 * 
 * */

@Service
public class NoteCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private NoteUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(username+" is not found.");
		}
		System.out.println("Username :: "+user.getUsername());
		System.out.println("Password :: "+user.getPassword());
		return new NoteCustomUserDetails(user);
		
	}
	
	

}
