package com.fikrisandi.repository.repository.category

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.model.remote.category.AreaCategoryResponse
import com.fikrisandi.model.remote.category.TypeCategoryResponse

interface MeatRecipeCategoryRepository {

    suspend fun getListCategoryArea(): ApiResult<AreaCategoryResponse>
    suspend fun getListCategoryType(): ApiResult<TypeCategoryResponse>
}