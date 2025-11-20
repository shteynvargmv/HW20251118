package com.example.hw20251118_3.controller;

import com.example.hw20251118_3.model.*;
import com.example.hw20251118_3.model.*;
import com.example.hw20251118_3.services.NewsApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/news/")
public class HomeController {

    @Autowired
    NewsApiServices newsApiServices;

    //    1. **GET /api/news/search?q={query}&page={page}&pageSize={size}**
    //   - Поиск новостей по ключевым словам
    //   - Пример: localhost:8080/api/news/search?q=bitcoin&page=1&pageSize=10
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(name = "q") String query,
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        return newsApiServices.search(query, page, pageSize);
    }

    //   2. **GET /api/news/top?country={country}&category={category}**
    //   - Топ новости страны
    //   - Пример: localhost:8080/api/news/top?country=us&category=technology
    @GetMapping("/top")
    public ResponseEntity<?> top(
            @RequestParam String country,
            @RequestParam String category
    ) {
        return newsApiServices.top(country, category);
    }

    //   3. **GET /api/news/sources?category={category}&country={country}**
    //   - Получить список источников новостей
    //   - Пример: localhost:8080/api/news/by-source?source=bbc-news&page=1
    @GetMapping("/sources")
    public ResponseEntity<?> sources(
            @RequestParam String country,
            @RequestParam String category
    ) {
        return newsApiServices.sources(country, category);
    }

    //    4. **GET /api/news/by-source?source={sourceId}&page={page}**
    //   - Новости от конкретного источника
    //   - Пример: `/api/news/by-source?source=bbc-news&page=1`
    @GetMapping("/by-source")
    public ResponseEntity<?> bySource(
            @RequestParam(name = "source") String sourceId,
            @RequestParam Integer page
    ) {
        return newsApiServices.bySource(sourceId, page);
//        sourceId = sourceId.toLowerCase();
//        if (page == null || page <= 0) {
//            HashMap<String, String> result = new HashMap<>();
//            result.put("result", "Ошибка ввода: page <= 0");
//            return ResponseEntity.badRequest().body(result);
//        }
//
//        Map<String, String> params = new HashMap<>();
//        params.put("sourceId", sourceId);
//        params.put("page", Integer.toString(page));
//        CacheKey cacheKey = new CacheKey("/by-source", params);
//        NewsApiResponse cached = (NewsApiResponse) cache.get(cacheKey);
//        if (cached != null) {
//            System.out.println("Запрос из кэша : " + cacheKey);
//            return ResponseEntity.ok(cached);
//        }
//
//        System.out.println("Запрос из API : " + cacheKey);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<NewsApiResponse> response;
//
//        String url = "https://newsapi.org/v2/top-headlines?sources=" +
//                sourceId + "&page=" + page + "&apiKey=" + API_KEY;
//
//        response = restTemplate.getForEntity(url, NewsApiResponse.class);
//        if (response.getStatusCode().is2xxSuccessful()) {
//            NewsApiResponse result = response.getBody();
//            cache.put(cacheKey, result);
//            return ResponseEntity.ok(result);
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new HashMap<String, String>() {{
//                    put("result", "Not Found");
//                }});
    }


    //  localhost:8080/api/news/cache/stats
    @GetMapping("/cache/stats")
    public ResponseEntity<?> cacheStats() {
        return newsApiServices.cacheStats();
    }

    //  localhost:8080/api/crypto/cache/clear
    @PostMapping("/cache/clear")
    public ResponseEntity<HashMap<String, String>> cacheClear() {
        return newsApiServices.cacheClear();
    }

}
