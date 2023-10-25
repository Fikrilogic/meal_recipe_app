package com.fikrisandi.movie.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.domain.genre.GetListGenre
import com.fikrisandi.domain.movie.GetMovieByGenre
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.remote.genre.Genre
import com.fikrisandi.model.remote.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieByGenre: GetMovieByGenre,
    private val getListGenre: GetListGenre
) : MvvmViewModel() {

    private val config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 10)

    private val _movieState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val movieState = _movieState.asStateFlow()

    private val _genreState: MutableStateFlow<List<Genre>> = MutableStateFlow(emptyList())
    val genreState = _genreState.asStateFlow()

    private val loading = mutableStateOf(false)

    init {
        getListGenre()
    }

    fun clearMovieState() = safeLaunch {
        _movieState.emit(PagingData.empty())
    }

    private fun getListGenre() = safeLaunch {
        getListGenre(Unit)
            .onStart {
                loading.value = true
            }.catch { e ->
                e.printStackTrace()
            }.collect { state ->
                val data = state.results.orEmpty()
                _genreState.update {
                    data.toList()
                }
                getListMovie(data.firstOrNull()?.id ?: 0)
                loading.value = false
            }
    }

    fun getListMovie(index: Int) = safeLaunch {
        getMovieByGenre(
            GetMovieByGenre.Params(
                config = config,
                option = mapOf(
                    "genre" to index
                )
            )
        ).onStart {
            loading.value = true
        }.onCompletion {
            loading.value = false
        }.catch { e ->
            e.printStackTrace()
        }.cachedIn(viewModelScope)
            .collect {
                _movieState.emit(it)
            }
    }
}