package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {
	
	boolean createNote(String token,Note user, HttpServletRequest request);
	
	Note updateNote(int id,String token,Note user,HttpServletRequest request);
	
	Note  deleteNote(int id,String token ,HttpServletRequest request);
	
   List<Note> retrieveNote(String token,HttpServletRequest request);
   
   
   
   
   boolean createLabel(String token,Label label, HttpServletRequest request);
	
   Label updateLabel(int id,String token,Label label,HttpServletRequest request);
	
   Label  deleteLabel(int id,String token, HttpServletRequest request);
   
   List<Label> retrieveLabel(String token,HttpServletRequest request);
   
   boolean mapNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
	
   boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
}
