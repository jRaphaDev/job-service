package br.com.techne.params;

import java.util.List;

import br.com.techne.params.Params;

public class EnviaEmailParams extends Params {
	
	private static final long serialVersionUID = 838086783631037747L;
	
	private String username;
	private String password;
	private List<String> to;
	private String title;
	private String msg;
	
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

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
		
}
