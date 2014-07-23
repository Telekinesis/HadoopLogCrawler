package org.telekinesis.hcrawler.hadoop.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentFetcher
{
    private List<String> urlsToFetch = new ArrayList<String>();
    
    public void addUrl(String url){
	urlsToFetch.add(url);
    }
    
    public List<Document> fetch() throws IOException, InterruptedException, ExecutionException{
	List<Callable<Document>> tasks = new ArrayList<Callable<Document>>();
	for (final String url : urlsToFetch)
        {
	    tasks.add(new Callable<Document>()
	    {

		@Override
                public Document call() throws Exception
                {
		    Document doc = Jsoup.connect(url).timeout(0).get();
	            return doc;
                }
	    });
        }
	urlsToFetch.clear();
	ExecutorService threadPool = Executors.newFixedThreadPool(3);
	List<Future<Document>> documents = threadPool.invokeAll(tasks);
	threadPool.shutdown();
	threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	List<Document> result = new ArrayList<Document>();
	for (Future<Document> future : documents)
        {
	    result.add(future.get());
        }
	return result;
    }
}
