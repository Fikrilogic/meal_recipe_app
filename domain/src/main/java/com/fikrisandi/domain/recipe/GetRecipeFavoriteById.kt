package com.fikrisandi.domain.recipe

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.local.recipe.toModel
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetRecipeFavoriteById @Inject constructor(private val repository: RecipeFavoriteRepository) :
    LocalUseCase<GetRecipeFavoriteById.Params, MealRecipe?>() {

    data class Params(
        val id: String
    )

    override suspend fun FlowCollector<MealRecipe?>.execute(params: Params) {
        try {
            val data = repository.get(params.id)
            emit(data.toModel())
        } catch (e: Throwable) {
            emit(null)
        }
    }
}