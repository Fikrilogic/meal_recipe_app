package com.fikrisandi.movie.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.domain.movie.GetMovieBySearch
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.remote.genre.Genre
import com.fikrisandi.model.remote.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val getMovieBySearch: GetMovieBySearch): MvvmViewModel() {

    private val config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 10)

    private val _movieState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val movieState = _movieState.asStateFlow()

    private val loading = mutableStateOf(false)
    var search by mutableStateOf("")
        private set

    init {
        searchMovie()
    }

    fun searchMovie()= safeLaunch {
        val params = GetMovieBySearch.Params(
            config = config,
            option = mapOf(
                "search" to search
            )
        )

        getMovieBySearch(params)
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

    fun handleSearchChange(text: String){
        search = text
    }
}