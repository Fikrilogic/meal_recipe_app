package com.fikrisandi.recipeapp.main

import com.fikrisandi.home.HomeNavGraph
import com.fikrisandi.recipe.detail.RecipeNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph: NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = emptyMap()
    override val route: String
        get() = "root"
    override val startRoute: Route
        get() = HomeNavGraph

    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        HomeNavGraph,
        RecipeNavGraph
    )
}