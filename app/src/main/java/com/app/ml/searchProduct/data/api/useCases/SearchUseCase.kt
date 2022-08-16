package com.app.ml.searchProduct.data.api.useCases

import com.app.ml.data.models.ActionScreen
import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import com.app.ml.database.entities.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    /**
     * Search api
     */
    suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>>

    /**
     * Recent search api
     */
    suspend fun loadRecentSearchByValue(value: String): List<RecentSearchEntity>
}