package com.app.ml.api.repositories

import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>>
}