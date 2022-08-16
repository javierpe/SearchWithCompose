package com.app.ml.productDetail.data.repositories

import com.app.ml.data.models.ActionScreen
import com.app.ml.productDetail.data.models.ProductDetailModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {

    suspend fun getProductDetail(productId: String): Flow<ActionScreen<ProductDetailModel>>
}