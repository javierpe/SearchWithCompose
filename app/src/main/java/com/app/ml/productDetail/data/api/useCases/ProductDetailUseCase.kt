package com.app.ml.productDetail.data.api.useCases

import com.app.ml.data.models.ActionScreen
import com.app.ml.productDetail.data.models.ProductDetailModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailUseCase {

    suspend fun loadProductDetail(value: String): Flow<ActionScreen<ProductDetailModel>>
}