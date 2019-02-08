package com.bridgelabz.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.UserDetails;

@Repository
	public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int register(UserDetails user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}

   
	   public UserDetails update(int id,UserDetails user)
	    {
	     Session session=sessionFactory.openSession();
	     Transaction tx=session.beginTransaction();
	     session.update(user);
	     tx.commit();
	     session.close();
		return user;
	    
	    }

		   public UserDetails login(String emailId)
		    {
		        Session session = sessionFactory.openSession();
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("from UserDetails");
		        @SuppressWarnings("unchecked")
				List<UserDetails> userList = query.list();
		        for(UserDetails user : userList){
		            System.out.println("List of users::"+user.getId()+","+user.getName()+","+user.getEmailId()+","+user.getMobileNumber());
		        }
		        query = session.createQuery("from UserDetails where emailId= :emailId");
		        query.setString("emailId", emailId);
		        UserDetails emp = (UserDetails) query.uniqueResult();
		        System.out.println("User detail is="+emp.getName()+","+emp.getEmailId()+","+emp.getMobileNumber());
		        session.close();
		        return emp;
		    }
	

	public void delete(int id) {
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("DELETE from UserDetails u where u.id= :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
	        tx.commit();
	        session.close();
	    }


	public UserDetails getUserByID(int id) {
		 Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        Query  query = session.createQuery("from UserDetails where id= :id");
	         query.setInteger("id", id);
	        UserDetails user = (UserDetails) query.uniqueResult();
	        if(user!=null) {
	        System.out.println("User detail is="+ user.getId()+user.getName()+","+user.getEmailId()+","+user.getMobileNumber()+","+ user.isActivationStatus());
	        }  tx.commit();
	        session.close();
			return user;
	}
	
	public List<UserDetails> getUsersList() {
	
		return null;
	}
	}