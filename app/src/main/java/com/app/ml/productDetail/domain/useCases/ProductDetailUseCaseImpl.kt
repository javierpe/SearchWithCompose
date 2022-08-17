package com.app.ml.productDetail.domain.useCases

import com.app.ml.productDetail.data.repositories.ProductDetailRepository
import com.app.ml.productDetail.data.api.useCases.ProductDetailUseCase
import com.app.ml.data.models.ActionScreen
import com.app.ml.productDetail.data.models.ProductDetailModel
import com.app.ml.database.AppDatabase
import com.app.ml.database.entities.SkeletonsEntity
import com.app.ml.di.qualifiers.IODispatcher
import com.app.ml.logger.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

const val CONTEXT_PRODUCT_DETAIL = "PRODUCT_DETAIL"

class ProductDetailUseCaseImpl @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val productDetailRepository: ProductDetailRepository,
    private val database: AppDatabase,
    private val logger: Logger
): ProductDetailUseCase {

    override suspend fun loadProductDetail(value: String): Flow<ActionScreen<ProductDetailModel>> {
        return productDetailRepository.getProductDetail(value).onEach {
            if (it is ActionScreen.SuccessAction) {
                database.skeletonsDao().saveSkeletonsByContext(
                    SkeletonsEntity(
                        context = CONTEXT_PRODUCT_DETAIL,
                        renders = listOf(CONTEXT_PRODUCT_DETAIL)
                    )
                )
            }
        }.onStart {
            val skeletonContext = database.skeletonsDao().provideSkeletonsByContext(CONTEXT_PRODUCT_DETAIL)

            skeletonContext?.let {
                emit(ActionScreen.SkeletonAction(it.renders))
            } ?: kotlin.run {
                emit(ActionScreen.LoadingAction())
            }
        }.catch {
            logger.e(it.message.orEmpty())
            emit(ActionScreen.ErrorAction(it))
        }.flowOn(coroutineDispatcher)
    }
}