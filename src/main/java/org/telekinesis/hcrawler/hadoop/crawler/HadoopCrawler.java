package org.telekinesis.hcrawler.hadoop.crawler;

import java.util.Map;

import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.JobSelector;

public interface HadoopCrawler<T>
{
    public Map<String, JobHistory<T>> crawl(String jobHistoryURL,
	    JobSelector selector) throws Exception;
}
