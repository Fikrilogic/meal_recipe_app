package com.fikrisandi.provider

import androidx.navigation.NavOptionsBuilder

interface NavigationProvider {
    fun navigateBack()

    fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit = {})
    fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit = {})
    fun navigateToDetailRecipe(id: String)
}

object EmptyNavigationProvider: NavigationProvider{
    override fun navigateBack() {
        TODO("Not yet implemented")
    }

    override fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }


    override fun navigateToDetailRecipe(id: String) {
        TODO("Not yet implemented")
    }


}