package com.app.ml.data.models.productDetail

import com.app.ml.data.models.ShippingModel
import com.google.gson.annotations.SerializedName

data class ProductDetailModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("original_price") val originalPrice: Double,
    @SerializedName("buying_mode") val buyingMode: String,
    @SerializedName("listing_type_id") val listingTypeId: String,
    @SerializedName("pictures") val pictures: List<PictureItemModel>,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean,
    @SerializedName("seller_address") val addressModel: AddressModel?,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("attributes") val attributes: List<AttributeModel>,
    @SerializedName("warranty") val warranty: String,
    @SerializedName("sold_quantity") val soldQuantity: Int,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("shipping") val shippingModel: ShippingModel
)