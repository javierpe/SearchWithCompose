package com.app.ml.repositories

import com.app.ml.api.network.ProductService
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.ShippingModel
import com.app.ml.data.models.productDetail.ProductDetailModel
import com.app.ml.impl.ProductDetailRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductDetailRepositoryImplTest {

    @Mock
    lateinit var productService: ProductService

    private lateinit var productDetailRepositoryImpl: ProductDetailRepositoryImpl

    private val model by lazy {
        ProductDetailModel(
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
    }

    @Before
    fun setUp() {
        productDetailRepositoryImpl = ProductDetailRepositoryImpl(
            productService
        )
    }

    @Test
    fun `ErrorAction should return when response is not success`() = runTest {
        whenever(productService.getProductDetail(String())).thenReturn(
            Response.error(401, String().toResponseBody())
        )
        val result = productDetailRepositoryImpl.getProductDetail(String()).first()
        assert(result is ActionScreen.ErrorAction)
    }

    @Test
    fun `SuccessAction should return when response is success`() = runTest {
        whenever(productService.getProductDetail(String())).thenReturn(
            Response.success(
                200,
                model
            )
        )
        val result = productDetailRepositoryImpl.getProductDetail(String()).first()
        assert(result is ActionScreen.SuccessAction)
    }
}