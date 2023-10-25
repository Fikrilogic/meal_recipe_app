package com.fikrisandi.domain.recipe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.model.local.recipe.toModel
import com.fikrisandi.model.remote.recipe.MealRecipe
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import javax.inject.Inject

class RecipeFavoritePagingSource @Inject constructor(
    val repository: RecipeFavoriteRepository,
    val options: Map<String, Any>
) :
    PagingSource<Int, MealRecipe>() {
    override fun getRefreshKey(state: PagingState<Int, MealRecipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MealRecipe> {
        val page = params.key ?: 0
        val limit = (options["limit"] as? Int) ?: 10
        val offset = page * limit
        return try {
            val response = repository.get(limit, offset)
            LoadResult.Page(
                data = response.map { it.toModel() },
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}