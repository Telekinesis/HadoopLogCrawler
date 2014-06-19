package org.telekinesis.hcrawler.hadoop;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class JobStatistics
{
    private final String              startTime;
    private final String              endTime;
    private final int                 mapSlots;
    private final int                 reduceSlots;
    private final List<String>        nodes;
    private final List<AllocatedTask> tasks;

    public JobStatistics(String startTime, String endTime, int mapSlots,
	    int reduceSlots, List<String> nodes, List<AllocatedTask> tasks)
    {
	this.startTime = startTime;
	this.endTime = endTime;
	this.mapSlots = mapSlots;
	this.reduceSlots = reduceSlots;
	this.nodes = nodes;
	this.tasks = tasks;
    }

    public String getStartTime()
    {
	return startTime;
    }

    public String getEndTime()
    {
	return endTime;
    }

    public int getMapSlots()
    {
	return mapSlots;
    }

    public int getReduceSlots()
    {
	return reduceSlots;
    }

    public List<String> getNodes()
    {
	return nodes;
    }

    public List<AllocatedTask> getTasks()
    {
	return tasks;
    }

    @Override
    public String toString()
    {
	return "JobStatistics [startTime=" + startTime + ", endTime=" + endTime
	        + ", mapSlots=" + mapSlots + ", reduceSlots=" + reduceSlots
	        + ", nodes=" + nodes + ", tasks=" + tasks + "]";
    }
    
    public static JobStatistics create(JobHistory<SlotConfig> history){
	SlotConfig slotConfig = history.getJobConfig();
	List<Task> exportedTasks = new ArrayList<Task>();
	List<Task> tasks = history.getTasks();
	long startTime = Long.MAX_VALUE;
	long endTime = Long.MIN_VALUE;
	TreeSet<String> nodes = new TreeSet<String>();
	for (Task task : tasks)
        {
	    long taskStart = task.getStartTime().getTime();
	    long taskEnd = task.getEndTime().getTime();
	    if(taskStart < startTime)
		startTime = taskStart;
	    if(taskEnd > endTime)
		endTime = taskEnd;
	    nodes.add(task.getNode());
	    if(task.getType() == TaskType.MAP || task.getType() == TaskType.REDUCE)
		exportedTasks.add(task);
        }
	List<String> nodeList = new ArrayList<String>(nodes);
	String startTimeString = TimeToStringFormatter.format(startTime); 
	String endTimeString = TimeToStringFormatter.format(endTime);
	TaskAllocator allocator = new TaskAllocator(slotConfig, nodeList);
	List<AllocatedTask> allocatedTasks = allocator.allocate(exportedTasks);
	JobStatistics statistics = new JobStatistics(startTimeString, endTimeString, slotConfig.getMapSlot(), slotConfig.getReduceSlot(), nodeList, allocatedTasks);
	return statistics;
    }
    
}
