package html;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.telekinesis.hcrawler.yarn.YarnCrawler;
import org.telekinesis.hcrawler.yarn.YarnJobStatistics;
import org.telekinesis.hcrawler.yarn.YarnMapReduceAttempt;
import org.telekinesis.hcrawler.yarn.YarnTaskAggregator;

import com.google.gson.Gson;

public class TestYarnCrawler
{
    private static final String jobHistoryRoot = "http://bigant-dev-001:19888";
    private static final String jobID = "job_1403674411309_0004";
    
    @Test
    public void testListJobs() throws IOException
    {
	YarnCrawler crawler = new YarnCrawler(jobHistoryRoot);
	Map<String, String> jobs = crawler.listJobs();
	List<YarnMapReduceAttempt> attempts = crawler.crawl(jobID);
	YarnTaskAggregator aggregator = new YarnTaskAggregator();
	YarnJobStatistics jobStatistics = aggregator.aggregate(attempts);
	Gson gsonInstance = new Gson();
	String json = gsonInstance.toJson(jobStatistics);
	System.out.println(json);
	System.out.println(gsonInstance.toJson(jobs.keySet()));
    }
}
