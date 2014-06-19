package org.telekinesis.hcrawler.hadoop;

import java.util.Map;

public class SlotConfigExtractor implements ConfigExtractor<SlotConfig>
{

    public SlotConfig extract(Map<String, String> rawAttributes)
    {
	int mapSlot = Integer.parseInt(rawAttributes.get("mapred.tasktracker.map.tasks.maximum"));
	int reduceSlot = Integer.parseInt(rawAttributes.get("mapred.tasktracker.reduce.tasks.maximum"));
	SlotConfig config = new SlotConfig(mapSlot, reduceSlot);
	return config;
    }

}
