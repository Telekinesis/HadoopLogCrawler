package org.telekinesis.hcrawler.yarn;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.telekinesis.hcrawler.hadoop.TaskType;
import org.telekinesis.hcrawler.yarn.YarnMapReduceAttempt.AttemptState;

public class YarnCrawler
{
    private final String jobHistoryURL;
    
    public YarnCrawler(String jobHistoryURL)
    {
	this.jobHistoryURL = jobHistoryURL;
    }

    public Map<String, String> listJobs() throws IOException{
	Document doc = Jsoup.connect(jobHistoryURL).timeout(0).get();
	Element script = doc.select("th>script").get(0);
	String data = YarnDatatableProcessor.extractDataFromScript(script);
	List<YarnJobHistory> jobHistory = YarnDatatableProcessor.breakScriptIntoTuples(data, new DataTableParser<YarnJobHistory>()
	{
	    private final Pattern hrefExtractor = Pattern.compile("(?<=(\\<a\\shref=\\')).*(?=(\\'\\>))");
	    private final Pattern jobIDExtractor = Pattern.compile("(?<=(\\'\\>)).*(?=(\\<\\/a\\>))");
	    
	    @Override
            public YarnJobHistory parse(String[] columns)
            {
	        String jobRelatedColumn = columns[3];
	        Matcher m = hrefExtractor.matcher(jobRelatedColumn);
	        m.find();
	        String historyURL = m.group();
	        m = jobIDExtractor.matcher(jobRelatedColumn);
	        m.find();
	        String jobID = m.group();
		return new YarnJobHistory(jobID, historyURL);
            }
	});
	Map<String, String> jobMap = new HashMap<String, String>();
	for (YarnJobHistory yarnJobHistory : jobHistory)
        {
	    jobMap.put(yarnJobHistory.getJobID(), yarnJobHistory.getHistoryURL());
        }
	return jobMap;
    }
    
    public List<YarnMapReduceAttempt> crawl(String... jobList) throws IOException{
	List<YarnMapReduceAttempt> attempts = new ArrayList<YarnMapReduceAttempt>();
	for (String jobID : jobList)
        {
	    String attemptsBaseURL = jobHistoryURL + "/jobhistory/attempts/" + jobID;
	    for (AttemptType type : AttemptType.values())
	    {
		String mapAttemptsURL = attemptsBaseURL + "/m/" + type;
		String reduceAttemptsURL = attemptsBaseURL + "/r/" + type;
		attempts.addAll(extractAttempts(mapAttemptsURL, jobID, TaskType.MAP));
		attempts.addAll(extractAttempts(reduceAttemptsURL, jobID, TaskType.REDUCE));
	    }
        }
	return attempts;
    }
    
    private List<YarnMapReduceAttempt> extractAttempts(String url, final String jobID, final TaskType taskType) throws IOException{
	Document doc = Jsoup.connect(url).timeout(0).get();
	Element script = doc.select("th>script").get(0);
	String data = YarnDatatableProcessor.extractDataFromScript(script);
	List<YarnMapReduceAttempt> attempts = YarnDatatableProcessor.breakScriptIntoTuples(data, new DataTableParser<YarnMapReduceAttempt>(){

	    @Override
            public YarnMapReduceAttempt parse(String[] columns)
            {
	        String attemptID = columns[0].split(" ")[1];
	        String taskID = extractTaskID(attemptID);
	        AttemptState state = AttemptState.valueOf(columns[1]);
	        String rawNodeID = columns[3];
	        String nodeID = extractNodeID(rawNodeID);
	        Timestamp startTime = new Timestamp(Long.parseLong(columns[5]));
	        Timestamp endTime = new Timestamp(Long.parseLong(columns[6]));
	        return new YarnMapReduceAttempt(jobID, taskID, attemptID, taskType, state, nodeID, startTime, endTime);
            }
	    
	    private String extractNodeID(String rawNodeID){
		final Pattern p = Pattern.compile("(?<=(\\'\\>)).*(?=(\\<\\/a\\>))");
		Matcher m = p.matcher(rawNodeID);
		m.find();
		return m.group();
	    }
	    
	    private String extractTaskID(String attemptID){
		int splitterIndex = attemptID.lastIndexOf("_");
		return attemptID.substring(0, splitterIndex).replace("attempt", "task");
	    }

	});
	return attempts;
    }
    
    public static enum AttemptType{
	FAILED, KILLED, SUCCESSFUL
    }
}
