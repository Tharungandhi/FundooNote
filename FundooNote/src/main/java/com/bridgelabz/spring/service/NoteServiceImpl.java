package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.NoteDao;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.util.GenerateTokenImlp;

@Service
public class NoteServiceImpl implements NoteService {

	
	@Autowired
	 private NoteDao noteDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GenerateTokenImlp tokenGenerator;
	
	@Transactional
	public boolean createNote(int id,Note note, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(id);
		if(user!=null)
		{
			note.setUserid(user);
		int id1 = noteDao.createNote(note);
		if (id1> 0) {
			return true;
		}
		}
		return false;
	}

	
	@Transactional
	public Note updateNote(int id,Note user,HttpServletRequest request)
	{
		Note user12=noteDao.getNoteByID(id);
		if(user12!=null) {
			  user12.setTitle(user.getTitle());
			  user12.setDiscription(user.getDiscription());
	           noteDao.updateNote(id, user12);
	}
		return user12;}

	
	
	@Transactional
	public Note deleteNote(int id,HttpServletRequest request) {
		Note user12=noteDao.getNoteByID(id);
		noteDao.deleteNote(id);
		return user12;
	}

	  @Transactional
	    public List<Note> retrieve(int id,HttpServletRequest request) {
		  UserDetails user=userDao.getUserByID(id);
		  if(user!=null) {
	        List<Note> listOfNote = noteDao.retrieve(id);
	        if (!listOfNote.isEmpty()) {
	            return listOfNote;
	        }}
	        return null;
	    }


	  
	  
	  @Transactional
	public boolean createLabel(int id, Label label, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(id);
		if(user!=null)
		{
			label.setUserid(user);
		int id1 = noteDao.createLabel(label);
		if (id1> 0) {
			return true;
		}
		}
		return false;
	}

	  @Transactional
	public Label updateLabel(int id, Label label, HttpServletRequest request) {
		Label label1=noteDao.getLabelByID(id);
		if(label1!=null) { 
			label1.setLabelName(label.getLabelName());
	           noteDao.updateLabel(id, label1);
	}
		return label1;
		
	}

	  @Transactional
	public Label deleteLabel(int id, HttpServletRequest request) {
		Label label=noteDao.getLabelByID(id);
		noteDao.deleteLabel(id);
		return label;
	}

	  @Transactional
	public List<Label> retrieveLabel(int id, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(id);
		  if(user!=null) {
	        List<Label> listOfLabel = noteDao.retrieveLabel(id);
	        if (!listOfLabel.isEmpty()) {
	            return listOfLabel;
	        }
	        }
	        return null;
		
	}
	  @Transactional
	    public boolean mapNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {
	        int id = tokenGenerator.verifyToken(token);
	        UserDetails user = userDao.getUserByID(id);
	        if (user != null) {
	            Note note = noteDao.getNoteByID(noteId);
	            Label label = noteDao.getLabelByID(labelId);
	            List<Label> listOfLabel = note.getListOfLabels();
	            listOfLabel.add(label);
	            if (!listOfLabel.isEmpty()) {
	                note.setListOfLabels(listOfLabel);
	                noteDao.updateNote(1, note);
	                return true;
	            }
	        }
	        return false;
	    }


}
