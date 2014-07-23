package org.telekinesis.hcrawler.hadoop;

public class AllocatedTask
{
    private final TaskType type;
    private final String   taskID;
    private final String   attemptID;
    private final String   node;
    private final int      slot;
    private final double   startTimeInSecond;
    private final double   endTimeInSecond;

    public AllocatedTask(TaskType type, String taskID, String attemptID,
	    String node, int slot, double startTimeInSecond, double endTimeInSecond)
    {
	this.type = type;
	this.taskID = taskID;
	this.attemptID = attemptID;
	this.node = node;
	this.slot = slot;
	this.startTimeInSecond = startTimeInSecond;
	this.endTimeInSecond = endTimeInSecond;
    }

    public AllocatedTask(Task task, int slot, long startTime)
    {
	this.type = task.getType();
	this.taskID = task.getTaskID();
	this.attemptID = task.getAttemptID();
	this.node = task.getNode();
	this.slot = slot;
	this.startTimeInSecond = ( task.getStartTime().getTime() - startTime ) / 1000D;
	this.endTimeInSecond = ( task.getEndTime().getTime() - startTime ) / 1000D;
    }

    public TaskType getType()
    {
	return type;
    }

    public String getTaskID()
    {
	return taskID;
    }

    public String getAttemptID()
    {
	return attemptID;
    }

    public String getNode()
    {
	return node;
    }

    public int getSlot()
    {
	return slot;
    }

    public double getStartTimeInSecond()
    {
	return startTimeInSecond;
    }

    public double getEndTimeInSecond()
    {
	return endTimeInSecond;
    }

    @Override
    public String toString()
    {
	return "AllocatedTask [type=" + type + ", taskID=" + taskID
	        + ", attemptID=" + attemptID + ", node=" + node + ", slot="
	        + slot + ", startTime=" + startTimeInSecond + ", endTime="
	        + endTimeInSecond + "]";
    }

}
