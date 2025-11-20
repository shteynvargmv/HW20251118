package com.example.hw20251118_3.services;

import com.example.hw20251118_3.model.*;
import com.example.hw20251118_3.utils.CacheUtils;
import com.example.hw20251118_3.utils.RestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NewsApiServices {
    @Autowired
    private Cache cache;
    @Autowired
    private ObjectFactory<CacheStat> cacheStatFactory;
    @Autowired
    private RestUtils restUtils;

    public ResponseEntity<?> search(String query, Integer page, Integer pageSize) {

        if (page == null || page <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: page <= 0");
            return ResponseEntity.badRequest().body(result);
        }
        if (pageSize == null || pageSize <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: pageSize <= 0");
            return ResponseEntity.badRequest().body(result);
        }

        CacheKey cacheKey = CacheUtils.getCacheKey("/search",
                new Param("q", query),
                new Param("page", Integer.toString(page)),
                new Param("pageSize", Integer.toString(pageSize)));

        NewsApiResponse cached = (NewsApiResponse) cache.get(cacheKey);
        if (cached != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            if (cached.getTotalResults() != 0) {
                return ResponseEntity.ok(cached);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new HashMap<String, String>() {{
                            put("result", "Not Found");
                        }});
            }
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<NewsApiResponse> response = restUtils.search(query, page, pageSize);
        if (response.getStatusCode().is2xxSuccessful()) {
            NewsApiResponse result = response.getBody();
            cache.put(cacheKey, result);
            if (result.getTotalResults() != 0) {
                return response;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> top(String country, String category) {
        CacheKey cacheKey = CacheUtils.getCacheKey("/top",
                new Param("country", country),
                new Param("category", category));
        NewsApiResponse cached = (NewsApiResponse) cache.get(cacheKey);
        if (cached != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            if (cached.getTotalResults() != 0) {
                return ResponseEntity.ok(cached);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("result", "Not Found");
                    }});
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<NewsApiResponse> response = restUtils.top(country, category);
        if (response.getStatusCode().is2xxSuccessful()) {
            NewsApiResponse result = response.getBody();
            cache.put(cacheKey, result);
            if (result.getTotalResults() != 0) {
                return response;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> sources(String country, String category) {
        CacheKey cacheKey = CacheUtils.getCacheKey("/sources",
                new Param("country", country),
                new Param("category", category));
        SourcesResponse cached = (SourcesResponse) cache.get(cacheKey);
        if (cached != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            if (!cached.getSources().isEmpty()) {
                return ResponseEntity.ok(cached);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new HashMap<String, String>() {{
                            put("result", "Not Found");
                        }});
            }
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<SourcesResponse> response = restUtils.sources(country, category);
        if (response.getStatusCode().is2xxSuccessful()) {
            SourcesResponse result = response.getBody();
            cache.put(cacheKey, result);
            if (!result.getSources().isEmpty()) {
                return response;
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> bySource(String sourceId, Integer page) {
        if (page == null || page <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: page <= 0");
            return ResponseEntity.badRequest().body(result);
        }

        CacheKey cacheKey = CacheUtils.getCacheKey("/by-source",
                new Param("sourceId", sourceId),
                new Param("page", Integer.toString(page)));
        NewsApiResponse cached = (NewsApiResponse) cache.get(cacheKey);
        if (cached != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            if (cached.getTotalResults() != 0) {
                return ResponseEntity.ok(cached);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("result", "Not Found");
                    }});
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<NewsApiResponse> response = restUtils.bySource(sourceId, page);
        if (response.getStatusCode().is2xxSuccessful()) {
            NewsApiResponse result = response.getBody();
            cache.put(cacheKey, result);
            if (result.getTotalResults() != 0) {
                return response;
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }


    public ResponseEntity<?> cacheStats() {
        CacheStat cacheStat = cacheStatFactory.getObject();
        if (!cacheStat.getCacheEntries().isEmpty()) {
            return ResponseEntity.ok(cacheStat);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("result", "Кэш пуст");
                    }});
        }
    }

    public ResponseEntity<HashMap<String, String>> cacheClear() {
        cache.clear();
        HashMap<String, String> result = new HashMap<>();
        if (cache.isEmpty()) {
            result.put("result", "Кэш успешно очищен");
        } else {
            result.put("result", "Очистить кэш не удалось");
        }
        return ResponseEntity.ok(result);
    }
}
