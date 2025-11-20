package com.example.hw20251118_2.model;

import java.util.Map;

public class CoinItem {
    private Item item;

    public CoinItem() {}
    public CoinItem(Item item) { this.item = item; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public class Item {
        private String id;
        private Long coin_id;
        private String name;
        private String symbol;
        private Integer market_cap_rank;
        private String thumb;
        private String small;
        private String large;
        private String slug;
        private Double price_btc;
        private Integer score;
        private CoinData data;

        public Item() {}

        // Геттеры и сеттеры
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public Long getCoin_id() { return coin_id; }
        public void setCoin_id(Long coin_id) { this.coin_id = coin_id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSymbol() { return symbol; }
        public void setSymbol(String symbol) { this.symbol = symbol; }

        public Integer getMarket_cap_rank() { return market_cap_rank; }
        public void setMarket_cap_rank(Integer market_cap_rank) { this.market_cap_rank = market_cap_rank; }

        public String getThumb() { return thumb; }
        public void setThumb(String thumb) { this.thumb = thumb; }

        public String getSmall() { return small; }
        public void setSmall(String small) { this.small = small; }

        public String getLarge() { return large; }
        public void setLarge(String large) { this.large = large; }

        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }

        public Double getPrice_btc() { return price_btc; }
        public void setPrice_btc(Double price_btc) { this.price_btc = price_btc; }

        public Integer getScore() { return score; }
        public void setScore(Integer score) { this.score = score; }

        public CoinData getData() { return data; }
        public void setData(CoinData data) { this.data = data; }

        public class CoinData {
            private Double price;
            private String price_btc;
            private Map<String, Double> price_change_percentage_24h;
            private String market_cap;
            private String market_cap_btc;
            private String total_volume;
            private String total_volume_btc;
            private String sparkline;
            private Content content;

            public CoinData() {}

            // Геттеры и сеттеры
            public Double getPrice() { return price; }
            public void setPrice(Double price) { this.price = price; }

            public String getPrice_btc() { return price_btc; }
            public void setPrice_btc(String price_btc) { this.price_btc = price_btc; }

            public Map<String, Double> getPrice_change_percentage_24h() { return price_change_percentage_24h; }
            public void setPrice_change_percentage_24h(Map<String, Double> price_change_percentage_24h) {
                this.price_change_percentage_24h = price_change_percentage_24h;
            }

            public String getMarket_cap() { return market_cap; }
            public void setMarket_cap(String market_cap) { this.market_cap = market_cap; }

            public String getMarket_cap_btc() { return market_cap_btc; }
            public void setMarket_cap_btc(String market_cap_btc) { this.market_cap_btc = market_cap_btc; }

            public String getTotal_volume() { return total_volume; }
            public void setTotal_volume(String total_volume) { this.total_volume = total_volume; }

            public String getTotal_volume_btc() { return total_volume_btc; }
            public void setTotal_volume_btc(String total_volume_btc) { this.total_volume_btc = total_volume_btc; }

            public String getSparkline() { return sparkline; }
            public void setSparkline(String sparkline) { this.sparkline = sparkline; }

            public Content getContent() { return content; }
            public void setContent(Content content) { this.content = content; }

        }
    }

}






