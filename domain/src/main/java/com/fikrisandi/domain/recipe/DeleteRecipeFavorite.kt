package com.fikrisandi.domain.recipe

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.local.recipe.toEntity
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteRecipeFavorite @Inject constructor(private val repository: RecipeFavoriteRepository) :
    LocalUseCase<DeleteRecipeFavorite.Params, Unit>() {

    data class Params(
        val data: MealRecipe
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        try {
            repository.delete(params.data.toEntity())

            emit(Unit)
        } catch (e: Throwable) {
            throw e
        }
    }
}