package com.fikrisandi.movie.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieapp.common.constant.ConstantVal
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel(),
    navigationProvider: NavigationProvider = EmptyNavigationProvider
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var tabIndex by remember {
        mutableIntStateOf(0)
    }


    val listMovieData = viewModel.movieState.collectAsLazyPagingItems()
    val listGenre = viewModel.genreState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text("Movie", style = MaterialTheme.typography.titleLarge)
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (listGenre.value.isNotEmpty()) {
                    ScrollableTabRow(
                        selectedTabIndex = tabIndex,
                        Modifier.fillMaxWidth()
                    ) {
                        listGenre.value.forEachIndexed { index, value ->
                            Tab(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 40.dp)
                                    .padding(horizontal = 2.dp),
                                selected = tabIndex == index,
                                onClick = {
                                    tabIndex = index
                                    scope.launch {
                                        viewModel.clearMovieState()
                                        viewModel.getListMovie(value.id ?: 0)
                                    }
                                },
                            ) {

                                Column(
                                    Modifier
                                        .padding(10.dp)
                                        .height(50.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = value.name ?: "",
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                            }
                        }
                    }
                }
                if (listMovieData.loadState.refresh is LoadState.Loading || listMovieData.loadState.append is LoadState.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics(mergeDescendants = true) {}
                    )
                }

            }

            if (listMovieData.loadState.refresh is LoadState.Error || listMovieData.loadState.append is LoadState.Error || listGenre.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                }
            }

            if (listMovieData.loadState.refresh is LoadState.NotLoading || listMovieData.loadState.append is LoadState.NotLoading) {
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
                                    navigationProvider.navigateToDetailMovie(
                                        data, listGenre.value
                                    )
                                },
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(100.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .weight(.2f), model = ImageRequest.Builder(context)
                                    .data("${ConstantVal.IMAGE_BASE_URL}${data?.posterPath}")
                                    .size(Size.ORIGINAL)
                                    .crossfade(true)
                                    .build(), contentDescription = data?.originalLanguage ?: ""
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
                                    Text("Rating", style = MaterialTheme.typography.bodySmall)
                                    Text(
                                        (data?.voteAverage ?: 0.0).toString(),
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