package com.fikrisandi.movie.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieapp.common.constant.ConstantVal
import com.fikrisandi.model.local.toModel
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.movie.search.MovieSearchViewModel
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieFavoriteScreenViewModel = hiltViewModel(),
    navigationProvider: NavigationProvider = EmptyNavigationProvider
) {

    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    val listMovieData = viewModel.movieState.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text("Search Movie", style = MaterialTheme.typography.titleLarge)
            }
        )
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (listMovieData.loadState.refresh is LoadState.Loading || listMovieData.loadState.append is LoadState.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics(mergeDescendants = true) {}
                    )
                }

            }
            when {
                listMovieData.loadState.refresh is LoadState.Error || listMovieData.loadState.append is LoadState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                    }
                }

                listMovieData.loadState.refresh is LoadState.NotLoading || listMovieData.loadState.append is LoadState.NotLoading -> {
                    when (listMovieData.itemCount) {
                        0 -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(), contentAlignment = Alignment.Center
                            ) {
                                Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                            }
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(listMovieData.itemCount) { index ->
                                    val data = listMovieData[index]

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                            .clickable {
                                                val movie  = try {
                                                    data?.toModel()
                                               } catch (e: Exception){
                                                   e.printStackTrace()
                                                   Movie()
                                               }
                                                navigationProvider.navigateToDetailMovie(
                                                    movie,
                                                    emptyList(),
                                                    data?.trailer
                                                )
                                            },
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .width(80.dp)
                                                .height(100.dp)
                                                .clip(MaterialTheme.shapes.medium)
                                                .weight(.2f),
                                            model = ImageRequest.Builder(context)
                                                .data("${ConstantVal.IMAGE_BASE_URL}${data?.posterPath}")
                                                .size(Size.ORIGINAL)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = data?.originalLanguage ?: ""
                                        )
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(.7f)
                                        ) {
                                            Text(
                                                data?.title ?: "",
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Text(
                                                    "Rating",
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                                Text(
                                                    (data?.voteAverage ?: 0f).toString(),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

        }
    }
}