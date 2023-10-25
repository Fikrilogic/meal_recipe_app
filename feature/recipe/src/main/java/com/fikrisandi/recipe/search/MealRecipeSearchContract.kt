package com.fikrisandi.recipe.search

import com.fikrisandi.model.remote.recipe.MealRecipe

data class MealRecipeSearchState(
    val search: String = "",
    val recipes: List<MealRecipe> = emptyList(),
    val loading: Boolean = false,
    val exception: Throwable? = null
)