package com.app.ml.searchProduct.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.ml.searchProduct.data.models.ProductSearchSearchModel
import com.app.ml.ui.designSystem.ProductItemView
import com.app.ml.searchProduct.presentation.viewModels.ProductListViewModel

@Composable
fun ProductListScreen(
    modifier : Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    products: List<ProductSearchSearchModel>,
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(items = products) { product ->
            ProductItemView(
                product = product
            ) {
                viewModel.goToProductDetail(product.id)
            }
        }
    }
}

@Composable
@Preview
fun PreviewProductListScreen() {
    ProductListScreen(products = emptyList())
}