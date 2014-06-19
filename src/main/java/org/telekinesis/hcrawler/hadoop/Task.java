package org.telekinesis.hcrawler.hadoop;

import java.sql.Timestamp;

public class Task
{
    private final String    taskID;
    private final String    attemptID;
    private final TaskType  type;
    private final String    node;
    private final Timestamp startTime;
    private final Timestamp endTime;

    public Task(String taskID, String attemptID, TaskType type,
	    String node, Timestamp startTime, Timestamp endTime)
    {
	this.taskID = taskID;
	this.attemptID = attemptID;
	this.type = type;
	this.node = node;
	this.startTime = startTime;
	this.endTime = endTime;
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
	return "TaskExecution [taskID=" + taskID + ", attemptID=" + attemptID
	        + ", type=" + type + ", node=" + node + ", startTime="
	        + startTime + ", endTime=" + endTime + "]";
    }

}
