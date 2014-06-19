package org.telekinesis.hcrawler.yarn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.telekinesis.commonclasses.collection.KeyToValueList;

public class YarnTaskAggregator
{
    private Timestamp startTime;
    private Timestamp endTime;
    
    public YarnJobStatistics aggregate(List<YarnMapReduceAttempt> attempts)
    {
	initializeTime();
	List<YarnNode> nodes = new ArrayList<YarnNode>();
	KeyToValueList<String, YarnMapReduceAttempt> sortedAttempts = sortAttemptsByNode(attempts);
	for (String nodeID : sortedAttempts.keySet())
	{
	    List<YarnMapReduceAttempt> attemptsOfTheNode = sortedAttempts.get(nodeID);
	    KeyToValueList<Timestamp, AttemptEvent> sortedEvents = extractEvents(attemptsOfTheNode);
	    YarnNode node = assignSlotsForAttempts(nodeID, sortedEvents);
	    nodes.add(node);
	}
	return new YarnJobStatistics(startTime.toString(), endTime.toString(), nodes);
    }
    
    private void initializeTime(){
	startTime = new Timestamp(Long.MAX_VALUE);
	endTime = new Timestamp(Long.MIN_VALUE);
    }
    
    private KeyToValueList<String, YarnMapReduceAttempt> sortAttemptsByNode(
	    List<YarnMapReduceAttempt> attempts)
    {
	KeyToValueList<String, YarnMapReduceAttempt> sorted = new KeyToValueList<String, YarnMapReduceAttempt>();
	for (YarnMapReduceAttempt attempt : attempts)
	{
	    String nodeID = attempt.getNode();
	    sorted.put(nodeID, attempt);
	}
	return sorted;
    }
    
    private KeyToValueList<Timestamp, AttemptEvent> extractEvents(List<YarnMapReduceAttempt> attemptsOfTheNode){
	KeyToValueList<Timestamp, AttemptEvent> sortedEvents = new KeyToValueList<Timestamp, AttemptEvent>(true);
	for (YarnMapReduceAttempt attempt : attemptsOfTheNode)
        {
	    Timestamp startTime = attempt.getStartTime();
	    Timestamp endTime = attempt.getEndTime();
	    if(endTime.after(startTime)){
		sortedEvents.put(startTime, new AttemptEvent(EventType.START, attempt));
		sortedEvents.put(endTime, new AttemptEvent(EventType.END, attempt));
		updateTime(startTime);
		updateTime(endTime);
	    }else{
		System.out.println("Time mismatch for attempt: " + attempt);
	    }
        }
	return sortedEvents;
    }
    
    private void updateTime(Timestamp time){
	if(startTime.after(time))
	    startTime = time;
	if(endTime.before(time))
	    endTime = time;
    }
    
    private YarnNode assignSlotsForAttempts(String nodeID, KeyToValueList<Timestamp, AttemptEvent> sortedEvents)
    {
	int maxSlotNumber = 0;
	Set<Integer> occupiedSlots = new HashSet<Integer>();
	Map<String, Integer> attemptWorkingSlot = new HashMap<String, Integer>();
	List<YarnAttempt> assignedAttempts = new ArrayList<YarnAttempt>();
	for (Timestamp time : sortedEvents.keySet())
        {
	    List<AttemptEvent> events = sortedEvents.get(time);
	    for (AttemptEvent event : events)
            {
	        if(event.type == EventType.START){
	            int availableSlotID = 0;
	            for (availableSlotID = 0; availableSlotID < maxSlotNumber; availableSlotID++)
                    {
	                if(!occupiedSlots.contains(availableSlotID))
	                    break;
                    }
	            attemptWorkingSlot.put(event.attempt.getAttemptID(), availableSlotID);
	            occupiedSlots.add(availableSlotID);
	            assignedAttempts.add(new YarnAttempt(event.attempt.getAttemptID(), availableSlotID, event.attempt.getStartTime().toString(), event.attempt.getEndTime().toString()));
	            if(availableSlotID >= maxSlotNumber)
	        	maxSlotNumber = availableSlotID + 1;
	        }else{
	            String attemptID = event.attempt.getAttemptID();
	            int slotID = attemptWorkingSlot.get(attemptID);
	            attemptWorkingSlot.remove(attemptID);
	            occupiedSlots.remove(slotID);
	        }
            }
        }
	YarnNode node = new YarnNode(nodeID, maxSlotNumber, assignedAttempts);
	return node;
    }

    private static class AttemptEvent
    {
	final EventType            type;
	final YarnMapReduceAttempt attempt;

	public AttemptEvent(EventType type, YarnMapReduceAttempt attempt)
	{
	    this.type = type;
	    this.attempt = attempt;
	}

	@Override
	public String toString()
	{
	    return "TaskEvent [type=" + type + ", attempt=" + attempt + "]";
	}

    }

    private static enum EventType
    {
	START, END
    }

}
