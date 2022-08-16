package com.app.ml.searchProduct.data.repositories

import com.app.ml.data.models.ActionScreen
import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>>
}