package com.fikrisandi.movie.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.domain.favorite.movie.GetListMovieFavorite
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.local.MovieFavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteScreenViewModel @Inject constructor(private val getListMovieFavorite: GetListMovieFavorite) :
    MvvmViewModel() {

    private val config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 10)

    private val _movieState: MutableStateFlow<PagingData<MovieFavoriteEntity>> =
        MutableStateFlow(PagingData.empty())
    val movieState = _movieState.asStateFlow()

    private val loading = mutableStateOf(false)
    init {
        getMovie()
    }

    fun getMovie()= safeLaunch {
        val params = GetListMovieFavorite.Params(
            pagingConfig = config,
            options = mapOf(
                "limit" to 10
            )
        )

        getListMovieFavorite(params)
            .onStart {
                loading.value = true
            }.onCompletion {
                loading.value = false
            }.catch { e ->
                e.printStackTrace()
            }.cachedIn(viewModelScope).collect {
                _movieState.emit(it)
            }
    }

}