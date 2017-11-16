package com.rabbit.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class PhoneMessage implements Serializable{

	private String id;
	
	private String telephoneNumber;

	public  String getId() {
		return id;
	}

	public  void setId(String id) {
		this.id = id;
	}

	public  String getTelephoneNumber() {
		return telephoneNumber;
	}

	public  void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}
