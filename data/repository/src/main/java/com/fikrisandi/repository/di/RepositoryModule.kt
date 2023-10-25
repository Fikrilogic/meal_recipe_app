package com.fikrisandi.repository.di

import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.local.dao.RecipeFavoriteDao
import com.fikrisandi.repository.repository.category.MeatRecipeCategoryRepository
import com.fikrisandi.repository.repository.category.MeatRecipeCategoryRepositoryImpl
import com.fikrisandi.repository.repository.recipe.RecipeRepository
import com.fikrisandi.repository.repository.recipe.RecipeRepositoryImpl
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepository
import com.fikrisandi.repository.repository.recipe.favorite.RecipeFavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMeatRecipeCategoryRepository(appClient: AppHttpClient): MeatRecipeCategoryRepository =
        MeatRecipeCategoryRepositoryImpl(appClient)

    @Provides
    @Singleton
    fun provideRecipeRepository(appClient: AppHttpClient): RecipeRepository =
        RecipeRepositoryImpl(appClient)

    @Provides
    @Singleton
    fun provideRecipeFavoriteRepository(dao: RecipeFavoriteDao): RecipeFavoriteRepository =
        RecipeFavoriteRepositoryImpl(dao)
}