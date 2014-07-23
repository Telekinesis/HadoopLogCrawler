package org.telekinesis.hcrawler.hadoop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telekinesis.hcrawler.hadoop.crawler.DocumentFetcher;
import org.telekinesis.hcrawler.hadoop.crawler.HadoopCrawler;
import org.telekinesis.hcrawler.hadoop.crawler.HadoopJobParser;
import org.telekinesis.hcrawler.log.CrawlerLogger;

public class MultiThreadHadoopCrawler<T> implements HadoopCrawler<T>
{
    private final HadoopJobParser<T> jobParser;
    private final DocumentFetcher fetcher = new DocumentFetcher();

    public MultiThreadHadoopCrawler(ConfigExtractor<T> configExtractor)
    {
	this.jobParser = new HadoopJobParser<T>(configExtractor);
    }

    @Override
    public Map<String, JobHistory<T>> crawl(String jobHistoryURL,
            JobSelector selector) throws Exception
    {
	Map<String, JobHistory<T>> result = new HashMap<String, JobHistory<T>>();
	Document doc = Jsoup.connect(jobHistoryURL).timeout(0).get();
	Elements elements = doc.select("td>a");
	for (Element element : elements)
	{
	    String jobID = element.text();
	    if (selector.accept(jobID))
	    {
		String jobURL = element.absUrl("href");
		fetcher.addUrl(jobURL);
	    }
	}
	List<Document> documents = fetcher.fetch();
	for (Document document : documents)
        {
	    JobHistory<T> history = jobParser.parse(document);
	    CrawlerLogger.log("Job history collected: " + history.getJobID());
	    result.put(history.getJobID(), history);
        }
	return result;
    }

}
