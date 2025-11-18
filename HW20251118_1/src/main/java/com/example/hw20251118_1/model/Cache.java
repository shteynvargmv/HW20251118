package com.example.hw20251118_1.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {
    private Map<CacheKey, CacheData> cache = new ConcurrentHashMap<>();
    private long timeToLive = 60 * 1000 * 30;//1 cas

    public void put(CacheKey cacheKey, CountryApiResponse[] data) {
        cache.put(cacheKey, new CacheData(data));
    }

    public CountryApiResponse[] get(CacheKey cacheKey) {
        CacheData cacheData = cache.get(cacheKey);

        if (cacheData == null) {
            return null;
        }

        if (cacheData.isExpired(this.timeToLive)) {
            cache.remove(cacheKey);
            return null;
        }

        cacheData.addUsage();
        return cacheData.data;
    }

    public Boolean isEmpty(){
        if (cache.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public Map<CacheKey, CacheData> getCache() {
        return cache;
    }

}
