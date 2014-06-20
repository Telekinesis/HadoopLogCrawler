package org.telekinesis.hcrawler.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.telekinesis.commonclasses.debug.ListPrinter;
import org.telekinesis.hcrawler.hadoop.HadoopCrawler;
import org.telekinesis.hcrawler.hadoop.JobHistory;
import org.telekinesis.hcrawler.hadoop.JobStatistics;
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
	Server server = new Server();
	ServerConnector connector = new ServerConnector(server);
	connector.setPort(8080);
	server.setConnectors(new Connector[]{connector});
	WebAppContext context = new WebAppContext();
	context.setContextPath("/");
	context.setResourceBase("src/main/resources");
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
	    String jobID = request.getParameter("jobs");
	    HadoopCrawler<SlotConfig> crawler = new HadoopCrawler<SlotConfig>(new SlotConfigExtractor());
	    Map<String, JobHistory<SlotConfig>> jobs = crawler.crawl(jobHistoryRoot, new OneJobSelector(jobID));
	    JobStatistics statistics = JobStatistics.create(jobs.get(jobID));
	    sendObjectAsJson(response, statistics);
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
	    sendObjectAsJson(response, jobs.keySet());
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
	    sendObjectAsJson(response, jobStatistics);
	}
    }
    
    public static void sendObjectAsJson(HttpServletResponse response, Object o) throws IOException{
	String json = convertToJson(o);
	sendJson(response, json);
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
    
}
