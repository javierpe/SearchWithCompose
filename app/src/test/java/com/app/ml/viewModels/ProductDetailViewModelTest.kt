package com.app.ml.viewModels

import app.cash.turbine.test
import com.app.ml.api.controllers.NavigationController
import com.app.ml.api.useCases.ProductDetailUseCase
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.productDetail.ProductDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductDetailViewModelTest {

    @Mock
    lateinit var productDetailUseCase: ProductDetailUseCase

    @Mock
    lateinit var navigationController: NavigationController

    @Mock
    lateinit var errorAction: ActionScreen.ErrorAction<ProductDetailModel>

    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        productDetailViewModel = ProductDetailViewModel(
            productDetailUseCase,
            navigationController
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `popBackStack should be called when goToBack is executed`() {
        productDetailViewModel.goToBack()
        verify(navigationController).popBackStack()
    }

    @Test
    fun `action property should be have a value when load product id`() = runTest {
        whenever(productDetailUseCase.loadProductDetail(String())).thenReturn(flow { emit(errorAction) })
        productDetailViewModel.load(String())
        productDetailViewModel.action.test {
            awaitItem()
            assert(awaitItem() is ActionScreen.ErrorAction)
            cancelAndIgnoreRemainingEvents()
        }
    }
}