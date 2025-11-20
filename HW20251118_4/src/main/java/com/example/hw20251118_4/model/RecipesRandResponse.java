package com.example.hw20251118_4.model;

import java.util.List;

public class RecipesRandResponse extends CacheDataBody{
    private List<RecipeDetail> recipes;

    public List<RecipeDetail> getRecipes() {
        return recipes;
    }
}
