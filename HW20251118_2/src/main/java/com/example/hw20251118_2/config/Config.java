package com.example.hw20251118_2.config;

import com.example.hw20251118_2.model.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean(name = "restTemplateLong")
    public RestTemplate getRestTemplateLong() {
        System.out.println("getRestTemplateLong");
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10 * 1000);
        requestFactory.setReadTimeout(20 * 1000);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    @Bean(name = "restTemplateShort")
    public RestTemplate getRestTemplateShort() {
        System.out.println("getRestTemplateShort");
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3 * 1000);
        requestFactory.setReadTimeout(5 * 1000);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    @Bean
    public Cache getCache() {
        Cache cache = new Cache();
        System.out.println(cache.getTimeToLive());
        return cache;
    }

}
