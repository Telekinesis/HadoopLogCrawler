package org.telekinesis.hcrawler.hadoop.crawler;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.telekinesis.hcrawler.hadoop.CrawlerException;

public class HadoopPageTimeParser
{
    private HadoopPageTimeParser(){}
    
    public static Timestamp parseTime(String timeString)
    {
	int index = timeString.indexOf("(");
	String timePart = ( index == -1 ) ? timeString : timeString.substring(
	        0, index);
	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols
	        .getInstance(Locale.ENGLISH));
	Date time;
	try
	{
	    time = parseTimeUsingYearAttachedFormat(timePart);
	    return new Timestamp(time.getTime());
	}
	catch (ParseException e)
	{
	    try
	    {
		time = parseTimeWithoutYear(timePart);
		return new Timestamp(time.getTime());
	    }
	    catch (ParseException e1)
	    {
		e1.printStackTrace();
		throw new CrawlerException(e);
	    }
	}
    }

    private static Date parseTimeUsingYearAttachedFormat(String timePart)
	    throws ParseException
    {
	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols
	        .getInstance(Locale.ENGLISH));
	return format.parse(timePart);
    }

    private static Date parseTimeWithoutYear(String timePart) throws ParseException
    {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols
	        .getInstance(Locale.ENGLISH));
	Date time = format.parse(timePart);
	time.setYear(2014 - 1900);
	return time;
    }
}
