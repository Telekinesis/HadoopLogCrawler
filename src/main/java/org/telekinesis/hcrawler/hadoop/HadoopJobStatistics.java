package org.telekinesis.hcrawler.hadoop;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class HadoopJobStatistics
{
    private final String              jobID;
    private final long                durationInSecond;
    private final int                 mapSlots;
    private final int                 reduceSlots;
    private final List<String>        nodes;
    private final List<AllocatedTask> tasks;

    public HadoopJobStatistics(String jobID, long durationInSecond,
	    int mapSlots, int reduceSlots, List<String> nodes,
	    List<AllocatedTask> tasks)
    {
	this.jobID = jobID;
	this.durationInSecond = durationInSecond;
	this.mapSlots = mapSlots;
	this.reduceSlots = reduceSlots;
	this.nodes = nodes;
	this.tasks = tasks;
    }

    public String getJobID()
    {
	return jobID;
    }

    public long getDurationInSecond()
    {
	return durationInSecond;
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
	return "HadoopJobStatistics [jobID=" + jobID + ", durationInSecond="
	        + durationInSecond + ", mapSlots=" + mapSlots
	        + ", reduceSlots=" + reduceSlots + ", nodes=" + nodes
	        + ", tasks=" + tasks + "]";
    }

    public static HadoopJobStatistics create(JobHistory<SlotConfig> history)
    {
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
	    if (taskStart < startTime) startTime = taskStart;
	    if (taskEnd > endTime) endTime = taskEnd;
	    nodes.add(task.getNode());
	    if (task.getType() == TaskType.MAP
		    || task.getType() == TaskType.REDUCE)
		exportedTasks.add(task);
	}
	List<String> nodeList = new ArrayList<String>(nodes);
	long durationInSeconds = ( endTime - startTime ) / 1000;
	TaskAllocator allocator = new TaskAllocator(startTime, slotConfig,
	        nodeList);
	List<AllocatedTask> allocatedTasks = allocator.allocate(exportedTasks);
	HadoopJobStatistics statistics = new HadoopJobStatistics(history.getJobID(),
	        durationInSeconds, slotConfig.getMapSlot(),
	        slotConfig.getReduceSlot(), nodeList, allocatedTasks);
	return statistics;
    }

}
