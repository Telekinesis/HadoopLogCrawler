package org.telekinesis.hcrawler.yarn;

import org.telekinesis.hcrawler.hadoop.TaskType;

public class YarnAttempt
{
    private final String   jobID;
    private final String   taskID;
    private final String   attemptID;
    private final TaskType type;
    private final int      slot;
    private final double     startSecond;
    private final double     endSecond;
    
    public YarnAttempt(YarnMapReduceAttempt attempt, int slot, double startSecond, double endSecond){
	this.jobID = attempt.getJobID();
	this.taskID = attempt.getTaskID();
	this.attemptID = attempt.getAttemptID();
	this.type = attempt.getType();
	this.slot = slot;
	this.startSecond = startSecond;
	this.endSecond = endSecond;
    }

    public YarnAttempt(String jobID, String taskID, String attemptID,
	    TaskType type, int slot, double startSecond, double endSecond)
    {
	this.jobID = jobID;
	this.taskID = taskID;
	this.attemptID = attemptID;
	this.type = type;
	this.slot = slot;
	this.startSecond = startSecond;
	this.endSecond = endSecond;
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

    public int getSlot()
    {
	return slot;
    }

    public double getStartSecond()
    {
	return startSecond;
    }

    public double getEndSecond()
    {
	return endSecond;
    }

    @Override
    public String toString()
    {
	return "YarnAttempt [jobID=" + jobID + ", taskID=" + taskID
	        + ", attemptID=" + attemptID + ", type=" + type + ", slot="
	        + slot + ", startSecond=" + startSecond + ", endSecond="
	        + endSecond + "]";
    }

}
