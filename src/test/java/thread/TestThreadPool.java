package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.telekinesis.commonclasses.debug.ListPrinter;

public class TestThreadPool
{
    @Test
    public void test() throws InterruptedException, ExecutionException
    {
	ExecutorService pool = Executors.newCachedThreadPool();
	final List<Integer> result = Collections
	        .synchronizedList(new ArrayList<Integer>());
	List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
	tasks.add(new Callable<Integer>()
	{

	    @Override
	    public Integer call() throws Exception
	    {
		Thread.sleep(1000);
		return 1;
	    }
	});
	tasks.add(new Callable<Integer>()
	{

	    @Override
	    public Integer call() throws Exception
	    {
		Thread.sleep(500);
		return 2;
	    }
	});
	tasks.add(new Callable<Integer>()
	{

	    @Override
	    public Integer call() throws Exception
	    {
		Thread.sleep(300);
		return 3;
	    }
	});
	List<Future<Integer>> taskResult = pool.invokeAll(tasks);
	pool.shutdown();
	boolean success = pool.awaitTermination(Long.MAX_VALUE,
	        TimeUnit.MILLISECONDS);
	System.out.println(success);
	ListPrinter.print(result);
    }
}
