package org.telekinesis.hcrawler.hadoop;

import java.util.Map;

public class SlotConfigExtractor implements ConfigExtractor<SlotConfig>
{

    public SlotConfig extract(Map<String, String> rawAttributes)
    {
	int mapSlot, reduceSlot;
	if(rawAttributes.containsKey("mapred.tasktracker.map.tasks.maximum")){
	    mapSlot = Integer.parseInt(rawAttributes.get("mapred.tasktracker.map.tasks.maximum"));
	}else{
	    mapSlot = Integer.parseInt(rawAttributes.get("mapreduce.tasktracker.map.tasks.maximum"));
	}
	if(rawAttributes.containsKey("mapred.tasktracker.reduce.tasks.maximum")){
	    reduceSlot = Integer.parseInt(rawAttributes.get("mapred.tasktracker.reduce.tasks.maximum"));
	}else{
	    reduceSlot = Integer.parseInt(rawAttributes.get("mapreduce.tasktracker.reduce.tasks.maximum"));
	}
	SlotConfig config = new SlotConfig(mapSlot, reduceSlot);
	return config;
    }

}
