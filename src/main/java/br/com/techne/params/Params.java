package br.com.techne.params;

import java.io.Serializable;

public class Params implements Serializable {
	
	private static final long serialVersionUID = 4547033375662177075L;

	private String classNome;
	private String jobNome;
	private String jobGroup;
	private String jobDescription;
	private String triggerNome;
	private String triggerGroup;
	private String triggerDescription;
	private String periodicidade;
	
	public String getJobNome() {
		return jobNome;
	}
	
	public void setJobNome(String jobNome) {
		this.jobNome = jobNome;
	}
	
	public String getJobGroup() {
		return jobGroup;
	}
	
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	public String getTriggerNome() {
		return triggerNome;
	}
	
	public void setTriggerNome(String triggerNome) {
		this.triggerNome = triggerNome;
	}
	
	public String getTriggerGroup() {
		return triggerGroup;
	}
	
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	
	public String getPeriodicidade() {
		return periodicidade;
	}
	
	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getClassNome() {
		return classNome;
	}

	public void setClassNome(String classNome) {
		this.classNome = classNome;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getTriggerDescription() {
		return triggerDescription;
	}

	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}
		
}
