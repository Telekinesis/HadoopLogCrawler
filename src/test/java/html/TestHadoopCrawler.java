package html;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.telekinesis.hcrawler.hadoop.HadoopCrawler;
import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.JobStatistics;
import org.telekinesis.hcrawler.hadoop.OneJobSelector;
import org.telekinesis.hcrawler.hadoop.SlotConfig;
import org.telekinesis.hcrawler.hadoop.SlotConfigExtractor;

import com.google.gson.Gson;

public class TestHadoopCrawler
{
    private static final String jobHistoryURL = "http://bigant-dev-001:40030/jobhistoryhome.jsp";
    private static final String jobID = "job_201406121109_0001";
    
    @Test
    public void test() throws IOException{
	HadoopCrawler<SlotConfig> crawler = new HadoopCrawler<SlotConfig>(new SlotConfigExtractor());
	Map<String, JobHistory<SlotConfig>> jobs = crawler.crawl(jobHistoryURL, new OneJobSelector(jobID));
	JobStatistics statistics = JobStatistics.create(jobs.get(jobID));
	Gson gsonInstance = new Gson();
	System.out.println(gsonInstance.toJson(statistics));
    }
}
