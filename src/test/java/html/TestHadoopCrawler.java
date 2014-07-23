package html;

import java.io.IOException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.telekinesis.hcrawler.hadoop.HadoopJobStatistics;
import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.MultiThreadHadoopCrawler;
import org.telekinesis.hcrawler.hadoop.OneJobSelector;
import org.telekinesis.hcrawler.hadoop.SingleThreadHadoopCrawler;
import org.telekinesis.hcrawler.hadoop.SlotConfig;
import org.telekinesis.hcrawler.hadoop.SlotConfigExtractor;

import com.google.gson.Gson;

public class TestHadoopCrawler
{
    private static final String jobHistoryURL = "http://bigant-test-001:50030/jobhistoryhome.jsp";
    private static final String jobID = "job_201407041417_0004";
    
    @Ignore@Test
    public void testSingleThreaded() throws IOException{
	SingleThreadHadoopCrawler<SlotConfig> crawler = new SingleThreadHadoopCrawler<SlotConfig>(new SlotConfigExtractor());
	Map<String, JobHistory<SlotConfig>> jobs = crawler.crawl(jobHistoryURL, new OneJobSelector(jobID));
	HadoopJobStatistics statistics = HadoopJobStatistics.create(jobs.get(jobID));
	Gson gsonInstance = new Gson();
	System.out.println(gsonInstance.toJson(statistics));
    }
    
    @Test
    public void testMultiThreaded() throws Exception{
	MultiThreadHadoopCrawler<SlotConfig> crawler = new MultiThreadHadoopCrawler<SlotConfig>(new SlotConfigExtractor());
	Map<String, JobHistory<SlotConfig>> jobs = crawler.crawl(jobHistoryURL, new OneJobSelector(jobID));
	HadoopJobStatistics statistics = HadoopJobStatistics.create(jobs.get(jobID));
	Gson gsonInstance = new Gson();
	System.out.println(gsonInstance.toJson(statistics));
    }
}
