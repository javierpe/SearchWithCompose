package com.app.ml.api.repositories

import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.productDetail.ProductDetailModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {

    suspend fun getProductDetail(productId: String): Flow<ActionScreen<ProductDetailModel>>
}