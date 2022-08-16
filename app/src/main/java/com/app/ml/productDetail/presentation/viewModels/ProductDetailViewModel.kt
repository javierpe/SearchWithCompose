package com.app.ml.productDetail.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ml.navigation.data.api.NavigationController
import com.app.ml.productDetail.data.api.useCases.ProductDetailUseCase
import com.app.ml.data.models.ActionScreen
import com.app.ml.productDetail.data.models.ProductDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailUseCase,
    private val navigationController: NavigationController
) : ViewModel() {

    private val _action = MutableStateFlow<ActionScreen<ProductDetailModel>>(ActionScreen.Undefined())
    val action: StateFlow<ActionScreen<ProductDetailModel>> = _action

    fun load(productId: String) {
        viewModelScope.launch {
            productDetailUseCase.loadProductDetail(productId).collect {
                _action.value = it
            }
        }
    }

    fun goToBack() {
        navigationController.popBackStack()
    }
}