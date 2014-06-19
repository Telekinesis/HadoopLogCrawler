package org.telekinesis.hcrawler.hadoop;

import java.util.List;

public class JobHistory<T>
{
    private final T                   jobConfig;
    private final List<Task> tasks;

    public JobHistory(T jobConfig, List<Task> tasks)
    {
	this.jobConfig = jobConfig;
	this.tasks = tasks;
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
	return "JobHistory [jobConfig=" + jobConfig + ", tasks=" + tasks + "]";
    }

}
