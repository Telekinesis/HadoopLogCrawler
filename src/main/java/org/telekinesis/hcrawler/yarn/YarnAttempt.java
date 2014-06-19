package org.telekinesis.hcrawler.yarn;

public class YarnAttempt
{
    private final String attemptID;
    private final int    slot;
    private final String startTime;
    private final String endTime;

    public YarnAttempt(String attemptID, int slot, String startTime,
            String endTime)
    {
	this.attemptID = attemptID;
	this.slot = slot;
	this.startTime = startTime;
	this.endTime = endTime;
    }

    public int getSlot()
    {
	return slot;
    }

    public String getStartTime()
    {
	return startTime;
    }

    public String getEndTime()
    {
	return endTime;
    }

    public String getAttemptID()
    {
        return attemptID;
    }

    @Override
    public String toString()
    {
	return "YarnAttempt [attemptID=" + attemptID + ", slot=" + slot
	        + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
