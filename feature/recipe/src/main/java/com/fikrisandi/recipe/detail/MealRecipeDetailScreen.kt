package com.fikrisandi.recipe.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.AppColors
import com.fikrisandi.theme.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealRecipeDetailScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider,
    viewModel: MealRecipeDetailViewModel = hiltViewModel(),
    id: String = ""
) {

    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getRecipeDetail(id)
    })

    LaunchedEffect(key1 = state.recipe, block = {
        state.recipe?.let {
            viewModel.checkRecipeFavorite(it.idMeal.orEmpty())
        }
    })

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text("Recipe", style = MaterialTheme.typography.titleLarge) },
            navigationIcon = {
                IconButton(onClick = { navigationProvider.navigateBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = AppColors.primary
                    )
                }
            },
            colors = TopAppBarColors(
                containerColor = AppColors.secondaryContainer,
                scrolledContainerColor = AppColors.secondaryContainer,
                navigationIconContentColor = AppColors.primary,
                titleContentColor = AppColors.scrim,
                actionIconContentColor = AppColors.primary
            )
        )

    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (state.loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics(mergeDescendants = true) {}
                )
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.recipe?.let { data ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                                .border(
                                    border = BorderStroke(
                                        2.dp,
                                        MaterialTheme.colorScheme.secondary
                                    ), shape = MaterialTheme.shapes.medium
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = MaterialTheme.shapes.medium),
                                model = "${data.strMealThumb}/preview",
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                                error = {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            "Something Error or Image Not Found",
                                            style = MaterialTheme.typography.headlineSmall
                                        )
                                    }
                                },
                                loading = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            )
                        }
                    }
                    item {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                data.strMeal.orEmpty(),
                                style = MaterialTheme.typography.titleLarge
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = "Category:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = data.strCategory.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = "Area:",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = data.strArea.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            when (state.favorite) {
                                false -> {
                                    Button(onClick = {
                                        viewModel.addToFavorite(data)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_star_border),
                                            contentDescription = "Add Favorite"
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            "Tambahkan Dalam Favorite",
                                            color = AppColors.background
                                        )
                                    }
                                }

                                true -> {
                                    Button(onClick = {
                                        viewModel.deleteToFavorite(data)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_star),
                                            contentDescription = "Add Favorite",
                                            tint = AppColors.onTertiary
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Favorite", color = AppColors.background)
                                    }
                                }
                            }
                            Column {
                                Text(
                                    "Ingredient:",
                                    style = MaterialTheme.typography.labelLarge
                                )
                                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                                    data.strIngredient1?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient2?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient3?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient4?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient5?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient6?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }

                                    data.strIngredient7?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient8?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient9?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient10?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient11?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient12?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient13?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient14?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient15?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient16?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient17?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient18?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient19?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    data.strIngredient20?.let { ingredient ->
                                        if (ingredient.isNotEmpty()) {
                                            Text(
                                                "- $ingredient",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                }
                            }
                            Column {
                                Text(
                                    "Instruction:",
                                    style = MaterialTheme.typography.labelLarge
                                )
                                Text(
                                    data.strInstructions.orEmpty().ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                        }
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("Tutorial", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            AndroidView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                factory = { context ->
                                    YouTubePlayerView(context).apply {
                                        addYouTubePlayerListener(
                                            object : AbstractYouTubePlayerListener() {
                                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                                    super.onReady(youTubePlayer)
                                                    data.strYoutube?.let { url ->
                                                        val id = url.split("=").last()
                                                        youTubePlayer.loadVideo(
                                                            id,
                                                            0f
                                                        )
                                                    }
                                                }
                                            }
                                        )
                                    }
                                })
                        }
                    }
                }

            }
        }
    }
}