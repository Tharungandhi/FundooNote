package com.bridgelabz.spring.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.service.UserService;

@RestController
public class USerController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody UserDetails user, HttpServletRequest request) {
			try {
				if (userService.register(user, request))
					return new ResponseEntity<String>("User Succesfully registered",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Details are not proper",HttpStatus.CONFLICT);
			}
		return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> login( @RequestParam("emailId") String emailId,@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
		try {
        UserDetails userDetails = userService.login(emailId,password, request,response);
        if(userDetails!= null) 
            return new ResponseEntity<UserDetails>(userDetails, HttpStatus.FOUND);
       
    }catch (Exception e) {
		e.printStackTrace();
        return new ResponseEntity<String>("User not Found by  Email Id",HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	
	   @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public ResponseEntity<String> update(@RequestParam("id") int id,@RequestBody UserDetails user, HttpServletRequest request)
	    {
	        try {
	            if (userService.update(id,user, request)!=null)
	                return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.CONFLICT);
	        }
	        return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	    }
	   
	   @RequestMapping(value="/delete",method=RequestMethod.DELETE)
	   public ResponseEntity<String> deleteNote(@RequestParam("id") int id ,HttpServletRequest request)
	   {
		   try {
			   if (userService.delete(id,request)!=null)
				   return new ResponseEntity<String>("Note Succesfully deleted",HttpStatus.FOUND);
		   else
			   return  new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.NOT_FOUND);
		   }catch (Exception e) {
	            e.printStackTrace();
	            
		    }
		   return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
		 }
	   
	   @RequestMapping(value = "/activationstatus/{token:.+}", method = RequestMethod.GET)
	    public ResponseEntity<?> activateUser(@PathVariable("token") String token, HttpServletRequest request) {

	        UserDetails user = userService.activateUser(token, request);
	        if (user != null) {
	            return new ResponseEntity<UserDetails>(user, HttpStatus.FOUND);
	        } else {
	            return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
	                    HttpStatus.NOT_FOUND);
	        }
	    }



}

