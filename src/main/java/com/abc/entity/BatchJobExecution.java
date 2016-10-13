package com.abc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Job Execution details
 *
 */
@Entity
public class BatchJobExecution {

	@Id
	private int jobExecutionId;
	private int jobInstanceId;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;
	private String version;
	private String lastUpdated;
	private String jobConfigurationLocation;
	
	public int getJobExecutionId() {
		return jobExecutionId;
	}
	public void setJobExecutionId(int jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}
	public int getJobInstanceId() {
		return jobInstanceId;
	}
	public void setJobInstanceId(int jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExitMessage() {
		return exitMessage;
	}
	public String getExitCode() {
		return exitCode;
	}
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getJobConfigurationLocation() {
		return jobConfigurationLocation;
	}
	public void setJobConfigurationLocation(String jobConfigurationLocation) {
		this.jobConfigurationLocation = jobConfigurationLocation;
	}
	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}
	
}
