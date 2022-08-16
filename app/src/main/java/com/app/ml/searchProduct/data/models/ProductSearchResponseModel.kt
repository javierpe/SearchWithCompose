package com.app.ml.searchProduct.data.models

import com.google.gson.annotations.SerializedName

data class ProductSearchResponseModel(
    @SerializedName("site_id") val siteId: String = "",
    @SerializedName("results") val products: List<ProductSearchSearchModel>
)
