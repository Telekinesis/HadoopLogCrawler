package org.telekinesis.hcrawler.yarn;

import java.sql.Timestamp;

public class YarnMapReduceAttempt
{
    private final String       attemptID;
    private final AttemptState state;
    private final String       node;
    private final Timestamp    startTime;
    private final Timestamp    endTime;

    public static enum AttemptState
    {
	SUCCEEDED, FAILED, KILLED
    }

    public YarnMapReduceAttempt(String attemptID, AttemptState state,
	    String node, Timestamp startTime, Timestamp endTime)
    {
	this.attemptID = attemptID;
	this.state = state;
	this.node = node;
	this.startTime = startTime;
	this.endTime = endTime;
    }

    public String getAttemptID()
    {
	return attemptID;
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
	return "YarnMapReduceAttempt [attemptID=" + attemptID + ", state="
	        + state + ", node=" + node + ", startTime=" + startTime
	        + ", endTime=" + endTime + "]";
    }

}
