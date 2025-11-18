package com.example.hw20251118_1.utils;

import com.example.hw20251118_1.model.CountryApiResponse;
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
    @Qualifier("restTemplateLong")
    private RestTemplate restTemplateLong;

    private final Environment env;

    @Autowired
    public RestUtils(Environment env) {
        this.env = env;
    }

    public ResponseEntity<CountryApiResponse[]> region(String regionName) {
        regionName = regionName.toLowerCase();
        String url = env.getProperty("country.api.base.url") + "region/" + regionName;
        System.out.println("Запрос из API : " + regionName);
        return restTemplateLong.getForEntity(url, CountryApiResponse[].class);
    }

    public ResponseEntity<CountryApiResponse[]> population(String countryName, Integer min, Integer max ) {
        countryName = countryName.toLowerCase();
        String url = env.getProperty("country.api.base.url") + "name/" + countryName +
                "/?fields=name,capital,currencies,population"; //добавила еще фильтр по полям
        System.out.println("Запрос из API : " + countryName + " население от " + min + " до " + max);
        return restTemplateShort.getForEntity(url, CountryApiResponse[].class);
    }

    public ResponseEntity<CountryApiResponse[]> search(String countryName) {
        countryName = countryName.toLowerCase();
        String url = env.getProperty("country.api.base.url") + "name/" + countryName;
        System.out.println(url);
        System.out.println("Запрос из API : " + countryName);
        return restTemplateShort.getForEntity(url,CountryApiResponse[].class);
    }
}