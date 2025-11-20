package com.example.hw20251118_2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class CoinPriceApiResponse extends CacheDataBody{
    private ArrayList<CoinPriceApi> coinPriceApis;

    public CoinPriceApiResponse() {
        this.coinPriceApis = new ArrayList<>();
    }

//    public void add(String id, String symbol, String name, CoinApiResponse0ld.CurrentPrice current_price){
//        coinPriceApis.add(new CoinPriceApi(id,symbol,name,current_price));
//    }
    public void add(String id, String symbol, String name, Map<String, BigDecimal> currentPrice){
        coinPriceApis.add(new CoinPriceApi(id,symbol,name,currentPrice));
    }

    public Boolean isEmpty(){
        if (coinPriceApis.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<CoinPriceApi> getCoinPriceApis() {
        return coinPriceApis;
    }
}
