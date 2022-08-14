package com.app.ml.data.models

import com.google.gson.annotations.SerializedName

data class ShippingModel(
    @SerializedName("free_shipping") val freeShipping: Boolean,
    @SerializedName("logistic_type") val logisticType: String,
    @SerializedName("store_pick_up") val storePickUp: Boolean
)
