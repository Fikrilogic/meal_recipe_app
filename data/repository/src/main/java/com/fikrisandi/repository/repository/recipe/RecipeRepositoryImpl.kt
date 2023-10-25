package com.fikrisandi.repository.repository.recipe

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.call
import com.fikrisandi.model.remote.recipe.MealRecipeResponse
import io.ktor.http.HttpMethod
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val appHttpClient: AppHttpClient): RecipeRepository {
    override suspend fun get(id: String): ApiResult<MealRecipeResponse> {
        return try {
            val result = appHttpClient.call<MealRecipeResponse>(
                route = "lookup.php",
                method = HttpMethod.Get,
                params = mapOf(
                    "i" to id
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }
    override suspend fun getRecipes(search: String): ApiResult<MealRecipeResponse> {
        return try {
            val result = appHttpClient.call<MealRecipeResponse>(
                route = "search.php",
                method = HttpMethod.Get,
                params = mapOf(
                    "s" to search
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }

    override suspend fun getRecipesByType(category: String): ApiResult<MealRecipeResponse> {
        return try {
            val result = appHttpClient.call<MealRecipeResponse>(
                route = "filter.php",
                method = HttpMethod.Get,
                params = mapOf(
                    "c" to category
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }

    override suspend fun getRecipesByArea(area: String): ApiResult<MealRecipeResponse> {
        return try {
            val result = appHttpClient.call<MealRecipeResponse>(
                route = "filter.php?a=$area",
                method = HttpMethod.Get
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }



}