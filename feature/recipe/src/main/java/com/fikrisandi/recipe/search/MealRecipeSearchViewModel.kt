package com.fikrisandi.recipe.search

import android.util.Log
import com.fikrisandi.domain.recipe.GetRecipesBySearch
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.framework.network.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealRecipeSearchViewModel @Inject constructor(
    private val getRecipesBySearch: GetRecipesBySearch
) : MvvmViewModel() {

    private val _uiState = MutableStateFlow(MealRecipeSearchState())
    val uiState = _uiState.asStateFlow()


    init {
        getRecipes("")
    }

    fun getRecipes(search: String) = safeLaunch {
        val params = GetRecipesBySearch.Params(
            search = search
        )

        getRecipesBySearch(params)
            .onStart {
                _uiState.update {
                    it.copy(
                        loading = true
                    )
                }
            }
            .catch { err ->
                _uiState.update {
                    it.copy(
                        loading = false,
                        exception = err
                    )
                }
            }
            .collectLatest {
                Log.e("viewmodel", "getRecipes: $it")
                when (it) {
                    is ViewState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                recipes = it.data
                            )
                        }

                    }

                    is ViewState.Failed -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                exception = it.exception
                            )
                        }
                    }

                    else -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                            )
                        }
                    }
                }
            }
    }

    fun handleOnChangeSearch(input: String) {
        _uiState.update {
            it.copy(
                search = input
            )
        }
    }
}