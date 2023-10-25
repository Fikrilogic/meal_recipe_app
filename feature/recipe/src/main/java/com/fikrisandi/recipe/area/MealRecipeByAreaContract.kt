package com.fikrisandi.recipe.area

import com.fikrisandi.model.remote.category.AreaCategory
import com.fikrisandi.model.remote.recipe.MealRecipe


data class MealRecipeByAreaState(
    val category: List<AreaCategory> = emptyList(),
    val categorySelected: AreaCategory? = null,
    val recipes: List<MealRecipe> = emptyList(),
    val loading: Boolean = false,
    val exception: Throwable? = null
)