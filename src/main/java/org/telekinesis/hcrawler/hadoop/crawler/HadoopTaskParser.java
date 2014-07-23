package org.telekinesis.hcrawler.hadoop.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telekinesis.hcrawler.hadoop.Task;
import org.telekinesis.hcrawler.hadoop.TaskType;

public class HadoopTaskParser implements DocumentParser<List<Task>>
{
    private final HadoopAttemptParser attemptParser;
    private final DocumentFetcher fetcher = new DocumentFetcher();

    public HadoopTaskParser(TaskType type)
    {
	this.attemptParser = new HadoopAttemptParser(type);
    }

    @Override
    public List<Task> parse(Document doc) throws Exception
    {
	List<Task> result = new ArrayList<Task>();
	Elements tasks = doc.select("td>a");
	for (Element task : tasks)
	{
	    String attemptsURL = task.absUrl("href");
	    fetcher.addUrl(attemptsURL);
	}
	List<Document> attemptDocs = fetcher.fetch();
	for (Document document : attemptDocs)
        {
	    List<Task> attempts = attemptParser.parse(document);
	    result.addAll(attempts);
        }
	return result;
    }

}
