package com.app.ml.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class RatingModel(
    @SerializedName("neutral") val neutral: Double,
    @SerializedName("negative") val negative: Double,
    @SerializedName("positive") val positive: Double
)
