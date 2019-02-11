package com.bridgelabz.spring.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	
	
	  UserDetails users = new UserDetails();

	   @Autowired
	   @Qualifier("UserValidation")
	   private org.springframework.validation.Validator validator;

	   @InitBinder
	   private void initBinder(WebDataBinder binder) {
	       binder.setValidator( validator);
	   }

	   @GetMapping("index")
	   public ModelAndView register(UserDetails user) {
	       return new ModelAndView("registeruser");
	   }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody UserDetails user, HttpServletRequest request) {
			try {
				if (userService.register(user, request))
					return new ResponseEntity<String>("User Succesfully registered",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Dinied In registering",HttpStatus.CONFLICT);
			}
		return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login( @RequestBody UserDetails user,HttpServletRequest request,HttpServletResponse response) {
		try {
        UserDetails userDetails = userService.login(user,request,response);
        if(userDetails!= null) 
            return new ResponseEntity<UserDetails>(userDetails, HttpStatus.FOUND);
       
    }catch (Exception e) {
		e.printStackTrace();
        return new ResponseEntity<String>("Dinied In Logging",HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	
	   @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public ResponseEntity<String> update(@RequestHeader("token") String token,@RequestBody UserDetails user, HttpServletRequest request)
	    {
	        try {
	            if (userService.update(token,user, request)!=null)
	                return new ResponseEntity<String>("User Succesfully updated",HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Dinied In Updating",HttpStatus.CONFLICT);
	        }
	        return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	    }
	   
	   @RequestMapping(value="/delete",method=RequestMethod.DELETE)
	   public ResponseEntity<String> deleteNote(@RequestHeader("token") String token,HttpServletRequest request)
	   {
		   try {
			   if (userService.delete(token,request)!=null){
				   return new ResponseEntity<String>("User Succesfully deleted",HttpStatus.FOUND);}
		   else
			   return  new ResponseEntity<String>("Dinied In Deleting",HttpStatus.NOT_FOUND);
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

   @RequestMapping(value="/forgotpassword",method=RequestMethod.POST)
   public ResponseEntity<?> forgotPassword(@RequestParam("emailId")String emailId,HttpServletRequest request){
   
	   boolean user=userService.forgotPassword(emailId,request);
	   if (user==false) {
           return new ResponseEntity<UserDetails>(HttpStatus.FOUND);
       } else {
           return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
                   HttpStatus.NOT_FOUND);
       }
   }
   
   @RequestMapping(value="/resetpassword/{token:.+}",method=RequestMethod.PUT)
   public ResponseEntity<?> resetPassword(@RequestBody  UserDetails user,@PathVariable("token")String token,HttpServletRequest request)
   {
	UserDetails user1=userService.resetPassword(user,token,request);
	if(user1!=null) {
		return new ResponseEntity<String>("password is Succeessfully updated",HttpStatus.FOUND);
		
	}else {
		return new ResponseEntity<String>("Dinied In Reseting Password ",HttpStatus.NOT_FOUND);
   }
   }
   
   
   
 


}

