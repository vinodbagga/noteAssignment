package com.assignment.demo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * this is data transfer class DTO,
 * used to map the properties with database table
 * 
 * */

@Entity
@Table(name = "user")
public class User {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int userid;
	private String username;
	private String password;
	private String role;
	private Date created_time;
	private Date last_updated_time;

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", created_time=" + created_time + ", last_updated_time=" + last_updated_time + "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getLast_updated_time() {
		return last_updated_time;
	}

	public void setLast_updated_time(Date last_updated_time) {
		this.last_updated_time = last_updated_time;
	}

}
