package com.fikrisandi.domain.recipe

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.framework.usecase.ViewStateUseCase
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetRecipesById @Inject constructor(val repository: RecipeRepository) :
    ViewStateUseCase<GetRecipesById.Params, MealRecipe?>() {
    data class Params(
        val id: String
    )

    override suspend fun FlowCollector<ViewState<MealRecipe?>>.execute(params: Params) {
        try {
            when (val result = repository.get(params.id)) {
                is ApiResult.Success -> {
                    emit(
                        ViewState.Success(
                            data = result.data.recipes?.firstOrNull()
                        )
                    )
                }

                is ApiResult.Failed -> {
                    emit(
                        ViewState.Failed(
                            exception = result.exception,
                            message = result.exception.message.orEmpty()
                        )
                    )
                }
            }
        } catch (e: Throwable) {
            emit(
                ViewState.Failed(
                    exception = e,
                    message = e.message.orEmpty()
                )
            )
        }
    }
}