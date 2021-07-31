package com.hzvh.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, Integer> {
    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUCache(int maxSize) {
        super(maxSize, 0.75F, true);
        this.maxElements = maxSize;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.com.hzvh.util.LinkedHashMap#removeEldestEntry(java.com.hzvh.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest)
    {
        return (this.size() > this.maxElements);
    }
}
