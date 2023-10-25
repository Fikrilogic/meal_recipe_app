package com.fikrisandi.recipe.type

import com.fikrisandi.model.remote.category.TypeCategory
import com.fikrisandi.model.remote.recipe.MealRecipe

data class MealRecipeByTypeState(
    val recipes: List<MealRecipe> = emptyList(),
    val category: List<TypeCategory> = emptyList(),
    val categorySelected: TypeCategory? = null,
    val loading: Boolean = false,
    val exception: Throwable? = null
)