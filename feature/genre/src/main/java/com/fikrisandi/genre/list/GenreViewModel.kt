package com.fikrisandi.genre.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fikrisandi.domain.genre.GetListGenre
import com.fikrisandi.model.remote.genre.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val getListGenre: GetListGenre): ViewModel() {

    private val _listGenreState: MutableStateFlow<List<Genre>> = MutableStateFlow(emptyList())
    val listGenreState = _listGenreState.asStateFlow()

    val loading = mutableStateOf(false)

    fun getListGenre(){
        viewModelScope.launch {
            getListGenre(Unit).onStart {
                loading.value = true
            }.catch {e ->
                e.printStackTrace()
            }.collect{state ->
                _listGenreState.update { state.results ?: emptyList() }
                loading.value = false
            }
        }
    }
}