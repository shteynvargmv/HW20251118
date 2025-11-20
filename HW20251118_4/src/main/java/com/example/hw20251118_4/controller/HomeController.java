package com.example.hw20251118_4.controller;


import com.example.hw20251118_4.services.RecipeApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/recipes/")
public class HomeController {

    @Autowired
    RecipeApiServices recipeApiServices;

    //   1. **GET /api/recipes/search?query={query}&number={number}**
    //   - Поиск рецептов по названию
    //   - Пример: localhost:8080/api/recipes/search?query=pasta&number=10
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String query,
            @RequestParam Integer number
    ) {
        return  recipeApiServices.search(query,number);
    }

    //   2. **GET /api/recipes/by-ingredients?ingredients={ingredients}&number={number}**
    //   - Поиск по ингредиентам
    //   - Пример: localhost:8080/api/recipes/by-ingredients?ingredients=chicken,rice&number=5
    @GetMapping("/by-ingredients")
    public ResponseEntity<?> byIngredients(
            @RequestParam String ingredients,
            @RequestParam Integer number
    ) {
        return recipeApiServices.byIngredients(ingredients,number);
    }

    //   3. **GET /api/recipes/{id}**
    //   - Получить детальную информацию о рецепте
    //   - Пример: localhost:8080/api/recipes/715538
    @GetMapping("/{id}")
    public ResponseEntity<?> byId(
            @PathVariable String id
    ) {
        return recipeApiServices.byId(id);
    }

    //   4. **GET /api/recipes/random?number={number}&tags={tags}**
    //   - Случайные рецепты с тегами
    //   - Пример: localhost:8080/api/recipes/random?number=3&tags=vegetarian,dessert
    @GetMapping("/random")
    public ResponseEntity<?> random(
            @RequestParam Integer number,
            @RequestParam String tags
    ) {
        return recipeApiServices.random(number,tags);
    }

    //  5. **GET /api/recipes/cache/stats**
    //  - Статистика кеша
    //  - Пример: localhost:8080/api/recipes/cache/stats
    @GetMapping("/cache/stats")
    public ResponseEntity<?> cacheStats() {
        return recipeApiServices.cacheStats();
    }

    //  6. **POST /api/recipes/cache/clear**
    //  - Очистить кеш
    //  - Пример: localhost:8080/api/recipes/cache/clear
    @PostMapping("/cache/clear")
    public ResponseEntity<HashMap<String, String>> cacheClear() {
        return recipeApiServices.cacheClear();
    }
}
