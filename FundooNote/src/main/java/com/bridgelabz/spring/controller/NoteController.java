package com.bridgelabz.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.service.NoteService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@RestController
public class NoteController {

	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<String> createNote( @RequestHeader("token") String token,@RequestBody Note note, HttpServletRequest request) {
			try {
				if (noteService.createNote(token,note, request))
					return new ResponseEntity<String>("Note Succesfully Created",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Dinied In Creation of Note",HttpStatus.CONFLICT);
			}
		return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
}
	
	
	   @RequestMapping(value = "/updatenote", method = RequestMethod.POST)
	    public ResponseEntity<String> updateNote(@RequestParam("id") int id, @RequestHeader("token") String token,@RequestBody Note user, HttpServletRequest request)
	    {
	        try {
	            if (noteService.updateNote(id,token,user, request)!=null)
	                return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.CONFLICT);
	        }
	        return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
	    }
	   
	   
	   @RequestMapping(value="/deletenote",method=RequestMethod.DELETE)
	   public ResponseEntity<String> deleteNote(@RequestParam("id") int id ,@RequestHeader("token") String token,HttpServletRequest request)
	   {
		   try {
			   if (noteService.deleteNote(id,token,request)!=null)
				   return new ResponseEntity<String>("Note Succesfully deleted",HttpStatus.FOUND);
		   else
			   return  new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.NOT_FOUND);
		   }catch (Exception e) {
	            e.printStackTrace();        
		    }
		   return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
		 }
	   
	   
	    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	    public ResponseEntity<?> retrieveNote( @RequestHeader("token") String token,HttpServletRequest request) {
	        List<Note> listOfNote = noteService.retrieveNote(token,request);
	        if (!listOfNote.isEmpty()) {
	            return new ResponseEntity<List<Note>>(listOfNote, HttpStatus.FOUND);
	        } else {
	            return new ResponseEntity<String>("ID incorrect. Please enter valid ID present in database",
	                    HttpStatus.NOT_FOUND);
	        }
	    }
	   
	    
	    
	    
	    @RequestMapping(value = "/createlabel", method = RequestMethod.POST)
		public ResponseEntity<?> createLabel( @RequestHeader("token") String token, @RequestBody Label label, HttpServletRequest request) {
				try {
					if (noteService.createLabel(token,label, request))
						return new ResponseEntity<String>("Label Succesfully Created",HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<String>("Dinied In Creation of Label",HttpStatus.CONFLICT);
				}
			return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
	}
	    
	    
	    
	    @RequestMapping(value = "/updatelabel", method = RequestMethod.POST)
	    public ResponseEntity<String> updateLabel(@RequestParam("id") int id, @RequestHeader("token") String token,@RequestBody Label label, HttpServletRequest request)
	    {
	        try {
	            if (noteService.updateLabel(id,token,label, request)!=null)
	                return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.CONFLICT);
	        }
	        return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
	    }
	   
	    
	    
	    @RequestMapping(value="/deletelabel",method=RequestMethod.DELETE)
		   public ResponseEntity<String> deleteLabel(@RequestParam("id") int id , @RequestHeader("token") String token,HttpServletRequest request)
		   {
			   try {
				   if (noteService.deleteLabel(id,token,request)!=null)
					   return new ResponseEntity<String>("Label Succesfully deleted",HttpStatus.FOUND);
			   else
				   return  new ResponseEntity<String>("Label not Found by given  Id",HttpStatus.NOT_FOUND);
			   }catch (Exception e) {
		            e.printStackTrace();
		            
			    }
			   return new ResponseEntity<String>("Pls provide details correctly",HttpStatus.CONFLICT);
			 }
	    
	    
	    
	    @RequestMapping(value = "/retrievelabel", method = RequestMethod.GET)
	    public ResponseEntity<?> retrieveLabel(@RequestHeader("token") String token, HttpServletRequest request) {
	        List<Label> listOfLabel = noteService.retrieveLabel(token, request);
	        if (!listOfLabel.isEmpty()) {
	            return new ResponseEntity<List<Label>>(listOfLabel, HttpStatus.FOUND);
	        } else {
	            return new ResponseEntity<String>("ID incorrect. Please enter valid ID present in database",
	                    HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    
	    @RequestMapping(value = "/mapnotelabel", method = RequestMethod.PUT)
	    public ResponseEntity<?> mapNoteLabel(@RequestParam("noteId") int noteId ,@RequestParam("labelId") int labelId ,@RequestHeader("token") String token, HttpServletRequest request) {
	       try {
	    	if (noteService.mapNoteLabel(token,noteId,labelId, request)) {
	            return new ResponseEntity<String>("Mapped Successfully", HttpStatus.FOUND);
	        }
	    	}
	    	catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Dinied In Mapping ",HttpStatus.NOT_FOUND);
	        }
		return new ResponseEntity<String>("Pls Enter the details properly",HttpStatus.NOT_FOUND);
		}
	    
	    
	    @RequestMapping(value="/deletenotelabel", method=RequestMethod.DELETE)
	    public ResponseEntity<?> deleteNoteLabel(@RequestHeader("token") String token,@RequestParam("noteId") int noteId,@RequestParam("labelId") int labelId ,HttpServletRequest request){
	    try {
	    if(noteService.removeNoteLabel(token, noteId, labelId, request))	
	    	return new ResponseEntity<String>("NoteLabel Deleted Successfully", HttpStatus.FOUND);
	    
    	}
    	catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dinied In Deleting NoteLabel",HttpStatus.NOT_FOUND);
        }
	return new ResponseEntity<String>("Pls Enter the details properly",HttpStatus.NOT_FOUND);
	}

}

