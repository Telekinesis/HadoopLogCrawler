package time;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.telekinesis.hcrawler.hadoop.CrawlerException;

public class TestTimeParser
{
    String example1 = "4-Jul-2014 11:07:48";
    String example2 = "3/07 16:34:10";
    @Test
    public void test(){
	System.out.println(parseTime(example1));
	System.out.println(parseTime(example2));
    }
    
    private Timestamp parseTime(String timeString)
    {
	int index = timeString.indexOf("(");
	String timePart = ( index == -1 ) ? timeString : timeString.substring(
	        0, index);
	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols.getInstance(Locale.ENGLISH));
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
    
    private Date parseTimeUsingYearAttachedFormat(String timePart) throws ParseException{
	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols.getInstance(Locale.ENGLISH));
	return format.parse(timePart);
    }
    
    private Date parseTimeWithoutYear(String timePart) throws ParseException{
	SimpleDateFormat format = new SimpleDateFormat("dd/MM HH:mm:ss");
	format.setDateFormatSymbols(DateFormatSymbols.getInstance(Locale.ENGLISH));
	Date time = format.parse(timePart);
	time.setYear(2014 - 1900);
	return time;
    }
}
