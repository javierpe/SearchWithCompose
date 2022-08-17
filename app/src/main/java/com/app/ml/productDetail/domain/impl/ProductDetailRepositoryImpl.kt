package com.app.ml.productDetail.domain.impl

import com.app.ml.productDetail.data.api.network.ProductService
import com.app.ml.productDetail.data.repositories.ProductDetailRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.logger.Logger
import com.app.ml.productDetail.data.models.ProductDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.log

class ProductDetailRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val logger: Logger
): ProductDetailRepository {

    override suspend fun getProductDetail(productId: String): Flow<ActionScreen<ProductDetailModel>> = flow{
        val response = productService.getProductDetail(productId)
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