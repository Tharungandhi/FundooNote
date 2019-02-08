package com.bridgelabz.spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	

	boolean register(UserDetails user, HttpServletRequest request);
		
   UserDetails update(String token,UserDetails user,HttpServletRequest request);

	UserDetails login(UserDetails user, HttpServletRequest request,HttpServletResponse response);
	
	UserDetails  delete(String token, HttpServletRequest request);
	
	UserDetails activateUser(String token, HttpServletRequest request);
	
	boolean forgotPassword(String emailid,HttpServletRequest request);
	
	UserDetails resetPassword(UserDetails user,String token,HttpServletRequest request);
	
	}

