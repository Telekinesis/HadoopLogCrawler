package org.telekinesis.hcrawler.yarn;

import java.util.List;

public class YarnNode
{
    private final String            nodeID;
    private final int               maxParallel;
    private final List<YarnAttempt> attempts;

    public YarnNode(String nodeID, int maxParallel, List<YarnAttempt> attempts)
    {
	this.nodeID = nodeID;
	this.maxParallel = maxParallel;
	this.attempts = attempts;
    }

    public String getNodeID()
    {
	return nodeID;
    }

    public int getMaxParallel()
    {
	return maxParallel;
    }

    public List<YarnAttempt> getAttempts()
    {
	return attempts;
    }

    @Override
    public String toString()
    {
	return "YarnNode [nodeID=" + nodeID + ", maxParallel=" + maxParallel
	        + ", attempts=" + attempts + "]";
    }

}
