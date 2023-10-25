package com.fikrisandi.domain.recipe

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.framework.usecase.ViewStateUseCase
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetRecipesByArea @Inject constructor(val repository: RecipeRepository) :
    ViewStateUseCase<GetRecipesByArea.Params, List<MealRecipe>>() {

    data class Params(
        val area: String
    )

    override suspend fun FlowCollector<ViewState<List<MealRecipe>>>.execute(params: Params) {
        try {
            when (val result = repository.getRecipesByArea(params.area)) {
                is ApiResult.Success -> {
                    emit(
                        ViewState.Success(
                            data = result.data.recipes ?: emptyList()
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
            emit(ViewState.Failed(exception = e, message = e.message.orEmpty()))
        }
    }
}