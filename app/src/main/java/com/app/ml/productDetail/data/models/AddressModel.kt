package com.app.ml.productDetail.data.models

import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("state") val stateName: AddressItem? = null,
    @SerializedName("city") val cityName: AddressItem? = null
)

data class AddressItem(
    @SerializedName("name") val name: String
)
