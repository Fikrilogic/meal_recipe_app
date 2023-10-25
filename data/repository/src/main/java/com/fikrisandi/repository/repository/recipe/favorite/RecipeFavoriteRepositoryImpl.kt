package com.fikrisandi.repository.repository.recipe.favorite

import com.fikrisandi.local.dao.RecipeFavoriteDao
import com.fikrisandi.model.local.recipe.RecipeFavoriteEntity
import javax.inject.Inject

class RecipeFavoriteRepositoryImpl @Inject constructor(private val dao: RecipeFavoriteDao) :
    RecipeFavoriteRepository {
    override suspend fun get(id: String): RecipeFavoriteEntity {
        return dao.get(id)
    }

    override suspend fun get(limit: Int, offset: Int): List<RecipeFavoriteEntity> {
        return dao.get(limit, offset)
    }

    override suspend fun insert(data: RecipeFavoriteEntity) {
        dao.insert(data)
    }

    override suspend fun delete(data: RecipeFavoriteEntity) {
        dao.delete(data)
    }
}