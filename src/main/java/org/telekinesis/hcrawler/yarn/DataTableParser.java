package org.telekinesis.hcrawler.yarn;

public interface DataTableParser<T>
{
    public T parse(String[] columns);
}
