package org.telekinesis.hcrawler.yarn;

public class YarnJobHistory
{
    private final String jobID;
    private final String historyURL;

    public YarnJobHistory(String jobID, String historyURL)
    {
	this.jobID = jobID;
	this.historyURL = historyURL;
    }

    public String getJobID()
    {
	return jobID;
    }

    public String getHistoryURL()
    {
	return historyURL;
    }

    @Override
    public String toString()
    {
	return "YarnJob [jobID=" + jobID + ", historyURL=" + historyURL + "]";
    }
    
}
