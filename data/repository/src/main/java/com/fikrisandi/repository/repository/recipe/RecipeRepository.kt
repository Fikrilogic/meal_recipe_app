package com.fikrisandi.repository.repository.recipe

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.recipe.MealRecipeResponse

interface RecipeRepository {

    suspend fun get(id: String): ApiResult<MealRecipeResponse>
    suspend fun getRecipes(search: String): ApiResult<MealRecipeResponse>
    suspend fun getRecipesByType(category: String): ApiResult<MealRecipeResponse>
    suspend fun getRecipesByArea(area: String): ApiResult<MealRecipeResponse>
}