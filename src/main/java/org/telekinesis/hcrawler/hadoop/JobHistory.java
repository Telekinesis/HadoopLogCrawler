package org.telekinesis.hcrawler.hadoop;

import java.util.List;

public class JobHistory<T>
{
    private final String     jobID;
    private final T          jobConfig;
    private final List<Task> tasks;

    public JobHistory(String jobID, T jobConfig, List<Task> tasks)
    {
	this.jobID = jobID;
	this.jobConfig = jobConfig;
	this.tasks = tasks;
    }

    public String getJobID()
    {
	return jobID;
    }

    public T getJobConfig()
    {
	return jobConfig;
    }

    public List<Task> getTasks()
    {
	return tasks;
    }

    @Override
    public String toString()
    {
	return "JobHistory [jobID=" + jobID + ", jobConfig=" + jobConfig
	        + ", tasks=" + tasks + "]";
    }

}
