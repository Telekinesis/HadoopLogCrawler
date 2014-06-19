package org.telekinesis.hcrawler.hadoop;

public interface JobSelector
{
    public boolean accept(String jobID);
}
