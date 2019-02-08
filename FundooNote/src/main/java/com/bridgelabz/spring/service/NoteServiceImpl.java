package com.bridgelabz.spring.service;

import java.util.List;
import java.util.stream.Collectors;

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
	private GenerateTokenImlp generateToken;
	
	@Transactional
	public boolean createNote(String token,Note note, HttpServletRequest request) {
		int id = generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(id);
		if(user!=null)
		{
			note.setUserid(user);
		int notId = noteDao.createNote(note);
		if (notId> 0) {
			return true;
		}
		}
		return false;
	}

	
	@Transactional
	public Note updateNote(int id,String token,Note note,HttpServletRequest request)
	{
		int userId = generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(userId);
		if(user!=null) {
			Note newNote=noteDao.getNoteByID(id);
			newNote.setTitle(note.getTitle());
			newNote.setDiscription(note.getDiscription());
	           noteDao.updateNote(id, newNote);
	           return newNote;
	}
		return null;
		}

	
	
	@Transactional
	public Note deleteNote(int id,String token,HttpServletRequest request) {
		int userId = generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(userId);
		if(user!=null) {
		Note newNote=noteDao.getNoteByID(id);
		noteDao.deleteNote(id);
		return newNote;
	}
		return null;}

	  @Transactional
	    public List<Note> retrieveNote(String token,HttpServletRequest request) {
		  int id = generateToken.verifyToken(token);
		  UserDetails note=userDao.getUserByID(id);
		  if(note!=null) {
	        List<Note> listOfNote = noteDao.retrieve(id);
	        if (!listOfNote.isEmpty()) {
	            return listOfNote;
	        }}
	        return null;
	    }


	  
	  
	  @Transactional
	public boolean createLabel(String token, Label label, HttpServletRequest request) {
		  int id = generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(id);
		if(user!=null)
		{
			label.setUserid(user);
		int newLabel = noteDao.createLabel(label);
		if (newLabel> 0) {
			return true;
		}
		}
		return false;
	}

	  @Transactional
	public Label updateLabel(int id,String token, Label label, HttpServletRequest request) {
		  int userId = generateToken.verifyToken(token);
		  UserDetails user=userDao.getUserByID(userId);
		  if(user!=null) {
		  Label newlabel=noteDao.getLabelByID(id);
		if(newlabel!=null) { 
			newlabel.setLabelName(label.getLabelName());
	           noteDao.updateLabel(id, newlabel);
	}
		return newlabel;
		  }
		return null;
	}

	  @Transactional
	public Label deleteLabel(int id,String token, HttpServletRequest request) {
		  int userId = generateToken.verifyToken(token);
		  UserDetails user=userDao.getUserByID(userId);
		  if(user!=null) {
		Label label=noteDao.getLabelByID(id);
		noteDao.deleteLabel(id);
		return label;
	}
		return null;}

	  @Transactional
	public List<Label> retrieveLabel(String token, HttpServletRequest request) {
		  int userId = generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(userId);
		  if(user!=null) {
	        List<Label> listOfLabel = noteDao.retrieveLabel(userId);
	        if (!listOfLabel.isEmpty()) {
	            return listOfLabel;
	        }
	        }
	        return null;
		
	}
	  @Transactional
	    public boolean mapNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {
	        int id = generateToken.verifyToken(token);
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



	public boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {
        int id = generateToken.verifyToken(token);
        UserDetails user = userDao.getUserByID(id);
        if (user != null) {
            Note note = noteDao.getNoteByID(noteId);
            List<Label> listOfLabels = note.getListOfLabels();
            if (!listOfLabels.isEmpty()) {
                listOfLabels = listOfLabels.stream().filter(label -> label.getId() != labelId)
                        .collect(Collectors.toList());
                note.setListOfLabels(listOfLabels);
                noteDao.updateNote(noteId, note);
                return true;
            }
        }
        return false;
    }


	
	 

}
