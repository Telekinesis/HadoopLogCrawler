package org.telekinesis.hcrawler.hadoop.crawler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telekinesis.hcrawler.hadoop.Task;
import org.telekinesis.hcrawler.hadoop.TaskType;
import org.telekinesis.hcrawler.log.CrawlerLogger;

public class HadoopAttemptParser implements DocumentParser<List<Task>>
{
    private final TaskType type;

    public HadoopAttemptParser(TaskType type)
    {
	this.type = type;
    }

    @Override
    public List<Task> parse(Document doc) throws Exception
    {
	String taskID = getTaskID(doc);
	CrawlerLogger.log("crawling for task:" + taskID);
	Element attemptTable = doc.select("center>table").get(0);
	Elements rows = attemptTable.child(0).children();
	List<Task> attempts = new ArrayList<Task>();
	for (int i = 1; i < rows.size(); i++)
	{
	    Element row = rows.get(i);
	    String attemptId = row.child(0).text();
	    Timestamp startTime = HadoopPageTimeParser.parseTime(row.child(1).text());
	    Timestamp endTime = HadoopPageTimeParser.parseTime(row.child(row.childNodeSize() - 5)
		    .text());
	    String host = row.child(row.childNodeSize() - 4).text();
	    Task execution = new Task(taskID, attemptId, type, host, startTime,
		    endTime);
	    attempts.add(execution);
	}
	return attempts;
    }
    
    private String getTaskID(Document doc){
	String text = doc.select("h2").get(0).text();
	return text.split(" ")[0];
    }
    
}
