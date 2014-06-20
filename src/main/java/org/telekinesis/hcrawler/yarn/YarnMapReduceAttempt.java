package org.telekinesis.hcrawler.yarn;

import java.sql.Timestamp;

import org.telekinesis.hcrawler.hadoop.TaskType;

public class YarnMapReduceAttempt
{
    private final String       jobID;
    private final String       taskID;
    private final String       attemptID;
    private final TaskType     type;
    private final AttemptState state;
    private final String       node;
    private final Timestamp    startTime;
    private final Timestamp    endTime;

    public static enum AttemptState
    {
	SUCCEEDED, FAILED, KILLED
    }

    public YarnMapReduceAttempt(String jobID, String taskID, String attemptID,
	    TaskType type, AttemptState state, String node,
	    Timestamp startTime, Timestamp endTime)
    {
	this.jobID = jobID;
	this.taskID = taskID;
	this.attemptID = attemptID;
	this.type = type;
	this.state = state;
	this.node = node;
	this.startTime = startTime;
	this.endTime = endTime;
    }

    public String getJobID()
    {
	return jobID;
    }

    public String getTaskID()
    {
	return taskID;
    }

    public String getAttemptID()
    {
	return attemptID;
    }

    public TaskType getType()
    {
	return type;
    }

    public AttemptState getState()
    {
	return state;
    }

    public String getNode()
    {
	return node;
    }

    public Timestamp getStartTime()
    {
	return startTime;
    }

    public Timestamp getEndTime()
    {
	return endTime;
    }

    @Override
    public String toString()
    {
	return "YarnMapReduceAttempt [jobID=" + jobID + ", taskID=" + taskID
	        + ", attemptID=" + attemptID + ", type=" + type + ", state="
	        + state + ", node=" + node + ", startTime=" + startTime
	        + ", endTime=" + endTime + "]";
    }

}
