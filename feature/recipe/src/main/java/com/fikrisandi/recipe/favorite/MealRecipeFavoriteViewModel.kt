package com.fikrisandi.recipe.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fikrisandi.domain.recipe.GetRecipeFavorite
import com.fikrisandi.framework.base.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealRecipeFavoriteViewModel @Inject constructor(
    private val getRecipeFavorite: GetRecipeFavorite
) : MvvmViewModel() {

    private val _uiState = MutableStateFlow(MealRecipeFavoriteState())
    val uiState = _uiState.asStateFlow()

    private val paging = PagingConfig(pageSize = 100, initialLoadSize = 10, prefetchDistance = 10)

    fun getRecipes() = safeLaunch {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        val params = GetRecipeFavorite.Params(
            pagingConfig = paging,
            options = mapOf(
                "limit" to 10
            )
        )
        _uiState.update {
            it.copy(
                loading = false,
                recipes = getRecipeFavorite(params)
                    .cachedIn(viewModelScope),
                exception = null
            )
        }
    }
}