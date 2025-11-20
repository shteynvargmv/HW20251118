package com.example.hw20251118_2.controller;

import com.example.hw20251118_2.model.*;
import com.example.hw20251118_2.services.CoinApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/crypto/")
public class HomeController {

    @Autowired
    CoinApiServices coinApiServices;

    //   ** GET /api/crypto/price?ids={bitcoin,cardano}**
    //   - Получить текущие цены криптовалют
    //   - Можно несколько через запятую
    //   - Пример: localhost:8080/api/crypto/price?ids=cardano,bitcoin
    @GetMapping("/price")
    public ResponseEntity<?> price(
            @RequestParam String ids
    ) {
        return coinApiServices.price(ids);
    }

    // 2. **GET /api/crypto/top?limit={limit}**
    //   - Топ криптовалют по рыночной капитализации
    //   - Пример: localhost:8080/api/crypto/top?limit=10
    @GetMapping("/top")
    public ResponseEntity<?> top(
            @RequestParam Integer limit
    ) {
        return coinApiServices.top(limit);
    }


    //   3. **GET /api/crypto/search?query={name}**
    //   - Поиск криптовалюты по названию
    //   - Пример: localhost:8080/api/crypto/search?query=bitcoin
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String query
    ) {
        return coinApiServices.search(query);
    }


    //  4. **GET /api/crypto/compare?ids={bitcoin,ethereum}**
    //   - Сравнить несколько криптовалют
    //   - Показать разницу в ценах и изменениях
    //   - Какая дороже
    //   - Какая больше выросла/упала за 24ч MarketData.priceChange24h (беру по модулю)
    //   - Какая больше по капитализации MarketData.marketCap
    //   - Пример: localhost:8080/api/crypto/compare?ids=cardano,bitcoin
    @GetMapping("/compare")
    public ResponseEntity<?> compare(
            @RequestParam String ids
    ) {
        return coinApiServices.compare(ids);
    }

    //  localhost:8080/api/crypto/cache/stats
    @GetMapping("/cache/stats")
    public ResponseEntity<?> cacheStats() {
        return coinApiServices.cacheStats();
    }

    //  localhost:8080/api/crypto/cache/clear
    @PostMapping("/cache/clear")
    public ResponseEntity<HashMap<String, String>> cacheClear() {
        return coinApiServices.cacheClear();
    }

}
