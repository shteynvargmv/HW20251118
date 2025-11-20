package com.example.hw20251118_4.utils;

import com.example.hw20251118_4.model.*;
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

    public ResponseEntity<RecipesResponse> search(String query, Integer number) {
        String url = env.getProperty("recipe.api.base.url") + "complexSearch?" +
                "query=" + query +
                "&number=" + number +
                "&apiKey=" + env.getProperty("recipe.api.key");
        return restTemplateLong.getForEntity(url, RecipesResponse.class);
    }

    public ResponseEntity<RecipeResponse> byIngredients(String ingredients, Integer number) {
        String url = env.getProperty("recipe.api.base.url") + "complexSearch?" + "findByIngredients?" +
                "ingredients=" + ingredients +
                "&number=" + number +
                "&apiKey=" + env.getProperty("recipe.api.key");
        return restTemplateLong.getForEntity(url, RecipeResponse.class);
    }

    public ResponseEntity<RecipeInfoResponse> byId(String id) {
        String url = env.getProperty("recipe.api.base.url") + id +
                "/information?apiKey=" + env.getProperty("recipe.api.key");
        return restTemplateShort.getForEntity(url, RecipeInfoResponse.class);
    }

    public ResponseEntity<RecipesRandResponse> random(Integer number, String tags) {
        String url = env.getProperty("recipe.api.base.url") + "random?"+
                "number=" + number +
                "&tags=" + tags +
                "&apiKey=" + env.getProperty("recipe.api.key");
        return restTemplateLong.getForEntity(url, RecipesRandResponse.class);
    }
}
