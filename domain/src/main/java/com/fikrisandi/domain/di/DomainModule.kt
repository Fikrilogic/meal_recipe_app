package com.fikrisandi.domain.di

import com.fikrisandi.domain.category.GetListCategoryArea
import com.fikrisandi.domain.category.GetListCategoryType
import com.fikrisandi.repository.repository.category.MeatRecipeCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [RecipeModule::class])
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetListCategoryArea(repository: MeatRecipeCategoryRepository) =
        GetListCategoryArea(repository)

    @Provides
    @Singleton
    fun provideGetListCategoryType(repository: MeatRecipeCategoryRepository) =
        GetListCategoryType(repository)


}