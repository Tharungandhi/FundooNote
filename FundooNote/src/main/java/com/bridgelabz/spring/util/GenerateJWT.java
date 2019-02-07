package com.bridgelabz.spring.util;

public interface GenerateJWT {

	   String generateToken(String id);
	   
	    int verifyToken(String token);
}
