package com.app.ml.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class TransactionModel(
    @SerializedName("total") val total: Int,
    @SerializedName("canceled") val canceled: Int,
    @SerializedName("completed") val completed: Int,
    @SerializedName("ratings") val ratings: RatingModel?,
)
