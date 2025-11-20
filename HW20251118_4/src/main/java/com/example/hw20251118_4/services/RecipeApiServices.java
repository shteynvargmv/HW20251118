package com.example.hw20251118_4.services;

import com.example.hw20251118_4.model.*;
import com.example.hw20251118_4.utils.CacheUtils;
import com.example.hw20251118_4.utils.RestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class RecipeApiServices {
    @Autowired
    private Cache cache;
    @Autowired
    private ObjectFactory<CacheStat> cacheStatFactory;
    @Autowired
    private RestUtils restUtils;

    public ResponseEntity<?> search(String query, Integer number) {

        if (number == null || number <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: number <= 0");
            return ResponseEntity.badRequest().body(result);
        }

        CacheKey cacheKey = CacheUtils.getCacheKey("/search",
                new Param("query", query),
                new Param("number", Integer.toString(number)));

        CacheDataBody[] cacheDataBody = cache.get(cacheKey);
        if (cacheDataBody != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            RecipesResponse cached = (RecipesResponse) cacheDataBody[0];
            return ResponseEntity.ok(cached);
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<RecipesResponse> response = restUtils.search(query, number);
        if (response.getStatusCode().is2xxSuccessful()) {
            cache.put(cacheKey, response.getBody());
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> byIngredients(String ingredients, Integer number) {

        if (number == null || number <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: number <= 0");
            return ResponseEntity.badRequest().body(result);
        }
        CacheKey cacheKey = CacheUtils.getCacheKey("/byIngredients",
                new Param("ingredients", ingredients));

        CacheDataBody[] cacheDataBody = cache.get(cacheKey);
        if (cacheDataBody != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            RecipeResponse cached = (RecipeResponse) cacheDataBody[0];
            return ResponseEntity.ok(cached);
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<RecipeResponse> response = restUtils.byIngredients(ingredients, number);
        if (response.getStatusCode().is2xxSuccessful()) {
            cache.put(cacheKey, response.getBody());
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> byId(String id) {

        CacheKey cacheKey = CacheUtils.getCacheKey("/" + id,
                new Param("id", id));
        CacheDataBody[] cacheDataBody = cache.get(cacheKey);
        if (cacheDataBody != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            RecipeInfoResponse cached = (RecipeInfoResponse) cacheDataBody[0];
            return ResponseEntity.ok(cached);
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<RecipeInfoResponse> response = restUtils.byId(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            cache.put(cacheKey, response.getBody());
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<String, String>() {{
                    put("result", "Not Found");
                }});
    }

    public ResponseEntity<?> random(Integer number, String tags) {
        if (number == null || number <= 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: number <= 0");
            return ResponseEntity.badRequest().body(result);
        }

        CacheKey cacheKey = CacheUtils.getCacheKey("/random",
                new Param("number", Integer.toString(number)),
                new Param("tags", tags));
        CacheDataBody[] cacheDataBody = cache.get(cacheKey);
        if (cacheDataBody != null) {
            System.out.println("Запрос из кэша : " + cacheKey);
            RecipesRandResponse cached = (RecipesRandResponse) cacheDataBody[0];
            return ResponseEntity.ok(cached);
        }

        System.out.println("Запрос из API : " + cacheKey);
        ResponseEntity<RecipesRandResponse> response = restUtils.random(number, tags);
        if (response.getStatusCode().is2xxSuccessful()) {
            cache.put(cacheKey, response.getBody());
            return response;
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
