package com.app.ml.data.models.search

import com.google.gson.annotations.SerializedName

data class ProductSearchResponseModel(
    @SerializedName("site_id") val siteId: String = "",
    @SerializedName("results") val products: List<ProductSearchSearchModel>
)
