package com.app.ml.api.network

import com.app.ml.data.models.productDetail.ProductDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("items/{productId}")
    suspend fun getProductDetail(@Path(value = "productId") productId: String): Response<ProductDetailModel>
}