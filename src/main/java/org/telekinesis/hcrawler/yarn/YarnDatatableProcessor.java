package org.telekinesis.hcrawler.yarn;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class YarnDatatableProcessor
{
    private YarnDatatableProcessor(){}
    
    public static String extractDataFromScript(Element scriptTag){
	String jobHistoryScript = scriptTag.dataNodes().get(0).getWholeData();
	int index = jobHistoryScript.indexOf("=[");
	return jobHistoryScript.substring(index + 1);
    }
    
    public static <T> List<T> breakScriptIntoTuples(String script, DataTableParser<T> parser){
	Gson gsonInstance = new Gson();
	List<String[]> rows = gsonInstance.fromJson(script, new TypeToken<List<String[]>>(){}.getType());
	List<T> tuples = new ArrayList<T>();
	for (String[] row : rows)
        {
	    tuples.add(parser.parse(row));
        }
	return tuples;
    }
}
