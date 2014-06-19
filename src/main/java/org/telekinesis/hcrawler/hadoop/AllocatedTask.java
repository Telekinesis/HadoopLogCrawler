package org.telekinesis.hcrawler.hadoop;

public class AllocatedTask
{
    private final TaskType type;
    private final String   taskID;
    private final String   attemptID;
    private final String   node;
    private final int      slot;
    private final String   startTime;
    private final String   endTime;

    public AllocatedTask(TaskType type, String taskID, String attemptID,
	    String node, int slot, String startTime, String endTime)
    {
	this.type = type;
	this.taskID = taskID;
	this.attemptID = attemptID;
	this.node = node;
	this.slot = slot;
	this.startTime = startTime;
	this.endTime = endTime;
    }
    
    public AllocatedTask(Task task, int slot){
	this.type = task.getType();
	this.taskID = task.getTaskID();
	this.attemptID = task.getAttemptID();
	this.node = task.getNode();
	this.slot = slot;
	this.startTime = TimeToStringFormatter.format(task.getStartTime());
	this.endTime = TimeToStringFormatter.format(task.getEndTime());
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

    public String getStartTime()
    {
	return startTime;
    }

    public String getEndTime()
    {
	return endTime;
    }

    @Override
    public String toString()
    {
	return "AllocatedTask [type=" + type + ", taskID=" + taskID
	        + ", attemptID=" + attemptID + ", node=" + node + ", slot="
	        + slot + ", startTime=" + startTime + ", endTime=" + endTime
	        + "]";
    }

}
