package com.fikrisandi.recipe.detail

import com.fikrisandi.domain.recipe.AddRecipeFavorite
import com.fikrisandi.domain.recipe.DeleteRecipeFavorite
import com.fikrisandi.domain.recipe.GetRecipeFavoriteById
import com.fikrisandi.domain.recipe.GetRecipesById
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.model.remote.recipe.MealRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealRecipeDetailViewModel @Inject constructor(
    private val getRecipesById: GetRecipesById,
    private val addRecipeFavorite: AddRecipeFavorite,
    private val deleteRecipeFavorite: DeleteRecipeFavorite,
    private val getRecipeFavoriteById: GetRecipeFavoriteById
) : MvvmViewModel() {

    private val _uiState = MutableStateFlow(MealRecipeDetailState())
    val uiState = _uiState.asStateFlow()


    fun getRecipeDetail(id: String) = safeLaunch {
        val params = GetRecipesById.Params(
            id = id
        )

        getRecipesById(params)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true
                    )
                }
            }
            .catch {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        exception = it
                    )
                }
            }
            .collectLatest {
                when (it) {
                    is ViewState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                recipe = it.data
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

    fun checkRecipeFavorite(id: String) = safeLaunch {
        val params = GetRecipeFavoriteById.Params(
            id = id
        )

        getRecipeFavoriteById(params)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true
                    )
                }
            }
            .catch {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        exception = it,
                        favorite = false
                    )
                }
            }
            .collectLatest {
                when (it) {
                    null -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                favorite = false
                            )
                        }
                    }

                    else -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                favorite = true
                            )
                        }
                    }
                }
            }
    }

    fun addToFavorite(data: MealRecipe) = safeLaunch {
        val params = AddRecipeFavorite.Params(
            data = data
        )

        addRecipeFavorite(params)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true
                    )
                }
            }
            .catch {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        exception = it
                    )
                }
            }
            .collectLatest {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        favorite = true
                    )
                }
            }
    }

    fun deleteToFavorite(data: MealRecipe) = safeLaunch {
        val params = DeleteRecipeFavorite.Params(
            data = data
        )

        deleteRecipeFavorite(params)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true
                    )
                }
            }
            .catch {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        exception = it
                    )
                }
            }
            .collectLatest {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        favorite = false
                    )
                }
            }
    }

}