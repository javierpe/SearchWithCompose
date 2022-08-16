package com.app.ml.extensions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.ml.navigation.data.models.Route
import com.app.ml.productDetail.presentation.screens.ProductDetailScreen
import com.app.ml.searchProduct.presentation.screens.SearchScreen

/**
 * Navigate to MainScreen
 */
fun NavGraphBuilder.homeNav() {
    composable(Route.Main.name) {
        SearchScreen()
    }
}

fun NavGraphBuilder.productDetailNav() {
    composable(
        route = Route.ProductDetail.name + "/{${Route.ProductDetail.PRODUCT_ID}}",
        arguments = listOf(
            navArgument(Route.ProductDetail.PRODUCT_ID) { type = NavType.StringType },
        )
    ) {
        ProductDetailScreen(
            productId = it.arguments?.getString(Route.ProductDetail.PRODUCT_ID).orEmpty()
        )
    }
}