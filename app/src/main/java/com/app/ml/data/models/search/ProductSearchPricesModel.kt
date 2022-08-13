package com.app.ml.data.models.search

import com.google.gson.annotations.SerializedName

data class ProductSearchPricesModel(
    @SerializedName("id") val id: String,
    @SerializedName("prices") val prices: List<ProductSearchPriceItemModel>
)
