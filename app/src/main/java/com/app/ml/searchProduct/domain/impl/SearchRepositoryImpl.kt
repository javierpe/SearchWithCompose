package com.app.ml.searchProduct.domain.impl

import com.app.ml.searchProduct.data.api.network.SearchService
import com.app.ml.searchProduct.data.repositories.SearchRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.logger.Logger
import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    private val logger: Logger
) : SearchRepository {

    override suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>> = flow {
        val response = searchService.search(value)
        if (response.isSuccessful) {
            response.body()?.let {
                logger.i(it.toString())
                emit(
                    ActionScreen.SuccessAction(
                        responseModel = it
                    )
                )
            }
        } else {
            logger.e(response.errorBody()?.toString().orEmpty())
            emit(
                ActionScreen.ErrorAction(
                    errorBody = response.errorBody()
                )
            )
        }
    }
}