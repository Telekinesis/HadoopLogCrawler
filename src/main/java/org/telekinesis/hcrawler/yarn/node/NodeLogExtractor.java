package org.telekinesis.hcrawler.yarn.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NodeLogExtractor
{
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    
    public List<String> extract(String logPath, Timestamp startTime, Timestamp endTime) throws IOException{
	if(startTime.after(endTime))
	    throw new IllegalArgumentException("Start time shall not be later than end time");
	URL url = new URL(logPath);
	BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	List<String> extracted = extractLogs(reader, startTime, endTime);
	return extracted;
    }
    
    private List<String> extractLogs(BufferedReader reader, Timestamp startTime, Timestamp endTime) throws IOException{
	List<String> extracted = new ArrayList<String>();
	boolean foundStart = false;
	int readLine = 0;
	String line;
	while((line = reader.readLine()) != null){
	    readLine++;
	    System.out.println("Read " + readLine + " lines");
	    try{
		Timestamp time = extractTimeFromLog(line);
		if(time.after(endTime)){
		    break;
		}
		if(!foundStart && time.after(startTime)){
		    foundStart = true;
		}
		if(foundStart){
		    extracted.add(line);
		}
	    }catch(ParseException invalidLine){}
	}
	return extracted;
    }
    
    private Timestamp extractTimeFromLog(String line) throws ParseException{
	if(line.length() < 23)
	    throw new ParseException(line, 0);
	String timePart = line.substring(0, 23);
	Timestamp time = new Timestamp(format.parse(timePart).getTime());
	return time;
    }
}
