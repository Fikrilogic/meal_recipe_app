package com.fikrisandi.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fikrisandi.local.dao.RecipeFavoriteDao
import com.fikrisandi.model.local.recipe.RecipeFavoriteEntity

@Database(
    version = 1,
    entities = [RecipeFavoriteEntity::class],
    exportSchema = true,
)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getRecipeFavoriteDao(): RecipeFavoriteDao
}