package org.telekinesis.hcrawler.yarn;

import java.util.List;

public class YarnJobStatistics
{
    private final String         startTime;
    private final String         endTime;
    private final List<YarnNode> nodes;

    public YarnJobStatistics(String startTime, String endTime,
	    List<YarnNode> nodes)
    {
	this.startTime = startTime;
	this.endTime = endTime;
	this.nodes = nodes;
    }

    public String getStartTime()
    {
	return startTime;
    }

    public String getEndTime()
    {
	return endTime;
    }

    public List<YarnNode> getNodes()
    {
	return nodes;
    }

    @Override
    public String toString()
    {
	return "YarnJobStatistics [startTime=" + startTime + ", endTime="
	        + endTime + ", nodes=" + nodes + "]";
    }

}
