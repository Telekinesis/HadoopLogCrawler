package org.telekinesis.hcrawler.hadoop;

import java.util.Map;

public interface ConfigExtractor<T>
{
    public T extract(Map<String, String> rawAttributes);
}
