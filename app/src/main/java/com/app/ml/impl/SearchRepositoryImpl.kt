package com.app.ml.impl

import com.app.ml.api.network.SearchService
import com.app.ml.api.repositories.SearchRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {

    override suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>> = flow {
        val response = searchService.search(value)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(
                    ActionScreen.SuccessAction(
                        responseModel = it
                    )
                )
            }
        } else {
            emit(
                ActionScreen.ErrorAction(
                    errorBody = response.errorBody()
                )
            )
        }
    }
}