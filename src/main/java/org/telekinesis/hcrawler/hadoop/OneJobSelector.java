package org.telekinesis.hcrawler.hadoop;

public class OneJobSelector implements JobSelector
{
    private final String jobID;
    
    public OneJobSelector(String jobID)
    {
	this.jobID = jobID;
    }

    @Override
    public boolean accept(String jobID)
    {
	return this.jobID.equals(jobID);
    }

}
