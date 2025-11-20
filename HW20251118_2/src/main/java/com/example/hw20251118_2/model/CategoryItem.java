package com.example.hw20251118_2.model;

import java.util.Map;

public class CategoryItem {
    private Integer id;
    private String name;
    private Double market_cap_1h_change;
    private String slug;
    private String coins_count;
    private CategoryData data;

    public CategoryItem() {}

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getMarket_cap_1h_change() { return market_cap_1h_change; }
    public void setMarket_cap_1h_change(Double market_cap_1h_change) {
        this.market_cap_1h_change = market_cap_1h_change;
    }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCoins_count() { return coins_count; }
    public void setCoins_count(String coins_count) { this.coins_count = coins_count; }

    public CategoryData getData() { return data; }
    public void setData(CategoryData data) { this.data = data; }

    public class CategoryData {
        private Double market_cap;
        private Double market_cap_btc;
        private Double total_volume;
        private Double total_volume_btc;
        private Map<String, Double> market_cap_change_percentage_24h;
        private String sparkline;

        public CategoryData() {}

        // Геттеры и сеттеры
        public Double getMarket_cap() { return market_cap; }
        public void setMarket_cap(Double market_cap) { this.market_cap = market_cap; }

        public Double getMarket_cap_btc() { return market_cap_btc; }
        public void setMarket_cap_btc(Double market_cap_btc) { this.market_cap_btc = market_cap_btc; }

        public Double getTotal_volume() { return total_volume; }
        public void setTotal_volume(Double total_volume) { this.total_volume = total_volume; }

        public Double getTotal_volume_btc() { return total_volume_btc; }
        public void setTotal_volume_btc(Double total_volume_btc) { this.total_volume_btc = total_volume_btc; }

        public Map<String, Double> getMarket_cap_change_percentage_24h() { return market_cap_change_percentage_24h; }
        public void setMarket_cap_change_percentage_24h(Map<String, Double> market_cap_change_percentage_24h) {
            this.market_cap_change_percentage_24h = market_cap_change_percentage_24h;
        }

        public String getSparkline() { return sparkline; }
        public void setSparkline(String sparkline) { this.sparkline = sparkline; }
    }

}


