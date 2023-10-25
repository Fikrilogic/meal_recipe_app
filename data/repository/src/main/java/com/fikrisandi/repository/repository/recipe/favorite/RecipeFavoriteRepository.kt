package com.fikrisandi.repository.repository.recipe.favorite

import com.fikrisandi.model.local.recipe.RecipeFavoriteEntity

interface RecipeFavoriteRepository {
    suspend fun get(id: String): RecipeFavoriteEntity
    suspend fun get(limit:Int, offset: Int): List<RecipeFavoriteEntity>
    suspend fun insert(data: RecipeFavoriteEntity)
    suspend fun delete(data: RecipeFavoriteEntity)
}