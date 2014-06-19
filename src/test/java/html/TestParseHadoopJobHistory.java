package html;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.telekinesis.hcrawler.hadoop.Task;
import org.telekinesis.hcrawler.hadoop.TaskType;

public class TestParseHadoopJobHistory
{
    private static final String targetURL     = "http://bigant-dev-001:40030/jobtracker.jsp";
    private static final String jobHistoryURL = "http://bigant-dev-001:40030/jobhistoryhome.jsp";
    private static final String jobLogURL     = "http://bigant-dev-001:40030/jobdetailshistory.jsp?logFile=file:/bi21a/var/ibm/biginsights/hadoop/logs/history/done/version-1/bigant-dev-001_1402542559351_/2014/06/12/000000/job_201406121109_0001_1402557311015_biadmin_Date%2BBased%2BNative%2BSorter";
    private static final String taskDetailURL = "http://bigant-dev-001:40030/jobtaskshistory.jsp?logFile=file:/bi21a/var/ibm/biginsights/hadoop/logs/history/done/version-1/bigant-dev-001_1402542559351_/2014/06/12/000000/job_201406121109_0001_1402557311015_biadmin_Date%2BBased%2BNative%2BSorter&taskType=MAP&status=all";

    @Ignore
    @Test
    public void test() throws IOException
    {
	Document doc = Jsoup.connect(targetURL).get();
	Elements e = doc.select("a[href=/jobhistoryhome.jsp]");
	System.out.println(e.get(0).attr("href"));
    }

    @Ignore
    @Test
    public void testListJobs() throws IOException
    {
	Document doc = Jsoup.connect(jobHistoryURL).get();
	Elements elements = doc.select("td>a");
	for (Element element : elements)
	{
	    System.out.println(element.text());
	    System.out.println(element.attr("href"));
	}
    }

    @Ignore
    @Test
    public void testGetJobConfig() throws IOException
    {
	Document doc = Jsoup.connect(jobLogURL).get();
	Element element = doc.select("b~a").get(0);
	String url = element.absUrl("href");
	System.out.println(url);
	doc = Jsoup.connect(url).get();
	Elements elements = doc.select("tr");
	int mapSlotCount = 0;
	int reduceSlotCount = 0;
	for (Element row : elements)
	{
	    System.out.println(row);
	    Element attributeName = row.child(0);
	    Element attributeValue = row.child(1);
	    System.out.println(attributeName.tagName());
	    if (attributeName.tagName().equals("th")) continue;
	    if (attributeName.child(0).text()
		    .equals("mapred.tasktracker.map.tasks.maximum"))
		mapSlotCount = Integer.parseInt(attributeValue.text());
	    if (attributeName.child(0).text()
		    .equals("mapred.tasktracker.reduce.tasks.maximum"))
		reduceSlotCount = Integer.parseInt(attributeValue.text());
	}
	System.out.println(mapSlotCount);
	System.out.println(reduceSlotCount);
    }

    @Test
    public void testGetTaskAllocation() throws IOException
    {
	Document doc = Jsoup.connect(jobLogURL).get();
	Element rows = doc.select("tbody").get(0);
	List<Element> children = rows.children();
	for (int i = 1; i < children.size(); i++)
	{
	    Element row = children.get(i);
	    TaskType type = TaskType.valueOf(row.child(0).text().toUpperCase());
	    Element countNode = row.child(1).child(0);
	    int taskCount = Integer.parseInt(countNode.text());
	    String taskLogURL = countNode.absUrl("href");
	    System.out.println(new TaskStatistics(type, taskCount, taskLogURL));
	}
    }
    
    public static class TaskStatistics
    {
    	private final TaskType type;
    	private final int      taskCount;
    	private final String   taskLogURL;

    	public TaskStatistics(TaskType type, int taskCount, String taskLogURL)
    	{
    	    this.type = type;
    	    this.taskCount = taskCount;
    	    this.taskLogURL = taskLogURL;
    	}

    	public TaskType getType()
    	{
    	    return type;
    	}

    	public int getTaskCount()
    	{
    	    return taskCount;
    	}

    	public String getTaskLogURL()
    	{
    	    return taskLogURL;
    	}

    	@Override
    	public String toString()
    	{
    	    return "TaskStatus [type=" + type + ", taskCount=" + taskCount
    		    + ", taskLogURL=" + taskLogURL + "]";
    	}
    }
    
    @Test
    public void testGetTaskDetail() throws IOException, ParseException{
	Document doc = Jsoup.connect(taskDetailURL).get();
	Elements tasks = doc.select("td>a");
	for (Element task : tasks)
        {
	    String taskID = task.text();
	    String url = task.absUrl("href");
	    Document detailDoc = Jsoup.connect(url).get();
	    Element attemptTable = detailDoc.select("center>table").get(0);
	    Elements rows = attemptTable.child(0).children();
	    for (int i = 1; i < rows.size(); i++)
            {
	        Element row = rows.get(i);
	        String attemptId = row.child(0).text();
	        String startTime = row.child(1).text();
	        Timestamp start = parseTime(startTime);
	        String endTime = row.child(2).text();
	        Timestamp end = parseTime(endTime);
	        String host = row.child(3).text();
	        Task execution = new Task(taskID, attemptId, TaskType.MAP, host, start, end);
	        System.out.println(execution);
            }
        }
    }
    
    private Timestamp parseTime(String timeString) throws ParseException{
	int index = timeString.indexOf("(");
	String timePart = (index == -1) ? timeString : timeString.substring(0, index);
	DateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss");
	Date time = format.parse(timePart);
	System.out.println(time);
	return new Timestamp(time.getTime());
    }

}
