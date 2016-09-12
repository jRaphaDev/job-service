package br.com.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JavaSendMail implements Job {
	
	private static String username;
	private static String password;
	private static List<String> to;
	private static String subject;
	private static String msg;
	
	public JavaSendMail() {}
	
	public JavaSendMail(String username, String password, List<String> to, String subject, String msg ) {
		JavaSendMail.username = username;
		JavaSendMail.password = password;
		JavaSendMail.to = to;
		JavaSendMail.subject = subject;
		JavaSendMail.msg = msg;
	}
	
	private static Properties setProps() throws Exception {
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        return props;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void execute() throws JobExecutionException {
		
		try {
			
			//set all properties
			Properties props = setProps();

			Authentication authentication = new Authentication();
			Authenticator auth = authentication.auth(username, password);
	        
			Session session = Session.getInstance(props, auth);
	
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));

	        for (int a = 0; a < to.size(); a++) {
	        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(a).toString()));
			}

	        message.setSubject(subject);
	        message.setText(msg);

	        Transport.send(message);
	        System.out.println("Mail Sent");
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(username + " >>>>>>>>>>>>>>>>> ");
		execute();
	}

}
