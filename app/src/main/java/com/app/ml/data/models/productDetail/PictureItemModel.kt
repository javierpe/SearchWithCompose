package com.app.ml.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class PictureItemModel(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
)
