package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteDao {
	
	Object note = null;
	
	int createNote(Note user);
	
	Note getNoteByID(int id);
	
	Note updateNote(int id, Note note);
	
	 void deleteNote(int id);
	
	 List<Note> retrieve(int id);
	
	 
	 
	 Object label=null;
	 
	 int createLabel(Label user);
		
	Label updateLabel(int id, Label label);
	
	Label getLabelByID(int id);
		
	   void deleteLabel(int id);
	   
		 List<Label> retrieveLabel(int id);
}
