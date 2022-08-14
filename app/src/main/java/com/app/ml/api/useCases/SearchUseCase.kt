package com.app.ml.api.useCases

import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import com.app.ml.db.entities.RecentSearchEntity
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