package org.telekinesis.hcrawler.hadoop.crawler;

import org.jsoup.nodes.Document;

public interface DocumentParser<T>
{
    public T parse(Document doc) throws Exception;
}
