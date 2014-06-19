package org.telekinesis.hcrawler.hadoop;

public class SlotConfig
{
    private final int mapSlot;
    private final int reduceSlot;

    public SlotConfig(int mapSlot, int reduceSlot)
    {
	this.mapSlot = mapSlot;
	this.reduceSlot = reduceSlot;
    }

    public int getMapSlot()
    {
	return mapSlot;
    }

    public int getReduceSlot()
    {
	return reduceSlot;
    }

    @Override
    public String toString()
    {
	return "SlotConfig [mapSlot=" + mapSlot + ", reduceSlot=" + reduceSlot
	        + "]";
    }

}
