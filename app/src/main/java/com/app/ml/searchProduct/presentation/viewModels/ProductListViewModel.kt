package com.app.ml.searchProduct.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.app.ml.navigation.data.api.NavigationController
import com.app.ml.navigation.data.models.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val navigationController: NavigationController
) : ViewModel() {

    fun goToProductDetail(productId: String) {
        navigationController.navigateTo(
            Route.ProductDetail,
            listOf(productId)
        )
    }
}