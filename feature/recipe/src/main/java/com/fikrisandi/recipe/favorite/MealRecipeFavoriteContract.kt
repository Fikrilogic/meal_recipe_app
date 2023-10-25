package com.fikrisandi.recipe.favorite

import androidx.paging.PagingData
import com.fikrisandi.model.remote.recipe.MealRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.lang.Exception


data class MealRecipeFavoriteState(
    val recipes: Flow<PagingData<MealRecipe>> = emptyFlow(),
    val loading: Boolean = false,
    val exception: Throwable? = null
)