package org.telekinesis.hcrawler.hadoop.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telekinesis.hcrawler.hadoop.ConfigExtractor;
import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.Task;
import org.telekinesis.hcrawler.hadoop.TaskType;
import org.telekinesis.hcrawler.log.CrawlerLogger;

public class HadoopJobParser<T> implements DocumentParser<JobHistory<T>>
{
    private final ConfigExtractor<T> configExtractor;

    public HadoopJobParser(ConfigExtractor<T> configExtractor)
    {
	this.configExtractor = configExtractor;
    }

    @Override
    public JobHistory<T> parse(Document doc) throws Exception
    {
	String jobID = getJobID(doc);
	CrawlerLogger.log("crawling for job:" + jobID);
	T config = crawlJobConfig(doc);
	List<Task> tasks = crawlTaskStatistics(doc);
	return new JobHistory<T>(jobID, config, tasks);
    }
    
    private String getJobID(Document doc){
	String title = doc.select("h2").get(0).text();
	return title.split(" ")[2];
    }

    private T crawlJobConfig(Document doc) throws IOException
    {
	Map<String, String> rawAttributes = extractRawAttributes(doc);
	T config = configExtractor.extract(rawAttributes);
	return config;
    }

    private Map<String, String> extractRawAttributes(Document doc)
	    throws IOException
    {
	Element configTag = doc.select("b~a").get(0);
	String url = configTag.absUrl("href");
	Document configDoc = Jsoup.connect(url).timeout(0).get();
	Elements elements = configDoc.select("tr");
	Map<String, String> rawAttributes = new HashMap<String, String>();
	for (Element row : elements)
	{
	    Element attributeName = row.child(0);
	    Element attributeValue = row.child(1);
	    if (attributeName.tagName().equals("th")) continue;
	    rawAttributes.put(attributeName.child(0).text(),
		    attributeValue.text());
	}
	return rawAttributes;
    }

    private List<Task> crawlTaskStatistics(Document doc) throws Exception
    {
	Element rows = doc.select("tbody").get(0);
	List<Element> children = rows.children();
	List<Task> tasks = new ArrayList<Task>();
	for (int i = 1; i < children.size(); i++)
	{
	    Element row = children.get(i);
	    TaskType type = TaskType.valueOf(row.child(0).text().toUpperCase());
	    if (type == TaskType.MAP || type == TaskType.REDUCE)
	    {
		Element countNode = row.child(1).child(0);
		String taskLogURL = countNode.absUrl("href");
		Document taskLogDoc = Jsoup.connect(taskLogURL).timeout(0).get();
		HadoopTaskParser taskParser = new HadoopTaskParser(type);
		List<Task> parsedTasks = taskParser.parse(taskLogDoc);
		tasks.addAll(parsedTasks);
	    }
	}
	return tasks;
    }

}
