package br.com.email;

import java.io.Serializable;
import java.util.List;

public class ParamsEmail implements Serializable {

	private static final long serialVersionUID = 4547033375662177075L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
}
