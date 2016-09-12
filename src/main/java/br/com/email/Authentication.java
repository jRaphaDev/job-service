package br.com.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Authentication {
	
	public Authenticator auth(final String username, final String password) {
	    Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    };
	    return auth;
	}
}
