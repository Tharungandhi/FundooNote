package com.bridgelabz.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(Note note) {
		Session session = sessionFactory.getCurrentSession();
		int userId = (Integer) session.save(note);
		session.close();
		return userId;
	}

	public Note updateNote(int id, Note user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
		return user;

	}

	public Note getNoteByID(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Note where id= :id");
		query.setInteger("id", id);
		Note emp = (Note) query.uniqueResult();
		if (note != null) {
			System.out.println("Note details is=" + emp.getId() + emp.getTitle() + emp.getDiscription()
					+ emp.getCreatedTime() + emp.getUpdateTime());
			tx.commit();
			session.close();
		}
		session.close();
		return emp;
	}

	public void deleteNote(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from Note u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public List<Note> retrieve(int id) {
		Session session = sessionFactory.openSession();
		Query hqlQuery = session.createQuery("from Note where userid=:userid");
		hqlQuery.setInteger("userid", id);
		@SuppressWarnings("unchecked")
		List<Note> listOfNote = hqlQuery.list();
		return listOfNote;
	}

	public int createLabel(Label label) {
		Session session = sessionFactory.getCurrentSession();
		int label1 = (Integer) session.save(label);
		return label1;

	}

	public Label updateLabel(int id, Label label) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(label);
		tx.commit();
		session.close();
		return label;
	}

	public void deleteLabel(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from Label u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();

	}

	public Label getLabelByID(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Label where id= :id");
		query.setInteger("id", id);
		Label label = (Label) query.uniqueResult();
		if (label != null) {
			System.out.println("Label details is=" + label.getId() + label.getLabelName());
			tx.commit();
			session.close();
		}
		return label;
	}

	public List<Label> retrieveLabel(int id) {
		Session session = sessionFactory.openSession();
		Query hqlQuery = session.createQuery("from Label where userid=:userid");
		hqlQuery.setInteger("userid", id);
		@SuppressWarnings("unchecked")
		List<Label> listOfLabel = hqlQuery.list();
		session.close();
		return listOfLabel;
	}

}
