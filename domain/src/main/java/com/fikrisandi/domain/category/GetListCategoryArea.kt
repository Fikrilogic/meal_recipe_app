package com.fikrisandi.domain.category

import com.fikrisandi.framework.network.ApiResult
import com.fikrisandi.framework.network.ViewState
import com.fikrisandi.framework.usecase.ViewStateUseCase
import com.fikrisandi.model.remote.category.AreaCategory
import com.fikrisandi.repository.repository.category.MeatRecipeCategoryRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetListCategoryArea @Inject constructor(private val repository: MeatRecipeCategoryRepository) :
    ViewStateUseCase<Unit, List<AreaCategory>>() {
    override suspend fun FlowCollector<ViewState<List<AreaCategory>>>.execute(params: Unit) {
        try {
            val data = repository.getListCategoryArea()
            when (data) {
                is ApiResult.Success -> {
                    emit(
                        ViewState.Success(data = data.data.categories)
                    )
                }

                is ApiResult.Failed -> {
                    emit(
                        ViewState.Failed(
                            data.exception,
                            message = data.exception.message.orEmpty()
                        )
                    )
                }

            }
        } catch (e: Throwable) {
            emit(ViewState.Success(data = emptyList()))
        }
    }
}