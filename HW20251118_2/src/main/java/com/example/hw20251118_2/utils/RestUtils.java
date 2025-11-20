package com.example.hw20251118_2.utils;

import com.example.hw20251118_2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    public ResponseEntity<CoinMarketApiResponse[]> markets() {
        String url = env.getProperty("coin.api.base.url") + "/coins/markets?vs_currency=usd";
        return restTemplateLong.getForEntity(url, CoinMarketApiResponse[].class);
    }

    public ResponseEntity<CoinPriceApiResponse> price(List<String> idsList) {
        ResponseEntity<CoinApiResponse> response;
        CoinPriceApiResponse result = new CoinPriceApiResponse();

        for (String id : idsList) {
            String url = env.getProperty("coin.api.base.url") + "coins/" + id;
            response = restTemplateLong.getForEntity(url, CoinApiResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                CoinApiResponse coin = response.getBody();
                if (coin != null) {
                    result.add(id, coin.getSymbol(), coin.getName(), coin.getMarketData().getCurrentPrice());
                }
            }
        }

        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<TopCoinApiResponse[]> top(Integer limit) {
        String url = env.getProperty("coin.api.base.url") +
                "coins/markets?vs_currency=usd&order=market_cap_desc&per_page=" + limit + "&page=1";
        return restTemplateLong.getForEntity(url, TopCoinApiResponse[].class);
    }

    public ResponseEntity<CoinApiResponse> search(String query) {
        String url = env.getProperty("coin.api.base.url") + "coins/" + query;
        return restTemplateLong.getForEntity(url, CoinApiResponse.class);
    }

    public ResponseEntity<CryptoCompareResponse> compare(List<String> idsList) {
        ResponseEntity<CoinApiResponse> response;

        CryptoCompareResponse result = new CryptoCompareResponse();
        for (String id : idsList) {
            String url = env.getProperty("coin.api.base.url") + "coins/" + id;
            response = restTemplateLong.getForEntity(url, CoinApiResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                CoinApiResponse coin = response.getBody();
                if (coin != null) {
                    result.add(coin);
                }
            }
        }

        if (!result.getCryptos().isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}