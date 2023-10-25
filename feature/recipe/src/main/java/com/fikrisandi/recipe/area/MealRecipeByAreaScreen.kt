package com.fikrisandi.recipe.area

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.fikrisandi.component.CustomDropdown
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealRecipeByAreaScreen(
    modifier: Modifier,
    navigationProvider: NavigationProvider,
    viewModel: MealRecipeByAreaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text("Recipe By Area", style = MaterialTheme.typography.titleLarge)
                },
                colors = TopAppBarColors(
                    containerColor = AppColors.secondaryContainer,
                    scrolledContainerColor = AppColors.secondaryContainer,
                    navigationIconContentColor = AppColors.primary,
                    titleContentColor = AppColors.scrim,
                    actionIconContentColor = AppColors.primary
                )
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
                CustomDropdown(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    value = state.categorySelected?.area.orEmpty(),
                    listOption = state.category,
                    label = {
                        Text("Select Area")
                    },
                    onChange = { category ->
                        viewModel.handleOnChangeDropdown(category)
                    })
                if (state.loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics(mergeDescendants = true) {}
                    )
                }

            }

            if (state.recipes.isEmpty() || state.exception != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text("Data Kosong", style = MaterialTheme.typography.displayMedium)
                }
            }

            if (state.recipes.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.recipes.count()) { index ->
                        val data = state.recipes[index]

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable {
                                    data.idMeal?.let{id ->
                                        navigationProvider.navigateToDetailRecipe(id)
                                    }
                                },
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(100.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .weight(.2f), model = ImageRequest.Builder(context)
                                    .data("${data.strMealThumb.orEmpty()}/preview")
                                    .size(Size.ORIGINAL)
                                    .crossfade(true)
                                    .build(), contentDescription = data.strMeal
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(.7f)
                            ) {
                                Text(
                                    data.strMeal.orEmpty(),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}