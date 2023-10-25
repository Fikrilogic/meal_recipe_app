package com.fikrisandi.domain.recipe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeFavorite @Inject constructor(val repository: RecipeFavoriteRepository) :
    FlowPagingUseCase<GetRecipeFavorite.Params, MealRecipe>() {
    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<MealRecipe>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                RecipeFavoritePagingSource(repository, params.options)
            }
        ).flow
    }
}