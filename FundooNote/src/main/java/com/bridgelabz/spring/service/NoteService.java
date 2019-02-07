package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {
	
	boolean createNote(int id,Note user, HttpServletRequest request);
	
	Note updateNote(int id,Note user,HttpServletRequest request);
	
	Note  deleteNote(int id, HttpServletRequest request);
	
   List<Note> retrieve(int id,HttpServletRequest request);
   
   
   
   
	boolean createLabel(int id,Label label, HttpServletRequest request);
	
   Label updateLabel(int id,Label label,HttpServletRequest request);
	
   Label  deleteLabel(int id, HttpServletRequest request);
   
   List<Label> retrieveLabel(int id,HttpServletRequest request);
   
   boolean mapNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
	
}
