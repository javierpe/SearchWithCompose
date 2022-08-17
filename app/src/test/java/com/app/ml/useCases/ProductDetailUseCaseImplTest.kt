package com.app.ml.useCases

import app.cash.turbine.test
import com.app.ml.productDetail.data.repositories.ProductDetailRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.productDetail.data.models.ShippingModel
import com.app.ml.productDetail.data.models.ProductDetailModel
import com.app.ml.database.AppDatabase
import com.app.ml.database.dao.SkeletonsDao
import com.app.ml.database.entities.SkeletonsEntity
import com.app.ml.logger.Logger
import com.app.ml.productDetail.domain.useCases.CONTEXT_PRODUCT_DETAIL
import com.app.ml.productDetail.domain.useCases.ProductDetailUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductDetailUseCaseImplTest {

    @Mock
    lateinit var logger: Logger

    @Mock
    lateinit var database: AppDatabase

    @Mock
    lateinit var productDetailRepository: ProductDetailRepository

    @Mock
    lateinit var skeletonsDao: SkeletonsDao

    @Mock
    lateinit var errorAction: ActionScreen.ErrorAction<ProductDetailModel>

    private lateinit var productDetailUseCaseImpl: ProductDetailUseCaseImpl

    private val successAction by lazy {
        ActionScreen.SuccessAction(
            responseModel = ProductDetailModel(
                String(),
                String(),
                0.0,
                0.0,
                String(),
                String(),
                emptyList(),
                false,
                null,
                String(),
                String(),
                String(),
                emptyList(),
                String(),
                0,
                0,
                ShippingModel(false, String(), false)
            )
        )
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        productDetailUseCaseImpl = ProductDetailUseCaseImpl(
            StandardTestDispatcher(),
            productDetailRepository,
            database,
            logger
        )

        whenever(database.skeletonsDao()).thenReturn(skeletonsDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadingAction should return first`() = runTest {
        whenever(productDetailRepository.getProductDetail(String())).thenReturn(flow { emit(errorAction) })
        assert(productDetailUseCaseImpl.loadProductDetail(String()).first() is ActionScreen.LoadingAction)
    }

    @Test
    fun `SkeletonAction should return when skeleton database have data`() = runTest {
        whenever(productDetailRepository.getProductDetail(String())).thenReturn(flow { emit(successAction) })
        whenever(
            database.skeletonsDao().provideSkeletonsByContext(CONTEXT_PRODUCT_DETAIL)
        ).thenReturn(SkeletonsEntity(CONTEXT_PRODUCT_DETAIL, emptyList()))

        assert(productDetailUseCaseImpl.loadProductDetail(String()).first() is ActionScreen.SkeletonAction)
    }

    @Test
    fun `saveSkeletonsByContext should be called when response is success`() = runTest {
        whenever(productDetailRepository.getProductDetail(String())).thenReturn(flow { emit(successAction) })
        productDetailUseCaseImpl.loadProductDetail(String()).collect()
        verify(database.skeletonsDao()).saveSkeletonsByContext(
            SkeletonsEntity(
                CONTEXT_PRODUCT_DETAIL,
                listOf(CONTEXT_PRODUCT_DETAIL)
            )
        )
    }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `ErrorAction should be return when an exception happens in the repository`() = runTest {
        whenever(productDetailRepository.getProductDetail(String())).thenReturn(
            flow { throw RuntimeException("Crash!") }
        )

        whenever(
            database.skeletonsDao().provideSkeletonsByContext(CONTEXT_PRODUCT_DETAIL)
        ).thenReturn(SkeletonsEntity(CONTEXT_PRODUCT_DETAIL, emptyList()))

        productDetailUseCaseImpl.loadProductDetail(String()).test {
            awaitItem()
            assert(awaitItem() is ActionScreen.ErrorAction)
            cancelAndIgnoreRemainingEvents()
        }
    }
}