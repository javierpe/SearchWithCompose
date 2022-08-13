package com.app.ml.viewModels

import androidx.lifecycle.ViewModel
import com.app.ml.api.controllers.NavigationController
import com.app.ml.data.models.Route
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