package com.example.hw20251118_2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CoinMarketApiResponse extends CacheDataBody{
    private String id;
    private String symbol;
    private String name;
    private String image;

    @JsonProperty("current_price")
    private Double currentPrice;

    @JsonProperty("market_cap")
    private Long marketCap;

    @JsonProperty("market_cap_rank")
    private Integer marketCapRank;

    @JsonProperty("fully_diluted_valuation")
    private Long fullyDilutedValuation;

    @JsonProperty("total_volume")
    private Long totalVolume;

    @JsonProperty("high_24h")
    private Double high24h;

    @JsonProperty("low_24h")
    private Double low24h;

    @JsonProperty("price_change_24h")
    private Double priceChange24h;

    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24h;

    @JsonProperty("market_cap_change_24h")
    private Long marketCapChange24h;

    @JsonProperty("market_cap_change_percentage_24h")
    private Double marketCapChangePercentage24h;

    @JsonProperty("circulating_supply")
    private Double circulatingSupply;

    @JsonProperty("total_supply")
    private Double totalSupply;

    @JsonProperty("max_supply")
    private Double maxSupply;

    private Double ath;

    @JsonProperty("ath_change_percentage")
    private Double athChangePercentage;

    @JsonProperty("ath_date")
    private LocalDateTime athDate;

    private Double atl;

    @JsonProperty("atl_change_percentage")
    private Double atlChangePercentage;

    @JsonProperty("atl_date")
    private LocalDateTime atlDate;

    private Object roi; // Может быть null или объектом

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

    // Конструкторы
    public CoinMarketApiResponse() {
    }

    public CoinMarketApiResponse(String id, String symbol, String name, String image, Double currentPrice,
                Long marketCap, Integer marketCapRank, Long fullyDilutedValuation,
                Long totalVolume, Double high24h, Double low24h, Double priceChange24h,
                Double priceChangePercentage24h, Long marketCapChange24h,
                Double marketCapChangePercentage24h, Double circulatingSupply,
                Double totalSupply, Double maxSupply, Double ath, Double athChangePercentage,
                LocalDateTime athDate, Double atl, Double atlChangePercentage,
                LocalDateTime atlDate, Object roi, LocalDateTime lastUpdated) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.fullyDilutedValuation = fullyDilutedValuation;
        this.totalVolume = totalVolume;
        this.high24h = high24h;
        this.low24h = low24h;
        this.priceChange24h = priceChange24h;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.marketCapChange24h = marketCapChange24h;
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.ath = ath;
        this.athChangePercentage = athChangePercentage;
        this.athDate = athDate;
        this.atl = atl;
        this.atlChangePercentage = atlChangePercentage;
        this.atlDate = atlDate;
        this.roi = roi;
        this.lastUpdated = lastUpdated;


    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public Integer getMarketCapRank() {
        return marketCapRank;
    }

    public Long getFullyDilutedValuation() {
        return fullyDilutedValuation;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public Double getHigh24h() {
        return high24h;
    }

    public Double getLow24h() {
        return low24h;
    }

    public Double getPriceChange24h() {
        return priceChange24h;
    }

    public Double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public Long getMarketCapChange24h() {
        return marketCapChange24h;
    }

    public Double getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public Double getCirculatingSupply() {
        return circulatingSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public Double getAth() {
        return ath;
    }

    public Double getAthChangePercentage() {
        return athChangePercentage;
    }

    public LocalDateTime getAthDate() {
        return athDate;
    }

    public Double getAtl() {
        return atl;
    }

    public Double getAtlChangePercentage() {
        return atlChangePercentage;
    }

    public LocalDateTime getAtlDate() {
        return atlDate;
    }

    public Object getRoi() {
        return roi;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
