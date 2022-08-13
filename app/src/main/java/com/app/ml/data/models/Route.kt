package com.app.ml.data.models

sealed class Route(val name: String) {
    object Main : Route("main")
    object ProductDetail : Route("product_detail") {
        const val PRODUCT_ID = "productId"
    }
}
