package com.fikrisandi.home


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.recipe.area.MealRecipeByAreaScreen
import com.fikrisandi.recipe.favorite.MealRecipeFavoriteScreen
import com.fikrisandi.recipe.search.MealRecipeSearchScreen
import com.fikrisandi.recipe.type.MealRecipeByTypeScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val listScreen by remember {
        mutableStateOf(
            listOf(
                BottomBarDestination.SEARCH,
                BottomBarDestination.TYPE,
                BottomBarDestination.PLACE,
                BottomBarDestination.FAVORITE,
            )
        )
    }

    Crossfade(targetState = selectedIndex, label = "bottom_navigation") { index ->
        Scaffold(bottomBar = {
            HomeBottomBar(
                modifier = modifier.fillMaxWidth(),
                listScreen = listScreen,
                selectedIndex = selectedIndex,
                onClick = {
                    selectedIndex = it
                })

        }) {
            val modifier = Modifier.padding(it)
            when (listScreen[index]) {
                BottomBarDestination.TYPE -> {
                    MealRecipeByTypeScreen(
                        modifier = modifier,
                        navigationProvider = navigationProvider
                    )
                }

                BottomBarDestination.PLACE -> {
                    MealRecipeByAreaScreen(
                        modifier = modifier,
                        navigationProvider = navigationProvider
                    )
                }

                BottomBarDestination.SEARCH -> {
                    MealRecipeSearchScreen(
                        modifier = modifier,
                        navigationProvider = navigationProvider
                    )
                }

                BottomBarDestination.FAVORITE -> {
                    MealRecipeFavoriteScreen(
                        modifier = modifier,
                        navigationProvider = navigationProvider
                    )
                }
            }
        }
    }
}