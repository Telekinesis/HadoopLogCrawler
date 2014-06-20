package org.telekinesis.hcrawler.yarn;

import java.util.List;

public class YarnJobStatistics
{
    private final long           durationInSecond;
    private final List<YarnNode> nodes;

    public YarnJobStatistics(long durationInSecond, List<YarnNode> nodes)
    {
	this.durationInSecond = durationInSecond;
	this.nodes = nodes;
    }

    public List<YarnNode> getNodes()
    {
	return nodes;
    }

    public long getDurationInSecond()
    {
	return durationInSecond;
    }

    @Override
    public String toString()
    {
	return "YarnJobStatistics [durationInSecond=" + durationInSecond
	        + ", nodes=" + nodes + "]";
    }

}
