package com.fikrisandi.domain.di

import com.fikrisandi.domain.recipe.AddRecipeFavorite
import com.fikrisandi.domain.recipe.DeleteRecipeFavorite
import com.fikrisandi.domain.recipe.GetRecipeFavorite
import com.fikrisandi.domain.recipe.GetRecipeFavoriteById
import com.fikrisandi.domain.recipe.GetRecipesByArea
import com.fikrisandi.domain.recipe.GetRecipesById
import com.fikrisandi.domain.recipe.GetRecipesBySearch
import com.fikrisandi.domain.recipe.GetRecipesByType
import com.fikrisandi.repository.repository.recipe.RecipeRepository
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeModule {

    @Provides
    @Singleton
    fun provideGetRecipeByArea(repository: RecipeRepository) = GetRecipesByArea(repository)

    @Provides
    @Singleton
    fun provideGetRecipeByType(repository: RecipeRepository) = GetRecipesByType(repository)

    @Provides
    @Singleton
    fun provideGetRecipeBySearch(repository: RecipeRepository) = GetRecipesBySearch(repository)

    @Provides
    @Singleton
    fun provideGetRecipeById(repository: RecipeRepository) = GetRecipesById(repository)

    @Provides
    @Singleton
    fun provideAddRecipeFavorite(repository: RecipeFavoriteRepository) =
        AddRecipeFavorite(repository)

    @Provides
    @Singleton
    fun provideDeleteRecipeFavorite(repository: RecipeFavoriteRepository) =
        DeleteRecipeFavorite(repository)

    @Provides
    @Singleton
    fun provideGetRecipeFavoriteById(repository: RecipeFavoriteRepository) =
        GetRecipeFavoriteById(repository)

    @Provides
    @Singleton
    fun provideGetRecipeFavorite(repository: RecipeFavoriteRepository) =
        GetRecipeFavorite(repository)
}