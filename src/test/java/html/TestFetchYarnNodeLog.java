package html;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.telekinesis.commonclasses.debug.ListPrinter;
import org.telekinesis.hcrawler.yarn.node.NodeLogExtractor;

public class TestFetchYarnNodeLog
{
    private static final String url = "http://bigant-dev-003:8042/logs/yarn-yarn-nodemanager-bigant-dev-003.log";
    private static final Timestamp startTime = Timestamp.valueOf("2014-07-09 23:34:00");
    private static final Timestamp endTime = Timestamp.valueOf("2014-07-09 23:43:00");
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    
    @Test
    public void test() throws IOException, ParseException {
	System.out.println(new Timestamp(format.parse("2014-07-10 13:52:14,281").getTime()));
	NodeLogExtractor extractor = new NodeLogExtractor();
	List<String> result = extractor.extract(url, startTime, endTime);
	ListPrinter.print(result);
    }
}
