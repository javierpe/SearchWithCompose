package com.app.ml.impl

import com.app.ml.api.network.ProductService
import com.app.ml.api.repositories.ProductDetailRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.productDetail.ProductDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val productService: ProductService
): ProductDetailRepository {

    override suspend fun getProductDetail(productId: String): Flow<ActionScreen<ProductDetailModel>> = flow{
        val response = productService.getProductDetail(productId)
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