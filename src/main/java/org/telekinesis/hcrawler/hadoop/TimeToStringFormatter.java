package org.telekinesis.hcrawler.hadoop;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeToStringFormatter
{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String format(long time){
	Date date = new Date(time);
	return formatter.format(date);
    }
    
    public static String format(Timestamp timestamp){
	return format(timestamp.getTime());
    }
}
