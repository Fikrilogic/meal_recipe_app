package com.fikrisandi.recipe.area

import com.fikrisandi.domain.category.GetListCategoryArea
import com.fikrisandi.domain.recipe.GetRecipesByArea
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.model.remote.category.AreaCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MealRecipeByAreaViewModel @Inject constructor(
    private val getListCategoryArea: GetListCategoryArea,
    private val getRecipesByArea: GetRecipesByArea
) : MvvmViewModel() {

    private val _uiState = MutableStateFlow(MealRecipeByAreaState())
    val uiState = _uiState.asStateFlow()

    init {
        getCategory()
    }

    fun getCategory() = safeLaunch {
        getListCategoryArea(Unit)
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

    fun getRecipes(area: String) = safeLaunch {
        val params = GetRecipesByArea.Params(
            area = area
        )

        getRecipesByArea(params)
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

    fun handleOnChangeDropdown(category: AreaCategory){
        _uiState.update {
            it.copy(
                categorySelected = category
            )
        }.also {
            _uiState.value.categorySelected?.let {
                getRecipes(it.area)
            }
        }
    }
}