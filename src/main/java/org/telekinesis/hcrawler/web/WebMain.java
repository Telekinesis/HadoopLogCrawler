package org.telekinesis.hcrawler.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.telekinesis.commonclasses.debug.ListPrinter;
import org.telekinesis.hcrawler.hadoop.HadoopJobStatistics;
import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.MultiThreadHadoopCrawler;
import org.telekinesis.hcrawler.hadoop.OneJobSelector;
import org.telekinesis.hcrawler.hadoop.SlotConfig;
import org.telekinesis.hcrawler.hadoop.SlotConfigExtractor;
import org.telekinesis.hcrawler.yarn.YarnCrawler;
import org.telekinesis.hcrawler.yarn.YarnJobStatistics;
import org.telekinesis.hcrawler.yarn.YarnMapReduceAttempt;
import org.telekinesis.hcrawler.yarn.YarnTaskAggregator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebMain
{
    public static void main(String[] args) throws Exception{
	Server server = new Server(8080);
	WebAppContext context = new WebAppContext();
	context.setContextPath("/");
	context.setResourceBase("./pages");	
	context.addServlet(HadoopHello.class, "/hadoopHello.do");
	context.addServlet(HadoopCrawlServlet.class, "/hadoopCrawl.do");
	context.addServlet(YarnHello.class, "/yarnHello.do");	
	context.addServlet(YarnJobList.class, "/yarnJobList.do");
	context.addServlet(YarnCrawlServlet.class, "/yarnCrawl.do");
	
	HandlerCollection handlers = new HandlerCollection();
	handlers.setHandlers(new Handler[]{context, new DefaultHandler()});
    	server.setHandler(handlers);
    	server.start();
	server.join();
    }
    
    @SuppressWarnings("serial")
    public static class HadoopHello extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    request.getRequestDispatcher("hadoopgantt.jsp").forward(request, response);
	}
    }
    
    @SuppressWarnings("serial")
    public static class HadoopCrawlServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    String jobHistoryRoot = request.getParameter("jobHistoryRoot");
	    String jobID = request.getParameter("jobID").trim();
	    MultiThreadHadoopCrawler<SlotConfig> crawler = new MultiThreadHadoopCrawler<SlotConfig>(new SlotConfigExtractor());
            try
            {
        	Map<String, JobHistory<SlotConfig>> jobs = crawler.crawl(jobHistoryRoot, new OneJobSelector(jobID));
        	HadoopJobStatistics statistics = HadoopJobStatistics.create(jobs.get(jobID));
        	String json = convertToJson(statistics);
    	    	sendJson(response, json);
    	    	recordJson(jobID, json);
            }
            catch (Exception e)
            {
	        e.printStackTrace();
            }
	}
    }
    
    @SuppressWarnings("serial")
    public static class YarnHello extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    request.getRequestDispatcher("yarngantt.jsp").forward(request, response);;
	}
    }
    
    @SuppressWarnings("serial")
    public static class YarnJobList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    String jobHistoryRoot = request.getParameter("jobHistoryRoot");
	    YarnCrawler crawler = new YarnCrawler(jobHistoryRoot);
	    Map<String, String> jobs = crawler.listJobs();
	    String json = convertToJson(jobs.keySet());
	    sendJson(response, json);
	}
    }
    
    @SuppressWarnings("serial")
    public static class YarnCrawlServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    String jobHistoryRoot = request.getParameter("jobHistoryRoot");
	    String jobs = request.getParameter("jobs");
	    Gson gsonInstance = new Gson();
	    List<String> jobList = gsonInstance.fromJson(jobs, new TypeToken<List<String>>(){}.getType());
	    ListPrinter.print(jobList);
	    YarnCrawler crawler = new YarnCrawler(jobHistoryRoot);
	    List<YarnMapReduceAttempt> attempts = crawler.crawl(jobList.toArray(new String[]{}));
	    YarnTaskAggregator aggregator = new YarnTaskAggregator();
	    YarnJobStatistics jobStatistics = aggregator.aggregate(attempts);
	    String json = convertToJson(jobStatistics);
	    sendJson(response, json);
	}
    }
    
    
    private static String convertToJson(Object o){
	Gson gsonInstance = new Gson();
	String json = gsonInstance.toJson(o);
	System.out.println(json);
	return json;
    }
    
    private static void sendJson(HttpServletResponse response, String json) throws IOException{
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	writer.write(json);
	writer.flush();
    }
    
    private static void recordJson(String request, String json) throws IOException{
	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(request + ".log")));
	writer.append(json);
	writer.flush();
	writer.close();
    }
    
}
