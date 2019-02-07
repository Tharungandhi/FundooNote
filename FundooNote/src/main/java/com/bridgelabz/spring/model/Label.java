package com.bridgelabz.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Label")
public class Label {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="labelName")
	private String labelName;
	
	@ManyToOne
	@JoinColumn(name="userid" )
	private UserDetails userid;
	
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDetails getUserid() {
		return userid;
	}

	public void setUserid(UserDetails userid) {
		this.userid = userid;
	}
	
	
}
