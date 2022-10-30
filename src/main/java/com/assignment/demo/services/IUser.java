package com.assignment.demo.services;

import java.util.Optional;
import com.assignment.demo.dto.User;

/*
 * interface:: declared the required methods
 * will be implemented or defined in their implementation classes.
 * 
 * */
public interface IUser {
	
	Optional<User> getUserById(int userid);

}
