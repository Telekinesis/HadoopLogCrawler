package html;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.telekinesis.hcrawler.yarn.YarnDatatableProcessor;

public class TestParseYarnHistory
{
    private static final String url = "http://bigant-dev-001:19888/jobhistory/tasks/job_1402538726852_0018/m";
    
    @Test
    public void test() throws IOException{
	Document doc = Jsoup.connect(url).timeout(0).get();
	Element dataScript = doc.select("th>script").get(0);
	String historyJson = YarnDatatableProcessor.extractDataFromScript(dataScript);
	System.out.println(historyJson);
    }

}
