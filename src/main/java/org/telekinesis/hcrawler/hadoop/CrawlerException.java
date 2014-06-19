package org.telekinesis.hcrawler.hadoop;

public class CrawlerException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final Exception cause;

    public CrawlerException(Exception cause)
    {
	this.cause = cause;
    }

    public Exception getCause()
    {
        return cause;
    }

    @Override
    public String toString()
    {
	return "CrawlerException [cause=" + cause + "]";
    }
    
}
