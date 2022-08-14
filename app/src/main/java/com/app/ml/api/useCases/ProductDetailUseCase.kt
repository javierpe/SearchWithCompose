package com.app.ml.api.useCases

import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.productDetail.ProductDetailModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailUseCase {

    suspend fun loadProductDetail(value: String): Flow<ActionScreen<ProductDetailModel>>
}