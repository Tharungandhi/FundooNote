package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.util.EmailUtil;
import com.bridgelabz.spring.util.GenerateTokenImlp;

@Service
public class UserServiceImlp implements UserService  {

	@Autowired
	private EmailUtil emailutil;
	
	@Autowired
	private UserDao userDao;

	   @Autowired
	    private PasswordEncoder bcryptEncoder;
	   	   
	@Autowired
	private GenerateTokenImlp generateToken;
	
	@Transactional
	public boolean register(UserDetails user, HttpServletRequest request) {
		int id = userDao.register(user);
		if (id > 0) {
			String token = generateToken.generateToken(String.valueOf(id));
			System.out.println(token);
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			String verificationLink="http://localhost:8080/FundooNote/activationstatus/"+token;
			emailutil.sendEmail("tharungandhi636@gmail.com","Verification mail", verificationLink);
			return true;
		}
		return false;
}

	
	@Transactional
	public UserDetails login(String emailId, String password, HttpServletRequest request,HttpServletResponse response) {
		UserDetails user=userDao.login(emailId);
		if(bcryptEncoder.matches(password, user.getPassword())  &&  user.isActivationStatus()==true) {
			String token = generateToken.generateToken(String.valueOf(user.getId()));
			response.setHeader("Tokens", token);
		return user;
		
	}
		return null;}
	
	@Transactional
	public UserDetails update(int id,UserDetails user,HttpServletRequest request)
	{
		UserDetails user12=userDao.getUserByID(id);
		if(user12!=null) {
			  user12.setName(user.getName());
			  user12.setEmailId(user.getEmailId());
			  user12.setPassword(user.getPassword());
	           user12.setMobileNumber(user.getMobileNumber());
	          userDao.update(id, user12);
	}
		return user12;}

	@Transactional
	public UserDetails delete(int id,HttpServletRequest request) {
		String emailId = null;
		UserDetails user12=userDao.getUserByID(id);
		userDao.delete(id);
		return user12;
	}

	
	  public UserDetails activateUser(String token, HttpServletRequest request) {
	        int id=generateToken.verifyToken(token);
	        UserDetails user=userDao.getUserByID(id);
	        if(user!=null)
	        {
	            user.setActivationStatus(true);
	            userDao.update(id, user);
	        }
	        return user;
	    }


	
	
	
	

	

	

	}

