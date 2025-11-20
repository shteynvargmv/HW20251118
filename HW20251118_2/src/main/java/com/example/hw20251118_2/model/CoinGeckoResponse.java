package com.example.hw20251118_2.model;
import java.util.List;
import java.util.Map;

public class CoinGeckoResponse extends CacheDataBody{
    private List<CoinItem> coins;
    private List<NFTItem> nfts;
    private List<CategoryItem> categories;

    // Конструкторы
    public CoinGeckoResponse() {}

    public CoinGeckoResponse(List<CoinItem> coins, List<NFTItem> nfts, List<CategoryItem> categories) {
        this.coins = coins;
        this.nfts = nfts;
        this.categories = categories;
    }

    // Геттеры и сеттеры
    public List<CoinItem> getCoins() { return coins; }
    public void setCoins(List<CoinItem> coins) { this.coins = coins; }

    public List<NFTItem> getNfts() { return nfts; }
    public void setNfts(List<NFTItem> nfts) { this.nfts = nfts; }

    public List<CategoryItem> getCategories() { return categories; }
    public void setCategories(List<CategoryItem> categories) { this.categories = categories; }
}