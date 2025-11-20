package com.example.hw20251118_3.model;

import java.util.List;

public class SourcesResponse extends CacheDataBody{
    private String status;
    private List<NewsSource> sources;

    public String getStatus() {
        return status;
    }

    public List<NewsSource> getSources() {
        return sources;
    }
}