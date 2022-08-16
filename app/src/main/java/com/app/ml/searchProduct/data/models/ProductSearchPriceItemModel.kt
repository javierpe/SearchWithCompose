package com.app.ml.searchProduct.data.models

import com.google.gson.annotations.SerializedName

data class ProductSearchPriceItemModel(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("amount") val amount: Double
)
