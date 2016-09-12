package br.com.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.jobs.TechneJob;

public class SendMail extends TechneJob {
	
	private static String username;
	private static String password;
	private static List<String> to;
	private static String subject;
	private static String msg;
	
	public SendMail() {}
	
	public SendMail(String username, String password, List<String> to, String subject, String msg ) {
		SendMail.username = username;
		SendMail.password = password;
		SendMail.to = to;
		SendMail.subject = subject;
		SendMail.msg = msg;
	}
	
	@Override
	public void beforeExecuteJob(JobExecutionContext context) throws JobExecutionException {}

	@Override
	public void executeJob(JobExecutionContext context) throws JobExecutionException {
		try {

			//set all properties
			Properties props = setProps();

			Authentication authentication = new Authentication();
			Authenticator auth = authentication.auth(username, password);

	        MimeMessage message = new MimeMessage(Session.getInstance(props, auth));
	        message.setFrom(new InternetAddress(username));

	        for (int a = 0; a < to.size(); a++) {
	        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(a).toString()));
			}

	        message.setSubject(subject);
	        message.setText(msg);

	        Transport.send(message);
	        log.info("---------- Mail sent ----------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterExecuteJob(JobExecutionContext context) throws JobExecutionException {}
	
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

}
