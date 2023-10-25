package com.fikrisandi.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.fikrisandi.framework.room.dao.BaseDao
import com.fikrisandi.model.local.recipe.RecipeFavoriteEntity
import java.lang.IllegalStateException

@Dao
interface RecipeFavoriteDao: BaseDao<RecipeFavoriteEntity>{
    @Query("SELECT * FROM ${RecipeFavoriteEntity.NAME} WHERE idMeal = :id")
    @Throws(IllegalStateException::class)
    suspend fun get(id: String): RecipeFavoriteEntity

    @Query("SELECT * FROM ${RecipeFavoriteEntity.NAME} LIMIT :limit OFFSET :offset")
//    @Throws(IllegalStateException::class)
    suspend fun get(limit: Int, offset: Int): List<RecipeFavoriteEntity>
}