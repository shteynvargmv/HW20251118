package com.example.hw20251118_4.model;

import java.util.List;

public class RecipeResponse extends CacheDataBody{
    private List<RecipeByIngredients> results;
    private Integer offset;
    private Integer number;
    private Integer totalResults;

    // Конструкторы
    public RecipeResponse() {}

    public RecipeResponse(List<RecipeByIngredients> results, Integer offset, Integer number, Integer totalResults) {
        this.results = results;
        this.offset = offset;
        this.number = number;
        this.totalResults = totalResults;
    }

    // Геттеры и сеттеры
    public List<RecipeByIngredients> getResults() {
        return results;
    }

    public void setResults(List<RecipeByIngredients> results) {
        this.results = results;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "results=" + results +
                ", offset=" + offset +
                ", number=" + number +
                ", totalResults=" + totalResults +
                '}';
    }
}
