package org.telekinesis.hcrawler.hadoop;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HadoopCrawler<T>
{
    private final ConfigExtractor<T> configExtractor;

    public HadoopCrawler(ConfigExtractor<T> configExtractor)
    {
	this.configExtractor = configExtractor;
    }

    public Map<String, JobHistory<T>> crawl(String jobHistoryURL,
	    JobSelector selector) throws IOException
    {
	Map<String, JobHistory<T>> result = new HashMap<String, JobHistory<T>>();
	Document doc = Jsoup.connect(jobHistoryURL).get();
	Elements elements = doc.select("td>a");
	for (Element element : elements)
	{
	    String jobID = element.text();
	    if (selector.accept(jobID))
	    {
		String jobURL = element.absUrl("href");
		JobHistory<T> history = crawlForJob(jobURL);
		result.put(jobID, history);
	    }
	}
	return result;
    }

    private JobHistory<T> crawlForJob(String jobURL) throws IOException
    {
	Document doc = Jsoup.connect(jobURL).get();
	T config = crawlJobConfig(doc);
	List<Task> tasks = crawlTaskStatistics(doc);
	return new JobHistory<T>(config, tasks);
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
	Document configDoc = Jsoup.connect(url).get();
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

    private List<Task> crawlTaskStatistics(Document doc)
	    throws IOException
    {
	Element rows = doc.select("tbody").get(0);
	List<Element> children = rows.children();
	List<Task> tasks = new ArrayList<Task>();
	for (int i = 1; i < children.size(); i++)
	{
	    Element row = children.get(i);
	    TaskType type = TaskType.valueOf(row.child(0).text().toUpperCase());
	    Element countNode = row.child(1).child(0);
	    String taskLogURL = countNode.absUrl("href");
	    Document taskLogDoc = Jsoup.connect(taskLogURL).get();
	    List<Task> attempts = crawlTasks(taskLogDoc, type);
	    tasks.addAll(attempts);
	}
	return tasks;
    }

    private List<Task> crawlTasks(Document taskLogDoc, TaskType type)
	    throws IOException
    {
	List<Task> result = new ArrayList<Task>();
	Elements tasks = taskLogDoc.select("td>a");
	for (Element task : tasks)
	{
	    String taskID = task.text();
	    String attemptsURL = task.absUrl("href");
	    List<Task> attempts = crawlAttempts(attemptsURL, taskID, type);
	    result.addAll(attempts);
	}
	return result;
    }

    private List<Task> crawlAttempts(String attemptsURL, String taskID, TaskType type) throws IOException
    {
	List<Task> attempts = new ArrayList<Task>();
	Document detailDoc = Jsoup.connect(attemptsURL).get();
	Element attemptTable = detailDoc.select("center>table").get(0);
	Elements rows = attemptTable.child(0).children();
	for (int i = 1; i < rows.size(); i++)
	{
	    Element row = rows.get(i);
	    String attemptId = row.child(0).text();
	    Timestamp startTime = parseTime(row.child(1).text());
	    Timestamp endTime = parseTime(row.child(row.childNodeSize() - 5).text());
	    String host = row.child(row.childNodeSize() - 4).text();
	    Task execution = new Task(taskID, attemptId, type, host, startTime, endTime);
	    attempts.add(execution);
	}
	return attempts;
    }

    private Timestamp parseTime(String timeString)
    {
	int index = timeString.indexOf("(");
	String timePart = ( index == -1 ) ? timeString : timeString.substring(
	        0, index);
	DateFormat format = new SimpleDateFormat("dd/MM HH:mm:ss");
	Date time;
	try
	{
	    time = format.parse(timePart);
	    return new Timestamp(time.getTime());
	}
	catch (ParseException e)
	{
	    e.printStackTrace();
	    throw new CrawlerException(e);
	}
    }

}
