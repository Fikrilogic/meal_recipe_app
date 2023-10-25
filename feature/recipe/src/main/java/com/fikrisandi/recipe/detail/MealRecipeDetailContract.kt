package com.fikrisandi.recipe.detail

import com.fikrisandi.model.remote.recipe.MealRecipe


data class MealRecipeDetailState(
    val recipe: MealRecipe? = null,
    val favorite: Boolean = false,
    val loading: Boolean = false,
    val exception: Throwable? = null
)