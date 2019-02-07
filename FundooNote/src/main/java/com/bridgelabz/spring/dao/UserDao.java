package com.bridgelabz.spring.dao;

import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {

	Object user = null;

	int register(UserDetails user);
	
	UserDetails login(String emailId);
	
	UserDetails getUserByID(int id);
	
	UserDetails update(int id, UserDetails user12);
	
	void delete(int id);





}

