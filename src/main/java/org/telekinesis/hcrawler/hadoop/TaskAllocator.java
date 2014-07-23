package org.telekinesis.hcrawler.hadoop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskAllocator
{
    private final long startTime;
    private final SlotConfig slotConfig;
    private final List<String> nodes;

    public TaskAllocator(long startTime, SlotConfig slotConfig, List<String> nodes)
    {
	this.startTime = startTime;
	this.slotConfig = slotConfig;
	this.nodes = nodes;
    };

    public List<AllocatedTask> allocate(List<Task> tasks)
    {
	Map<SlotKey, Boolean> slotStatus = initializeSlots();
	Map<Long, List<TaskEvent>> taskEvents = sortTaskEvents(tasks);
	List<AllocatedTask> allocated = allocate(slotStatus, taskEvents);
	return allocated;
    }

    private Map<SlotKey, Boolean> initializeSlots()
    {
	Map<SlotKey, Boolean> slots = new HashMap<TaskAllocator.SlotKey, Boolean>();
	for (String node : nodes)
	{
	    for (int i = 0; i < slotConfig.getMapSlot(); i++)
	    {
		slots.put(new SlotKey(node, TaskType.MAP, i), true);
	    }
	    for (int i = 0; i < slotConfig.getReduceSlot(); i++)
	    {
		slots.put(new SlotKey(node, TaskType.REDUCE, i), true);
	    }
	}
	return slots;
    }

    private Map<Long, List<TaskEvent>> sortTaskEvents(List<Task> tasks)
    {
	Map<Long, List<TaskEvent>> sortedTasks = new TreeMap<Long, List<TaskEvent>>();
	for (Task task : tasks)
	{
	    putIntoAMultiList(task.getStartTime().getTime(), new TaskEvent(
		    task, TaskStatus.START), sortedTasks);
	    putIntoAMultiList(task.getEndTime().getTime(), new TaskEvent(task,
		    TaskStatus.END), sortedTasks);
	}
	return sortedTasks;
    }

    private void putIntoAMultiList(long time, TaskEvent event,
	    Map<Long, List<TaskEvent>> map)
    {
	List<TaskEvent> events = map.get(time);
	if (events == null)
	{
	    events = new ArrayList<TaskEvent>();
	    map.put(time, events);
	}
	events.add(event);
    }

    private List<AllocatedTask> allocate(Map<SlotKey, Boolean> slotStatus,
	    Map<Long, List<TaskEvent>> taskEvents)
    {
	List<AllocatedTask> allocated = new ArrayList<AllocatedTask>();
	Map<String, SlotKey> allocationStatus = new HashMap<String, TaskAllocator.SlotKey>();
	for (long time : taskEvents.keySet())
	{
	    List<TaskEvent> eventsAtTheMoment = taskEvents.get(time);
	    for (TaskEvent event : eventsAtTheMoment)
	    {
		TaskType taskType = event.task.getType();
		if (event.status == TaskStatus.START)
		{
		    if (taskType == TaskType.MAP)
		    {
			processStartingEvent(event, slotConfig.getMapSlot(), allocated,
			        slotStatus, allocationStatus);
		    }
		    else
		    {
			processStartingEvent(event, slotConfig.getReduceSlot(), allocated,
			        slotStatus, allocationStatus);
		    }
		}
		else
		{
		    SlotKey occupiedSlot = allocationStatus.get(event.task
			    .getAttemptID());
		    if (occupiedSlot == null)
		    {
			System.err.println("Unexpected null allocation by "
			        + event);
		    }
		    slotStatus.put(occupiedSlot, true);
		    allocationStatus.remove(event.task.getAttemptID());
		}
	    }

	}
	return allocated;
    }

    private void processStartingEvent(TaskEvent event, int slotCount,
	    List<AllocatedTask> allocated, Map<SlotKey, Boolean> slotStatus,
	    Map<String, SlotKey> allocationStatus)
    {
	int i = 0;
	for (i = 0; i < slotCount; i++)
	{
	    SlotKey slotKey = new SlotKey(event.task.getNode(),
		    event.task.getType(), i);
	    if (slotStatus.get(slotKey) == true)
	    {
		allocated.add(new AllocatedTask(event.task, i, startTime));
		slotStatus.put(slotKey, false);
		allocationStatus.put(event.task.getAttemptID(), slotKey);
		break;
	    }
	}
	if (i == slotCount)
	    System.err.println(event + " does not have slot to execute");
    }

    private static class SlotKey
    {
	final String   node;
	final TaskType type;
	final int      slot;

	public SlotKey(String node, TaskType type, int slot)
	{
	    this.node = node;
	    this.type = type;
	    this.slot = slot;
	}

	@Override
	public int hashCode()
	{
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ( ( node == null ) ? 0 : node.hashCode() );
	    result = prime * result + slot;
	    result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
	    return result;
	}

	@Override
	public boolean equals(Object obj)
	{
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    SlotKey other = (SlotKey) obj;
	    if (node == null)
	    {
		if (other.node != null) return false;
	    }
	    else if (!node.equals(other.node)) return false;
	    if (slot != other.slot) return false;
	    if (type != other.type) return false;
	    return true;
	}

	@Override
	public String toString()
	{
	    return "SlotKey [node=" + node + ", type=" + type + ", slot="
		    + slot + "]";
	}
    }

    private static class TaskEvent
    {
	final Task       task;
	final TaskStatus status;

	public TaskEvent(Task task, TaskStatus status)
	{
	    this.task = task;
	    this.status = status;
	}

	@Override
	public String toString()
	{
	    return "TaskEvent [task=" + task + ", status=" + status + "]";
	}

    }

    private static enum TaskStatus
    {
	START, END
    }
}
