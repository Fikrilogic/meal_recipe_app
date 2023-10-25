package com.fikrisandi.movie.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.domain.favorite.movie.AddMovieFavorite
import com.fikrisandi.domain.favorite.movie.DeleteMovieFavorite
import com.fikrisandi.domain.favorite.movie.GetMovieFavoriteById
import com.fikrisandi.domain.movie.GetMovieReview
import com.fikrisandi.domain.movie.GetMovieTrailer
import com.fikrisandi.framework.base.MvvmViewModel
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.model.remote.movie.Trailer
import com.fikrisandi.model.remote.user.UserReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getMovieTrailer: GetMovieTrailer,
    private val getMovieReview: GetMovieReview,
    private val addMovieFavorite: AddMovieFavorite,
    private val deleteMovieFavorite: DeleteMovieFavorite,
    private val getMovieFavoriteById: GetMovieFavoriteById,
) : MvvmViewModel() {

    private val _listMovieTrailer: MutableStateFlow<List<Trailer>> = MutableStateFlow(emptyList())
    val listMovieTrailer = _listMovieTrailer.asStateFlow()
    private val _listReviewMovie: MutableStateFlow<PagingData<UserReview>> = MutableStateFlow(
        PagingData.empty()
    )
    val listReviewMovie = _listReviewMovie.asStateFlow()

    private val _addAsFavorite: MutableStateFlow<MovieFavoriteEntity?> = MutableStateFlow(null)
    val addAsFavorite = _addAsFavorite.asStateFlow()

    val loading = mutableStateOf(false)
    private val config = PagingConfig(pageSize = 5, initialLoadSize = 5, prefetchDistance = 5)

    fun getMovieTrailer(id: String) = safeLaunch {
        getMovieTrailer(
            GetMovieTrailer.Params(id)
        ).onStart {
            loading.value = true
        }.catch { error ->
            handleError(error)
        }.onCompletion {
            loading.value = false
        }.collect { dataState ->
            dataState.results?.let { list ->
                _listMovieTrailer.update {
                    list
                }
            }
        }
    }

    fun getUserMovieReview(id: String) = safeLaunch {
        getMovieReview(
            GetMovieReview.Params(
                config = config,
                option = mapOf(
                    "id" to id
                )
            )
        ).catch { error ->
            handleError(error)
        }.cachedIn(viewModelScope).collect {
            _listReviewMovie.emit(it)
        }
    }

    fun addToFavorite(movie: Movie, trailer: Trailer) = safeLaunch {
        val params = AddMovieFavorite.Params(data = movie, trailer = trailer)
        addMovieFavorite(params)
            .catch {
                handleError(it)
            }
            .collectLatest {
                showAlert("Berhasil Menambahkan ke Dalam Favorite", true)
                movie.id?.let{
                    checkAddedFavorite(it)
                }
            }
    }

    fun checkAddedFavorite(id: Int) = safeLaunch {
        val params = GetMovieFavoriteById.Params(
            id = id
        )

        getMovieFavoriteById(params)
            .onStart { loading.value = true }
            .onCompletion {
                loading.value = false
            }
            .catch {
                handleError(it)
                _addAsFavorite.emit(null)
            }
            .collectLatest {
                _addAsFavorite.emit(it)
            }
    }

    fun deleteFromFavorite(data: MovieFavoriteEntity) = safeLaunch {
        val params = DeleteMovieFavorite.Params(
            data
        )
        deleteMovieFavorite(params)
            .catch {
                handleError(it)
            }
            .collectLatest {
                showAlert("Berhasil Menghapus dari Favorite", true)
                checkAddedFavorite(data.id)
            }
    }

}