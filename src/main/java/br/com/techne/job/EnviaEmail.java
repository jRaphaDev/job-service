package br.com.techne.job;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.techne.job.TechneJob;
import br.com.techne.params.EnviaEmailParams;
import br.com.techne.security.Authentication;
import br.com.techne.util.StatusException;

public class EnviaEmail extends TechneJob {
	
	private static String username;
	private static String password;
	private static List<String> to;
	private static String subject;
	private static String msg;
	
	public EnviaEmail() {}
	
	@SuppressWarnings("static-access")
	public EnviaEmail(String params) throws StatusException, Exception {
		
		this.mapper = new ObjectMapper();
		EnviaEmailParams enviaEmailParams = mapper.readValue(params, EnviaEmailParams.class);

		//verifica parametros necessários para o envio de email.
		verificaParametrosObrigatorios(enviaEmailParams);

		this.username = enviaEmailParams.getUsername();
		this.password = enviaEmailParams.getPassword();
		this.to = enviaEmailParams.getTo();
		this.subject = enviaEmailParams.getTitle();
		this.msg = enviaEmailParams.getMsg();
	}
		
	@Override
	public void beforeExecuteJob(JobExecutionContext context) throws JobExecutionException {}

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		try {
			//seta todas as propriedades para configuração de envio de email(gmail).
			Properties props = setProps();

			//autenticação de email.
			Authentication authentication = new Authentication();
			Authenticator auth = authentication.auth(username, password);

	        MimeMessage message = new MimeMessage(Session.getInstance(props, auth));
	        message.setFrom(new InternetAddress(username));//from
	        message.setSubject(subject); //title
	        message.setText(msg); //text
	        
	        for (String destinatario : to) {
	        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			}
	       
	        Transport.send(message);
	        log.info("---------- Mail sent ----------");

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
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
	
	private void verificaParametrosObrigatorios(EnviaEmailParams enviaEmailParams) throws Exception {
		try {
			if (enviaEmailParams.getUsername().equals(null) || enviaEmailParams.getUsername().equals("")) {
				log.error(" O usuário é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode() ," O usuário é obrigatório.");
			}
			
			if (enviaEmailParams.getPassword().equals(null) || enviaEmailParams.getPassword().equals("")) {
				log.error("A senha é obrigatória.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode() ," A senha é obrigatória.");
			}
			
			if (enviaEmailParams.getTo().equals(null) || enviaEmailParams.getTo().equals("")) {
				log.error(" O destinatário é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode() ," O destinatário é obrigatório.");
			}
			
			if (enviaEmailParams.getTitle().equals(null) || enviaEmailParams.getTitle().equals("")){
				log.error(" O titulo é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode() ," O titulo é obrigatório.");
			}
			
			if (enviaEmailParams.getMsg().equals(null) || enviaEmailParams.getMsg().equals("")) {
				log.error(" A menssagem é obrigatória.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode() ," A menssagem é obrigatória.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
