package com.simpleproject.employeesalary.model;

import java.io.Serializable;





public class Employeesalary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
		
		
	}

	
	private String firstName;
	
	
	private String lastName;
	
	
	private String salary;
	

}
