package br.com.techne.job;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.techne.job.TechneJob;

@DisallowConcurrentExecution
public abstract class TechneJob implements Job {

	 public static Logger log = LoggerFactory.getLogger(TechneJob.class);
	 public ObjectMapper mapper;
	 public TechneJob() {}

	 public final void execute(JobExecutionContext context) throws JobExecutionException{
		 
		 JobKey jobKey = context.getJobDetail().getKey();
	     log.info("Iniciando execu��o de tarefa: " + jobKey + " �s " + new Date() + ", disparado por: " + context.getTrigger().getKey());
	     
	     try {
	    	 
	    	 log.info("---------- Executando prepara��o de tarefa ----------");
	    	 beforeExecuteJob(context);

	    	 log.info("---------- Executando tarefa agendada ----------");
	    	 executeJob(context);
	    	 
	    	 log.info("---------- Executando finaliza��o de tarefa ----------");
	    	 afterExecuteJob(context);
	    	 
	     } catch (Exception e){
	    	 JobExecutionException jobException = new JobExecutionException(e);
	    	 throw jobException;
	     }
	 }
	 
	 public abstract void beforeExecuteJob(JobExecutionContext context) throws JobExecutionException;
	 
	 public abstract void executeJob(JobExecutionContext context) throws JobExecutionException, Exception;
	 
	 public abstract void afterExecuteJob(JobExecutionContext context) throws JobExecutionException;
}
