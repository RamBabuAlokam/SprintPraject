package com.cg.drycleaning.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*************************************************************************
 * @author               Shaik Abdul Rahiman
 * Description           It is a entity class that provides the details of the User
 * Version               1.0
 * created date          24-03-2021    
 ***************************************************************************/
@Entity
@Table(name = "app_users")
public class User {
	@Id
	private String userName;
	private String password;
	private String role;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
		
	public void setRole(String role) {
		this.role = role;
	}

	public User() {
		super();
	}

	public User(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
}
