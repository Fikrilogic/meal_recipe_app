package com.fikrisandi.repository.repository.category

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.call
import com.fikrisandi.model.remote.category.AreaCategoryResponse
import com.fikrisandi.model.remote.category.TypeCategoryResponse
import io.ktor.http.HttpMethod
import javax.inject.Inject


class MeatRecipeCategoryRepositoryImpl @Inject constructor (val appHttpClient: AppHttpClient): MeatRecipeCategoryRepository {
    override suspend fun getListCategoryArea(): ApiResult<AreaCategoryResponse> {
        return try {
            val result = appHttpClient.call<AreaCategoryResponse>(
                route = "list.php",
                method = HttpMethod.Get,
                params = mapOf(
                    "a" to "list"
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }

    override suspend fun getListCategoryType(): ApiResult<TypeCategoryResponse> {
        return try {
            val result = appHttpClient.call<TypeCategoryResponse>(
                route = "list.php",
                method = HttpMethod.Get,
                params = mapOf(
                    "c" to "list"
                )
            )
            ApiResult.Success(
                data = result,
            )
        } catch (e: Throwable) {
            ApiResult.Failed(exception = e)
        }
    }


}

