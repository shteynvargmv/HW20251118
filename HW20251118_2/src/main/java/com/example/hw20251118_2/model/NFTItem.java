package com.example.hw20251118_2.model;

public class NFTItem {
    private String id;
    private String name;
    private String symbol;
    private String thumb;
    private Integer nft_contract_id;
    private String native_currency_symbol;
    private Double floor_price_in_native_currency;
    private Double floor_price_24h_percentage_change;
    private NFTData data;

    public NFTItem() {}

    // Геттеры и сеттеры
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getThumb() { return thumb; }
    public void setThumb(String thumb) { this.thumb = thumb; }

    public Integer getNft_contract_id() { return nft_contract_id; }
    public void setNft_contract_id(Integer nft_contract_id) { this.nft_contract_id = nft_contract_id; }

    public String getNative_currency_symbol() { return native_currency_symbol; }
    public void setNative_currency_symbol(String native_currency_symbol) { this.native_currency_symbol = native_currency_symbol; }

    public Double getFloor_price_in_native_currency() { return floor_price_in_native_currency; }
    public void setFloor_price_in_native_currency(Double floor_price_in_native_currency) {
        this.floor_price_in_native_currency = floor_price_in_native_currency;
    }

    public Double getFloor_price_24h_percentage_change() { return floor_price_24h_percentage_change; }
    public void setFloor_price_24h_percentage_change(Double floor_price_24h_percentage_change) {
        this.floor_price_24h_percentage_change = floor_price_24h_percentage_change;
    }

    public NFTData getData() { return data; }
    public void setData(NFTData data) { this.data = data; }

    public class NFTData {
        private String floor_price;
        private String floor_price_in_usd_24h_percentage_change;
        private String h24_volume;
        private String h24_average_sale_price;
        private String sparkline;
        private Content content;

        public NFTData() {}

        // Геттеры и сеттеры
        public String getFloor_price() { return floor_price; }
        public void setFloor_price(String floor_price) { this.floor_price = floor_price; }

        public String getFloor_price_in_usd_24h_percentage_change() { return floor_price_in_usd_24h_percentage_change; }
        public void setFloor_price_in_usd_24h_percentage_change(String floor_price_in_usd_24h_percentage_change) {
            this.floor_price_in_usd_24h_percentage_change = floor_price_in_usd_24h_percentage_change;
        }

        public String getH24_volume() { return h24_volume; }
        public void setH24_volume(String h24_volume) { this.h24_volume = h24_volume; }

        public String getH24_average_sale_price() { return h24_average_sale_price; }
        public void setH24_average_sale_price(String h24_average_sale_price) {
            this.h24_average_sale_price = h24_average_sale_price;
        }

        public String getSparkline() { return sparkline; }
        public void setSparkline(String sparkline) { this.sparkline = sparkline; }

        public Content getContent() { return content; }
        public void setContent(Content content) { this.content = content; }
    }

}
