package com.bridgelabz.spring.model;

	import java.io.Serializable;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.hibernate.annotations.GenericGenerator;

	@Entity
	@Table(name= "UserDetails")
	public class UserDetails implements Serializable{

		@Id
		@GeneratedValue
		@Column(name = "id")
		private int id;

		@Column(name = "name")
		private String name;

		@Column(name = "emailId", unique = true)
		private String emailId;

		@Column(name = "password",length = 60)
		private String password;

		@Column(name = "mobileNumber")
		private long mobileNumber;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public long getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", password=" + password
					+ ", mobileNumber=" + mobileNumber+"]";
	}
	}

