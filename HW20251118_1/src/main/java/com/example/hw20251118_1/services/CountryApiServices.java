package com.example.hw20251118_1.services;

import com.example.hw20251118_1.model.Cache;
import com.example.hw20251118_1.model.CacheKey;
import com.example.hw20251118_1.model.CacheStat;
import com.example.hw20251118_1.model.CountryApiResponse;
import com.example.hw20251118_1.utils.RestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class CountryApiServices {

    @Autowired
    private Cache cache;
    @Autowired
    private ObjectFactory<CacheStat> cacheStatFactory;
    @Autowired
    private RestUtils restUtils;

    public ResponseEntity<?> region(String regionName) {
        CountryApiResponse[] cached = cache.get(new CacheKey("region_name_" + regionName));
        if (cached != null) {
            System.out.println("Запрос из кэша : " + regionName);
            return ResponseEntity.ok(cached);
        }
        ResponseEntity<CountryApiResponse[]> response = restUtils.region(regionName);
        if (response.getStatusCode().is2xxSuccessful()) {
            CountryApiResponse[] result = response.getBody();
            cache.put(new CacheKey("region_name_" + regionName), result);
            if (result != null && result.length > 0) {
                return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> population(String countryName, Integer min, Integer max) {

        if (min > max) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: min > max");
            return ResponseEntity.badRequest().body(result);
        }

        if (min < 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: min < 0");
            return ResponseEntity.badRequest().body(result);
        }

        if (max < 0) {
            HashMap<String, String> result = new HashMap<>();
            result.put("result", "Ошибка ввода: max < 0");
            return ResponseEntity.badRequest().body(result);
        }

        countryName = countryName.toLowerCase();
        CountryApiResponse[] cached = cache.get(new CacheKey("country_filter_" + countryName, min, max));
        if (cached != null) {
            System.out.println("Запрос из кэша : " + countryName + " население  от " + min + " до " + max);
            return ResponseEntity.ok(cached);
        }

        ResponseEntity<CountryApiResponse[]> response = restUtils.population(countryName, min, max);

        if (response.getStatusCode().is2xxSuccessful()) {
            CountryApiResponse[] result = response.getBody();
            if (result != null) {
                result = Arrays.stream(result).filter(x -> x.population >= min && x.population <= max)
                        .toArray(CountryApiResponse[]::new);
                if (result.length > 0) {
                    cache.put(new CacheKey("country_filter_" + countryName, min, max), result);
                    return ResponseEntity.ok(result);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<CountryApiResponse[]> search(String countryName) {
        CountryApiResponse[] cached = cache.get(new CacheKey("country_name_" + countryName));
        if (cached != null) {
            System.out.println("Запрос из кэша : " + countryName);
            return ResponseEntity.ok(cached);
        }

        ResponseEntity<CountryApiResponse[]> response = restUtils.search(countryName);

        if (response.getStatusCode().is2xxSuccessful()) {
            CountryApiResponse[] result = response.getBody();
            if (result != null && result.length > 0) {
                cache.put(new CacheKey("country_name_" + countryName), response.getBody());
                return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> cacheStats() {
        CacheStat cacheStat = cacheStatFactory.getObject();
//        cacheStat.setCache(cache);
        if (!cacheStat.getCacheEntries().isEmpty()) {
            return ResponseEntity.ok(cacheStat);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String,String>(){{put("result","Кэш пуст");}});
        }
    }

    public ResponseEntity<HashMap<String, String>> cacheClear() {
        cache.clear();
        HashMap<String,String> result = new HashMap<>();
        if (cache.isEmpty()) {
            result.put("result", "Кэш успешно очищен");
        } else {
            result.put("result", "Очистить кэш не удалось");
        }
        return ResponseEntity.ok(result) ;
    }
}
