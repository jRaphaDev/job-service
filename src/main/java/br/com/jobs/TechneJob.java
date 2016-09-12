package br.com.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TechneJob implements Job {

	 public static Logger log = LoggerFactory.getLogger(TechneJob.class);

	 public TechneJob() {}

	 public final void execute(JobExecutionContext context) throws JobExecutionException{
		 
		 JobKey jobKey = context.getJobDetail().getKey();
	     log.info("Iniciando execução de tarefa: " + jobKey + " às " + new Date() + ", disparado por: " + context.getTrigger().getKey());
	     
	     try {
	    	 
	    	 log.info("---------- Executando preparação de tarefa ----------");
	    	 beforeExecuteJob(context);
	    	 
	    	 log.info("---------- Executando tarefa agendada ----------");
	    	 executeJob(context);
	    	 
	    	 log.info("---------- Executando finalização de tarefa ----------");
	    	 afterExecuteJob(context);
	    	 
	     } catch (Exception e){
	    	 log.info("---------- Erro na tarefa "+ jobKey+": Agendamentos desta tarefa serão temporariamente cancelados ----------");
	    	 JobExecutionException jobException = new JobExecutionException(e);
	    	 throw jobException;
	     }
	 }
	 
	 public abstract void beforeExecuteJob(JobExecutionContext context) throws JobExecutionException;
	 
	 public abstract void executeJob(JobExecutionContext context) throws JobExecutionException;
	 
	 public abstract void afterExecuteJob(JobExecutionContext context) throws JobExecutionException;
}
