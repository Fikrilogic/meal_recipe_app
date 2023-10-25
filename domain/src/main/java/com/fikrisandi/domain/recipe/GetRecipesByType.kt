package com.fikrisandi.domain.recipe

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.framework.usecase.ViewStateUseCase
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetRecipesByType @Inject constructor(private val repository: RecipeRepository) :
    ViewStateUseCase<GetRecipesByType.Params, List<MealRecipe>>() {

    data class Params(
        val type: String
    )

    override suspend fun FlowCollector<ViewState<List<MealRecipe>>>.execute(params: Params) {
        try {
            when (val result = repository.getRecipesByType(params.type)) {
                is ApiResult.Success -> {
                    emit(
                        ViewState.Success(data = result.data.recipes.orEmpty())
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
            emit(ViewState.Failed(exception = e, message = e.message.orEmpty()))
        }
    }
}