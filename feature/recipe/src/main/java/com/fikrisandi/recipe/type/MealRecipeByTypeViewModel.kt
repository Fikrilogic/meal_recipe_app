package com.fikrisandi.recipe.type

import com.fikrisandi.domain.category.GetListCategoryType
import com.fikrisandi.domain.recipe.GetRecipesByType
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.model.remote.category.TypeCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealRecipeByTypeViewModel @Inject constructor(
    private val getListCategoryType: GetListCategoryType,
    private val getRecipesByType: GetRecipesByType
) :
    MvvmViewModel() {

    private val _uiState = MutableStateFlow(MealRecipeByTypeState())
    val uiState = _uiState.asStateFlow()


    init {
        getCategory()
    }

    fun getCategory() = safeLaunch {
        getListCategoryType(Unit)
            .onStart {
                _uiState.update { state ->
                    state.copy(
                        loading = true,
                        exception = null
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
                                exception = null,
                                category = it.data
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

    fun getRecipe(category: String) = safeLaunch {
        val params = GetRecipesByType.Params(
            type = category
        )
        getRecipesByType(params)
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
                                exception = null,
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

    fun handleOnChangeDropdown(category: TypeCategory) {
        _uiState.update { state ->
            state.copy(categorySelected = category)
        }.also {
            _uiState.value.categorySelected?.let {
                getRecipe(it.name)
            }
        }
    }
}