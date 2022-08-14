package com.app.ml.viewModels

import com.app.ml.api.controllers.NavigationController
import com.app.ml.data.models.Route
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest {

    @Mock
    lateinit var navigationController: NavigationController

    private lateinit var productListViewModel: ProductListViewModel

    @Before
    fun setUp() {
        productListViewModel = ProductListViewModel(
            navigationController
        )
    }

    @Test
    fun `popBackStack should be called when goToBack is executed`() {
        productListViewModel.goToProductDetail(String())
        verify(navigationController).navigateTo(
            Route.ProductDetail,
            listOf(String())
        )
    }
}