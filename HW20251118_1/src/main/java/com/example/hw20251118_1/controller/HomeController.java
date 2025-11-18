package com.example.hw20251118_1.controller;

import com.example.hw20251118_1.model.*;
import com.example.hw20251118_1.model.CountryApiResponse;
import com.example.hw20251118_1.services.CountryApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/countries")
public class HomeController {

    @Autowired
    CountryApiServices countryApiServices;

    //  localhost:8080/api/countries/search?name=russia
    //  Поиск по имени:
    //  https://restcountries.com/v3.1/name/{name}
    @GetMapping("/search")
    public ResponseEntity<CountryApiResponse[]> search(
            @RequestParam(name = "name") String countryName
    ) {
        return countryApiServices.search(countryName);
    }

    //  localhost:8080/api/countries/region?name=asia
    //  По региону:
    //  https://restcountries.com/v3.1/region/{region}
    @GetMapping("/region")
    public ResponseEntity<?> region(
            @RequestParam(name = "name") String regionName
    ) {
        return countryApiServices.region(regionName);
    }

    //  localhost:8080/api/countries/usa/population?min=45&max=500000000
    //  Сервис https://restcountries.com/v3.1/all?fields=population не отрабатывает даже в браузере,
    //  сайт не отвечает, поэтому сделала фильтр для https://restcountries.com/v3.1/name/:
    //  страна будет попадать в результат, только если население в заданных пределах
    //  https://restcountries.com/v3.1/name/{name}/?fields=name,capital,currencies, population
    @GetMapping("/{name}/population")
    public ResponseEntity<?> population(
            @PathVariable(name = "name") String countryName,
            @RequestParam Integer min,
            @RequestParam Integer max
    ) {
        return countryApiServices.population(countryName,min,max);
    }

    //  localhost:8080/api/countries/cache/stats
    @GetMapping("/cache/stats")
    public ResponseEntity<?> cacheStats() {
        return countryApiServices.cacheStats();
    }

    //  localhost:8080/api/countries/cache/clear
    @PostMapping("/cache/clear")
    public ResponseEntity<HashMap<String, String>> cacheClear() {
        return countryApiServices.cacheClear();
    }
}
