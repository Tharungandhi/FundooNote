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
public class UserServiceImlp implements UserService {

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
			String verificationLink = "http://localhost:8080/FundooNote/activationstatus/" + token;
			emailutil.sendEmail("tharungandhi636@gmail.com", "Verification mail", verificationLink);
			return true;
		}
		return false;
	}

	@Transactional
	public UserDetails login(UserDetails user, HttpServletRequest request,HttpServletResponse response) {
		UserDetails newUser = userDao.login(user.getEmailId());
		if (bcryptEncoder.matches(user.getPassword(),newUser.getPassword() ) && newUser.isActivationStatus() == true) {
			String token = generateToken.generateToken(String.valueOf(newUser.getId()));
			response.setHeader("Tokens", token);
			return newUser;

		}
		return null;
	}

	@Transactional
	public UserDetails update(String token, UserDetails user, HttpServletRequest request) {
		int id = generateToken.verifyToken(token);
		UserDetails updatedUser = userDao.getUserByID(id);
		if (updatedUser != null) {
			updatedUser.setName(user.getName());
			updatedUser.setEmailId(user.getEmailId());
			updatedUser.setPassword(user.getPassword());
			updatedUser.setMobileNumber(user.getMobileNumber());
			userDao.update(id, updatedUser);
		}
		return updatedUser;
	}
	

	@Transactional
	public UserDetails delete( String token,HttpServletRequest request) {
		int id = generateToken.verifyToken(token);
		UserDetails newUser = userDao.getUserByID(id);
		if(newUser!=null) {
		userDao.delete(id);}
		return newUser;
	}

	@Transactional
	public UserDetails activateUser(String token, HttpServletRequest request) {
		int id = generateToken.verifyToken(token);
		UserDetails user = userDao.getUserByID(id);
		if (user != null) {
			user.setActivationStatus(true);
			userDao.update(id, user);
		}
		return user;
	}

	@Transactional
	public boolean forgotPassword(String emailId, HttpServletRequest request) {
		UserDetails user = userDao.login(emailId);
		if (user != null) {
			String token = generateToken.generateToken(String.valueOf(user.getId()));
			String forgotPasswordLink = "http://localhost:8080/FundooNote/resetpassword/" + token;
			emailutil.sendEmail("tharungandhi636@gmail.com", "Reset Password Link", forgotPasswordLink);
		}
		return false;

	}

	@Transactional
	public UserDetails resetPassword(UserDetails user, String token, HttpServletRequest request) {
		int id = generateToken.verifyToken(token);
		UserDetails reSetUser = userDao.getUserByID(id);
		if (reSetUser != null) {
			reSetUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			userDao.update(1, reSetUser);
			return reSetUser;
		}
		return null;
	}

}
