package com.example.hw20251118_4.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecipesResponse extends CacheDataBody{
    private List<Recipe> results;
    private Integer offset;
    private Integer number;

    @JsonProperty("totalResults")
    private Integer totalResults;

    public List<Recipe> getResults() {
        return results;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getTotalResults() {
        return totalResults;
    }
}
