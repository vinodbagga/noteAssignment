package com.assignment.demo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.demo.dto.User;
import com.assignment.demo.repositories.NoteUserRepo;
import com.assignment.demo.services.IUser;

/*
 * 
 * Service class implementing the required interface
 * business logic implementation 
 * 
 * */

@Service
public class IUserImpl implements IUser {
	
	@Autowired NoteUserRepo userRepo;

	@Override
	public Optional<User> getUserById(int userid) {
		return userRepo.findById(userid);
	}

	
}
