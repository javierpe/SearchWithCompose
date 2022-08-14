package com.app.ml.data.models.search

import com.app.ml.data.models.ShippingModel
import com.google.gson.annotations.SerializedName

data class ProductSearchSearchModel(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("prices") val productPrices: ProductSearchPricesModel? = null,
    @SerializedName("available_quantity") val available: Int = 0,
    @SerializedName("buying_mode") val buyingMode: String = "",
    @SerializedName("condition") val condition: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean = false,
    @SerializedName("shipping") val shipping: ShippingModel? = null
)
