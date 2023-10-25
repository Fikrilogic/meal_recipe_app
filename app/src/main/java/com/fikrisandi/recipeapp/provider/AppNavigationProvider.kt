package com.fikrisandi.recipeapp.provider

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.recipe.detail.destinations.MealRecipeDetailScreenDestination
import com.ramcosta.composedestinations.navigation.navigate


class AppNavigationProvider(private val navController: NavController) : NavigationProvider {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit) {}

    override fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit) {}



    override fun navigateToDetailRecipe(id: String) {
        navController.navigate(
            MealRecipeDetailScreenDestination.invoke(id = id)
        )
    }


}