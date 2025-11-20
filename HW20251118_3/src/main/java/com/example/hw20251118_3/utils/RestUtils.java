package com.example.hw20251118_3.utils;

import com.example.hw20251118_3.model.NewsApiResponse;
import com.example.hw20251118_3.model.SourcesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtils {
    @Autowired
    @Qualifier("restTemplateShort")
    private RestTemplate restTemplateShort;
    @Autowired
    @Qualifier("restTemplateLong")
    private RestTemplate restTemplateLong;

    private final Environment env;

    @Autowired
    public RestUtils(Environment env) {
        this.env = env;
    }

    public ResponseEntity<NewsApiResponse> search(String query, Integer page, Integer pageSize) {
        String url = env.getProperty("news.api.base.url") + "everything?" +
                "q=" + query +
                "&page=" + page +
                "&pageSize=" + pageSize +
                "&apiKey=" + env.getProperty("news.api.key");
        return restTemplateLong.getForEntity(url, NewsApiResponse.class);

    }

    public ResponseEntity<NewsApiResponse> top(String country, String category) {
        String url = env.getProperty("news.api.base.url") + "top-headlines?" +
                "country=" + country +
                "&category=" + category +
                "&apiKey=" + env.getProperty("news.api.key");
        return restTemplateLong.getForEntity(url, NewsApiResponse.class);
    }

    public ResponseEntity<SourcesResponse> sources(String country, String category) {
        String url = env.getProperty("news.api.base.url") + "/top-headlines/sources?" +
                "category=" + category +
                "&country=" + country +
                "&apiKey=" + env.getProperty("news.api.key");
        return restTemplateLong.getForEntity(url, SourcesResponse.class);
    }

    public ResponseEntity<NewsApiResponse> bySource(String sourceId, Integer page) {
        String url = env.getProperty("news.api.base.url") + "top-headlines?" +
                "sources=" + sourceId +
                "&page=" + page +
                "&apiKey=" + env.getProperty("news.api.key");
        return restTemplateShort.getForEntity(url, NewsApiResponse.class);
    }
}

